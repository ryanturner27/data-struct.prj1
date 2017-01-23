package edu.usm.cos420.example1.dao.domain;


import java.util.List;

import edu.usm.cos420.example1.dao.GenericDao;
import edu.usm.cos420.example1.dao.ObjectStreamDao;
import edu.usm.cos420.example1.domain.Order;

/**
 * A Data Access Object specifically for Order entities
 * @author Ryan
 *
 */
public class OrderDao {
	
	private GenericDao<Long,Order> ord;
	
	/**
	 * Default constructor creates an ObjectStream file called orders.ser
	 */
	public OrderDao(){
		ord = new ObjectStreamDao<Long, Order>("orders.ser");
		
	}
	
	/**
	 * Constructor where the filename is provided
	 * @param filename
	 */
	public OrderDao(String filename){
		ord = new ObjectStreamDao<Long, Order>(filename);
	}
	
	/**
	 * Support for other DAOs is provided
	 * @param dao a Data Access Object class that implements GenericDao(Long,Orders)
	 */
	
	public OrderDao(GenericDao<Long, Order> dao){
		ord = dao;
	}
	
	/**
	 * Returns the DAO used in the class
	 * @return a DAO that implements GenericDao(Long, Orders)
	 */
	public GenericDao<Long,Order> getOrderDao(){
		return ord;
	}
	
	/**
	 * Add an Order to the DAO repository
	 * @param entity any Order object
	 */
	public void add(Order entity){
		ord.add(entity.getId(), entity);
	}
	
	/**
	 * Update an Order in the DAO repository
	 * @param entity any Order object
	 */
	public void update(Order entity){
		ord.update(entity.getId(), entity);
	}
	
	/**
	 * Remove an Order in the DAO repository
	 * @param id of the Order object to remove
	 */
	public void remove(Long id){
		ord.remove(id);
	}
	
	/**
	 * Find an Object in the DAO repository
	 * @param key of the Order object to locate
	 * @return the Order with id field equal to key
	 */
	
	public Order find(Long key){
		return ord.find(key);
	}
	
	/**
	 * Generate a list of Orders in the DAO repository
	 * @return List of Orders
	 */
	public List<Order> list(){
		return ord.list();
	}
}
