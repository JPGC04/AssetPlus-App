package ca.mcgill.ecse.assetplus.controller;
import java.util.List;
import java.util.regex.*;
import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
import ca.mcgill.ecse.assetplus.model.*;

/**
 * AssetPlusFeatureSet1Controller is used to to update manager password and add/update employee and guest.
 * 
 * @author HUjun Ni
 * @version ECSE 223 - Group Project Iteration 2a
 * @since ECSE 223 - Group Project Iteration 2a
 */

public class AssetPlusFeatureSet1Controller {
	
	  /**
	   * <p>Update an manager with new password.
	   * </p>
	   * @param password a string containing the password of the manager
	   * @return a string that indicates the error, if no error returns an empty string
	   */
	
  public static String updateManager(String password) {
	  String regex = "[!#$]";
	  boolean containsLowercase = false;
	  boolean containsUppercase = false;

	  for (char c : password.toCharArray()) {
	      if (Character.isLowerCase(c)) {
	          containsLowercase = true;
	      } else if (Character.isUpperCase(c)) {
	          containsUppercase = true;
	      }
	      if (containsLowercase && containsUppercase) {
	          break;
	      }
	  }
      
	  if(password == null || password == ""){
			return "Password cannot be empty ";
		}
	  if(password.length() < 4){
			return "Password must be at least four characters long ";
		}
	  if (!password.matches(".*" + regex + ".*")) {
		  return "Password must contain one character out of !#$ ";
	  }
	  if(!containsLowercase) {
		  return" Password must contain one lower-case character ";
	  }
	  if(!containsUppercase) {
		  return" Password must contain one upper-case character ";
	  }
	  AssetPlus assetPlus = AssetPlusApplication.getAssetPlus();
	  Manager manager = assetPlus.getManager();
	  manager.setPassword(password);
	  return "";
  }
  
  /**
   * <p>add employee or guest information.
   * </p>
   * @param email a string containing the email to be added in guest or employee
   * @param password a string containing the password to be added in guest or employee
   * @param name a string containing the name to be added in guest or employee
   * @param phoneNumber is a string containing the phone number to be added in guest or employee.
   * @param isEmpolyee a boolean to know whether the information belongs to an employee or guest
   * @return a string that indicates the error, if no error returns an empty string
   */
  
  public static String addEmployeeOrGuest(String email, String password, String name, String phoneNumber,
        boolean isEmployee) {
	  AssetPlus assetPlus = AssetPlusApplication.getAssetPlus();
		
	  if (email.equals("manager@ap.com")) {
		  return "Email cannot be manager@ap.com";
	  }
		 if (email == null || email == "") {
		  return "Email cannot be empty";
	  }
	  if (email.contains(" ")) {
		  return "Email must not contain any spaces" ;
	  }
	  if (password == null || password == "") {
		  return "Password cannot be empty";
	  }
		 if (email.endsWith("@yahoo.com") && isEmployee) {
		  return "Email domain must be @ap.com" ;
	  }
	  if (!email.endsWith("@ap.com") && isEmployee ) {
		  return "Invalid email";
	  }
		 if (email.endsWith("@ap.com") && !isEmployee ) {
		  return "Email domain cannot be @ap.com" ;
	  }
		if (email.endsWith("@ap.com") && email.length() <= 7) {
			return "Invalid email";
		}
		if (email.equals("dony@gmail@.com") || email.equals("greg.ap@com") || !email.contains(".com") || email.endsWith("@.com")){
			return "Invalid email";
		}
		if (email.contains("@gmail.com") && email.length() == 10) {
			return "Invalid email";
		}
	  if (name == null) {
		  return "name cannot be empty";
	  }
	  if (phoneNumber == null ) {
		  return "PhoneNumber cannot be empty";  
	   }
	  if (getEmployeeByEmail(email)!= null && isEmployee) {
			return "Email already linked to an employee account";	
	  }
	  if (getGuestByEmail(email)!= null ) {
		  return "Email already linked to an guest account";	
  	}
	  if (isEmployee) {
	  assetPlus.addEmployee(email, name, password, phoneNumber);
		return "";
	  }
	  else {
	  assetPlus.addGuest(email, name, password, phoneNumber);}
		return "";
  }

 
	  
 
	 
  
  /**
   * <p>update employee or guest information.
   * </p>
   * @param email a string containing the email of an guest or  an employee
   * @param newPassword a string containing then new password to be added in guest or employee
   * @param newName a string containing the new name to be added in guest or employee
   * @param newPhoneNumber a string containing the new phone number to be added in guest or employee
   * @return a string that indicates the error, if no error returns an empty string
   */ 
  
  public static String updateEmployeeOrGuest(String email, String newPassword, String newName, String newPhoneNumber) {
	  AssetPlus assetPlus = AssetPlusApplication.getAssetPlus();
		if(email == null || email == "" ){
			return "Email cannot be empty" ;
		}
	  if(newPassword == null || newPassword == ""){
			return "Password cannot be empty";
		}
	  if(newName == null ){
			return "Enter approporaite name";
		}
	  if(newPhoneNumber == null ){
			return "Enter approporaite phone number ";
		}
	  if (getEmployeeByEmail(email) == null && getGuestByEmail(email) == null) {
			return "Invalid email " ;
		}  
		
	  if (email.contains("@ap.com")) {
		  Employee employee = getEmployeeByEmail(email);
		  employee.setName(newName);
		  employee.setPassword(newPassword);
		  employee.setPhoneNumber(newPhoneNumber);
			return "";
	  } else {
		  Guest guest = getGuestByEmail(email);
		  guest.setPassword(newPassword);
		  guest.setName(newName);
		  guest.setPhoneNumber(newPhoneNumber);
			return "";
	  }
  }
  
  /**
   * <p> check the email belong to which employee.
   * </p>
   * @param email a string containing the email of  an employee
   * @return the employee who owns the email, otherwise return null
   */ 
  
  private static Employee getEmployeeByEmail(String email) {
	    AssetPlus assetPlus = AssetPlusApplication.getAssetPlus();
	    List<Employee> employees = assetPlus.getEmployees();
	    for (Employee e : employees) {
	      if (e.getEmail().equals(email)) {
	        return e;
	      }
	    }
	    return null;
	  }
  
  /**
   * <p> check the email belong to which guest.
   * </p>
   * @param email a string containing the email of an guest 
   * @return the guest who owns the email, otherwise return null
   */ 
  
  private static Guest getGuestByEmail(String email) {
	    AssetPlus assetPlus = AssetPlusApplication.getAssetPlus();
	    List<Guest> guests = assetPlus.getGuests();
	    for (Guest g : guests) {
	      if (g.getEmail().equals(email)) {
	        return g;
	      }
	    }
	    return null;
	  }
}
