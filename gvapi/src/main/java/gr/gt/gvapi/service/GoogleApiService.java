package gr.gt.gvapi.service;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import gr.gt.gvapi.dao.GoogleResponseDao;
import gr.gt.gvapi.dto.GoogleApiDto;
import gr.gt.gvapi.entity.File;
import gr.gt.gvapi.entity.GoogleResponse;

@Service
@Transactional
public class GoogleApiService {

    @Autowired
    private GoogleResponseDao googleResponseDao;

    @Autowired
    private GoogleApiServiceHelper googleApiServiceHelper;

    public List<File> sendListToGoogle(GoogleApiDto googleApiDto) {

        List<File> fileList = new ArrayList<File>();

        File f;
        int counter = 0;
        for (Long id : googleApiDto.getIdList()) {
            try {
                System.out.println(Thread.currentThread().getName() + "-->" + ++counter + " / "
                        + googleApiDto.getIdList().size());
                f = googleApiServiceHelper.sendToGoogle(id);
                fileList.add(f);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return fileList;
    }

    public List<GoogleResponse> getGoogleResponse(Long fileId) {
        return googleResponseDao.getGoogleResponse(fileId);
    }

    public void saveFinalDescription() {
        List<Long> list = googleResponseDao.getIDList();
        for (Long l : list) {
            googleResponseDao.saveFinalDescription(l);
        }

    }


}
