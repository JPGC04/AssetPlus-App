package ca.mcgill.ecse.assetplus.features;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet5Controller;
import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet4Controller;
import ca.mcgill.ecse.assetplus.model.AssetPlus;
import ca.mcgill.ecse.assetplus.model.AssetType;
import ca.mcgill.ecse.assetplus.model.HotelStaff;
import ca.mcgill.ecse.assetplus.model.MaintenanceNote;
import ca.mcgill.ecse.assetplus.model.MaintenanceTicket;
import ca.mcgill.ecse.assetplus.model.Manager;
import ca.mcgill.ecse.assetplus.model.SpecificAsset;
import ca.mcgill.ecse.assetplus.model.TicketImage;
import ca.mcgill.ecse.assetplus.model.User;
import ca.mcgill.ecse.assetplus.model.MaintenanceTicket.TimeEstimate;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class MaintenanceTicketsStepDefinitions {
  private AssetPlus assetPlus = AssetPlusApplication.getAssetPlus();

  private String error;

  @Given("the following employees exist in the system")
  public void the_following_employees_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
      List<Map<String, String>> rows = dataTable.asMaps();
      for (var row : rows) {
        String email = row.get("email");
        String name = row.get("name");
        String password = row.get("password");
        String phoneNumber = row.get("phoneNumber");
        assetPlus.addEmployee(email, name, password, phoneNumber);
      }
  }

  @Given("the following manager exists in the system")
  public void the_following_manager_exists_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
      List<Map<String, String>> managerData = dataTable.asMaps();
      for (Map<String, String> data : managerData) {
        String email = data.get("email");
        String password = data.get("password");
        if (assetPlus.hasManager()) {
          assetPlus.getManager().setPassword(password);
        } else {
          new Manager(email, "", password, "", assetPlus);
        }
      }
  }

  @Given("the following asset types exist in the system")
  public void the_following_asset_types_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> assetTypes = dataTable.asMaps();
        for (Map<String, String> data : assetTypes) {
          assetPlus.addAssetType(data.get("name"), Integer.parseInt(data.get("expectedLifeSpan")));
        }
  }

  @Given("the following assets exist in the system")
  public void the_following_assets_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
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

  @Given("the following tickets exist in the system")
  public void the_following_tickets_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
      List<Map<String, String>> tickets = dataTable.asMaps();
      for (Map<String, String> data : tickets) {
        int id = Integer.parseInt(data.get("id"));
        String ticketRaiserEmail = data.get("ticketRaiser");
        Date raisedOnDate = Date.valueOf(data.get("raisedOnDate"));
        String description = data.get("description");

        int assetNumber = -1;
        String tempNumber = data.get("assetNumber");
        if (tempNumber != null) {
          assetNumber = Integer.parseInt(tempNumber);
        }
        User ticketRaiser = User.getWithEmail(ticketRaiserEmail);
        SpecificAsset specificAsset = SpecificAsset.getWithAssetNumber(assetNumber);
        AssetPlus assetPlus = AssetPlusApplication.getAssetPlus();
        MaintenanceTicket newMaintenanceTicket =
            new MaintenanceTicket(id, raisedOnDate, description, assetPlus, ticketRaiser);
      newMaintenanceTicket.setAsset(specificAsset);
      assetPlus.addMaintenanceTicket(newMaintenanceTicket);
    }
  }

  @Given("the following notes exist in the system")
  public void the_following_notes_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
    // Turns the dataTable into a list of lists (each row becomes a list).
    List<Map<String, Object>> tableList = dataTable.asMaps(String.class, Object.class);

    // Iterates through each list to create the specified tickets and add them to the AssetPlus
    // application.
    for (Map<String, Object> row : tableList) {
      String noteTaker = (row.get("noteTaker").toString());
      int ticketID = Integer.parseInt(row.get("ticketId").toString());
      Date addedOnDate = Date.valueOf(row.get("addedOnDate").toString());
      String description = (row.get("description").toString());

      // Instantiate and add the specified maintenance ticket notes to the appropriate maintenance
      // ticket.
      MaintenanceTicket ticket = MaintenanceTicket.getWithId(ticketID);
      HotelStaff staff = (HotelStaff) HotelStaff.getWithEmail(noteTaker); // check this
      MaintenanceNote note = new MaintenanceNote(addedOnDate, description, ticket, staff);
      ticket.addTicketNote(note);
    }

  }

  @Given("the following ticket images exist in the system")
  public void the_following_ticket_images_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> images = dataTable.asMaps();
    for (Map<String, String> data : images) {
      String imageUrl = data.get("imageUrl");
      int ticketId = Integer.parseInt(data.get("ticketId"));
      MaintenanceTicket ticket = MaintenanceTicket.getWithId(ticketId);
      new TicketImage(imageUrl, ticket);
    }
  }

  @Given("ticket {string} is marked as {string} with requires approval {string}")
  public void ticket_is_marked_as_with_requires_approval(String string, String string2,
      String string3) { 
        int id = Integer.parseInt(string);
        boolean approval = false;
        MaintenanceTicket.Status status = MaintenanceTicket.Status.valueOf(string2);
        if (string3.toLowerCase().equals("true")) {
           approval = true;
        }

        //Get the ticket
        MaintenanceTicket ticket = MaintenanceTicket.getWithId(id);
        ticket.setRequiresApproval(approval);
        ticket.setStatus(status);

  }

  @Given("ticket {string} is marked as {string}")
  public void ticket_is_marked_as(String string, String string2) {
    int id = Integer.parseInt(string);
    MaintenanceTicket.Status status = MaintenanceTicket.Status.valueOf(string2);
    MaintenanceTicket ticket = MaintenanceTicket.getWithId(id);
    ticket.setStatus(status);
  }

  @When("the manager attempts to view all maintenance tickets in the system")
  public void the_manager_attempts_to_view_all_maintenance_tickets_in_the_system() {
    AssetPlusFeatureSet4Controller.listMaintenanceTickets();
  }

  //NOT FINISHED
  @When("the manager attempts to assign the ticket {string} to {string} with estimated time {string}, priority {string}, and requires approval {string}")
  public void the_manager_attempts_to_assign_the_ticket_to_with_estimated_time_priority_and_requires_approval(
      String string, String string2, String string3, String string4, String string5) {

    int id = Integer.parseInt(string);
    boolean requiresApproval = Boolean.parseBoolean(string5);

    MaintenanceTicket ticket = MaintenanceTicket.getWithId(id);

//    ticket.assignTicket(id, string2, string3, string4, requiresApproval);

    error = AssetPlusFeatureSet4Controller.assignMaintenanceTicket(id, string2, string3, string4, requiresApproval);
  }

  @When("the hotel staff attempts to start the ticket {string}")
  public void the_hotel_staff_attempts_to_start_the_ticket(String string) {
    // Write code here that turns the phrase above into concrete actions
    int ticketId = Integer.parseInt(string);
    MaintenanceTicket ticket = MaintenanceTicket.getWithId(ticketId);
    ticket.startProgress();
  }

  @When("the manager attempts to approve the ticket {string}")
  public void the_manager_attempts_to_approve_the_ticket(String string) {
    // Write code here that turns the phrase above into concrete actions
    int ticketId = Integer.parseInt(string);
    MaintenanceTicket ticket = MaintenanceTicket.getWithId(ticketId);
    ticket.approve();
  }

  @When("the hotel staff attempts to complete the ticket {string}")
  public void the_hotel_staff_attempts_to_complete_the_ticket(String string) {
    // Write code here that turns the phrase above into concrete actions
    int ticketId = Integer.parseInt(string);
    MaintenanceTicket ticket = MaintenanceTicket.getWithId(ticketId);
    ticket.Resolve();
  }

  @When("the manager attempts to disapprove the ticket {string} on date {string} and with reason {string}")
  public void the_manager_attempts_to_disapprove_the_ticket_on_date_and_with_reason(String string,
      String string2, String string3) {
    // Write code here that turns the phrase above into concrete actions
    int ticketId = Integer.parseInt(string);
    MaintenanceTicket ticket = MaintenanceTicket.getWithId(ticketId);
    Date date = Date.valueOf(string2);

    //TODO not sure what to put as id
    ticket.disaprove(1, date, string3);

  }

  /**
   * Checks the state of a given maintenance ticket, after some operations.
   * @param string TicketID of ticket we are testing.
   * @param string2 The expected state of the maintenance ticket.
   */
  @Then("the ticket {string} shall be marked as {string}")
  public void the_ticket_shall_be_marked_as(String string, String string2) {
    // Write code here that turns the phrase above into concrete actions
    int ticketId = Integer.parseInt(string);
    MaintenanceTicket ticket = MaintenanceTicket.getWithId(ticketId);
    String state = ticket.getStatusFullName();
    assertEquals(string2, state);
  }

  @Then("the system shall raise the error {string}")
  public void the_system_shall_raise_the_error(String string) {
    // Write code here that turns the phrase above into concrete actions
    assertEquals(string, error);
  }

  /**
   * Checks the existence of the ticket in the system.
   * @param string TicketID of ticket subject to this test.
   */
  @Then("the ticket {string} shall not exist in the system")
  public void the_ticket_shall_not_exist_in_the_system(String string) {
    // Write code here that turns the phrase above into concrete actions
    int ticketId = Integer.parseInt(string);
    MaintenanceTicket ticket = MaintenanceTicket.getWithId(ticketId);
    assertNull(ticket);
  }

  /**
   * Tests if a given ticket has the expected estimated time, priority and if it require approval.
   * @param string The ticketId of the maintenanceTicket.
   * @param string2 The expected time its going to take.
   * @param string3 The expected priority level of the ticket.
   * @param string4 The expected approval setting of the ticket.
   */
  @Then("the ticket {string} shall have estimated time {string}, priority {string}, and requires approval {string}")
  public void the_ticket_shall_have_estimated_time_priority_and_requires_approval(String string,
      String string2, String string3, String string4) {
    // Write code here that turns the phrase above into concrete actions
    int ticketId = Integer.parseInt(string);
    MaintenanceTicket ticket = MaintenanceTicket.getWithId(ticketId);
    String time = String.valueOf(ticket.getTimeToResolve());
    String priority = String.valueOf(ticket.getPriority());
    String reqApproval = String.valueOf(ticket.getRequiresApproval());

    assertEquals(string2, time);
    assertEquals(string3, priority);
    assertEquals(string4, reqApproval);

  }

  /**
   * Checks the assignee of a given ticket.
   * @param string TicketID of the maintenance ticket.
   * @param string2 The expected name of the hotel staff assigned to this ticket.
   */
  @Then("the ticket {string} shall be assigned to {string}")
  public void the_ticket_shall_be_assigned_to(String string, String string2) {
    // Write code here that turns the phrase above into concrete actions
    int ticketId = Integer.parseInt(string);
    MaintenanceTicket ticket = MaintenanceTicket.getWithId(ticketId);
    HotelStaff assignee = ticket.getTicketFixer();
    String staffName = assignee.getEmail();

    assertEquals(string2, staffName);
  }

  /**
   * Checks the number of tickets in the system.
   * @param string the expected number of tickets in the system.
   */
  @Then("the number of tickets in the system shall be {string}")
  public void the_number_of_tickets_in_the_system_shall_be(String string) {
    // Write code here that turns the phrase above into concrete actions
    List<MaintenanceTicket> tickets = assetPlus.getMaintenanceTickets();
    int realSize = tickets.size();
    int expectedSize = Integer.parseInt(string);

    assertEquals(expectedSize, realSize);
  }
//TODO NOT FINISHED
  @Then("the following maintenance tickets shall be presented")
  public void the_following_maintenance_tickets_shall_be_presented(
      io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    List<Map<String, String>> maintenanceTickets = dataTable.asMaps();
    for (Map<String, String> ticket: maintenanceTickets) {
      int id = Integer.parseInt(ticket.get("id"));

      String email = ticket.get("ticketRaiser");
      String raisedDate = ticket.get("raisedOnDate");
      String description = ticket.get("description");
      String assetNumber = ticket.get("assetNumber");
      String status = ticket.get("status");
      String assigneeEmail = ticket.get("fixedByEmail");
      String estimatedTime = ticket.get("timeToResolve");
      String priority = ticket.get("priority");
      String approval = ticket.get("approvalRequired");


    }
  }

  @Then("the ticket with id {string} shall have the following notes")
  public void the_ticket_with_id_shall_have_the_following_notes(String string,
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

  @Then("the ticket with id {string} shall have no notes")
  public void the_ticket_with_id_shall_have_no_notes(String string) {
    int id = Integer.parseInt(string);
    MaintenanceTicket ticket = MaintenanceTicket.getWithId(id);
    List notes = ticket.getTicketNotes();
    int num = 1;
    if(notes == null){
     num = 0;
    }
    assertTrue(num == 0);
  }

  @Then("the ticket with id {string} shall have the following images")
  public void the_ticket_with_id_shall_have_the_following_images(String string,
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

  @Then("the ticket with id {string} shall have no images")
  public void the_ticket_with_id_shall_have_no_images(String string) {
    int id = Integer.parseInt(string);
    MaintenanceTicket ticket = MaintenanceTicket.getWithId(id);
    List images = ticket.getTicketImages();
    assertTrue(images != null && images.isEmpty());
  }
}

