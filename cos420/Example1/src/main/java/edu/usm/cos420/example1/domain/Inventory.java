package edu.usm.cos420.example1.domain;

import java.io.Serializable;

/**
 * 
 * Inventory holds three parts of data. The class implements the
 * interface Serializable in order to store and retrieve information 
 * in this class 
 * @author Ryan Turner
 *
 */
public class Inventory implements Serializable{

	private static final long serialVersionUID = 7526472295622776147L;
	private Long idInv;	
	private static Long COUNTER = 0L;
	private String description;
	private int amount;
	 
	/**
	 * Default Constructor with no arguments in the parameters
	 */
	public Inventory(){
		
		this.description ="";
		this.amount = 0;
		idInv = generateIdnum();
	}
	
	/**
	 * Default Constructor with two arguments with a randomly generated ID number
	 * @param flowerDescription is a description of the Inventory Object
	 * @param totalamount is the total amount of items that are in stock for this Inventory Object
	 */
	public Inventory (String flowerDescription, int totalamount){

		this.description = flowerDescription;
		this.amount = totalamount;
		idInv = generateIdnum();
	}

	/**
	 * Default Constructor with three arguments
	 * @param id is the ID number that is specified for the Inventory Object 
	 * @param flowerDescription is the description of the Inventory Object
	 * @param totalamount is the total amount of items that are in stock for this Inventory Object
	 */
	public Inventory (Long id, String flowerDescription, int totalamount){
		this.description = flowerDescription;
		this.amount = totalamount;
		idInv = id;
	}
	
	/**
	 * Returns ID number of Inventory Object
	 * @return a Long ID number
	 */
	public Long getID(){
		return idInv;
	}

	/**
	 * Returns the total amount of items in stock
	 * @return an int value of total amounts in stock for this Inventory Object 
	 */
	public int getTotal(){
		return amount;
	}
	
	/**
	 * Update the total amount of items in stock
	 * @param newTotal is the new total amount that is to replace the amount for this Inventory Object
	 */
	public void setTotal(int newTotal){
		amount = newTotal;
	}
	
	/**
	 * Returns the description of the Inventory Object
	 * @return the description 
	 */
	public String getDescription(){
		return description;
	}

	/**
	 * Updates the description of the Inventory Object
	 * @param des is the new description that will update the description in Inventory Object
	 */
	public void setDes(String des){
		description = des;

	}
	
    /**
     * Returns the String representation of this User. Not required, it just pleases reading logs.
     * @see java.lang.Object#toString()
     */
    @Override
	public String toString() {
		return String.format("\nid= %6d\nDescription= %s......Total Quantity= %d\n", getID(), getDescription(),getTotal());
	}

	// for autogeneration of ids
	private Long generateIdnum()
	{
		return COUNTER = (long)Math.round(Math.random()*999999);
	}
}
