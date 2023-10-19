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
import ca.mcgill.ecse.assetplus.model.TicketImage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class AddTicketImageStepDefinitions {
  private AssetPlus assetPlus = AssetPlusApplication.getAssetPlus();
  private String error;
  private int errorCntr;
  @Given("the following employees exist in the system \\(p5)")
  public void the_following_employees_exist_in_the_system_p5(
          io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> employeeDataList = dataTable.asMaps();
    for (Map<String, String> data : employeeDataList) {
      AssetPlusFeatureSet1Controller.addEmployeeOrGuest(data.get("email"),data.get("name"),data.get("password"),data.get("phoneNumber"),true);
    }

  }

  @Given("the following manager exists in the system \\(p5)")
  public void the_following_manager_exists_in_the_system_p5(
          io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> managerData = dataTable.asMaps(String.class, String.class);
    error="";
    errorCntr=0;
    for (Map<String, String> data : managerData) {
      assetPlus= AssetPlusApplication.getAssetPlus();
      Manager manager = new Manager(data.get("email"), "Lebron", data.get("password"), "(555)555-5555", assetPlus);
      assetPlus.setManager(manager);
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

  @Given("the following tickets exist in the system \\(p5)")
  public void the_following_tickets_exist_in_the_system_p5(
          io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> tickets = dataTable.asMaps();
    for (Map<String, String> data : tickets) {
      AssetPlusFeatureSet4Controller.addMaintenanceTicket(Integer.parseInt(data.get("id")), Date.valueOf(data.get("raisedOnDate")),data.get("description"),data.get("email"),Integer.parseInt(data.get("assetNumber")));
    }
  }

 //FIXED 
  @Given("the following ticket images exist in the system \\(p5)")
  public void the_following_ticket_images_exist_in_the_system_p5(
      io.cucumber.datatable.DataTable dataTable) {
	  
    List<Map<String, String>> images = dataTable.asMaps();
    for (Map<String, String> data : images) {
    	String imageUrl = data.get("imageURL");
    	int ticketId = Integer.parseInt(data.get("ticketId"));
    	AssetPlusFeatureSet5Controller.addImageToMaintenanceTicket(imageUrl, ticketId);
    }
  }

  @When("hotel staff adds an image with url {string} to the ticket with id {string} \\(p5)")
  public void hotel_staff_adds_an_image_with_url_to_the_ticket_with_id_p5(String string, String string2) {
    AssetPlusFeatureSet5Controller.addImageToMaintenanceTicket(string2, Integer.parseInt(string2));
  }


  @Then("the number of images in the system shall be {string} \\(p5)")
  public void the_number_of_images_in_the_system_shall_be_p5(String string) {
    int numOfImages = 0;
    List<MaintenanceTicket> maintenanceTickets = assetPlus.getMaintenanceTickets();
    for (MaintenanceTicket aTicket : maintenanceTickets) {
      List<TicketImage> ticketImage = aTicket.getTicketImages();
      int thisTicketImageSize = ticketImage.size();
      if (thisTicketImageSize != 0) {
        numOfImages += thisTicketImageSize;
      }
    }
    String strNumOfImages = Integer.toString(numOfImages);
    assertEquals(string, strNumOfImages);
  }

  @Then("the following ticket images shall exist in the system \\(p5)")
  public void the_following_ticket_images_shall_exist_in_the_system_p5(
          io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    throw new io.cucumber.java.PendingException();
  }

  @Then("the ticket with id {string} shall have an image with url {string} \\(p5)")
  public void the_ticket_with_id_shall_have_an_image_with_url_p5(String string, String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
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
    throw new RuntimeException(string);
  }
}
