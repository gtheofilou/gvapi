package gr.gt.gvapi.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import gr.gt.gvapi.dao.AbstractDao;
import gr.gt.gvapi.dao.FileDao;
import gr.gt.gvapi.dao.FileUserAssocDao;
import gr.gt.gvapi.dao.UserDao;
import gr.gt.gvapi.entity.File;
import gr.gt.gvapi.entity.User;
import gr.gt.gvapi.utils.AppProperties;
import gr.gt.gvapi.utils.ImageUtils;

@Service
@Transactional
public class FileService extends AbstractService<File, Long> {

    private static final int THUMB_SIZE = 64;

    private Path fileLocation;

    private FileDao fileDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private FileUserAssocDao fileUserAsscoDao;

    @Autowired
    private AppProperties appProperties;

    @Autowired
    public FileService(@Qualifier("FileDao") AbstractDao<File, Long> abstractDao) {
        super(abstractDao);
        this.fileDao = (FileDao) abstractDao;
    }

    @PostConstruct
    private void init() {
        this.fileLocation = Paths.get(appProperties.getUploadDir()).toAbsolutePath().normalize();
    }

    public File saveFile(MultipartFile inputFile, String userName) throws IOException {

        String fileName = StringUtils.cleanPath(inputFile.getOriginalFilename());
        File file = fileDao.findFileNByName(fileName);
        User user = userDao.findUserByName(userName);

        if (file == null) {
            Path targetLocation = fileLocation.resolve(fileName);
            Path thumbLocation = fileLocation.resolve("thumb/" + fileName);
            try (InputStream in = inputFile.getInputStream()) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                in.transferTo(baos);
                InputStream clone = new ByteArrayInputStream(baos.toByteArray());
                InputStream clone2 = new ByteArrayInputStream(baos.toByteArray());
                Files.copy(clone, targetLocation, StandardCopyOption.REPLACE_EXISTING);
                ImageUtils.createThumbnail(clone2, thumbLocation,
                        FilenameUtils.getExtension(inputFile.getOriginalFilename()), THUMB_SIZE);
            }

            file = new File();
            file.setName(fileName);
            persist(file);
        }
        fileUserAsscoDao.createFileUserAssocIfNotExists(file.getId(), user.getId());
        return file;
    }

    public List<File> downloadFiles(String urls, String userName) {
        String urlsArr[], filename;
        List<Pair<String, String>> urlsToProcess = new ArrayList<Pair<String, String>>();
        List<File> filesDownloaded = new ArrayList<File>();

        User user = userDao.findUserByName(userName);

        if (StringUtils.isEmpty(urls))
            return Collections.emptyList();

        urlsArr = urls.split("[\\r\\n]+");

        String tmp;
        for (String u : urlsArr) {
            if (StringUtils.isEmpty(u))
                continue;

            tmp = u.trim();
            filename = FilenameUtils.getName(tmp);
            File file = fileDao.findFileNByName(filename);
            if (file == null)
                urlsToProcess.add(new ImmutablePair<String, String>(tmp, filename));
            else {// File already exist, just add association
                System.out.println("File already exist, just add association");
                fileUserAsscoDao.createFileUserAssocIfNotExists(file.getId(), user.getId());
            }
        }

        for (Pair<String, String> p : urlsToProcess) {
            try {
                download(p);

                File file = new File();
                file.setName(p.getRight());
                persist(file);

                filesDownloaded.add(file);
                fileUserAsscoDao.createFileUserAssocIfNotExists(file.getId(), user.getId());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return filesDownloaded;
    }

    private void download(Pair<String, String> pair) throws MalformedURLException, IOException {
        URLConnection c = new URL(pair.getLeft()).openConnection();
        c.setConnectTimeout(10000);
        c.setReadTimeout(20000);
        // InputStream in = c.getInputStream();
        Path targetLocation = fileLocation.resolve(pair.getRight());
        Path thumbLocation = fileLocation.resolve("thumb/" + pair.getRight());
        // Files.copy(in, targetLocation, StandardCopyOption.REPLACE_EXISTING);

        try (InputStream in = c.getInputStream()) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            in.transferTo(baos);
            InputStream clone = new ByteArrayInputStream(baos.toByteArray());
            InputStream clone2 = new ByteArrayInputStream(baos.toByteArray());
            Files.copy(clone, targetLocation, StandardCopyOption.REPLACE_EXISTING);
            String filetype = URLConnection.guessContentTypeFromStream(clone2);
            filetype = filetype.substring(filetype.lastIndexOf("/") + 1);
            ImageUtils.createThumbnail(clone2, thumbLocation, filetype, THUMB_SIZE);
        }
    }

    public Resource loadFile(String fileName) {
        try {
            Path filePath = fileLocation.resolve(fileName).normalize();
            Path thumbPath = fileLocation.resolve("thumb/" + fileName).normalize();
            Resource resourceThumb = new UrlResource(thumbPath.toUri());
            if (resourceThumb.exists())
                return resourceThumb;
            else {
                Resource resource = new UrlResource(filePath.toUri());
                if (resource.exists())
                    return resource;
                else
                    throw new IllegalArgumentException("File not found " + fileName);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<File> getFileList() {
        return fileDao.getFileList();
    }
}
