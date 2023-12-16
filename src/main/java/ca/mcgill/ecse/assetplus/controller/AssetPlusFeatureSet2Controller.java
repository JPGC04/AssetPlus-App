package ca.mcgill.ecse.assetplus.controller;

import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
import ca.mcgill.ecse.assetplus.model.AssetPlus;
import ca.mcgill.ecse.assetplus.model.AssetType;
import ca.mcgill.ecse.assetplus.model.MaintenanceTicket;
import ca.mcgill.ecse.assetplus.model.SpecificAsset;
import ca.mcgill.ecse.assetplus.persistence.AssetPlusPersistence;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * AssetPlusFeatureSet2Controller is the main entity that is used to add, update, and delete asset
 * types
 * 
 * @author John-Paul Chouery -- JPGC04
 * @version ECSE 223 - Group Project Iteration 4
 * @since ECSE 223 - Group Project Iteration 2a
 */
public class AssetPlusFeatureSet2Controller {

  public static AssetPlus assetPlus = AssetPlusApplication.getAssetPlus();

  /**
   * Adds an asset type with the given input name and expected lifespan in days. Written by:
   * John-Paul Chouery
   * 
   * @param name a string containing the name of the asset type
   * @param expectedLifeSpanInDays an integer containing the expected life span of the asset type in
   *        days
   * @return string containing error message if error detected
   * @return empty string if no error detected and asset type added
   */
  public static String addAssetType(String name, int expectedLifeSpanInDays) {
    if (name == null || name.length() == 0) {
      return "The name must not be empty";
    }
    if (expectedLifeSpanInDays <= 0) {
      return "The expected life span must be greater than 0 days";
    }

    if (AssetType.getWithName(name) != null) {
      return "The asset type already exists";
    }

    try {
      AssetType assetType = new AssetType(name, expectedLifeSpanInDays, assetPlus);
      assetPlus.addAssetType(assetType);
      AssetPlusPersistence.save();
      return "";
    } catch (Exception e) {
      return "Error: " + e;
    }

  }

  /**
   * Updates an old asset type with the given input name and expected lifespan in days. Written by:
   * John-Paul Chouery
   * 
   * @param oldName a string containing the name of the asset type to be updated
   * @param newName a string containing the new name of the asset type to be updated
   * @param newExpectedLifeSpanInDays an integer containing the new expected life span of the asset
   *        type to be updated
   * @return string containing error message if error detected
   * @return empty string if no error detected and asset type updated
   */
  public static String updateAssetType(String oldName, String newName,
      int newExpectedLifeSpanInDays) {
    if (oldName == null || oldName.length() == 0 || newName == null || newName.length() == 0) {
      return "The name must not be empty";
    }
    if (newExpectedLifeSpanInDays <= 0) {
      return "The expected life span must be greater than 0 days";
    }

    if (AssetType.getWithName(oldName) == null) {
      return "The asset type does not exist";
    }
    if (AssetType.getWithName(newName) != null
        && (AssetType.getWithName(newName).getExpectedLifeSpan() == newExpectedLifeSpanInDays
            || !oldName.equals(newName))) {
      return "The asset type already exists";
    }

    try {
      AssetType assetType = AssetType.getWithName(oldName);
      assetType.setName(newName);
      assetType.setExpectedLifeSpan(newExpectedLifeSpanInDays);
      AssetPlusPersistence.save();
      return "";
    } catch (Exception e) {
      return "Error: " + e;
    }

  }

  /**
   * Deletes an asset type with the given input name. Written by: John-Paul Chouery
   * 
   * @param name a string containing the name of the asset type to be deleted
   */
  public static void deleteAssetType(String name) {
    try {
      if (name != null && name.length() > 0) {
        AssetType assetType = AssetType.getWithName(name);
        if (assetType != null) {
          int number = assetPlus.getMaintenanceTickets().size();
          if (number > 0) {
            List<Integer> IDtoDelete = new ArrayList<>();
            for (MaintenanceTicket m : assetPlus.getMaintenanceTickets()) {
              SpecificAsset thisAsset = m.getAsset();
              if (thisAsset != null) {
                AssetType thisType = thisAsset.getAssetType();
                if (thisType != null) {
                  if (thisType.getName().equals(name)) {
                    IDtoDelete.add(m.getId());
                  }
                }
              }
            }

            Collections.reverse(IDtoDelete);

            for (int id : IDtoDelete) {
              AssetPlusFeatureSet4Controller.deleteMaintenanceTicket(id);
            }
          }

          assetType.delete();
          AssetPlusPersistence.save();
        }
      }
    } catch (Exception e) {
      return;
    }
  }

  /**
   * Returns a list of strings of the AssetTypes in the system. Written by: John-Paul Chouery
   */
  public static List<String> getAssetTypes() {
    List<AssetType> assetTypes = assetPlus.getAssetTypes();
    List<String> asStrings = new ArrayList<>();
    for (AssetType a : assetTypes) {
      asStrings.add(a.getName());
    }
    return asStrings;
  }

}
