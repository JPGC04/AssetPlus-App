package ca.mcgill.ecse.assetplus.features;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import ca.mcgill.ecse.assetplus.controller.*;
import ca.mcgill.ecse.assetplus.model.Employee;
import ca.mcgill.ecse.assetplus.model.MaintenanceTicket;
import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
import ca.mcgill.ecse.assetplus.model.AssetPlus;
import ca.mcgill.ecse.assetplus.model.AssetType;
import ca.mcgill.ecse.assetplus.model.Manager;
import ca.mcgill.ecse.assetplus.model.SpecificAsset;
import ca.mcgill.ecse.assetplus.model.TicketImage;
import ca.mcgill.ecse.assetplus.model.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
public class AddTicketImageStepDefinitions {
private AssetPlus assetPlus = AssetPlusApplication.getAssetPlus();
private String error;
private int errorCntr;
/** Calls controller and sets error and counter. */
  private void callController(String result) {
    if (!result.isEmpty()) {
      error += result;
      errorCntr += 1;
    }
  }

  @Given("the following employees exist in the system \\(p5)")
  public void the_following_employees_exist_in_the_system_p5(
          io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> employeeDataList = dataTable.asMaps();
    for (Map<String, String> data : employeeDataList) {
    	String email = data.get("email");
    	String name = data.get("name");
    	String password = data.get("password");
    	String phoneNumber = data.get("phoneNumber");
    	assetPlus.addEmployee(email, name, password, phoneNumber);
      //AssetPlusFeatureSet1Controller.addEmployeeOrGuest(data.get("email"),data.get("name"),data.get("password"),data.get("phoneNumber"),true);
    }

  }

  @Given("the following manager exists in the system \\(p5)")
  public void the_following_manager_exists_in_the_system_p5(
          io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> managerData = dataTable.asMaps();
    for (Map<String, String> data : managerData) {
    	String email = data.get("email");
    	String password = data.get("password");
    	if (assetPlus.hasManager()) {
    		assetPlus.getManager().setPassword(password);
    	}
    	
    	else {
    		new Manager(email, "", password, "", assetPlus);
    	}
    }

  }

  @Given("the following asset types exist in the system \\(p5)")
  public void the_following_asset_types_exist_in_the_system_p5(
          io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> assetTypes = dataTable.asMaps();
    for (Map<String, String> data : assetTypes) {
      AssetPlusFeatureSet2Controller.addAssetType(data.get("name"), Integer.parseInt(data.get("expectedLifeSpan")));
    }
  }

   //FIXED
  @Given("the following assets exist in the system \\(p5)")
  public void the_following_assets_exist_in_the_system_p5(
      io.cucumber.datatable.DataTable dataTable) {
	  
	  List<Map<String, String>> assetData = dataTable.asMaps();
	  
	  for (Map<String, String> data : assetData) {
		  int assetNumber = Integer.parseInt(data.get("assetNumber"));
	      AssetType assetType = AssetType.getWithName(data.get("type"));
	      Date purchaseDate = Date.valueOf(data.get("purchaseDate"));
	      int floorNumber = Integer.parseInt(data.get("floorNumber"));
	      int roomNumber = Integer.parseInt(data.get("roomNumber"));
	      assetPlus.addSpecificAsset(assetNumber, floorNumber, roomNumber, purchaseDate, assetType);
	  }
  }

  //FIXED SOMEONE FILLED IN CONTROLLER WITH THE WRONG PARAMETERS 
  @Given("the following tickets exist in the system \\(p5)")
  public void the_following_tickets_exist_in_the_system_p5(
          io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> tickets = dataTable.asMaps();
    for (Map<String, String> data : tickets) {
    	
    	 int id = Integer.parseInt (data.get("id")); 
    	 String ticketRaiser = data.get("ticketRaiser");
         Date raisedOnDate = Date.valueOf(data.get("raisedOnDate"));
         String description = data.get("description"); 
         int assetNumber = Integer.parseInt(data.get("assetNumber"));

         AssetPlusFeatureSet4Controller.addMaintenanceTicket(id, raisedOnDate, description, ticketRaiser, assetNumber);
         
    }
  }

 //FIXED 
  @Given("the following ticket images exist in the system \\(p5)")
  public void the_following_ticket_images_exist_in_the_system_p5(
      io.cucumber.datatable.DataTable dataTable) {
	  
    List<Map<String, String>> images = dataTable.asMaps();
    for (Map<String, String> data : images) {
    	String imageUrl = data.get("imageUrl");
    	int ticketId = Integer.parseInt(data.get("ticketId"));
    	MaintenanceTicket ticket = MaintenanceTicket.getWithId(ticketId);
    	new TicketImage(imageUrl, ticket);
    	//ticket.addTicketImage(imageUrl);
    	//AssetPlusFeatureSet5Controller.addImageToMaintenanceTicket(imageUrl, ticketId); //There is a problem with the controller or linking urls
    }
  }

  @When("hotel staff adds an image with url {string} to the ticket with id {string} \\(p5)")
  public void hotel_staff_adds_an_image_with_url_to_the_ticket_with_id_p5(String string, String string2) {
    callController(AssetPlusFeatureSet5Controller.addImageToMaintenanceTicket(string, Integer.parseInt(string2)));
  }

  //REWORKED AND WORKING
  @Then("the number of images in the system shall be {string} \\(p5)")
  public void the_number_of_images_in_the_system_shall_be_p5(String string) {
	  
    List<MaintenanceTicket> maintenanceTickets = assetPlus.getMaintenanceTickets();
    //System.out.println(maintenanceTickets.size());
    
    
    int numOfImages = 0;
    for (MaintenanceTicket aTicket : maintenanceTickets) {
    	//System.out.println(aTicket);
    	//System.out.println("");
    	//System.out.println(aTicket.getTicketImages());
    	//System.out.println("");
      numOfImages += aTicket.numberOfTicketImages();
    }
  }

  
  
  private Supplier<String> parseInt(String string) {
    return null;
  }

  @Then("the following ticket images shall exist in the system \\(p5)")
public void the_following_ticket_images_shall_exist_in_the_system_p5(
        io.cucumber.datatable.DataTable dataTable) {
  List<Map<String, String>> ticketimages = dataTable.asMaps();
  
  for (Map<String, String> data : ticketimages) {
    boolean image_found=false;
    String url=data.get("imageUrl");
    String id=data.get("ticketId");
    MaintenanceTicket ticket=MaintenanceTicket.getWithId(Integer.parseInt(id));
    for (TicketImage image:ticket.getTicketImages()) {
      if(url.equals(image.getImageURL())){
        image_found=true;
      }
    }
    if(!image_found){
      
    }
  }
}
  
  
  //THIS NEEDS TO BE FIXED
  @Then("the ticket with id {string} shall have an image with url {string} \\(p5)")
  public void the_ticket_with_id_shall_have_an_image_with_url_p5(String string, String string2) {
    String url = string2;
    int id = Integer.parseInt(string);
    
    MaintenanceTicket ticket = MaintenanceTicket.getWithId(id);
    
    
    List<TicketImage> images = ticket.getTicketImages();
    for (TicketImage image : images) {
      if (!(image.getImageURL() == null)) {
    	  if (image.getImageURL().equals(url)) {
    		  return;
      }
    }
    }
    fail();
  }

  @Then("the number of images for ticket with id {string} in the system shall be {string} \\(p5)")
  public void the_number_of_images_for_ticket_with_id_in_the_system_shall_be_p5(String string,
                                                                                String string2) {
    List<MaintenanceTicket> maintenanceTickets = assetPlus.getMaintenanceTickets();
    MaintenanceTicket t = getTicketById(maintenanceTickets, string);
    assertEquals(Integer.valueOf(string2), t.numberOfTicketImages());
  }

  private MaintenanceTicket getTicketById(List<MaintenanceTicket> maintenanceTickets, String string) {
    for (MaintenanceTicket t : maintenanceTickets) {
      if (Integer.toString(t.getId()).equals(string) ) {
        return t;
      }
    }
    return null;
  }

  @Then("the system shall raise the error {string} \\(p5)")
  public void the_system_shall_raise_the_error_p5(String string) {
    assertTrue(error.contains(string));
  }
}
