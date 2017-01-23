package edu.usm.cos420.example1.dao.domain;

import java.util.List;

import edu.usm.cos420.example1.dao.GenericDao;
import edu.usm.cos420.example1.dao.ObjectStreamDao;

import edu.usm.cos420.example1.domain.Inventory;


/**
 *  A Data Access Object specifically for Inventory entities
 * @author Ryan Turner
 *
 */
public class InventoryDao {


	private GenericDao<Long,Inventory> inv;

	
	/**
	 * Default constructor creates an ObjectStream file called inventory.ser
	 */
	public InventoryDao(){
		inv = new ObjectStreamDao<Long,Inventory>("inventory.ser");
	}

	/**
	 * Constructor where the filename is provided
	 * @param filename
	 */
	public InventoryDao(String filename){

		inv = new ObjectStreamDao<Long,Inventory>(filename);

	}

	/**
	 * Support for other DAOs is provided
	 * @param dao a Data Access Object class that implements GenericDao(Long, Inventory)
	 */
	public InventoryDao(GenericDao<Long,Inventory> dao){
		inv = dao;
	}

	/**
	 * Returns the DAO used in the class
	 * @return a DAO that implements GenericDao(Long, Inventory)
	 */
	public GenericDao<Long, Inventory> getInventoryDao(){
		return inv;
	}

	/**
	 * Add an Inventory to the DAO repository
	 * @param entity any Inventory object
	 */
	public void add(Inventory entity){
		inv.add(entity.getID(), entity);
	}

	/**
	 * Update an Inventory in the DAO repository
	 * @param entity any Inventory object
	 */
	public void update(Inventory entity){
		inv.update(entity.getID(), entity);
	}

	/**
	 * Remove an Inventory in the DAO repository
	 * @param id of the Inventory object to remove
	 */
	public void remove(Long id){
		inv.remove(id);
	}

	/**
	 * Find an Inventory in the DAO repository
	 * @param key of the Inventory object to locate
	 * @return the Inventory with id field equal to key
	 */
	public Inventory find(Long key){
		return inv.find(key);		
	}

	/**
	 * Generate a list of Inventories in the DAO repository
	 * @return List of Inventories
	 */
	public List<Inventory> list(){
		return inv.list();
	}
}


