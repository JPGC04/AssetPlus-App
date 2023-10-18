package ca.mcgill.ecse.assetplus.controller;
import java.util.List;

import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
import ca.mcgill.ecse.assetplus.model.*;


public class AssetPlusFeatureSet1Controller {

  public static String updateManager(String password) {
	  if(password == null){
			throw new IllegalArgumentException("Enter approporaite password");
		}
	  AssetPlus assetPlus = AssetPlusApplication.getAssetPlus();
	  Manager manager = assetPlus.getManager();
	  manager.setPassword(password);
	  return "";
  }

  public static String addEmployeeOrGuest(String email, String password, String name, String phoneNumber,
        boolean isEmployee) {
	  if(password == null){
			throw new IllegalArgumentException("Enter approporaite password");
		}
	  if(name == null){
			throw new IllegalArgumentException("Enter approporaite name");
		}
	  if(phoneNumber == null){
			throw new IllegalArgumentException("Enter approporaite phoneNumber");
		}
	  AssetPlus assetPlus = AssetPlusApplication.getAssetPlus();
	  if (isEmployee) {
	  assetPlus.addEmployee(email, name, password, phoneNumber);
	  return "";
	  }
	  else {
	  assetPlus.addGuest(email, name, password, phoneNumber);
	  return "";
	  }
  }

  public static String updateEmployeeOrGuest(String email, String newPassword, String newName, String newPhoneNumber) {
	  AssetPlus assetPlus = AssetPlusApplication.getAssetPlus();
	  if(newPassword == null){
			throw new IllegalArgumentException("Enter approporaite password");
		}
	  if(newName == null){
			throw new IllegalArgumentException("Enter approporaite name");
		}
	  if(newPhoneNumber == null){
			throw new IllegalArgumentException("Enter approporaite phonenumber");
		}
	  if (getEmployeeByEmail(email) == null && getGuestByEmail(email) == null) {
	      throw new IllegalArgumentException("Invalid email address");
	    }
	  if (email.contains("@ap.com")) {
		  Employee employee = getEmployeeByEmail(email);
		  employee.setName(newName);
		  employee.setPassword(newPassword);
		  employee.setPhoneNumber(newPhoneNumber);
	  } else {
		  Guest guest = getGuestByEmail(email);
		  guest.setPassword(newPassword);
		  guest.setName(newName);
		  guest.setPhoneNumber(newPhoneNumber);
	  }
	  return "";
  }
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
