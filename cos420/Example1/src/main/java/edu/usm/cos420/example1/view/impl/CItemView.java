package edu.usm.cos420.example1.view.impl;

import java.util.Scanner;

import edu.usm.cos420.example1.service.ExampleService;
import edu.usm.cos420.example1.service.impl.Example1Service;

/* 
 * 	  CItemView class 
 *    A Command line User Interface which displays menu of CItem options to user and collects 
 *    the user choice.  
 * 
 */

public class CItemView{

	/** {@value}  : no choice selected by user */
	public static final int NO_CHOICE = 0;
	/** {@value #ADDONE}  : New order*/
	public static final int ADDONE = 1;
	/** {@value #NCUST}  : Add new customer */
	public static final int NCUST = 2;
	/** {@value #NINV}  : Add new inventory */
	public static final int NINV = 3;
	/** {@value #UINV}  : update inventory */
	public static final int UINV = 4;
	/** {@value #INV}  : Display Inventory */
	public static final int INV = 5;
	/** {@value #CUST}  : Display Customers */
	public static final int CUST = 6;
	/** {@value #ORDER}  : Display Orders */
	public static final int ORDER = 7;
	/** {@value #EXIT}  : Exit the program */
	public static final int EXIT = 8;

	// Object to read menu choices
	private Scanner in = new Scanner(System.in); 
	private ExampleService example1Service;

	/**
	 * This small version of the UI does not need the model or service objects but, in general, 
	 *     references to these objects are needed in the UI. Default constructor
	 *     creates a reference to Example1Service class to illustrate this.
	 */
	public CItemView()
	{
		this.example1Service = new Example1Service();
	}
	/**
	 * This small version of the UI does not need the model or service objects but, in general, 
	 *     references to these objects are needed in the UI.
	 * @param example1Service reference to class which provides CItem Services
	 */
	public CItemView(ExampleService example1Service)
	{
		this.example1Service = example1Service;
	}

	/**
	 * Display top level menu.
	 */

	/**
	 * Read the menu choice from user.
	 *  
	 *  <ul>
	 *    <li>{@value #ADDONE}  : Add new Order
	 *	  <li>{@value #NCUST}   : Add new customer
	 *    <li>{@value #NINV}    : Add to Stock
	 *    <li>{@value #INV}     : Display Inventory
	 *    <li>{@value #CUST}    : Display Customers
	 *    <li>{@value #ORDER}   : Display Orders 
	 *    <li>{@value #EXIT}    : Exit the program 
	 * </ul>
	 */

	public void displayMenu () {
		System.out.println();
		System.out.println("Enter the number denoting the action " +
				"to perform:");
		System.out.println("PLACE ORDER................." + ADDONE);
		System.out.println("ADD NEW CUSTOMER............" + NCUST);
		System.out.println("ADD INVENTORY ITEM.........." + NINV);
		System.out.println("UPDATE STOCK................" + UINV);
		System.out.println("DISPLAY INVENTORY..........." + INV);
		System.out.println("DISPLAY CUSTOMERS..........." + CUST);
		System.out.println("DISPLAY CURRENT ORDERS......" + ORDER);       
		System.out.println("Exit........................" + EXIT);

	}

	/**
	 * Reads user input and returns an integer
	 * @param prompt is the String message is provided
	 * @return an integer that is parsed from the String choice
	 */

	public int readIntWithPrompt (String prompt) {
		System.out.println(prompt); 
		System.out.flush();
		String choice = in.nextLine();		
		int i = Integer.parseInt(choice);
		return i;
	}

	/**
	 * Reads user input and returns a String 
	 * @param prompts is a String value that allows for a question to be asked
	 * @return a String value from the Scanner in.nextLine() method
	 */
	public String readStringWithPrompt(String prompts){
		System.out.println(prompts); 
		System.out.flush();
		String text = in.nextLine();
		return text;
	}

	/**
	 * Reads user input without a prompt message and returns a String value
	 * @return a String value from what user typed in
	 */
	public String readString(){
		String noprompt = in.nextLine();
		return noprompt;
	}
	
	/**
	 * Displays question in regards to adding manually a 6 digit ID number
	 */
	public void display2ndTierMenu(){
			System.out.println("Provide 6 digit ID number? Y/N\n(One will be automatically assigned if not provided)");
	
	}
	
	/**
	 * When viewing the list of Inventory Items this menu allows for the user to make a choice of editing or removing items
	 */
	public void addOrRemoveInventory(){
		System.out.println();
		System.out.println("TO UPDATE AN ITEM ON THE LIST PRESS 1");
		System.out.println("TO REMOVE AN ITEM ON THE LIST PRESS 2" + "\nPRESS ANY OTHER BUTTON TO RETURN TO MAIN MENU");
		
	}
	
	/**
	 * When viewing the list of Customers this menu allows for the user to make a choice of editing or removing Customers
	 */
	public void addOrRemoveCustomer(){
		System.out.println();
		System.out.println("TO UPDATE CUSTOMER INFORMATION ON THE LIST PRESS 1");
		System.out.println("TO REMOVE A CUSTOMER ON THE LIST PRESS 2" + "\nPRESS ANY OTHER BUTTON TO RETURN TO MAIN MENU");
	}
	
	/**
	 * When viewing the current list of Orders this menu allows for the user to remove any orders from the list
	 */
	public void addOrRemoveOrder(){
		System.out.println();
		System.out.println("TO REMOVE AN ORDER ON THE LIST PRESS 1" + "\nPRESS ANY OTHER BUTTON TO RETURN TO MAIN MENU");		
	}
	
	/**
	 * Displays warning if the user tries to order more than the Inventory Object's total amount
	 */
	public void notEnough(){
		System.out.println("SORRY, THERE IS NOT ENOUGH IN STOCK FOR THAT ITEM");
	}
	
	/**
	 * Displays warning when the Customer ID is already in use
	 */
	public void customerIdAlreadyTaken(){
		System.out.println("CUSTOMER ID ALREADY BEING USED.");		
	}
	
	/**
	 * Displays warning if the user inputs something other than what was required
	 */
	public void invalidUserInput(){
		System.out.println("INVALID ENTRY");
		System.out.println();
	}
	
	/**
	 * Displays warning if the Inventory ID number is already in use
	 */
	public void inventoryIdAlreadyTaken(){
		System.out.println("INVENTORY ID ALREADY USED.");		
	}
	
	/**
	 * Displays warning if the Inventory ID number does not exist
	 */
	public void noInventoryId(){
		System.out.println("NO RECORD OF THAT INVENTORY ID");
	}
	
	/**
	 * Displays warning if the Customer Id number does not exist
	 */
	public void customerIdDoesNotExist(){
		System.out.println("NO RECORD OF THAT CUSTOMER ID");		
	}
	
	/**
	 * Question for retrieving the beginning date for an Order being made
	 */
	public void beginDate(){
		System.out.println("ENTER BEGIN DATE? Y/N (default is last month)");
	}
	
	/**
	 * Question for retrieving the end date for an Order being made
	 */
	public void endDate(){
		System.out.println("ENTER END DATE? Y/N (default is today)");
	}
	
	/**
	 * Displays warning if the Inventory ID number does not exist
	 */
	public void invalidIdInv(){
		System.out.println("INVENTORY ITEM DOES NOT EXIST");
	}
}
