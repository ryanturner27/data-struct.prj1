package edu.usm.cos420.example1.dao.domain;

import java.util.List;

import edu.usm.cos420.example1.dao.GenericDao;
import edu.usm.cos420.example1.dao.ObjectStreamDao;
import edu.usm.cos420.example1.domain.Customer;

/**
 * A Data Access Object specifically for Customer entities
 * @author Ryan Turner
 *  
 */
public class CustomerDao {

	private GenericDao<Long,Customer> cust;
	

	/**
	 * Default constructor creates an ObjectStream file called customer.ser
	 */
	public CustomerDao(){
		cust = new ObjectStreamDao<Long,Customer>("customer.ser");
	}
	
	/**
	 * Constructor where the filename is provided
	 * @param filename 
	 */
	public CustomerDao(String filename){
		
		cust = new ObjectStreamDao<Long,Customer>(filename);
		
	}
	
	/**
	 * Support for other DAOs is provided
	 * @param dao a Data Access Object class that implements GenericDao(Long, Customer)
	 */
	
	
	public CustomerDao(GenericDao<Long,Customer> dao){
		cust = dao;
	}
	
	/**
	 * Returns the DAO used in the class
	 * @return a DAO that implements GenericDao(Long, Customer)
	 */
	public GenericDao<Long, Customer> getCustomerDao(){
		return cust;
	}
	
	/**
	 * Add a Customer to the DAO repository
	 * @param entity any Customer object
	 */
	public void add(Customer entity){
		cust.add(entity.getIdnumber(), entity);
	}
	
	/**
	 * Update a Customer in the DAO repository
	 * @param entity any Customer object
	 */
	public void update(Customer entity){
		cust.update(entity.getIdnumber(), entity);
	}
	
	/**
	 * Remove a Customer in the DAO repository
	 * @param id of the Customer object to remove
	 */
	public void remove(Long id){
		cust.remove(id);
	}
	
	/**
	 * Find a Customer in the DAO repository
	 * @param key the ID of the Customer object to locate
	 * @return the Customer with id field equal to key
	 */
	public Customer find(Long key){
		return cust.find(key);		
	}
	
	/**
	 * Generate a list of Customers in the DAO repository
	 * @return List of Customers
	 */
	public List<Customer> list(){
		return cust.list();
	}
}
