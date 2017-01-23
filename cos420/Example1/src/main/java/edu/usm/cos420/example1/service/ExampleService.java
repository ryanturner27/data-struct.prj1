package edu.usm.cos420.example1.service;

import edu.usm.cos420.example1.domain.Customer;
import edu.usm.cos420.example1.domain.Inventory;
import edu.usm.cos420.example1.domain.Order;

/**
 * 
 *  The Example1 Service Interface is based a design pattern
 *      which aims to organize the functionality of the application into logical units 
 *      that are typically layered on top of much of the low level functionality of the 
 *      application. This organization helps support service oriented architectures. 
 *
 */
public interface ExampleService {
	
	/**
	 * Add a randomly generated CItem element to the repository
	 */
    public void addACItem();
    /**
     * Calculate the maximum ID value of elements in the repository     
     * @return the maximum id of a CItem in the repository
     */
	public Long maxCItemId();
	
	
	
	/**
	 * Adds a new Customer class without passing an ID number to the repository 
	 * @param name passing in the full name for creating a new Customer
	 * @param address passing in the address for creating a new Customer
	 * @return the ID number that is randomly created in the Customer Class
	 */
	public Long addACustomerwithoutId(String name, String address);
	
	/**
	 * Adds a new Customer class with a custom ID number to the repository 
	 * @param idnumber is the custom ID number the user enters
	 * @param name is the name of the Customer being created
	 * @param address is the Customer's address
	 */
	public void addACustomerwithId(int idnumber, String name, String address);
	
	/**
	 * Finds the Customer object associated with the ID number 
	 * @param idnum is an integer that is converted to a long value in order to find object in repository
	 * @return a Customer object there is associated to the ID number being passed
	 */
	public Customer validCid(int idnum);
	
	/**
	 * Adds a new Inventory Object with a specified assigned ID number to the repository
	 * @param idnumber is the specified ID number that the user inputs
	 * @param descript is the description of the Inventory Object being created
	 * @param amount is the total amount of the inventory item being created within the Inventory Object
	 */
	public void addInventorywithID(int idnumber, String descript, int amount);
	
	/**
	 * Adds a new Inventory Object with a randomly assigned ID number to the repository
	 * @param des is the description of the the Inventory Object that is being created
	 * @param amount is the total inventory amount that is being created within the Inventory Object
	 * @return is the long ID number that was created from the random ID number generator in the Inventory Class
	 */
	public Long addInventorywithoutID(String des, int amount);
	
	/**
	 * Updates the Full Name by adding both the first and last name in the Customer Class and updates the Customer Object in the repository
	 * @param id is the ID of the Customer Object that is currently being stored in the repository
	 * @param newfirstName is the first name of the Customer
	 * @param newlastName is the last name of the Customer
	 */
	public void updateCustomerName(int id, String newfirstName, String newlastName);
	
	/**
	 * Updates the address variable field in Customer Object related to the ID number. It is then updated to in the repository
	 * @param id is the ID of the Customer Object that is currently being stored in the repository
	 * @param newaddress is the new updated address variable that is being updated into the Customer Object
	 */
	public void updateCustomerAdd(int id, String newaddress);
	
	/**
	 * Finds Customer Object within repository and returns Object that is associated to the ID number provided
	 * @param customeridnumber is the Customer ID number that gets converted to a long value in order to look up the correct Object in the repository
	 * @return the Customer Object associated with the ID number provided
	 */
	public Customer validCust(int customeridnumber);
	
	/**
	 * Finds the Order Object within the repository and returns the particular Order Object associated to the ID number provided 
	 * @param orderidnumber is the Order ID number that is provided and is converted to a long value
	 * @return the Order Object that is associated with the ID number that is provided
	 */
	public Order validOrder(int orderidnumber);
	
	/**
	 * Updates the Inventory Object total amount by looking up the Object in the repository and updating the Inventory item
	 * @param num is the ID number associated to the Inventory Object that needs to be updated
	 * @param newtotal is the new total amount that needs to be updated to the Inventory Object in the repository
	 */
	public void updateInventory(int num, int newtotal);
	
	/**
	 * Finds the Inventory Object that is associated to the IDnumber provided in the repository
	 * @param idnumber is converted to a long and passed into a find method in order to find the specific Inventory Object associated to the ID number
	 * @return a specific Inventory Object that is associated to the ID number
	 */
	public Inventory validId(int idnumber);
	
	/**
	 * Creates a new Order Object to add to the repository
	 * @param id is the ID number of the Customer that is associated to making the order
	 * @param idInv is the ID number of the Inventory Item that is currently being ordered
	 * @param amountOrdered is the total amount that has been ordered from the Customer
	 * @param beginDate is the beginning date that is created with the Order Object
	 * @param endDate is the end date that is created with the Order Object
	 */
	public void newOrder(Long id, Long idInv, int amountOrdered, String beginDate, String endDate);
	
	/**
	 * Finds the total amount in a Inventory Object Stock in and returns the total amount associated to that specific Inventory Object
	 * @param q is the ID number of the specific Inventory Object Stock
	 * @return the Inventory Object total amount to keep track of inventory totals 
	 */
	public int quantity(int q);
	
	/**
	 * Removes Inventory Object from the repository
	 * @param id is the specific ID number that is associated with the Inventory Object
	 */
	public void removeInventoryItem(long id);
	
	/**
	 * Removes Customer Object from the repository
	 * @param id is the specific ID number that is associated with the Customer Object
	 */
	public void removeCustomerItem(long id);
	
	/**
	 * Removes Order Object from the repository
	 * @param id is the specific ID number that is associated with the Order Object
	 */
	public void removeOrderItem(long id);
	
	/**
	 *Prints out the List of Customer Objects and outputs the Objects as a String 
	 */
	public void displayCustomers();
	
	/**
	 * Prints out the List of Inventory Objects and outputs the Objects as a String 
	 */
	public void displayInventory();
	
	/**
	 * Prints out the List of Order Objects and outputs the Objects as a String
	 */
	public void displayOrders();
}
