package edu.usm.cos420.iteration1.dao;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * Simular to the Generic DAO that we used in 
 * the first assignment. 
 * @author Ryan Turner
 *
 */
public interface GenericDao<IDType, T extends Serializable> {

	/**
	 * 
	 * @param id
	 * @param entity
	 */
	void add(IDType id, T entity);
	
	/**
	 * @param id
	 * @param entity
	 */
	void update(IDType id, T entity);
	
	/**
	 * @param id
	 */
	void remove(IDType id);
	
	/**
	 * @param key
	 */
	T find(IDType key);
	
	/**
	 * @return
	 */
	List<T> list();
	
}
