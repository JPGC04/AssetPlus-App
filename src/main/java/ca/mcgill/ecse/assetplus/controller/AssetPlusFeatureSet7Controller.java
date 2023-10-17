package ca.mcgill.ecse.assetplus.controller;

import java.sql.Date;
import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
import ca.mcgill.ecse.assetplus.model.AssetPlus;
import ca.mcgill.ecse.assetplus.model.MaintenanceNote;
import ca.mcgill.ecse.assetplus.model.MaintenanceTicket;

public class AssetPlusFeatureSet7Controller {
  private static AssetPlus assetplus=AssetPlusApplication.getAssetPlus();

  private AssetPlusFeatureSet7Controller() {}


  public static String addMaintenanceNote(Date date, String description, int ticketID,
      String email) {
    MaintenanceTicket ticket=Utils.getMaintenanceTicketbyID(ticketID);
    if(ticket!=null){
      ticket.addTicketNote(new MaintenanceNote(date,description,ticket,Utils.getHotelStaffbyemail(email)));
      return "";
    }
    return "error";
  }

  // index starts at 0
  public static String updateMaintenanceNote(int ticketID, int index, Date newDate,
      String newDescription, String newEmail) {
    MaintenanceNote maintenanceNote=Utils.getMaintenanceTicketbyID(ticketID).getTicketNotes().get(index);
    if(maintenanceNote!=null){
      maintenanceNote.setDate(newDate);
      maintenanceNote.setNoteTaker(Utils.getHotelStaffbyemail(newEmail));
      maintenanceNote.setDescription(newDescription);
      return "";
    }
    return "error";
  }

  // index starts at 0
  public static void deleteMaintenanceNote(int ticketID, int index) {
    MaintenanceNote maintenanceNote=Utils.getMaintenanceTicketbyID(ticketID).getTicketNotes().get(index);
    if(maintenanceNote!=null){
      maintenanceNote.delete();
    }
    throw new RuntimeException("Ticket/Note not found.");
  }

}
