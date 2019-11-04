package gr.gt.gvapi.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import gr.gt.gvapi.dao.AbstractDao;
import gr.gt.gvapi.dao.FileDao;
import gr.gt.gvapi.entity.File;
import gr.gt.gvapi.utils.AppProperties;

@Service
@Transactional
public class FileService extends AbstractService<File, Long> {
	
	private Path fileLocation;
	
	private FileDao fileDao;
	
	@Autowired
	private AppProperties appProperties;
	
	@Autowired
	public FileService(@Qualifier("FileDao") AbstractDao<File, Long> abstractDao) {
		super(abstractDao);
		this.fileDao = (FileDao) abstractDao;
	}
	
	@PostConstruct
	private void init() {
		 this.fileLocation = Paths.get(appProperties.getUploadDir())
				 .toAbsolutePath().normalize();
	}
	
	public void saveFile(MultipartFile inputFile) throws IOException {
		
		String fileName = StringUtils.cleanPath(inputFile.getOriginalFilename());
		Path targetLocation = fileLocation.resolve(fileName);
		Files.copy(inputFile.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
		
		File file =  new File();
		file.setName(fileName);
		persist(file);
		
	}
	
	public Resource loadFile(String fileName) {
		try {
            Path filePath = fileLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new IllegalArgumentException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new IllegalArgumentException("File not found " + fileName, ex);
        }
	}
	
	public List<File> getFileList() {
		return fileDao.getFileList();
	}
}
