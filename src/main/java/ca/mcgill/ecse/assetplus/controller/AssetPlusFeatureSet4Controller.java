package ca.mcgill.ecse.assetplus.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
import ca.mcgill.ecse.assetplus.model.*;
import ca.mcgill.ecse.assetplus.persistence.AssetPlusPersistence;

/**
 * AssetPlusFeatureSet4Controller is the main entity that we'll be using to add, update, and delete
 * MaintenanceTicket
 * 
 * @author Yazid Asselah -- Dramocrystal
 * @version ECSE 223 - Group Project Iteration 3
 * @since ECSE 223 - Group Project Iteration 2a
 */
public class AssetPlusFeatureSet4Controller {

  private static AssetPlus assetplus = AssetPlusApplication.getAssetPlus();

  /**
   * Adds an MainteanceTicket type with the given id, date, description, email, assetnumber. Written
   * by: Yazid Asselah
   * 
   * @param id an int corresponding to the id of the ticket
   * @param raisedOnDate a Date object corresponding to the date of when the ticket was created
   * @param description a String that will contain the description of the ticket
   * @param email a String that will enable us to retrace the raiser of the ticket
   * @param assetNumber an int that will enable us to retrace the specific asset, if -1 it means no
   *        asset
   * @return a string that indicates the error, if no error returns an empty string
   */
  public static String addMaintenanceTicket(int id, Date raisedOnDate, String description,
      String email, int assetNumber) {
    if (description.isEmpty() || description == null) {
      return "Ticket description cannot be empty";
    }

    User ticketRaiser = User.getWithEmail(email);
    if (ticketRaiser == null) {
      return "The ticket raiser does not exist";
    }

    SpecificAsset specificAsset = null;
    if (assetNumber != -1) {
      specificAsset = SpecificAsset.getWithAssetNumber(assetNumber);
      if (specificAsset == null) {
        return "The asset does not exist";
      }
    }
    try {
      MaintenanceTicket newMaintenanceTicket =
          new MaintenanceTicket(id, raisedOnDate, description, assetplus, ticketRaiser);
      if (specificAsset != null) {
        newMaintenanceTicket.setAsset(specificAsset);
      }
      AssetPlusPersistence.save();
    } catch (RuntimeException e) {
      return "Ticket id already exists";
    }
    return "";
  }

  /**
   * Updates a MainteanceTicket type with the given id, date, description, email, assetnumber.
   * Written by: Yazid Asselah
   * 
   * @param id an int corresponding to the id of the ticket
   * @param newRaisedOnDate a Date object corresponding to the date of when the ticket was updated
   * @param newDescription a String that will contain the new description of the ticket
   * @param newEmail a String that will enable us to retrace the editor of the ticket
   * @param newAssetNumber an int that will enable us to retrace the specific asset, if -1 it means
   *        no asset
   * @return a string that indicates the error, if no error returns an empty string
   */
  public static String updateMaintenanceTicket(int id, Date newRaisedOnDate, String newDescription,
      String newEmail, int newAssetNumber) {
    if (newDescription.isEmpty() || newDescription == null) {
      return "Ticket description cannot be empty";
    }
    User newTicketRaiser = User.getWithEmail(newEmail);
    if (newTicketRaiser == null) {
      return "The ticket raiser does not exist";
    }

    SpecificAsset newSpecificAsset = null;
    if (newAssetNumber != -1) {
      newSpecificAsset = SpecificAsset.getWithAssetNumber(newAssetNumber);
      if (newSpecificAsset == null) {
        return "The asset does not exist";
      }
    }

    MaintenanceTicket ticket = MaintenanceTicket.getWithId(id);
    if (ticket == null) {
      return "ticket not found";
    }

    try {
      ticket.setDescription(newDescription);
      ticket.setRaisedOnDate(newRaisedOnDate);
      ticket.setTicketRaiser(newTicketRaiser);
      ticket.setTicketRaiser(newTicketRaiser);
      if (newAssetNumber == -1) {
        ticket.getAsset().delete();
      }
      ticket.setAsset(newSpecificAsset);
      AssetPlusPersistence.save();
      return "";
    } catch (Exception e) {
      return "Error: " + e;
    }

  }

  /**
   * Deletes a MainteanceTicket type with the given id Written by: Yazid Asselah
   * 
   * @param id an int corresponding to the id of the ticket
   */
  public static void deleteMaintenanceTicket(int id) {
    try {
      MaintenanceTicket ticket = MaintenanceTicket.getWithId(id);
      if (ticket != null) {
        ticket.delete();
        AssetPlusPersistence.save();
      }
    } catch (Exception e) {
      return;
    }

  }

  /**
   * Assigns Maintenance Ticket within the input constraints
   * 
   * @author Group 5
   * @param id an int corresponding to the id of the ticket
   * @param employeeEmail a String containing email of employee to assign to
   * @param aTimeToResolve a String containing the time requirement to resolve the ticket
   * @param aPriority a String containing the priority of the ticket
   * @param requiresApproval a boolean which indicates whether the manager needs to confirm after
   *        ticket is completed
   * @return a string that indicates the error, if no error returns an empty string
   */
  public static String assignMaintenanceTicket(int id, String employeeEmail, String aTimeToResolve,
      String aPriority, boolean requiresApproval) {
    MaintenanceTicket ticket = MaintenanceTicket.getWithId(id);
    String error = "";
    if (ticket == null) {
      return "Maintenance ticket does not exist.";
    }
    if (ticket.getStatus() == MaintenanceTicket.Status.Resolved) {
      return "Cannot assign a maintenance ticket which is resolved.";
    }

    HotelStaff ticketFixer = (HotelStaff) User.getWithEmail(employeeEmail);
    if (ticketFixer == null) {
      return "Staff to assign does not exist.";
    }
    Manager fixApprover = (Manager) User.getWithEmail("manager@ap.com");

    if (fixApprover == null) {
      return "manager not found";
    }
    if (ticket.getStatus() == MaintenanceTicket.Status.Closed) {
      return "Cannot assign a maintenance ticket which is closed.";
    }
    if (ticket.getStatus() == MaintenanceTicket.Status.InProgress) {
      return "Cannot assign a maintenance ticket which is in progress.";
    }
    if (ticket.getStatus() == MaintenanceTicket.Status.Assigned) {
      return "The maintenance ticket is already assigned.";
    }

    if (error.isEmpty()) {
      ticket.assignTicket(id, employeeEmail, aTimeToResolve, aPriority, requiresApproval);
      AssetPlusPersistence.save();
    } else {
      return error;
    }
    return error;

  }

  /**
   * Starts the Maintenance Ticket with the given id
   * 
   * @author Group 5
   * @param id an int corresponding to the id of the ticket
   * @return a string that indicates the error, if no error returns an empty string
   */
  public static String startTicketProgress(int id) {
    MaintenanceTicket ticket = MaintenanceTicket.getWithId(id);
    if (ticket == null) {
      return "Maintenance ticket does not exist.";
    }
    if (ticket.getStatus() == MaintenanceTicket.Status.Resolved) {
      return "Cannot start a maintenance ticket which is resolved.";
    }

    if (ticket.getStatus() == MaintenanceTicket.Status.Closed) {
      return "Cannot start a maintenance ticket which is closed.";
    }
    if (ticket.getStatus() == MaintenanceTicket.Status.InProgress) {
      return "The maintenance ticket is already in progress.";
    }
    if (ticket.getStatus() == MaintenanceTicket.Status.Open) {
      return "Cannot start a maintenance ticket which is open.";
    }
    ticket.startProgress();
    AssetPlusPersistence.save();

    return "";
  }

  /**
   * Sets the Maintenance Ticket with the given id as complete
   * 
   * @author Group 5
   * @param id an int corresponding to the id of the ticket
   * @return a string that indicates the error, if no error returns an empty string
   */
  public static String completeTicket(int id) {
    MaintenanceTicket ticket = MaintenanceTicket.getWithId(id);
    if (ticket == null) {
      return "Maintenance ticket does not exist.";
    }
    if (ticket.getStatus() == MaintenanceTicket.Status.Resolved) {
      return "The maintenance ticket is already resolved.";
    }

    if (ticket.getStatus() == MaintenanceTicket.Status.Closed) {
      return "The maintenance ticket is already closed.";
    }
    if (ticket.getStatus() == MaintenanceTicket.Status.Open) {
      return "Cannot complete a maintenance ticket which is open.";
    }
    if (ticket.getStatus() == MaintenanceTicket.Status.Assigned) {
      return "Cannot complete a maintenance ticket which is assigned.";
    }
    ticket.Resolve();
    AssetPlusPersistence.save();
    return "";
  }

  /**
   * Sets the Maintenance Ticket with the given id as approved
   * 
   * @author Group 5
   * @param id an int corresponding to the id of the ticket
   * @return a string that indicates the error, if no error returns an empty string
   */
  public static String approveTicket(int id) {

    MaintenanceTicket ticket = MaintenanceTicket.getWithId(id);

    if (ticket == null) {
      return "Maintenance ticket does not exist.";
    }

    MaintenanceTicket.Status status = ticket.getStatus();
    if (status == MaintenanceTicket.Status.Open) {
      return "Cannot approve a maintenance ticket which is open.";
    }

    if (status == MaintenanceTicket.Status.Assigned) {
      return "Cannot approve a maintenance ticket which is assigned.";
    }

    if (status == MaintenanceTicket.Status.Closed) {
      return "The maintenance ticket is already closed.";
    }

    if (status == MaintenanceTicket.Status.InProgress) {
      return "Cannot approve a maintenance ticket which is in progress.";
    }

    ticket.approve();
    AssetPlusPersistence.save();
    return "";
  }

  /**
   * Sets the Maintenance Ticket from input as disapproved
   * 
   * @author Group 5
   * @param id an int corresponding to the id of the ticket
   * @param aDate a Date correspinding to the date of the ticket
   * @param aDescription a String containing the description of the ticket
   * @return a string that indicates the error, if no error returns an empty string
   */
  public static String disapproveTicket(int id, Date aDate, String aDescription) {

    MaintenanceTicket ticket = MaintenanceTicket.getWithId(id);

    if (ticket == null) {
      return "Maintenance ticket does not exist.";
    }

    MaintenanceTicket.Status status = ticket.getStatus();
    if (status == MaintenanceTicket.Status.Open) {
      return "Cannot disapprove a maintenance ticket which is open.";
    }

    if (status == MaintenanceTicket.Status.Assigned) {
      return "Cannot disapprove a maintenance ticket which is assigned.";
    }

    if (status == MaintenanceTicket.Status.Closed) {
      return "Cannot disapprove a maintenance ticket which is closed.";
    }

    if (status == MaintenanceTicket.Status.InProgress) {
      return "Cannot disapprove a maintenance ticket which is in progress.";
    }

    ticket.disaprove(id, aDate, aDescription);
    AssetPlusPersistence.save();
    return "";
  }



  public static List<MaintenanceTicketString> getSpecificTickets() {
    List<MaintenanceTicket> tickets = assetplus.getMaintenanceTickets();
    List<MaintenanceTicketString> result = new ArrayList<>(); // javafx

    for (MaintenanceTicket ticket : tickets) {
      try {
        //String fixer = ticket.getTicketFixer().getEmail();
        String assetNumber = "";
        if (ticket.getAsset() == null) {
          assetNumber = "None";
        } else {
          assetNumber = String.valueOf(ticket.getAsset().getAssetNumber());
        }
        if (ticket.getAsset() == null) {
          String assetType = "None";
          String room = "";
          String floor = "";
          String lifespan = "";
          String purchaseDate = "";

          String thefixer = "";
          HotelStaff theemail = ticket.getTicketFixer();
          if (theemail != null) {
            thefixer = theemail.getEmail();
          }
          result.add(new MaintenanceTicketString(String.valueOf(ticket.getId()),
              String.valueOf(ticket.getRaisedOnDate()),
              String.valueOf(ticket.getTicketRaiser().getEmail()), ticket.getDescription(),
              ticket.getStatusFullName(), assetNumber, room, floor, purchaseDate, lifespan,
              assetType, thefixer));

        } else {
          String room = String.valueOf(ticket.getAsset().getRoomNumber());
          String floor = String.valueOf(ticket.getAsset().getFloorNumber());

          String assetType = ticket.getAsset().getAssetType().getName();
          String lifespan = String.valueOf(ticket.getAsset().getAssetType().getExpectedLifeSpan());
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          String purchaseDate = sdf.format(ticket.getAsset().getPurchaseDate());

          String thefixer = "";
          HotelStaff theemail = ticket.getTicketFixer();
          if (theemail != null) {
            thefixer = theemail.getEmail();
          }
          result.add(new MaintenanceTicketString(String.valueOf(ticket.getId()),
              String.valueOf(ticket.getRaisedOnDate()),
              String.valueOf(ticket.getTicketRaiser().getEmail()), ticket.getDescription(),
              ticket.getStatusFullName(), assetNumber, room, floor, purchaseDate, lifespan,
              assetType, thefixer));

        }
      } catch (Exception e) {
        System.out.println(e);
      }
    }

    return result;
  }


  public static String getTicketStatus(int id) {
    MaintenanceTicket ticket = MaintenanceTicket.getWithId(id);
    return ticket.getStatus().toString();



  }


  public static boolean getRequiresApproval(int id) {
    MaintenanceTicket ticket = MaintenanceTicket.getWithId(id);
    return ticket.getRequiresApproval();
  }

  public static class MaintenanceTicketString {
    private String id;
    private String date;
    private String ticketRaiser;
    private String description;
    private String status;
    private String asset;
    private String fixer = "";
    private String priorityLevel;
    private String timeToResolve;
    private boolean requiresApproval;
    private String room;
    private String floor;
    private String purchaseDate;
    private String lifespan;
    private String assetType;

    public MaintenanceTicketString(String id, String date, String ticketRaiser, String description,
            String status, String asset, String room, String floor, String purchaseDate,
            String lifespan, String assetType, String fixer) {
        this.id = id;
        this.date = date;
        this.ticketRaiser = ticketRaiser;
        this.description = description;
        this.status = status;
        this.asset = asset;
        this.room = room;
        this.floor = floor;
        this.purchaseDate = purchaseDate;
        this.lifespan = lifespan;
        this.assetType = assetType;
        this.fixer = fixer;

    }

    // Setters
    public String getAssetType() {
        return assetType;
    };

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    };

    public void setRequiresApproval(boolean requiresApproval) {
        this.requiresApproval = requiresApproval;
    };

    public boolean getRequiresApproval() {
        return this.requiresApproval;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTicketRaiser(String ticketRaiser) {
        this.ticketRaiser = ticketRaiser;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public void setFixer(String fixer) {
        this.fixer = fixer;
    }

    public void setPriorityLevel(String level) {
        this.priorityLevel = level;
    }

    public void setTimeToResolve(String time) {
        this.timeToResolve = time;
    }

    public String getPriorityLevel() {
        return this.priorityLevel;
    }

    public String getTimeToResolve() {
        return this.timeToResolve;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getTicketRaiser() {
        return ticketRaiser;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public String getAsset() {
        return asset;
    }

    public String getFixer() {
        return fixer;
    }
    // Setters for new fields

    public void setRoom(String room) {
        this.room = room;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setLifespan(String lifespan) {
        this.lifespan = lifespan;
    }

    // Getters for new fields

    public String getRoom() {
        return room;
    }

    public String getFloor() {
        return floor;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public String getLifespan() {
        return lifespan;
    }
}
}
