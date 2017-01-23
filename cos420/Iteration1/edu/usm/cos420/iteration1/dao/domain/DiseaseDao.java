package edu.usm.cos420.iteration1.dao.domain;

import edu.usm.cos420.iteration1.dao.GenericDao;
import edu.usm.cos420.iteration1.dao.ObjectStreamDao;
import edu.usm.cos420.iteration1.domain.Disease;

public class DiseaseDao {

	private GenericDao<Long,Disease> diseaseDao;
	
	public DiseaseDao(){
		diseaseDao = new ObjectStreamDao<Long,Disease>("diseaseDao.ser");
	}
	
	public DiseaseDao(String filename){
		diseaseDao = new ObjectStreamDao<Long,Disease>(filename);
		
	}
	
	public DiseaseDao(GenericDao<Long,Disease> dao){
		diseaseDao = dao;
	}
	
	
	public GenericDao<Long,Disease> getDiseaseDao(){
		return diseaseDao;
	}
	
	public void add(Disease entity){
		diseaseDao.add(entity.getId(), entity);
	}
	
	public void update(Disease entity){
		
	}
}
