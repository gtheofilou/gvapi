package gr.gt.gvapi.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import gr.gt.gvapi.dao.AbstractDao;
import gr.gt.gvapi.dao.FileDao;
import gr.gt.gvapi.entity.File;

@Service
@Transactional
public class FileService extends AbstractService<File, Long> {
	
	@SuppressWarnings("unused")
	private FileDao fileDao;
	
	@Autowired
	public FileService(@Qualifier("FileDao") AbstractDao<File, Long> abstractDao) {
		super(abstractDao);
		this.fileDao = (FileDao) abstractDao;
	}
}
