package ca.mcgill.ecse.assetplus.controller;


import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
import ca.mcgill.ecse.assetplus.model.AssetPlus;
import ca.mcgill.ecse.assetplus.model.MaintenanceTicket;
import ca.mcgill.ecse.assetplus.model.TicketImage;

import java.util.List;

/**
 * AssetPlusFeatureSet5Controller is a set of methods that add and delete images to maintenance tickets
 *
 * @author Alan Brotherton -- AlanBrotherton
 * @version ECSE 223 - Group Project Iteration 2a
 * @since ECSE 223 - Group Project Iteration 2a
 */

public class AssetPlusFeatureSet5Controller {

  public static String addImageToMaintenanceTicket(String imageURL, int ticketID) {

    if (imageURL == null || imageURL.isEmpty() ) {
      return "Image URL cannot be empty";
    }

    if (!imageURL.startsWith("http://") && !imageURL.startsWith("https://")) {
      return "Image URL must start with http:// or https://";
    }

    MaintenanceTicket maintenanceTicket = MaintenanceTicket.getWithId(ticketID);

    if (maintenanceTicket != null) {

      List<TicketImage> ticketImages = maintenanceTicket.getTicketImages();
      for (TicketImage image : ticketImages) {
        if (imageURL.equals(image.getImageURL())) {
          return "Image already exists for the ticket";
        }
      }

      maintenanceTicket.addTicketImage(imageURL);

      return "";
    }
    else return "Ticket does not exist";
  }

  public static void deleteImageFromMaintenanceTicket(String imageURL, int ticketID) {
    MaintenanceTicket maintenanceTicket = Utils.getMaintenanceTicketbyID(ticketID);
    if(maintenanceTicket != null){
      List<TicketImage> ticketImages = maintenanceTicket.getTicketImages();
      for (TicketImage image : ticketImages) {
        if(imageURL.equals(image.getImageURL())){
          maintenanceTicket.removeTicketImage(image);
        }
      }
    }
  }
}
