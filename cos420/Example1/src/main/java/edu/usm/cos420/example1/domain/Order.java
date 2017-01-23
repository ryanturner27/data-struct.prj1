package edu.usm.cos420.example1.domain;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 * Order holds six parts of data. The class implements the 
 * interface Serializable in order to store and retrieve information
 * in this class
 * @author Ryan Turner
 *
 */
public class Order implements Serializable{

	private static final long serialVersionUID = 7526472295622776147L;
	private Long idOrder;
	private int customerId;
	private static Long COUNTER = 0L;
	private int totalOrder = 0;
	private int inventoryID= 0;
	private String begin ="";
	private String end = "";
	
	
	/**
	 * Default Constructor with no arguments 
	 */
	public Order(){
		this.idOrder = generateIdnum();
		this.customerId = 0;
		this.begin = setDate();
		this.end = setDate();	
		this.inventoryID = 0;
		this.totalOrder = 0;
		
	}
	
	/**
	 * Default Constructor with a randomly generated Order ID number
	 * @param customerIDnumber is the Customer ID number which belongs to the Customer that is making the order
	 * @param inventoryNumber is the Inventory ID number that current from the Inventory Object item being ordered
	 * @param totalOrdered is the total amount being ordered of this inventory item
	 * @param bdate is the start date of the Order
	 * @param edate is the end date of the Order
	 */
	public Order(int customerIDnumber, int inventoryNumber, int totalOrdered, String bdate, String edate){
		idOrder = generateIdnum();
		this.customerId = customerIDnumber;
		this.begin = bdate;
		this.end = edate;
		this.inventoryID = inventoryNumber;
		this.totalOrder = totalOrdered;		
	}
	
	/**
	 * Returns the ID number of the Order Object
	 * @return the ID number that is of type long
	 */
	public Long getId(){
		return idOrder;
	}
	
	/**
	 * Returns the Customer ID number that is part of the Order Object
	 * @return the customer ID number as type Integer
	 */
	public int getCustId(){
		return customerId;
	}
	
	/**
	 * Returns the Inventory ID number that is part of the Order Object
	 * @return the Inventory ID number as type Integer
	 */
	public int getItemIdNumber(){
		return inventoryID;
	}
	
	/**
	 * Returns the total amount Ordered from the current Inventory that is part of the Order Object
	 * @return the total amount ordered
	 */
	public int getAmountOrdered(){
		return totalOrder;
	}
	
	/**
	 * Update the total amount being ordered
	 * @param amount is the new amount being ordered in Order Object
	 */
	public void setAmountOrder(int amount){
		totalOrder = amount;
	}
	
	/**
	 * Returns the beginning Date for inventory
	 * @return the begin date formatted as mm/dd/yyyy as type String
	 */
	public String getBeginDate(){
		return begin;
	}
	
	/**
	 * Returns end date for Order
	 * @return end date formatted as mm/dd/yyyy as type String 
	 */
	public String getEndDate(){
		return end;
	}
	
	//Sets date for default constructor with no arguments
	private String setDate(){
		DateFormat dateformat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		return dateformat.format(date); 
	}
	
	/**
     * Returns the String representation of this User. Not required, it just pleases reading logs.
     * @see java.lang.Object#toString()
     */
    @Override
	public String toString() { 
		return String.format("\nOrder:\nOrder Number ID= %6d\nCustomer ID = %6d\nInventory Item= %6d\nAmount Ordered= %d"
				+ "\nBegin Date= %s\nEnd Date= %s\n", getId(),getCustId(), getItemIdNumber(),getAmountOrdered(), getBeginDate(), getEndDate());
	}
	// for autogeneration of ids
	private Long generateIdnum()
	{
		return COUNTER = (long)Math.round(Math.random()*999999);
	}

}
