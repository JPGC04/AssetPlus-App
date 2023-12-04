package ca.mcgill.ecse.assetplus.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
import ca.mcgill.ecse.assetplus.model.AssetPlus;
import ca.mcgill.ecse.assetplus.model.HotelStaff;
import ca.mcgill.ecse.assetplus.model.MaintenanceNote;
import ca.mcgill.ecse.assetplus.model.MaintenanceTicket;
import ca.mcgill.ecse.assetplus.model.User;
import ca.mcgill.ecse.assetplus.persistence.AssetPlusPersistence;

/**
 * AssetPlusFeatureSet7Controller is a set of controller methods that allow us to add, update, and 
 * delete maintenance notes
 * 
 * @author NizarKheireddine
 * @version ECSE 223 - Group Project Iteration 3
 * @since ECSE 223 - Group Project Iteration 2a
 */
public class AssetPlusFeatureSet7Controller {
  private AssetPlusFeatureSet7Controller() {}

  private static AssetPlus assetPlus = AssetPlusApplication.getAssetPlus();

  /**
   * <p>Adds a maintenance note to a ticket using the ticket ID.</p>
   * 
   * @param date date the note is added.
   * @param description description of the note.
   * @param ticketID id of the ticket.
   * @param email email of the employee that added the note.
   * @return error message if there is any.
   */
  public static String addMaintenanceNote(Date date, String description, int ticketID,
      String email) {
    if (description == "" || description == null) {
      String s = "Ticket description cannot be empty";
      return s;
    }
    MaintenanceTicket ticket = MaintenanceTicket.getWithId(ticketID);
    if (ticket == null) {
      return "Ticket does not exist";
    }
    HotelStaff staff = (HotelStaff) User.getWithEmail(email);
    if (staff == null) {
      return "Hotel staff does not exist";
    }
    try {
      ticket.addTicketNote(new MaintenanceNote(date, description, ticket, staff));
      AssetPlusPersistence.save();
      return "";
    } catch (Exception e) {
      return "Error: " + e;
    }
  }

  /**
   * <p>Updates a maintenance note to a ticket using the ticket ID.</p>
   * 
   * @param newDate date the note is updated.
   * @param newDescription description of the updated note.
   * @param ticketID id of the ticket.
   * @param newEmail email of the employee that updated the note.
   * @param index index of the note.
   * @return error message if there is any.
   */
  public static String updateMaintenanceNote(int ticketID, int index, Date newDate,
      String newDescription, String newEmail) {
    MaintenanceTicket ticket = MaintenanceTicket.getWithId(ticketID);
    if (ticket == null) {
      return "Ticket does not exist";
    }
    HotelStaff staff = (HotelStaff) User.getWithEmail(newEmail);
    int number_of_notes = ticket.getTicketNotes().size();
    System.out.println(ticket.getTicketNotes().size() + " is the size of ticket");
    System.out.println(index);
    System.out.println(ticketID);
    if (index >= number_of_notes) {
      return "Note does not exist";
    }
    MaintenanceNote maintenanceNote = ticket.getTicketNotes().get(index);
    if (newDescription == "" || newDescription == null) {
      String s = "Ticket description cannot be empty";
      return s;
    }
    if (maintenanceNote == null) {
      return "Note does not exist";
    }
    if (staff == null) {
      return "Hotel staff does not exist";
    }

    try {
      maintenanceNote.setDate(newDate);
      maintenanceNote.setNoteTaker(staff);
      maintenanceNote.setDescription(newDescription);
      AssetPlusPersistence.save();
      return "";
    } catch (Exception e) {
      return "Error: " + e;
    }
  }

  /**
   * <p>Deletes a maintenance note from a ticket using the ticket ID.</p>
   * 
   * @param ticketID id of the ticket.
   * @param index index of the note.
   */
  public static void deleteMaintenanceNote(int ticketID, int index) {
    MaintenanceTicket ticket = MaintenanceTicket.getWithId(ticketID);
    if (ticket == null) {
      return;
    }
    int size = ticket.getTicketNotes().size();
    if (size <= index || ticket == null) {
      return;
    }
    MaintenanceNote maintenanceNote = ticket.getTicketNotes().get(index);
    try {
      if (maintenanceNote != null) {
        maintenanceNote.delete();
        AssetPlusPersistence.save();
      }
    } catch (Exception e) {
      return;
    }
  }

  public static List<AMaintenanceNote> getSpecificNotes(int i) {
    try {
      MaintenanceTicket ticket = MaintenanceTicket.getWithId(i);
      System.out.println(ticket.getDescription());

      List<MaintenanceNote> notes = ticket.getTicketNotes();
      List<AMaintenanceNote> res = new ArrayList<>();
      int ind = 0;
      for (MaintenanceNote note: notes) {
        res.add(new AMaintenanceNote(note.getDescription(), String.valueOf(note.getDate()), note.getNoteTaker().getEmail(), ind));
        ind++;
      }
      return res;
    } catch (Exception e) {

    }
    return null;
  }

  public static class AMaintenanceNote {
    private String description;
    private String date;
    private String noteTaker;
    private int noteIndex;

    public AMaintenanceNote(String description, String date, String noteTaker, int noteIndex) {
        this.description = description;
        this.date = date;
        this.noteTaker = noteTaker;
        this.noteIndex = noteIndex;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getNoteTaker() {
        return noteTaker;
    }

    public int getNoteIndex() {
        return noteIndex;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setNoteTaker(String noteTaker) {
        this.noteTaker = noteTaker;
    }

    public void setNoteIndex(int noteIndex) {
        this.noteIndex = noteIndex;
    }
}


}
