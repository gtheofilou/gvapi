package gr.gt.gvapi.service;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gcp.vision.CloudVisionTemplate;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.Feature.Type;

import gr.gt.gvapi.dao.FileDao;
import gr.gt.gvapi.dao.GoogleResponseDao;
import gr.gt.gvapi.dto.GoogleApiDto;
import gr.gt.gvapi.entity.File;
import gr.gt.gvapi.entity.GoogleResponse;
import gr.gt.gvapi.utils.AppProperties;

@Service
@Transactional
public class GoogleApiService {
	
	private Path fileLocation;
	
	@Autowired
	private AppProperties appProperties;

	@Autowired
	private FileDao fileDao;
	
	@Autowired
	private GoogleResponseDao googleResponseDao;
	
	@Autowired
	private CloudVisionTemplate cloudVisionTemplate;
	
	@PostConstruct
	private void init() {
		 this.fileLocation = Paths.get(appProperties.getUploadDir())
				 .toAbsolutePath().normalize();
	}
	
	public File sendToGoogle(GoogleApiDto googleApiDto) throws MalformedURLException {
		
		File file = fileDao.find(googleApiDto.getId());
		
		Path filePath = fileLocation.resolve(file.getName()).normalize();
        Resource resource = new UrlResource(filePath.toUri());
		
		AnnotateImageResponse response = this.cloudVisionTemplate.analyzeImage(
				resource, Type.LABEL_DETECTION);

//		    Map<String, Float> imageLabels =
//		        response.getLabelAnnotationsList()
//		            .stream()
//		            .collect(
//		                Collectors.toMap(
//		                    EntityAnnotation::getDescription,
//		                    EntityAnnotation::getScore,
//		                    (u, v) -> {
//		                      throw new IllegalStateException(String.format("Duplicate key %s", u));
//		                    },
//		                    LinkedHashMap::new));
		    
		    List<GoogleResponse> googleResponseList = response.getLabelAnnotationsList()
		    											.stream().map(e -> new GoogleResponse(googleApiDto.getId(), e.getDescription(), e.getScore()))
		    											.distinct().collect(Collectors.toList());
		    
		    for(GoogleResponse g: googleResponseList) {
		    	googleResponseDao.persist(g);
		    }
		    //[END spring_vision_image_labelling]

//		MapUtils.debugPrint(System.out, "imageLabels", imageLabels);
		
		file.setSent(Boolean.TRUE);
		return file;
	}
}
