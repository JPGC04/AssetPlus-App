package ca.mcgill.ecse.assetplus.controller;

import java.util.List;
import javax.management.RuntimeErrorException;
import java.util.ArrayList;
import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
import ca.mcgill.ecse.assetplus.model.AssetPlus;
import ca.mcgill.ecse.assetplus.model.Employee;
import ca.mcgill.ecse.assetplus.model.Guest;
import ca.mcgill.ecse.assetplus.model.MaintenanceNote;
import ca.mcgill.ecse.assetplus.model.MaintenanceTicket;
import ca.mcgill.ecse.assetplus.model.SpecificAsset;
import ca.mcgill.ecse.assetplus.model.TicketImage;
import ca.mcgill.ecse.assetplus.persistence.AssetPlusPersistence;
import java.sql.Date;

/**
 * AssetPlusFeatureSet6Controller is a set of controller methods that allow us to delete an employee
 * or a guest given their email, and get all maintenancetickets
 * 
 * @author Cleo Tang
 * @version ECSE 223 - Group Project Iteration 3
 * @since ECSE 223 - Group Project Iteration 2a
 */
public class AssetPlusFeatureSet6Controller {

  /**
   * delete an employee or a guest given their email.
   * Written by: Cleo Tang
   * 
   * @param email a string of the email of the user aimed to be deleted
   * @return null, void method
   * @throws IllegalArgumentException if the email provided is invalid or person with the email is
   *         not in the system.
   */
  public static void deleteEmployeeOrGuest(String email) {
    try {
      if (getEmployeeByEmail(email) == null && getGuestByEmail(email) == null) {
        return;
      }
      if (email.contains("@ap.com")) {
        Employee employee = getEmployeeByEmail(email);
        employee.delete();
      } else {
        Guest guest = getGuestByEmail(email);
        guest.delete();
      }
      AssetPlusPersistence.save();
    } catch (RuntimeException e) {
      return;
    }


  }

  /**
   * get the particular employee with the specified email.
   * Written by: Cleo Tang
   * 
   * @param email a string of the employee's email
   * @return an Employee required
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
   * get the particular guest with the specified email.
   * Written by: Cleo Tang
   * 
   * @param email a string of the guest's email
   * @return a Guest matched
   */
  private static Guest getGuestByEmail(String email) {
    try {
      AssetPlus assetPlus = AssetPlusApplication.getAssetPlus();
      List<Guest> guests = assetPlus.getGuests();
      for (Guest g : guests) {
        if (g.getEmail().equals(email)) {
          return g;
        }
      }
      return null;
    } catch (RuntimeErrorException e) {
      return null;
    }
  }

  /**
   * Gets all maintenancetickets
   * Written by: Cleo Tang
   * 
   * @return a list of Transfer Obeject of MaintenanceTicket
   */
  public static List<TOMaintenanceTicket> getTickets() {
    try {
      AssetPlus assetplus = AssetPlusApplication.getAssetPlus();
      List<MaintenanceTicket> maintenanceTickets = assetplus.getMaintenanceTickets();
      List<TOMaintenanceTicket> TOMaintenanceTickets = new ArrayList<TOMaintenanceTicket>();
      for (MaintenanceTicket t : maintenanceTickets) {
        TOMaintenanceTickets.add(createTOMaintenanceTicket(t));
      }
      AssetPlusPersistence.save();
      return TOMaintenanceTickets;
    } catch (RuntimeException e) {
      return null;
    }
  }

  /**
   * Initialize a transfer obejct of MaintenanceTicket.
   * Written by: Cleo Tang
   * 
   * @param t a MiantenanceTicket
   * @return TOMaintenanceTicket that is a transfer object of MaintenanceTicket t
   */
  private static TOMaintenanceTicket createTOMaintenanceTicket(MaintenanceTicket t) {
    try {
      int aId = t.getId();
      Date aRaisedOnDate = t.getRaisedOnDate();
      String aDescription = t.getDescription();
      String aRaisedByEmail = t.getTicketRaiser().getEmail();
      SpecificAsset specificAsset = (t.getAsset() != null) ? t.getAsset() : null;
      String aAssetName = (t.getAsset() != null) ? t.getAsset().getAssetType().getName() : null;
      int aExpectLifeSpanInDays =
          (specificAsset != null) ? specificAsset.getAssetType().getExpectedLifeSpan() : -1;
      Date aPurchaseDate = (specificAsset != null) ? specificAsset.getPurchaseDate() : null;
      int aFloorNumber = (specificAsset != null) ? specificAsset.getFloorNumber() : -1;
      int aRoomNumber = (specificAsset != null) ? specificAsset.getRoomNumber() : -1;
      List<String> aImageURLs = new ArrayList<String>();
      List<TicketImage> ticketImages = t.getTicketImages();
      for (TicketImage i : ticketImages) {
        aImageURLs.add(i.getImageURL());
      }
      List<MaintenanceNote> allNotes = t.getTicketNotes();

      TOMaintenanceNote[] TOmaintenanceNotes = new TOMaintenanceNote[allNotes.size()];
      for (int i = 0; i < allNotes.size(); i++) {
        TOmaintenanceNotes[i] = createTOMaintenanceNote(allNotes.get(i));
      }
      TOMaintenanceTicket aTOMaintenanceTicket = new TOMaintenanceTicket(aId, aRaisedOnDate,
          aDescription, aRaisedByEmail, aAssetName, aRaisedByEmail, aDescription, aRaisedByEmail,
          false, aAssetName, aExpectLifeSpanInDays, aPurchaseDate, aFloorNumber, aRoomNumber,
          aImageURLs, TOmaintenanceNotes);
      AssetPlusPersistence.save();
      return aTOMaintenanceTicket;
    } catch (Exception e) {
      throw e;
    }
  }

  /**
   * Initialize a transfer object of MaintenanceNote.
   * Written by: Cleo Tang
   * 
   * @param maintenanceNote a MaintenanceNote
   * @return TOMaintenanceNote that is a transfer object of MaintenanceNote
   */
  private static TOMaintenanceNote createTOMaintenanceNote(MaintenanceNote maintenanceNote) {
    try {
      Date aDate = maintenanceNote.getDate();
      String aDescription = maintenanceNote.getDescription();
      String aNoteTakerEmail = maintenanceNote.getNoteTaker().getEmail();
      AssetPlusPersistence.save();
      return new TOMaintenanceNote(aDate, aDescription, aNoteTakerEmail);
    } catch (Exception e) {
      return null;
    }
  }
}
