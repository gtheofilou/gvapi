package gr.gt.gvapi.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
	
	@SuppressWarnings("unused")
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
}
