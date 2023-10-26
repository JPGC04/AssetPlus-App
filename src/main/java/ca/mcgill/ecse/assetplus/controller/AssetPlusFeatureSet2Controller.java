package ca.mcgill.ecse.assetplus.controller;

import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
import ca.mcgill.ecse.assetplus.model.AssetPlus;
import ca.mcgill.ecse.assetplus.model.AssetType;

import java.util.List;

/**
 * AssetPlusFeatureSet2Controller is the main entity that we'll be using to add, update, and delete
 * asset types
 * 
 * @author John-Paul Chouery
 * @version ECSE 223 - Group Project Iteration 2b
 * @since ECSE 223 - Group Project Iteration 2a
 */
public class AssetPlusFeatureSet2Controller {

  public static AssetPlus assetPlus = AssetPlusApplication.getAssetPlus();

  /**
   * Adds an asset type with the given input name and expected lifespan in days.
   * Written by: John-Paul Chouery
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

    if (assetPlus.hasAssetTypes()) {
      List<AssetType> assetTypes = assetPlus.getAssetTypes();
      for (AssetType aType : assetTypes) {
        if (aType.getName().equals(name)) {
          return "The asset type already exists";
        }
      }
    }

    AssetType assetType = new AssetType(name, expectedLifeSpanInDays, assetPlus);
    assetPlus.addAssetType(assetType);

    return "";
  }

  /**
   * Updates an old asset type with the given input name and expected lifespan in days.
   * Written by: John-Paul Chouery
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

    int oldExist = 0;
    int newExist = 0;

    if (assetPlus.hasAssetTypes()) {
      List<AssetType> assetTypes = assetPlus.getAssetTypes();
      for (AssetType aType : assetTypes) {
        if (aType.getName().equals(newName)) {
          newExist = 1;
          if (aType.getExpectedLifeSpan() == (newExpectedLifeSpanInDays)) {
            return "The asset type already exists";
          }
        }
        if (aType.getName().equals(oldName)) {
          oldExist = 1;
        }
      }
      if (oldExist == 0) {
        return "The asset type does not exist";
      }
    } else {
      return "The asset type does not exist";
    }

    if (!oldName.equals(newName) && newExist == 1) {
      return "The asset type already exists";
    }

    AssetType assetType = AssetType.getWithName(oldName);
    assetType.setName(newName);
    assetType.setExpectedLifeSpan(newExpectedLifeSpanInDays);

    return "";
  }

  /**
   * Deletes an asset type with the given input name.
   * Written by: John-Paul Chouery
   * 
   * @param name a string containing the name of the asset type to be deleted
   */
  public static void deleteAssetType(String name) {
    if (name != null && name.length() > 0) {
      AssetType assetType = AssetType.getWithName(name);

      if (assetType != null) {
        assetType.delete();
      }
    }
  }

}
