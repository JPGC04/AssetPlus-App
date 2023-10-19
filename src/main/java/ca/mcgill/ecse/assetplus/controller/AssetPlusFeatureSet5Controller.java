package ca.mcgill.ecse.assetplus.controller;

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

  /**
   * Adds an image to a maintenance ticket given an imageURL and a ticketID
   * Written by: Alan Brotherton
   * 
   * @param imageURL a string that contains the URL of an image
   * @param ticketID an integer that cointains the ID number of a specific maintenance ticket
   * @return an error message corresponding to where a problem was found if the image cannot be succesfullly added
   * @return an empty string if the image was succesfully added to the maintenance ticket
   */

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

  /**
   * Deletes an image from a maintenance ticket given an imageURL and a ticketID
   * Written by: Alan Brotherton
   * 
   * @param imageURL a string that contains the URL of an image
   * @param ticketID an integer that cointains the ID number of a specific maintenance ticket
   * @return nothing
   */

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
