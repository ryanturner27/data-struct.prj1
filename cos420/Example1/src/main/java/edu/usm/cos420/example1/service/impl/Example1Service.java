package edu.usm.cos420.example1.service.impl;

import java.util.Iterator;
import java.util.List;

import edu.usm.cos420.example1.dao.domain.CItemDao;
import edu.usm.cos420.example1.dao.domain.CustomerDao;
import edu.usm.cos420.example1.dao.domain.InventoryDao;
import edu.usm.cos420.example1.dao.domain.OrderDao;
import edu.usm.cos420.example1.domain.CItem;
import edu.usm.cos420.example1.domain.Customer;
import edu.usm.cos420.example1.domain.Inventory;
import edu.usm.cos420.example1.domain.Order;
import edu.usm.cos420.example1.service.ExampleService;

/**
 * 
 *  The Example1 Service Layer Implementation is based a design pattern
 *      which aims to organize the functionality of the application into logical units 
 *      that are typically layered on top of much of the low level functionality of the 
 *      application. This organization helps support service oriented architectures. 
 *
 */
public class Example1Service implements ExampleService {

	CItemDao dao;
	CustomerDao cdao;
	InventoryDao idao;
	OrderDao odao;

	/**
	 * Default Constructor creates a default CItemDao object 
	 */
	public Example1Service()
	{
		this.dao = new CItemDao();
		this.cdao = new CustomerDao();
		this.idao = new InventoryDao();
		this.odao = new OrderDao();
	}

	public Example1Service(CItemDao dao)
	{
		this.dao = new CItemDao();
	}

	/**
	 * Constructor with the DAO provided 
	 * @param dao dao Data Access Object to use in the service
	 * @param custdao dao Data Access Object to use in the service
	 * @param invdao dao Data Access Object to use in the service
	 * @param orddao dao Data Access Object to use in the service
	 */
	public Example1Service(CItemDao dao, CustomerDao custdao, InventoryDao invdao, OrderDao orddao)
	{
		this.dao = dao;	
		this.cdao = custdao;
		this.idao = invdao;
		this.odao = orddao;
	}


	/**
	 * Add a randomly generated CItem element to the repository
	 */

	public void addACItem() 
	{
		int randomNum = 1 + (int)(Math.random()*999999);
		CItem anItem = new CItem(new Long(randomNum), randomNum,"String with random number " + randomNum);
		dao.add(anItem);

	}

	/**
	 * Adds a new Customer class without passing an ID number to the repository 
	 * @param name passing in the full name for creating a new Customer
	 * @param address passing in the address for creating a new Customer
	 * @return the ID number that is randomly created in the Customer Class
	 */
	public Long addACustomerwithoutId(String name, String address)
	{
		Customer newCustomer = new Customer(name, address);
		cdao.add(newCustomer);
		return newCustomer.getIdnumber();
	}

	/**
	 * Adds a new Customer class with a custom ID number to the repository 
	 * @param idnumber is the custom ID number the user enters
	 * @param name is the name of the Customer being created
	 * @param address is the Customer's address
	 */
	public void addACustomerwithId(int idnumber, String name, String address)
	{
		Long idnum = (long)idnumber;	
		Customer newCustomer = new Customer(idnum,name, address);
		cdao.add(newCustomer);
	}
	/**
	 * Finds the Customer object associated with the ID number 
	 * @param idnum is an integer that is converted to a long value in order to find object in repository
	 * @return a Customer object there is associated to the ID number being passed
	 */
	public Customer validCid(int idnum){
		Long id = (long)idnum;
		return cdao.find(id);
	}
	
	/**
	 * Adds a new Inventory Object with a specified assigned ID number to the repository
	 * @param idnumber is the specified ID number that the user inputs
	 * @param descript is the description of the Inventory Object being created
	 * @param amount is the total amount of the inventory item being created within the Inventory Object
	 */
	public void addInventorywithID(int idnumber, String descript, int amount)
	{
		Long id = (long)idnumber;
		Inventory newInv = new Inventory(id,descript,amount);
		idao.add(newInv);
	}
	/**
	 * Adds a new Inventory Object with a randomly assigned ID number to the repository
	 * @param des is the description of the the Inventory Object that is being created
	 * @param amount is the total inventory amount that is being created within the Inventory Object
	 * @return is the long ID number that was created from the random ID number generator in the Inventory Class
	 */
	public Long addInventorywithoutID(String des, int amount)
	{	
		Inventory newInv = new Inventory(des, amount);
		idao.add(newInv);
		return newInv.getID(); 
	}
	/**
	 * Updates the Full Name by adding both the first and last name in the Customer Class and updates the Customer Object in the repository
	 * @param id is the ID of the Customer Object that is currently being stored in the repository
	 * @param newfirstName is the first name of the Customer
	 * @param newlastName is the last name of the Customer
	 */
	public void updateCustomerName(int id, String newfirstName, String newlastName){
		Customer entity = validCust(id);
		entity.setName(newfirstName, newlastName);
		cdao.update(entity);
	}
	/**
	 * Updates the address variable field in Customer Object related to the ID number. It is then updated to in the repository
	 * @param id is the ID of the Customer Object that is currently being stored in the repository
	 * @param newaddress is the new updated address variable that is being updated into the Customer Object
	 */
	public void updateCustomerAdd(int id, String newaddress){
		Customer entity = validCust(id);
		entity.setAddress(newaddress);
		cdao.update(entity);
	}
	/**
	 * Finds Customer Object within repository and returns Object that is associated to the ID number provided
	 * @param customeridnumber is the Customer ID number that gets converted to a long value in order to look up the correct Object in the repository
	 * @return the Customer Object associated with the ID number provided
	 */
	public Customer validCust(int customeridnumber){
		Long idc = (long)customeridnumber;
		return cdao.find(idc);  	
	}
	/**
	 * Finds the Order Object within the repository and returns the particular Order Object associated to the ID number provided 
	 * @param orderidnumber is the Order ID number that is provided and is converted to a long value
	 * @return the Order Object that is associated with the ID number that is provided
	 */
	public Order validOrder(int orderidnumber){
		Long ido = (long)orderidnumber;
		return odao.find(ido);  	
	}
	/**
	 * Updates the Inventory Object total amount by looking up the Object in the repository and updating the Inventory item
	 * @param num is the ID number associated to the Inventory Object that needs to be updated
	 * @param newtotal is the new total amount that needs to be updated to the Inventory Object in the repository
	 */
	public void updateInventory(int num, int newtotal){
		Inventory entity = validId(num);
		entity.setTotal(newtotal);
		idao.update(entity);	
	}
	/**
	 * Finds the Inventory Object that is associated to the IDnumber provided in the repository
	 * @param idnumber is converted to a long and passed into a find method in order to find the specific Inventory Object associated to the ID number
	 * @return a specific Inventory Object that is associated to the ID number
	 */
	public Inventory validId(int idnumber){
		Long idn = (long)idnumber;
		return idao.find(idn);  	
	}
	/**
	 * Creates a new Order Object to add to the repository
	 * @param id is the ID number of the Customer that is associated to making the order
	 * @param idInv is the ID number of the Inventory Item that is currently being ordered
	 * @param amountOrdered is the total amount that has been ordered from the Customer
	 * @param beginDate is the beginning date that is created with the Order Object
	 * @param endDate is the end date that is created with the Order Object
	 */
	public void newOrder(Long id, Long idInv, int amountOrdered, String beginDate, String endDate){
		int customer = Math.toIntExact(id);
		int inventory = Math.toIntExact(idInv);
		Order newOrder = new Order(customer, inventory, amountOrdered, beginDate, endDate);
		odao.add(newOrder);
	}
	/**
	 * Finds the total amount in a Inventory Object Stock in and returns the total amount associated to that specific Inventory Object
	 * @param q is the ID number of the specific Inventory Object Stock
	 * @return the Inventory Object total amount to keep track of inventory totals 
	 */
	public int quantity(int q){
		Long key = (long)q;
		Inventory currInv = idao.find(key);
		return currInv.getTotal();
		
	}
	/**
	 * Removes Inventory Object from the repository
	 * @param id is the specific ID number that is associated with the Inventory Object
	 */
	public void removeInventoryItem(long id){
		idao.remove(id);
	}
	/**
	 * Removes Customer Object from the repository
	 * @param id is the specific ID number that is associated with the Customer Object
	 */
	public void removeCustomerItem(long id){
		cdao.remove(id);
	}
	/**
	 * Removes Order Object from the repository
	 * @param id is the specific ID number that is associated with the Order Object
	 */
	public void removeOrderItem(long id){
		odao.remove(id);
	}
	/**
	 * Calculate the maximum ID value of elements in the repository     
	 * @return the maximum id of a CItem in the repository
	 */

	public Long maxCItemId() {
		List<CItem> list = dao.list();
		Long max = 0L;
		if (list.isEmpty())
			return max;
		else 
		{
			Iterator<CItem> iter = list.iterator();
			max = iter.next().getId();
			while (iter.hasNext())
			{
				CItem anItem = iter.next();
				if (anItem.getId() > max)
					max = anItem.getId();
			}
			return max;
		}
	}
	/**
	 *Prints out the List of Customer Objects and outputs the Objects as a String 
	 */
	public void displayCustomers() {
		List<Customer> clist = cdao.list();
		System.out.println(clist.toString());
	}
	/**
	 * Prints out the List of Inventory Objects and outputs the Objects as a String 
	 */
	public void displayInventory() {
		List<Inventory> ilist = idao.list();
		System.out.println(ilist.toString());
	}
	/**
	 * Prints out the List of Order Objects and outputs the Objects as a String
	 */
	public void displayOrders(){//need to edit
		List<Order> olist = odao.list();
		System.out.println(olist.toString());
		
	}


}
