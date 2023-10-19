package ca.mcgill.ecse.assetplus.controller;

import java.util.List;

import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
import ca.mcgill.ecse.assetplus.model.AssetPlus;
import ca.mcgill.ecse.assetplus.model.MaintenanceTicket;
import ca.mcgill.ecse.assetplus.model.TicketImage;

/**
 * AssetPlusFeatureSet5Controller is a set of methods that add and delete images to maintenance tickets
 * 
 * @author Alan Brotherton -- AlanBrotherton
 * @version ECSE 223 - Group Project Iteration 2a
 * @since ECSE 223 - Group Project Iteration 2a
 */

public class AssetPlusFeatureSet5Controller {
	
private static AssetPlus assetplus=AssetPlusApplication.getAssetPlus();

  public static String addImageToMaintenanceTicket(String imageURL, int ticketID) {
	  
	  if (imageURL == null || imageURL.isEmpty() ) {
		  return "Empty Image URL";
	  }
	  
	  if (!imageURL.startsWith("http://") && !imageURL.startsWith("https://")) {
		  return "imageURL must starts with http or https";
	  }
	  
    MaintenanceTicket maintenanceTicket = Utils.getMaintenanceTicketbyID(ticketID);
    if (maintenanceTicket != null) {
    	
    	List<TicketImage> ticketImages = maintenanceTicket.getTicketImages();
    	for (TicketImage image : ticketImages) {
          if (imageURL.equals(image.getImageURL())) {
               return "Cannot have duplicate image URL: ";
            }
        }
    	
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
      }
    } 
  }
}
