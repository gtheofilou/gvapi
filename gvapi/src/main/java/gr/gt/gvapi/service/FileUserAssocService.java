package gr.gt.gvapi.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.gt.gvapi.dao.FileUserAssocDao;

@Service
@Transactional
public class FileUserAssocService {

    @SuppressWarnings("unused")
    @Autowired
    private FileUserAssocDao fileUserAssocDao;
}
