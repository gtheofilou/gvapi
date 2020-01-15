package gr.gt.gvapi.service;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gcp.vision.CloudVisionTemplate;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.Feature.Type;
import gr.gt.gvapi.dao.FileDao;
import gr.gt.gvapi.dao.GoogleResponseDao;
import gr.gt.gvapi.entity.File;
import gr.gt.gvapi.entity.GoogleResponse;
import gr.gt.gvapi.utils.AppProperties;

@Service
@Transactional
public class GoogleApiServiceHelper {

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
        this.fileLocation = Paths.get(appProperties.getUploadDir()).toAbsolutePath().normalize();
    }

    @Transactional(TxType.REQUIRES_NEW)
    public File sendToGoogle(Long id) throws MalformedURLException {

        File file = fileDao.find(id);

        Path filePath = fileLocation.resolve(file.getName()).normalize();
        Resource resource = new UrlResource(filePath.toUri());

        // Label
        AnnotateImageResponse response =
                this.cloudVisionTemplate.analyzeImage(resource, Type.LABEL_DETECTION);

        List<GoogleResponse> googleResponseLabelList = response
                .getLabelAnnotationsList().stream().map(e -> new GoogleResponse(id,
                        GoogleResponse.Type.LABEL, e.getDescription(), e.getScore()))
                .distinct().collect(Collectors.toList());
        // OCR
        response = this.cloudVisionTemplate.analyzeImage(resource, Type.TEXT_DETECTION);
        GoogleResponse googleResponseOcr = new GoogleResponse();
        googleResponseOcr.setDescription(response.getTextAnnotationsList().stream()
                .map(x -> x.getDescription()).collect(Collectors.joining("|")));
        googleResponseOcr.setFileId(id);
        googleResponseOcr.setScore(0F);
        googleResponseOcr.setType(GoogleResponse.Type.OCR);

        // System.out.println("Calling Google");

        for (GoogleResponse g : googleResponseLabelList) {
            googleResponseDao.persist(g);
        }
        googleResponseDao.persist(googleResponseOcr);

        file.setSent(Boolean.TRUE);
        return file;
    }
}
