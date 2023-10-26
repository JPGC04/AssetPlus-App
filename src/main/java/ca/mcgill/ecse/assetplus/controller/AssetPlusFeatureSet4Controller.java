package ca.mcgill.ecse.assetplus.controller;

import java.sql.Date;
import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
import ca.mcgill.ecse.assetplus.model.AssetPlus;
import ca.mcgill.ecse.assetplus.model.MaintenanceTicket;
import ca.mcgill.ecse.assetplus.model.User;
import ca.mcgill.ecse.assetplus.model.SpecificAsset;

/**
 * AssetPlusFeatureSet4Controller is the main entity that we'll be using to add, update, and delete MaintenanceTicket
 * 
 * @author Yazid Asselah -- Dramocrystal
 * @version ECSE 223 - Group Project Iteration 2a
 * @since ECSE 223 - Group Project Iteration 2a
 */

public class AssetPlusFeatureSet4Controller {

  private static AssetPlus assetplus=AssetPlusApplication.getAssetPlus();

  // assetNumber -1 means that no asset is specified

    /**
   * Adds an MainteanceTicket type with the given id, date, description, email, assetnumber.
   * Written by: Yazid Asselah
   * 
   * @param id an int corresponding to the id of the ticket
   * @param raisedOnDate a Date object corresponding to the date of when the ticket was created
   * @param description a String that will contain the description of the ticket
   * @param email a String that will enable us to retrace the raiser of the ticket
   * @param assetNumber an int that will enable us to retrace the specific asset, if -1 it means no asset
   * @return a string that indicates the error, if no error returns an empty string
   */

  public static String addMaintenanceTicket(int id, Date raisedOnDate, String description,
      String email, int assetNumber) {
      
        //Input validation constraint
        if (description.isEmpty() || description == null) {
          return "Ticket description cannot be empty";
        }

        //Find the user by email

          User ticketRaiser = User.getWithEmail(email);

          if (ticketRaiser == null) {
            return "The ticket raiser does not exist";
          }


        //Find the specific Asset

        SpecificAsset specificAsset = null;

        if (assetNumber != -1) {
          specificAsset = SpecificAsset.getWithAssetNumber(assetNumber);
          if (specificAsset == null) {
            return "The asset does not exist";
          }
        }
        
        try {
  
          MaintenanceTicket newMaintenanceTicket = new MaintenanceTicket(id, raisedOnDate, description, assetplus, ticketRaiser);

          if (specificAsset != null) {
            newMaintenanceTicket.setAsset(specificAsset);
          }
        } catch (RuntimeException e){

          return "Ticket id already exists";
        }

        return ""; 
  }

  // newAssetNumber -1 means that no asset is specified
   /**
   * Updates a MainteanceTicket type with the given id, date, description, email, assetnumber.
   * Written by: Yazid Asselah
   * 
   * @param id an int corresponding to the id of the ticket
   * @param newrRaisedOnDate a Date object corresponding to the date of when the ticket was updated
   * @param newDescription a String that will contain the new description of the ticket
   * @param newEmail a String that will enable us to retrace the editor of the ticket
   * @param assetNumber an int that will enable us to retrace the specific asset, if -1 it means no asset
   * @return a string that indicates the error, if no error returns an empty string
   */

  public static String updateMaintenanceTicket(int id, Date newRaisedOnDate, String newDescription,
      String newEmail, int newAssetNumber) {

        

        //Input validation constraint
        if (newDescription.isEmpty() || newDescription == null) {
          return "Ticket description cannot be empty";
        }

        //Find specific user
        User newTicketRaiser = User.getWithEmail(newEmail);
          if (newTicketRaiser == null) {
            return "The ticket raiser does not exist";
          }

        //Find specific asset

        SpecificAsset newSpecificAsset = null;
        if (newAssetNumber != -1) {
          newSpecificAsset = SpecificAsset.getWithAssetNumber(newAssetNumber);
          if (newSpecificAsset == null) {
            return "The asset does not exist";
          }
        }

        //Find specific Ticket
        MaintenanceTicket ticket = MaintenanceTicket.getWithId(id);

        if (ticket == null) {
          return "ticket not found";
        }


        ticket.setDescription(newDescription);
        ticket.setRaisedOnDate(newRaisedOnDate);
        ticket.setTicketRaiser(newTicketRaiser);
        ticket.setTicketRaiser(newTicketRaiser);
        if (newAssetNumber == -1) {
          ticket.getAsset().delete();
        }
        ticket.setAsset(newSpecificAsset);

        return "";
  }

   /**
   * Deletes a MainteanceTicket type with the given id
   * Written by: Yazid Asselah
   * 
   * @param id an int corresponding to the id of the ticket
   * @return nothing
   */


  public static void deleteMaintenanceTicket(int id) {
    //Find ticket
        //Find specific Ticket
        MaintenanceTicket ticket = MaintenanceTicket.getWithId(id);
        
        if (ticket != null) {
          ticket.delete();
        }
        
  }

}
