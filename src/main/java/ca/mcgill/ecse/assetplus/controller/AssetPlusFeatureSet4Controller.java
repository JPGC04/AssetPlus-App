package ca.mcgill.ecse.assetplus.controller;

import java.sql.Date;
import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
import ca.mcgill.ecse.assetplus.model.AssetPlus;
import ca.mcgill.ecse.assetplus.model.MaintenanceTicket;
import ca.mcgill.ecse.assetplus.model.User;
import ca.mcgill.ecse.assetplus.model.SpecificAsset;

//Yazid Asselah
//Dramocrystal

public class AssetPlusFeatureSet4Controller {

  private static AssetPlus assetplus=AssetPlusApplication.getAssetPlus();

  // assetNumber -1 means that no asset is specified

  public static String addMaintenanceTicket(int id, Date raisedOnDate, String description,
      String email, int assetNumber) {
      
        //Input validation constraint
        if (description.isEmpty() || description == null) {
          return "Error Description cannot be empty";
        }

        //Find the user by email

          User ticketRaiser = User.getWithEmail(email);

          if (ticketRaiser == null) {
            return "User not found";
          }


        //Find the specific Asset

        SpecificAsset specificAsset = null;

        if (assetNumber != -1) {
          specificAsset = SpecificAsset.getWithAssetNumber(assetNumber);
          if (specificAsset == null) {
            return "Specific asset not found";
          }
        }
        
        try {
  
          MaintenanceTicket newMaintenanceTicket = new MaintenanceTicket(id, raisedOnDate, description, assetplus, ticketRaiser);

          if (specificAsset != null) {
            newMaintenanceTicket.setAsset(specificAsset);
          }
        } catch (RuntimeException e){

          return e.getMessage();
        }

        return ""; 
  }

  // newAssetNumber -1 means that no asset is specified
  public static String updateMaintenanceTicket(int id, Date newRaisedOnDate, String newDescription,
      String newEmail, int newAssetNumber) {

        

        //Input validation constraint
        if (newDescription.isEmpty() || newDescription == null) {
          return "Error Description cannot be empty";
        }

        //Find specific user
        User newTicketRaiser = User.getWithEmail(newEmail);
          if (newTicketRaiser == null) {
            return "User not found";
          }

        //Find specific asset

        SpecificAsset newSpecificAsset = null;
        if (newAssetNumber != -1) {
          newSpecificAsset = SpecificAsset.getWithAssetNumber(newAssetNumber);
          if (newSpecificAsset == null) {
            return "Specific asset not found";
          }
        }

        //Find specific Ticket
        MaintenanceTicket ticket = assetplus.getMaintenanceTicket(id);

        if (ticket == null) {
          return "ticket not found";
        }


        ticket.setDescription(newDescription);
        ticket.setRaisedOnDate(newRaisedOnDate);
        ticket.setTicketRaiser(newTicketRaiser);
        ticket.setTicketRaiser(newTicketRaiser);
        ticket.setAsset(newSpecificAsset);

        return "";
  }

  public static void deleteMaintenanceTicket(int id) {
    //Find ticket
        //Find specific Ticket
        MaintenanceTicket ticket = assetplus.getMaintenanceTicket(id);
        
        ticket.delete();
  }

}
