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
   * @return a string indicating that the asset type has been added
   * @throws IllegalArgumentException if name is null or has a length of 0
   * @throws IllegalArgumentException if expectedLifeSpanInDays is less than 0
   */
  public static String addAssetType(String name, int expectedLifeSpanInDays) {
    if (name == null || name.length() == 0) {
      throw new IllegalArgumentException("Enter appropriate name");
    }
    if (expectedLifeSpanInDays <= 0) {
      throw new IllegalArgumentException("Enter appropriate lifespan");
    }

    AssetPlus assetPlus = AssetPlusApplication.getAssetPlus();
    assetPlus.addAssetType(name, expectedLifeSpanInDays);

    return "Asset type added";
  }

  /**
   * Updates an old asset type with the given input name and expected lifespan in days.
   * Written by: John-Paul Chouery
   * 
   * @param oldNname a string containing the name of the asset type to be updated
   * @param newName a string containing the new name of the asset type to be updated
   * @param newExpectedLifeSpanInDays a string containing the new expected life span of the asset type to be updated
   * @return a string indicating that the asset type has been updated
   * @throws IllegalArgumentException if new or old name is null or has a length of 0
   * @throws IllegalArgumentException if newExpectedLifeSpanInDays is less than 0
   * @throws IllegalArgumentException if no asset type with old name exists
   */
  public static String updateAssetType(String oldName, String newName, int newExpectedLifeSpanInDays) {
    if (oldName == null || oldName.length() == 0) {
      throw new IllegalArgumentException("Enter appropriate old name");
    }
    if (newName == null || newName.length() == 0) {
      throw new IllegalArgumentException("Enter appropriate new name");
    }
    if (newExpectedLifeSpanInDays <= 0) {
      throw new IllegalArgumentException("Enter appropriate expected lifespan");
    }

    AssetType assetType = AssetType.getWithName(oldName);
    if (assetType == null) {
      throw new IllegalArgumentException("Enter appropriate old name for asset type");
    }
    
    assetType.setName(newName);
    assetType.setExpectedLifeSpan(newExpectedLifeSpanInDays);

    return "Asset type updated";
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