package edu.usm.cos420.example1.domain;

import java.io.Serializable;

/**
 * 
 * Customer holds three pieces of data. The class implements
 * the interface Serializable in order to store and retrieve the information
 * in the class
 * @author Ryan Turner
 *
 */
public class Customer implements Serializable{
	
	private static final long serialVersionUID = 7526472295622776147L;
	private Long ID;
	private static Long COUNTER = 0L;
	private String fullname;
	private String address;

	
	/**
	 * Default Constructor with no arguments:
	 * Uses the autogenerated sequence for an ID
	 */
	public Customer(){
		this.fullname ="";
		this.address = "";
		ID = generateId();
	}
	
	/** 
	 * Two field Constructor : 
	 * Creates new Customer with an autogenerated sequence ID 
	 */
	
	public Customer(String name, String add)
	{
		this.fullname = name;
		this.address = add;
		ID = generateId();
	}
	
	
	
	/**
	 * Three field Constructor:
	 * @param idnum is the ID number that is specified for the Customer Object
	 * @param name is the name of the Customer 
	 * @param add is the address of the Customer
	 */
	public Customer(Long idnum, String name, String add){
		this.fullname = name;
		this.address = add;
		this.ID = idnum;
	}
	
	/**
	 * Creates a new name for the Customer
	 * @param first is the first name of the Customer 
	 * @param last is the last name of the Customer
	 */
	public void setName(String first, String last){
		fullname = first + " " + last;
	}
	
	/**
	 * Update Address for the Customer Object
	 * @param newAdd is the new address that updates the older address from Customer Object
	 */
	public void setAddress(String newAdd){
		address = newAdd;
	}
	
	/**
	 * Returns the current Address of the Customer Object
	 * @return the current Address
	 */
	public String getAddress(){
		return address;
	}

	/**
	 * Returns the ID number for Customer
	 * @return the ID number as a long type
	 */
	public Long getIdnumber(){
		return ID;
	}
	
	/**
	 * Returns the name of the Customer
	 * @return the String name data in Customer Object
	 */
	public String getName(){
		return fullname;
	}
	
	/**
     * Returns the String representation of this User. Not required, it just pleases reading logs.
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String.format("\nid= %6d\nName= %s\nAddress= %s\n", getIdnumber(), fullname, address);
    }
	
    // for autogeneration of ids   
    private Long generateId()
    {
    	return COUNTER = (long)Math.round(Math.random()*999999);
    }
}