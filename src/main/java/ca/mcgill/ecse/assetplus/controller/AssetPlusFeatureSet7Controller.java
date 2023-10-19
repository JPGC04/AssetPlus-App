package ca.mcgill.ecse.assetplus.controller;

import java.sql.Date;
import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
import ca.mcgill.ecse.assetplus.model.AssetPlus;
import ca.mcgill.ecse.assetplus.model.HotelStaff;
import ca.mcgill.ecse.assetplus.model.MaintenanceNote;
import ca.mcgill.ecse.assetplus.model.MaintenanceTicket;
/**
 @NizarKheireddine
 */
public class AssetPlusFeatureSet7Controller {
  private static AssetPlus assetplus=AssetPlusApplication.getAssetPlus();

  private AssetPlusFeatureSet7Controller() {}

  /**
   * <p>Adds a maintenance note to a ticket using the ticket ID.
   * </p>
   * @param date date the note is added.
   * @param description description of the note.
   * @param ticketID id of the ticket.
   * @param email email of the employee that added the note.
   * @return error message if there is any.
   */
  public static String addMaintenanceNote(Date date, String description, int ticketID,
      String email) {
    if (description==""||description==null){
      String s = "Description empty.";
      throw new IllegalArgumentException(s);
    }
    MaintenanceTicket ticket=Utils.getMaintenanceTicketbyID(ticketID);
    HotelStaff staff=Utils.getHotelStaffbyemail(email);
    if(ticket==null||staff==null){
      String s = "Ticket/HotelStaff not found.";
      throw new IllegalArgumentException(s);
    }
    ticket.addTicketNote(new MaintenanceNote(date,description,ticket,staff));
    return "";
  }

  // index starts at 0
  /**
   * <p>Updates a maintenance note to a ticket using the ticket ID.
   * </p>
   * @param newDate date the note is updated.
   * @param newDescription description of the updated note.
   * @param ticketID id of the ticket.
   * @param newEmail email of the employee that updated the note.
   * @param index index of the note.
   * @return error message if there is any.
   */
  public static String updateMaintenanceNote(int ticketID, int index, Date newDate,
      String newDescription, String newEmail) {
    MaintenanceNote maintenanceNote=Utils.getMaintenanceTicketbyID(ticketID).getTicketNotes().get(index);
    if (newDescription==""||newDescription==null){
      String s = "Description empty.";
      throw new IllegalArgumentException(s);
    }
    if(maintenanceNote==null){
      throw new IllegalArgumentException("Maintenance note not found.");
    }
    maintenanceNote.setDate(newDate);
    maintenanceNote.setNoteTaker(Utils.getHotelStaffbyemail(newEmail));
    maintenanceNote.setDescription(newDescription);
    return "";
  }

  // index starts at 0
  /**
   * <p>Deletes a maintenance note from a ticket using the ticket ID.
   * </p>
   * @param ticketID id of the ticket.
   * @param index index of the note.
   * @return error message if there is any.
   */
  public static void deleteMaintenanceNote(int ticketID, int index) {
    MaintenanceNote maintenanceNote=Utils.getMaintenanceTicketbyID(ticketID).getTicketNotes().get(index);
    if(maintenanceNote!=null){
      maintenanceNote.delete();
    }
    throw new RuntimeException("Ticket/Note not found.");
  }

}
