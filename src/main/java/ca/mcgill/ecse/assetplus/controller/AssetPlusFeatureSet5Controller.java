package ca.mcgill.ecse.assetplus.controller;

import ca.mcgill.ecse.assetplus.model.MaintenanceTicket;
import ca.mcgill.ecse.assetplus.model.TicketImage;
import ca.mcgill.ecse.assetplus.persistence.AssetPlusPersistence;
import java.util.ArrayList;
import java.util.List;

/**
 * AssetPlusFeatureSet5Controller is a set of methods that add and delete images to maintenance
 * tickets
 *
 * @author Alan Brotherton -- AlanBrotherton
 * @version ECSE 223 - Group Project Iteration 3
 * @since ECSE 223 - Group Project Iteration 2a
 */

public class AssetPlusFeatureSet5Controller {

  /**
   * Adds an image to a maintenance ticket given an imageURL and a ticketID Written by: Alan
   * Brotherton
   * 
   * @param imageURL a string that contains the URL of an image
   * @param ticketID an integer that cointains the ID number of a specific maintenance ticket
   * @return an error message corresponding to where a problem was found if the image cannot be
   *         succesfullly added
   * @return an empty string if the image was succesfully added to the maintenance ticket
   */
  public static String addImageToMaintenanceTicket(String imageURL, int ticketID) {
    if (imageURL == null || imageURL.isEmpty()) {
      return "Image URL cannot be empty";
    }

    if (!imageURL.startsWith("http://") && !imageURL.startsWith("https://")) {
      return "Image URL must start with http:// or https://";
    }

    try {
      MaintenanceTicket maintenanceTicket = MaintenanceTicket.getWithId(ticketID);

      if (maintenanceTicket != null) {
        List<TicketImage> ticketImages = maintenanceTicket.getTicketImages();

        for (TicketImage image : ticketImages) {
          if (imageURL.equals(image.getImageURL())) {
            return "Image already exists for the ticket";
          }
        }

        maintenanceTicket.addTicketImage(imageURL);
        AssetPlusPersistence.save();
        return "";

      } else
        return "Ticket does not exist";

    } catch (Exception e) {
      return "Error: " + e;
    }
  }

  /**
   * Deletes an image from a maintenance ticket given an imageURL and a ticketID Written by: Alan
   * Brotherton
   * 
   * @param imageURL a string that contains the URL of an image
   * @param ticketID an integer that cointains the ID number of a specific maintenance ticket
   */
  public static void deleteImageFromMaintenanceTicket(String imageURL, int ticketID) {
    try {
      MaintenanceTicket maintenanceTicket = Utils.getMaintenanceTicketbyID(ticketID);

      if (maintenanceTicket != null) {
        List<TicketImage> ticketImages = maintenanceTicket.getTicketImages();

        for (TicketImage image : ticketImages) {
          if (imageURL.equals(image.getImageURL())) {
            image.delete();
            AssetPlusPersistence.save();
            return;
          }
        }
      }
    } catch (Exception e) {
      return;
    }
  }

  public static List<Image> getSpecificImages(int i) {
    try {
      MaintenanceTicket ticket = MaintenanceTicket.getWithId(i);

      List<TicketImage> images = ticket.getTicketImages();
      List<Image> res = new ArrayList<>();
      int ind = 0;
      for (TicketImage img : images) {
        res.add(new Image(img.getImageURL(), ind));
        ind++;
      }
      return res;
    } catch (Exception e) {

    }
    return null;
  }

  public static class Image {

    private String url;
    private int index;


    public Image(String url, int index) {
      this.url = url;
      this.index = index;
    }

    public int getIndex() {
      return this.index;
    }

    public void setIndex(int newIndex) {
      this.index = newIndex;

    }

    public String getUrl() {
      return this.url;

    }

    public void setUrl(String newUrl) {

      this.url = newUrl;
    }

  }


}
