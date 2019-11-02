package gr.gt.gvapi.dao;

import org.springframework.stereotype.Repository;

import gr.gt.gvapi.entity.File;


@Repository("FileDao")
public class FileDao extends AbstractDao<File, Long> {

}
