package edu.usm.cos420.example1.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import edu.usm.cos420.example1.domain.Customer;
import edu.usm.cos420.example1.domain.Inventory;
import edu.usm.cos420.example1.domain.Order;
import edu.usm.cos420.example1.service.ExampleService;
import edu.usm.cos420.example1.view.impl.CItemView;

/**
 *   A Controller class to execute user's menu choice.
 *   
 *   
 */	 
public class CItemController {

	private ExampleService atMyService; 
	private CItemView view;

	/**
	 * 
	 * Constructor : pass in a service class which can provide access to cItem operations. 
	 * @param view is part of CItemView Object
	 * @param service is part of ExampleService Object
	 */
	public CItemController(CItemView view, ExampleService service)
	{
		this.view = view;
		this.atMyService = service;
	}

	/**
	 * Allow the user to access the citem collection
	 * 
	 */

	public void provideAccess()  {
		int choice = CItemView.NO_CHOICE;
		//String text = "";
		do {	
			view.displayMenu();
			String temp ="";
			temp = view.readStringWithPrompt("Enter choice: ");
			if(temp.matches("[0-9]")){
				choice = Integer.parseInt(temp);
				executeChoice(choice);
			}else{
				view.invalidUserInput();
			}	
		}while (choice != CItemView.EXIT);

	}

	/**
	 *   Performs the branching logic to call appropriate functions to satisfy user choice
	 *   @param choice represents the user selection of action they want accomplished. 
	 */

	public void executeChoice (int choice) {
		System.out.println();
		/*
		 * First choice: Place new Order
		 */
		if (choice == CItemView.ADDONE)
		{
			String customerid = "";
			String invItem = "";
			String orderAmount = "";
			int orderAm =0;
			int totalorders = 0;
			String bdate ="";
			String edate ="";	
			customerid = view.readStringWithPrompt("ENTER 6 DIGIT CUSTOMER'S ID NUMBER ######");
			int custidnum = validIntId(customerid);
			Customer cust = atMyService.validCid(custidnum);
			if(cust == null){
				view.customerIdDoesNotExist();
			}
			else{
				boolean moreOrders = true;
				while(moreOrders){
					invItem = view.readStringWithPrompt("ENTER 6 DIGIT INVENTORY'S NUMBER ######:");
					int invidnum = validIntId(invItem);
					
					Inventory invent = atMyService.validId(invidnum);
					if(invent == null ){
						view.noInventoryId();
						break;
					}
					else{
						orderAmount = view.readStringWithPrompt("ENTER QUANTITY:");
						if(orderAmount.matches("[0-9]+")){
						orderAm = Integer.parseInt(orderAmount);
						}else{
							view.invalidUserInput();
							break;
						}
						int totalAmount = atMyService.quantity(invidnum); 
						if(orderAm > totalAmount){
							view.notEnough();
							break;
						}
						else{
							int updateInv = totalAmount - orderAm;
							bdate = getBeginDate();
							edate = getEndDate();
							totalorders = 1;
							System.out.println("\n" + totalorders + " Order(s) Made for Customer:\n" 
									+ cust.getName() +" CUSTOMER ID: "+ custidnum + "\nfor "+ orderAm+ " order(s) of " + invent.getDescription()
									+ "\nBegin Date: " + bdate +" and End Date: " + edate + "\n");
							atMyService.newOrder(cust.getIdnumber(), invent.getID(), orderAm, bdate, edate);
							atMyService.updateInventory(invidnum, updateInv);
							totalorders++;

						}
					}
					String more = view.readStringWithPrompt("PRESS 1 FOR ADDITIONAL ORDERS OR PRESS ENTER TO CONTINUE");
					if(!more.equalsIgnoreCase("1")){
						moreOrders = false;
					}
				}
			}
		}
		/*
		 *  2nd Choice: List all Inventory
		 */
		else if (choice == CItemView.INV){
			System.out.println("List of Inventory:");
			atMyService.displayInventory();
			String yesorNo = view.readStringWithPrompt("\nWould you like to edit or remove an item from the list? Y/N");
			if(yesorNo.equalsIgnoreCase("y") || yesorNo.equalsIgnoreCase("yes")){
				view.addOrRemoveInventory();
				String updateOrRemove = view.readString();
				if(updateOrRemove.equalsIgnoreCase("1")){
					String in = "";
					String newAmount = "";
					int newAm = 0;
					in = view.readStringWithPrompt("ENTER 6 DIGIT ID NUMBER ######");
					int idnum = validIntId(in);
					Inventory i = atMyService.validId(idnum);
					if(i != null){
						newAmount = view.readStringWithPrompt("ENTER NEW AMOUNT");
						if(newAmount.matches("[0-9]+")){
							newAm = Integer.parseInt(newAmount);
							atMyService.updateInventory(idnum, newAm);
							System.out.println("Updated Inventory Stock for: " + i.getDescription() + ", new total = " + newAmount);
						}else{
							view.invalidUserInput();
						}	
					}
					else{
						view.invalidIdInv();
					}
				}else if(updateOrRemove.equalsIgnoreCase("2")){
					String in = "";
					String remove = "";
					in = view.readStringWithPrompt("ENTER 6 DIGIT ID NUMBER ######");
					int idnum = validIntId(in);
					Inventory i = atMyService.validId(idnum);
					if(i != null){
						remove = view.readStringWithPrompt("ARE YOU SURE YOU WANT TO DELETE ITEM? Y/N");
						if(remove.equalsIgnoreCase("y") || remove.equalsIgnoreCase("yes")){
							atMyService.removeInventoryItem(idnum);
							System.out.println("INVENTORY ITEM: " + idnum + " DELETED");
						}else{
							System.out.println("NO CHANGE TO INVENTORY ITEM");
						}	
					}
					else{
						view.invalidIdInv();
					}
				}			
			}
		}
		/*
		 *  3rd Choice: List all Customers
		 */
		else if(choice == CItemView.CUST){
			System.out.println("List of Customers:");
			atMyService.displayCustomers();
			String yesOrNo = view.readStringWithPrompt("\nWould you like to edit or remove an item from the list? Y/N");
			if(yesOrNo.equalsIgnoreCase("y") || yesOrNo.equalsIgnoreCase("yes")){
				view.addOrRemoveCustomer();
				String updateorRemove = view.readString();
				if(updateorRemove.equalsIgnoreCase("1")){
					String input = "";
					input = view.readStringWithPrompt("ENTER 6 DIGIT ID NUMBER ######");
					int idnum = validIntId(input);
					Customer c = atMyService.validCid(idnum);
					if(c != null){
						String choices = view.readStringWithPrompt("TO EDIT NAME TYPE: NAME\nTO EDIT ADDRESS TYPE: ADD");
						if(choices.equalsIgnoreCase("name") || choices.equals("n")){
							String f = view.readStringWithPrompt("ENTER NEW FIRST NAME");
							String l = view.readStringWithPrompt("ENTER NEW LAST NAME");
							String update = view.readStringWithPrompt("YOU HAVE ENTERED " + f + " " + l + "\nIS THIS CORRECT? Y/N");
							if(update.equalsIgnoreCase("y") || update.equalsIgnoreCase("yes")){
								atMyService.updateCustomerName(idnum, f, l);
								System.out.println("Updated Customer Name: " + c.getName() + " for ID " + c.getIdnumber());
							}
						}
						else if(choices.equalsIgnoreCase("add") || choices.equals("address") || choices.equalsIgnoreCase("a")){
							String a = view.readStringWithPrompt("ENTER NEW ADDRESS:");
							String choiceYorN= view.readStringWithPrompt("YOU HAVE ENTERED " + a + "\nIS THIS CORRECT? Y/N");
							if(choiceYorN.equalsIgnoreCase("y") || choiceYorN.equalsIgnoreCase("yes")){
								atMyService.updateCustomerAdd(idnum, a);
								System.out.println("Updated Customer Address: " + c.getAddress() + " for ID " + c.getIdnumber());
							}
						}else{
							System.out.println("NO CHANGE TO CUSTOMER");
						}	
					}
					else{
						view.invalidUserInput();
					}
				}else if(updateorRemove.equalsIgnoreCase("2")){
					String inID = "";
					String removeC = "";
					inID = view.readStringWithPrompt("ENTER 6 DIGIT ID NUMBER ######");
					int idnumb = validIntId(inID);
					Customer cust = atMyService.validCid(idnumb);
					if(cust != null){
						removeC = view.readStringWithPrompt("ARE YOU SURE YOU WANT TO DELETE CUSTOMER? Y/N");
						if(removeC.equalsIgnoreCase("y") || removeC.equalsIgnoreCase("yes")){
							atMyService.removeCustomerItem(idnumb);
							System.out.println("CUSTOMER: " + idnumb + " DELETED");
						}else{
							System.out.println("NO CHANGE TO CUSTOMER");
						}	
					}
					else{
						view.invalidUserInput();
					}
				}
			}
		}
		/*
		 *  4th Choice: List all current Orders
		 */
		else if(choice == CItemView.ORDER){
			System.out.println("List of Current Orders:");
			atMyService.displayOrders();
			String yesORNo = view.readStringWithPrompt("\nWould you like to remove an order from the list? Y/N");
			if(yesORNo.equalsIgnoreCase("y") || yesORNo.equalsIgnoreCase("yes")){
				String orderId = "";
				String removeO = "";
				orderId = view.readStringWithPrompt("ENTER 6 DIGIT ID NUMBER ######");
				int idnumb = validIntId(orderId);
				Order o = atMyService.validOrder(idnumb);
				if(o != null){
					removeO = view.readStringWithPrompt("ARE YOU SURE YOU WANT TO DELETE ORDER? Y/N");
					if(removeO.equalsIgnoreCase("y") || removeO.equalsIgnoreCase("yes")){
						atMyService.removeOrderItem(idnumb);
						System.out.println("ORDER: " + idnumb + " DELETED");
					}else{
						System.out.println("NO CHANGE TO CUSTOMER");
					}	
				}
				else{
					view.invalidUserInput();
				}
			}
		}
		/*
		 *  5th Choice: Create new Customer
		 */
		else if(choice == CItemView.NCUST){	
			String firstname = "";
			String lastname = "";
			String address = "";
			view.display2ndTierMenu();
			String yesorno = view.readStringWithPrompt("");
			if(yesorno.equalsIgnoreCase("y") || yesorno.equalsIgnoreCase("yes")){
				String idnumber = view.readStringWithPrompt("ENTER 6 DIGIT ID NUMBER ######");
				int idnum = validIntId(idnumber);
				if(idnum > 0 ){
					Customer c = atMyService.validCid(idnum);
					if(c != null){
						view.customerIdAlreadyTaken();
					}
					else{
						firstname = view.readStringWithPrompt("ENTER FIRST NAME:");
						lastname = view.readStringWithPrompt("ENTER LAST NAME:");
						address = view.readStringWithPrompt("ENTER CUSTOMER'S ADDRESS");
						String fullname = firstname + " " + lastname;
						atMyService.addACustomerwithId(idnum, fullname, address);
						System.out.println("Added Customer: " + fullname + " with ID number: " + idnum);
					}
				}
				else{
					view.invalidUserInput();
				}
			}
			else{
				firstname = view.readStringWithPrompt("ENTER FIRST NAME:");
				lastname = view.readStringWithPrompt("ENTER LAST NAME:");
				address = view.readStringWithPrompt("ENTER CUSTOMER'S ADDRESS");
				String fullname = firstname + " " + lastname;
				long cid = atMyService.addACustomerwithoutId(fullname, address);
				System.out.println("Added Customer: " + fullname + " with ID number: " + cid);
			}				
		}
		/*
		 * 6th Choice: Create new Inventory Item
		 */
		else if(choice == CItemView.NINV){
			String description = "";
			int amount = 0;
			view.display2ndTierMenu();
			String yesorno = view.readStringWithPrompt("");
			if(yesorno.equalsIgnoreCase("y") || yesorno.equalsIgnoreCase("yes")){
				String idnumber = view.readStringWithPrompt("ENTER 6 DIGIT ID NUMBER ######");	
				int idnum = validIntId(idnumber);
				if(idnum > 0){
					Inventory in = atMyService.validId(idnum);
					if(in != null){
						view.inventoryIdAlreadyTaken();
					}
					else{
						description = view.readStringWithPrompt("ENTER DESCRIPTION:");
						amount = view.readIntWithPrompt("ENTER AMOUNT IN STOCK:");
						atMyService.addInventorywithID(idnum, description, amount);
						System.out.println("Added Inventory: " + description + " total = ..... " + amount);
					}
				}else{
					view.invalidUserInput();
				}

			}
			else{		
				description = view.readStringWithPrompt("ENTER DESCRIPTION:");
				amount = view.readIntWithPrompt("ENTER AMOUNT IN STOCK:");
				long id = atMyService.addInventorywithoutID(description, amount);
				System.out.println("Added Inventory: " + description + " with ID number: " + id + " total = ..... " + amount);
			}
		}
		/*
		 *  7th Choice: Update current Inventory Item
		 */
		else if(choice == CItemView.UINV){
			String idnumber = "";
			String newAmount = "";
			int newAm = 0;
			idnumber = view.readStringWithPrompt("ENTER 6 DIGIT ID NUMBER ######");
			int idnum = validIntId(idnumber);
			Inventory i = atMyService.validId(idnum);
			if(i != null){
				newAmount = view.readStringWithPrompt("ENTER NEW AMOUNT");
				if(newAmount.matches("[0-9]+")){
					newAm = Integer.parseInt(newAmount);
					atMyService.updateInventory(idnum, newAm);
					System.out.println("Updated Inventory Stock for: " + i.getDescription() + ", new total = " + newAmount);
				}else{
					view.invalidUserInput();
				}	
			}
			else{
				view.invalidIdInv();
			}

		}
		/*
		 *  8th Choice: Exit the program
		 */
		else if (choice == CItemView.EXIT){
			System.out.println("Goodbye.");
		}
		/*
		 * For invalid Choices
		 */
		else{
			System.out.println("Invalid choice, Please enter a number between 0 and 8");
		}

	}

	/**
	 * Checks to see if the ID number that was entered from the user matches the correct ID template, and returns ID as an integer. Returns -1 if not 
	 * @param id is the user input for entering an ID number
	 * @return an integer if the ID number matches a 6 digit number or negative 1
	 */

	private int validIntId(String id){
		if(id.matches("[0-9][0-9][0-9][0-9][0-9][0-9]")){
			return Integer.parseInt(id);	
		}else{
			return -1;
		}	
	}



	/**
	 * Gets the begin Date from the user for the Order Object
	 * @return a Date that was manually entered using the "MM/dd/yyyy" format or a default date being a month ago
	 */

	private String getBeginDate(){
		boolean data = true;
		view.beginDate();
		Calendar c = Calendar.getInstance();
		SimpleDateFormat datef = new SimpleDateFormat("MM/dd/yyyy");
		String date = "";
		int d =0;
		int m =0;
		int y =0;
		String month = "";
		String year = "";
		String yesorno = view.readStringWithPrompt("");
		if(yesorno.equalsIgnoreCase("y") || yesorno.equalsIgnoreCase("yes")){
			while(data){
				month = view.readStringWithPrompt("ENTER MONTH XX (example 05 for May)");
				if(month.matches("[0-9]") || month.matches("[0-1][1-9]")){
					m = Integer.parseInt(month);
					if(m == 0){
						m = 12;
					}
					else{
						m -= 1;
					}
				}
				else{
					view.invalidUserInput();
					continue;
				}
				date = view.readStringWithPrompt("ENTER DAY XX");
				if(date.matches("[0-9]") || date.matches("[0-3][1-9]")){
					d = Integer.parseInt(date);	
				}else{
					view.invalidUserInput();
					continue;
				}
				year = view.readStringWithPrompt("ENTER YEAR XXXX");
				if(year.matches("[0-9][0-9][0-9][0-9]")){
					y = Integer.parseInt(year);
					data = false;
				}else{
					view.invalidUserInput();
					continue;
				}
			}

			c.set(y, m, d);
			System.out.println("DATE ENTERED: " + datef.format(c.getTime()));
			return datef.format(c.getTime());
		}
		else{
			c.add(Calendar.MONTH, -1);
			System.out.println("DATE ENTERED: " + datef.format(c.getTime()));	
			return datef.format(c.getTime());
		}
	}

	/**
	 * Gets the end date from the user for the Order Object
	 * @return a Date that was manually entered using the "MM/dd/yyyy" format or a default of today's date
	 */

	private String getEndDate(){
		view.endDate();
		boolean data = true;
		Calendar c = Calendar.getInstance();
		SimpleDateFormat datef = new SimpleDateFormat("MM/dd/yyyy");	
		int d =0;
		int m =0;
		int y =0;
		String month = "";
		String date = "";
		String year = "";
		String yesorno = view.readStringWithPrompt("");
		if(yesorno.equalsIgnoreCase("y") || yesorno.equalsIgnoreCase("yes")){
			while(data){
				month = view.readStringWithPrompt("ENTER MONTH XX (example 05 for May)");
				if(month.matches("[0-9]") || month.matches("[0-1][1-9]")){
					m = Integer.parseInt(month);
					if(m == 0){
						m = 12;
					}
					else{
						m -= 1;
					}
				}
				else{
					view.invalidUserInput();
					continue;
				}
				date = view.readStringWithPrompt("ENTER DAY XX");
				if(date.matches("[0-9]") || date.matches("[0-3][1-9]")){
					d = Integer.parseInt(date);	
				}else{
					view.invalidUserInput();
					continue;
				}
				year = view.readStringWithPrompt("ENTER YEAR XXXX");
				if(year.matches("[0-9][0-9][0-9][0-9]")){
					y = Integer.parseInt(year);
					data = false;
				}else{
					view.invalidUserInput();
					continue;
				}
			}			
			c.set(y, m, d);
			System.out.println("DATE ENTERED: " + datef.format(c.getTime()));
			return datef.format(c.getTime());
		}
		else{
			System.out.println("DATE ENTERED: " + datef.format(c.getTime()));
			return datef.format(c.getTime());
		}

	}


}
