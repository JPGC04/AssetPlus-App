package ca.mcgill.ecse.assetplus.features;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet4Controller;
import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet6Controller;
import ca.mcgill.ecse.assetplus.controller.TOMaintenanceTicket;
import ca.mcgill.ecse.assetplus.model.AssetPlus;
import ca.mcgill.ecse.assetplus.model.AssetType;
import ca.mcgill.ecse.assetplus.model.HotelStaff;
import ca.mcgill.ecse.assetplus.model.MaintenanceNote;
import ca.mcgill.ecse.assetplus.model.MaintenanceTicket;
import ca.mcgill.ecse.assetplus.model.Manager;
import ca.mcgill.ecse.assetplus.model.SpecificAsset;
import ca.mcgill.ecse.assetplus.model.TicketImage;
import ca.mcgill.ecse.assetplus.model.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;



public class MaintenanceTicketsStepDefinitions {
  private AssetPlus assetPlus = AssetPlusApplication.getAssetPlus();
  private List<TOMaintenanceTicket> listTickets;
  private String error;

  /**
   * Defines the intial employees found in the system
   * 
   * @author Group 5
   * @param dataTable tabular data containing email, password, name, and phone number of employees
   *        in system
   */
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

  /**
   * Defines the intial managers found in the system
   * 
   * @author Group 5
   * @param dataTable tabular data containing email and password of managers in system
   */
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

  /**
   * Defines the intial asset types found in the system
   * 
   * @author Group 5
   * @param dataTable tabular data containing name and expected lifespan of asset types in system
   */
  @Given("the following asset types exist in the system")
  public void the_following_asset_types_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> assetTypes = dataTable.asMaps();
    for (Map<String, String> data : assetTypes) {
      assetPlus.addAssetType(data.get("name"), Integer.parseInt(data.get("expectedLifeSpan")));
    }
  }

  /**
   * Defines the intial assets found in the system
   * 
   * @author Group 5
   * @param dataTable tabular data containing assetNumber, type, purchase date, floor number, and
   *        room number of assets in system
   */
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

  /**
   * Defines the intial tickets found in the system
   * 
   * @author Group 5
   * @param dataTable tabular data containing id, ticket raiser, raise date, raisedOnDate,
   *        description, and asset numberasset of tickets in system
   */
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

  /**
   * Defines the notes that are present inside the system.
   * 
   * @author Group 5
   * @param dataTable Contains an array of information about each note.
   */
  @Given("the following notes exist in the system")
  public void the_following_notes_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, Object>> tableList = dataTable.asMaps(String.class, Object.class);

    for (Map<String, Object> row : tableList) {
      String noteTaker = (row.get("noteTaker").toString());
      int ticketID = Integer.parseInt(row.get("ticketId").toString());
      Date addedOnDate = Date.valueOf(row.get("addedOnDate").toString());
      String description = (row.get("description").toString());

      MaintenanceTicket ticket = MaintenanceTicket.getWithId(ticketID);
      HotelStaff staff = (HotelStaff) HotelStaff.getWithEmail(noteTaker);
      MaintenanceNote note = new MaintenanceNote(addedOnDate, description, ticket, staff);
      ticket.addTicketNote(note);
    }

  }

  /**
   * Defines the images that are present inside theh system.
   * 
   * @author Group 5
   * @param dataTable Contains an array of informatin about each image: its URL, and ticketID/
   */
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

  /**
   * Defines the status and if the ticket needs approval.
   * 
   * @author Group 5
   * @param string the TicketID in string.
   * @param string2 The status of the ticket.
   * @param string3 true/false value indicating if the ticket requires approval.
   */
  @Given("ticket {string} is marked as {string} with requires approval {string}")
  public void ticket_is_marked_as_with_requires_approval(String string, String string2,
      String string3) {
    int id = Integer.parseInt(string);
    boolean approval = false;
    MaintenanceTicket.Status status = MaintenanceTicket.Status.valueOf(string2);
    if (string3.toLowerCase().equals("true")) {
      approval = true;
    }

    MaintenanceTicket ticket = MaintenanceTicket.getWithId(id);
    ticket.setRequiresApproval(approval);
    ticket.setStatus(status);

  }

  /**
   * Defines the status of a given ticket
   * 
   * @author Group 5
   * @param string TicketID of ticket.
   * @param string2 Status of the ticket.
   */
  @Given("ticket {string} is marked as {string}")
  public void ticket_is_marked_as(String string, String string2) {
    int id = Integer.parseInt(string);
    MaintenanceTicket.Status status = MaintenanceTicket.Status.valueOf(string2);
    MaintenanceTicket ticket = MaintenanceTicket.getWithId(id);
    ticket.setStatus(status);
  }

  /**
   * The scenario when the manager attempts to view all maintenance tickets.
   * 
   * @author Group 5
   */
  @When("the manager attempts to view all maintenance tickets in the system")
  public void the_manager_attempts_to_view_all_maintenance_tickets_in_the_system() {
    listTickets = AssetPlusFeatureSet6Controller.getTickets();
  }

  /**
   * Theh scenario when a manager attempts to assign a ticket with the following properties.
   * 
   * @author Group 5
   * @param string ticketID
   * @param string2 email address of employee that is getting tasked with this ticket.
   * @param string3 Estimated time for ticket to resolve.
   * @param string4 Priority of ticket.
   * @param string5 Does the ticket need approval?
   */
  @When("the manager attempts to assign the ticket {string} to {string} with estimated time {string}, priority {string}, and requires approval {string}")
  public void the_manager_attempts_to_assign_the_ticket_to_with_estimated_time_priority_and_requires_approval(
      String string, String string2, String string3, String string4, String string5) {

    int id = Integer.parseInt(string);
    boolean requiresApproval = Boolean.parseBoolean(string5);

    error = AssetPlusFeatureSet4Controller.assignMaintenanceTicket(id, string2, string3, string4,
        requiresApproval);
  }

  /**
   * Scenario where the staff attempts to start the ticket.
   * 
   * @author Group 5
   * @param string TicketID
   */
  @When("the hotel staff attempts to start the ticket {string}")
  public void the_hotel_staff_attempts_to_start_the_ticket(String string) {
    int ticketId = Integer.parseInt(string);
    error = AssetPlusFeatureSet4Controller.startTicketProgress(ticketId);
  }

  /**
   * When the manager tries to approve a given ticket.
   * 
   * @author Group 5
   * @param string TicketID
   */
  @When("the manager attempts to approve the ticket {string}")
  public void the_manager_attempts_to_approve_the_ticket(String string) {
    int ticketId = Integer.parseInt(string);
    error = AssetPlusFeatureSet4Controller.approveTicket(ticketId);
  }

  /**
   * When the hotel staff tries to complete a ticket.
   * 
   * @author Group 5
   * @param string ticketID
   */
  @When("the hotel staff attempts to complete the ticket {string}")
  public void the_hotel_staff_attempts_to_complete_the_ticket(String string) {
    int ticketId = Integer.parseInt(string);
    error = AssetPlusFeatureSet4Controller.completeTicket(ticketId);
  }

  /**
   * When the manager attempts to disapprove a ticket on a specific date, and with a specific
   * reason.
   * 
   * @author Group 5
   * @param string TicketID
   * @param string2 Date of attempted disapproval.
   * @param string3 Reasoning behind the disapproval.
   */
  @When("the manager attempts to disapprove the ticket {string} on date {string} and with reason {string}")
  public void the_manager_attempts_to_disapprove_the_ticket_on_date_and_with_reason(String string,
      String string2, String string3) {
    int ticketId = Integer.parseInt(string);
    Date date = Date.valueOf(string2);

    error = AssetPlusFeatureSet4Controller.disapproveTicket(ticketId, date, string3);

  }


  /**
   * Checks the state of a given maintenance ticket, after some operations.
   * 
   * @author Group 5
   * @param string TicketID of ticket we are testing.
   * @param string2 The expected state of the maintenance ticket.
   */
  @Then("the ticket {string} shall be marked as {string}")
  public void the_ticket_shall_be_marked_as(String string, String string2) {
    int ticketId = Integer.parseInt(string);
    MaintenanceTicket ticket = MaintenanceTicket.getWithId(ticketId);
    String state = ticket.getStatusFullName();
    assertEquals(string2, state);
  }

  /**
   * Checks whether the correct error is raised.
   * 
   * @author Group 5
   * @param string The error string we are expecting.
   */
  @Then("the system shall raise the error {string}")
  public void the_system_shall_raise_the_error(String string) {
    assertEquals(string, error);
  }

  /**
   * Checks the existence of the ticket in the system.
   * 
   * @author Group 5
   * @param string TicketID of ticket subject to this test.
   */
  @Then("the ticket {string} shall not exist in the system")
  public void the_ticket_shall_not_exist_in_the_system(String string) {
    int ticketId = Integer.parseInt(string);
    MaintenanceTicket ticket = MaintenanceTicket.getWithId(ticketId);
    assertNull(ticket);
  }

  /**
   * Tests if a given ticket has the expected estimated time, priority and if it require approval.
   * 
   * @author Group 5
   * @param string The ticketId of the maintenanceTicket.
   * @param string2 The expected time its going to take.
   * @param string3 The expected priority level of the ticket.
   * @param string4 The expected approval setting of the ticket.
   */
  @Then("the ticket {string} shall have estimated time {string}, priority {string}, and requires approval {string}")
  public void the_ticket_shall_have_estimated_time_priority_and_requires_approval(String string,
      String string2, String string3, String string4) {
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
   * 
   * @author Group 5
   * @param string TicketID of the maintenance ticket.
   * @param string2 The expected name of the hotel staff assigned to this ticket.
   */
  @Then("the ticket {string} shall be assigned to {string}")
  public void the_ticket_shall_be_assigned_to(String string, String string2) {
    int ticketId = Integer.parseInt(string);
    MaintenanceTicket ticket = MaintenanceTicket.getWithId(ticketId);
    HotelStaff assignee = ticket.getTicketFixer();
    String staffName = assignee.getEmail();

    assertEquals(string2, staffName);
  }

  /**
   * Checks the number of tickets in the system.
   * 
   * @author Group 5
   * @param string the expected number of tickets in the system.
   */
  @Then("the number of tickets in the system shall be {string}")
  public void the_number_of_tickets_in_the_system_shall_be(String string) {
    List<MaintenanceTicket> tickets = assetPlus.getMaintenanceTickets();
    int realSize = tickets.size();
    int expectedSize = Integer.parseInt(string);

    assertEquals(expectedSize, realSize);
  }

  /**
   * Checks for the presence of the maintenance ticket submited by the system.
   * 
   * @author Group 5
   * @param dataTable table containing a list of all maintenance tickets in the system.
   */
  @Then("the following maintenance tickets shall be presented")
  public void the_following_maintenance_tickets_shall_be_presented(
      io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> maintenanceTickets = dataTable.asMaps();
    for (Map<String, String> ticket : maintenanceTickets) {
      int id = Integer.parseInt(ticket.get("id"));
      boolean ticketFound = false;
      TOMaintenanceTicket comparingTicket = null;
      for (TOMaintenanceTicket toTICKET : listTickets) {
        if (toTICKET.getId() == id) {
          comparingTicket = toTICKET;
          ticketFound = true;
        }
      }
      assertTrue(ticketFound);
      String email = ticket.get("ticketRaiser");
      String raisedDate = ticket.get("raisedOnDate");
      String description = ticket.get("description");
      String purchaseDate = ticket.get("purchaseDate");
      String assetName = ticket.get("assetName");
      if (assetName != null) {
        int expectLifeSpan = Integer.parseInt(ticket.get("expectLifeSpan"));
        int floorNumber = Integer.parseInt(ticket.get("floorNumber"));
        int roomNumber = Integer.parseInt(ticket.get("roomNumber"));
        assertEquals(roomNumber, comparingTicket.getRoomNumber());
        assertEquals(floorNumber, comparingTicket.getFloorNumber());
        assertEquals(expectLifeSpan, comparingTicket.getExpectLifeSpanInDays());
        assertEquals(purchaseDate, String.valueOf(comparingTicket.getPurchaseDate()));
      }
      assertEquals(email, comparingTicket.getRaisedByEmail());
      assertEquals(raisedDate, String.valueOf(comparingTicket.getRaisedOnDate()));
      assertEquals(description, comparingTicket.getDescription());
      assertEquals(assetName, comparingTicket.getAssetName());
    }
  }

  /**
   * Verifies the notes of a given ticket. Test fails if a ticket's notes does not match the
   * expected.
   * 
   * @author Group 5
   * @param string TicketID in string form.
   * @param dataTable A table containing a list of notes.
   */
  @Then("the ticket with id {string} shall have the following notes")
  public void the_ticket_with_id_shall_have_the_following_notes(String string,
      io.cucumber.datatable.DataTable dataTable) {
    MaintenanceTicket ticket = MaintenanceTicket.getWithId(Integer.parseInt(string));
    List<MaintenanceNote> ticketMaintenanceNotes = ticket.getTicketNotes();
    List<Map<String, String>> maintenanceNotes = dataTable.asMaps();
    int i = 0;
    for (Map<String, String> note : maintenanceNotes) {
      String noteTaker = note.get("noteTaker");
      String addedOnDate = note.get("addedOnDate");
      String description = note.get("description");
      MaintenanceNote ticketNote = ticketMaintenanceNotes.get(i);
      String ticketNoteTaker = ticketNote.getNoteTaker().getEmail();
      String ticketAddedOnDate = ticketNote.getDate().toString();
      String ticketDescription = ticketNote.getDescription();
      assertEquals(noteTaker, ticketNoteTaker);
      assertEquals(addedOnDate, ticketAddedOnDate);
      assertEquals(description, ticketDescription);
      i++;
    }
  }

  /**
   * Verifies that the ticket has no notes. Test fails if the ticket contains notes.
   * 
   * @author Group 5
   * @param string TicketID in string form
   */
  @Then("the ticket with id {string} shall have no notes")
  public void the_ticket_with_id_shall_have_no_notes(String string) {
    int id = Integer.parseInt(string);
    MaintenanceTicket ticket = MaintenanceTicket.getWithId(id);
    List<MaintenanceNote> notes = ticket.getTicketNotes();
    assertTrue(notes == null || notes.isEmpty());
  }

  /**
   * Verifies the images linked to a ticket.
   * 
   * @author Group 5
   * @param string ticketID in string form
   * @param dataTable Table containing a list of images.
   */
  @Then("the ticket with id {string} shall have the following images")
  public void the_ticket_with_id_shall_have_the_following_images(String string,
      io.cucumber.datatable.DataTable dataTable) {
    MaintenanceTicket ticket = MaintenanceTicket.getWithId(Integer.parseInt(string));
    List<TicketImage> ticketMaintenanceimages = ticket.getTicketImages();
    List<Map<String, String>> images = dataTable.asMaps();
    for (Map<String, String> image : images) {
      String imageurl = image.get("imageUrl");
      boolean image_found = false;
      for (TicketImage image1 : ticketMaintenanceimages) {
        if (image1.getImageURL().equals(imageurl)) {
          image_found = true;
        }
      }
      assertTrue(image_found);
    }
  }

  /**
   * Verifies if the chosen ticket contains any images. Test fails if it does contain images.
   * 
   * @author Group 5
   * @param string
   */
  @Then("the ticket with id {string} shall have no images")
  public void the_ticket_with_id_shall_have_no_images(String string) {
    int id = Integer.parseInt(string);
    MaintenanceTicket ticket = MaintenanceTicket.getWithId(id);
    List<TicketImage> images = ticket.getTicketImages();
    assertTrue(images != null && images.isEmpty());
  }
}

