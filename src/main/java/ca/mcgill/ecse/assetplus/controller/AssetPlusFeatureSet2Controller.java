package ca.mcgill.ecse.assetplus.controller;

import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
import ca.mcgill.ecse.assetplus.model.AssetPlus;
import ca.mcgill.ecse.assetplus.model.AssetType;

/**
 * AssetPlusFeatureSet2Controller is the main entity that we'll be using to add, update, and delete asset types
 * 
 * @author John-Paul Chouery
 * @version ECSE 223 - Group Project Iteration 2a
 * @since ECSE 223 - Group Project Iteration 2a
 */
public class AssetPlusFeatureSet2Controller {

  /**
   * Adds an asset type with the given input name and expected lifespan in days.
   * Written by: John-Paul Chouery
   * 
   * @param name a string containing the name of the asset type
   * @param expectedLifeSpanInDays an int containing the expected life span of the asset type in days
   * @return a string if an error was detected else return empty string
   */
  public static String addAssetType(String name, int expectedLifeSpanInDays) {
    if (name == null || name.length() == 0) {
      return "Error Asset type name is null or empty";
    }
    if (expectedLifeSpanInDays <= 0) {
      return "Error Asset type expected lifespan is not valid";
    }

    AssetPlus assetPlus = AssetPlusApplication.getAssetPlus();
    assetPlus.addAssetType(name, expectedLifeSpanInDays);

    return "";
  }

  /**
   * Updates an old asset type with the given input name and expected lifespan in days.
   * Written by: John-Paul Chouery
   * 
   * @param oldNname a string containing the name of the asset type to be updated
   * @param newName a string containing the new name of the asset type to be updated
   * @param newExpectedLifeSpanInDays a string containing the new expected life span of the asset type to be updated
   * @return a string if an error was detected else empty string
   */
  public static String updateAssetType(String oldName, String newName, int newExpectedLifeSpanInDays) {
    if (oldName == null || oldName.length() == 0) {
      return "Error Asset type old name is null or empty";
    }
    if (newName == null || newName.length() == 0) {
      return "Error Asset type new name is null or empty";
    }
    if (newExpectedLifeSpanInDays <= 0) {
      return "Error Asset type new expected lifespan is not valid";
    }

    AssetType assetType = AssetType.getWithName(oldName);
    if (assetType == null) {
      return "Error Asset type with old name does not exist";
    }
    
    assetType.setName(newName);
    assetType.setExpectedLifeSpan(newExpectedLifeSpanInDays);

    return "";
  }

  /**
   * Deletes an asset type with the given input name.
   * Written by: John-Paul Chouery
   * 
   * @param name a string containing the name of the asset type to be deleted
   * @throws IllegalArgumentException if name is null or has a length of 0
   * @throws IllegalArgumentException if no asset type with name exists
   */
  public static void deleteAssetType(String name) {
    if (name == null || name.length() == 0) {
      throw new IllegalArgumentException("Enter appropriate name");
    }

    AssetType assetType = AssetType.getWithName(name);
    if (assetType == null) {
      throw new IllegalArgumentException("Enter appropriate name for asset type");
    }

    assetType.delete();
  }

}