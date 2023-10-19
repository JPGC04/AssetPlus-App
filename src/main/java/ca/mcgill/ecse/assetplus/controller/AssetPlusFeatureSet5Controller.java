package ca.mcgill.ecse.assetplus.controller;

import java.util.List;
import ca.mcgill.ecse.assetplus.model.MaintenanceTicket;
import ca.mcgill.ecse.assetplus.model.TicketImage;

public class AssetPlusFeatureSet5Controller {

  public static String addImageToMaintenanceTicket(String imageURL, int ticketID) {
    MaintenanceTicket maintenanceTicket = Utils.getMaintenanceTicketbyID(ticketID);
    if (maintenanceTicket != null) {
      maintenanceTicket.addTicketImage(imageURL);
      return "";
    }
    else return "Ticket not found";
  }

  public static void deleteImageFromMaintenanceTicket(String imageURL, int ticketID) {
    MaintenanceTicket maintenanceTicket = Utils.getMaintenanceTicketbyID(ticketID);
    if(maintenanceTicket != null){
      List<TicketImage> ticketImages = maintenanceTicket.getTicketImages();
      for (TicketImage image : ticketImages) {
        if(imageURL == image.getImageURL()){
          maintenanceTicket.removeTicketImage(image);
        }
        else throw new RuntimeException("Image not found");
      }
    }
    else throw new RuntimeException("Ticket not found");
  }
}