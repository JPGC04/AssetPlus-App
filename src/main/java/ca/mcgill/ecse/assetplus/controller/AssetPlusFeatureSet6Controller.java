package ca.mcgill.ecse.assetplus.controller;

import java.util.List;
import java.util.ArrayList;
import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
import ca.mcgill.ecse.assetplus.model.AssetPlus;
import ca.mcgill.ecse.assetplus.model.Employee;
import ca.mcgill.ecse.assetplus.model.Guest;
import ca.mcgill.ecse.assetplus.model.MaintenanceNote;
import ca.mcgill.ecse.assetplus.model.MaintenanceTicket;
import ca.mcgill.ecse.assetplus.model.SpecificAsset;
import ca.mcgill.ecse.assetplus.model.TicketImage;
import java.sql.Date;

public class AssetPlusFeatureSet6Controller {

  public static void deleteEmployeeOrGuest(String email) {
    
    if (getEmployeeByEmail(email) == null && getGuestByEmail(email) == null) {
      throw new IllegalArgumentException("Invalid email address");
    } 
    
    if (email.contains("@ap.com")){
      Employee employee = getEmployeeByEmail(email);
      employee.delete();
    } else {
      Guest guest = getGuestByEmail(email);
      guest.delete();
    }
    
      
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
  
  // returns all tickets
  public static List<TOMaintenanceTicket> getTickets() {
    AssetPlus assetplus = AssetPlusApplication.getAssetPlus();
    List<MaintenanceTicket> maintenanceTickets = assetplus.getMaintenanceTickets();
    List<TOMaintenanceTicket> TOMaintenanceTickets = null;
    for (MaintenanceTicket t : maintenanceTickets) {
      TOMaintenanceTickets.add(createTOMaintenanceTicket(t));
    }
    return TOMaintenanceTickets;
  }
  private static TOMaintenanceTicket createTOMaintenanceTicket(MaintenanceTicket t) {
    try {  
      int aId = t.getId();
      Date aRaisedOnDate = t.getRaisedOnDate();
      String aDescription = t.getDescription();
      String aRaisedByEmail = t.getTicketRaiser().getEmail();
      SpecificAsset specificAsset = t.getAsset();
      String aAssetName = t.getAsset().getAssetType().getName();
      int aExpectLifeSpanInDays = specificAsset.getAssetType().getExpectedLifeSpan();
      Date aPurchaseDate = specificAsset.getPurchaseDate();
      int aFloorNumber = specificAsset.getFloorNumber();
      int aRoomNumber = specificAsset.getRoomNumber();
      List<String> aImageURLs = null;
      List<TicketImage> ticketImages = t.getTicketImages();
      for (TicketImage i : ticketImages) {
        aImageURLs.add(i.getImageURL());
      }
      List<MaintenanceNote> allNotes = t.getTicketNotes();
      
      ArrayList<TOMaintenanceNote> TOmaintenanceNotes = new ArrayList<TOMaintenanceNote>();
      for (MaintenanceNote n : allNotes) {
        TOmaintenanceNotes.add(createTOMaintenanceNote(n));
      }
      
      TOMaintenanceTicket aTOMaintenanceTicket = new TOMaintenanceTicket(aId, aRaisedOnDate, aDescription, aRaisedByEmail, aAssetName, aExpectLifeSpanInDays,  aPurchaseDate, aFloorNumber, aRoomNumber, aImageURLs, TOmaintenanceNotes.toArray(new TOMaintenanceNote[TOmaintenanceNotes.size()]));
      return aTOMaintenanceTicket;
    } catch (Exception e) {
      return null;
    } 
  }

  private static TOMaintenanceNote createTOMaintenanceNote(MaintenanceNote maintenanceNote) {
    try {  
      Date aDate = maintenanceNote.getDate();
      String aDescription = maintenanceNote.getDescription();
      String aNoteTakerEmail = maintenanceNote.getNoteTaker().getEmail();
      return new TOMaintenanceNote(aDate, aDescription, aNoteTakerEmail);
    } catch (Exception e) {
      return null;
    }
  }

  
}
