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
 * @version ECSE 223 - Group Project Iteration 2a
 * @since ECSE 223 - Group Project Iteration 2a
 */
public class AssetPlusFeatureSet2Controller {

  public static AssetPlus assetPlus = AssetPlusApplication.getAssetPlus();

  /**
   * Adds an asset type with the given input name and expected lifespan in days.
   * Written by: John-Paul Chouery
   * 
   * @param name a string containing the name of the asset type
   * @param expectedLifeSpanInDays an int containing the expected life span of the asset type in
   *        days
   * @throws IllegalArgumentException if name empty, expected lifespan less than 1, or asset type
   *         already exists
   * @return empty string if no error detected and asset type added
   */
  public static String addAssetType(String name, int expectedLifeSpanInDays) {
    if (name == null || name.length() == 0) {
      throw new IllegalArgumentException("The name must not be empty");
    }
    if (expectedLifeSpanInDays <= 0) {
      throw new IllegalArgumentException("The expected life span must be greater than 0 days");
    }

    if (assetPlus.hasAssetTypes()) {
      List<AssetType> assetTypes = assetPlus.getAssetTypes();
      for (AssetType aType : assetTypes) {
        if (aType.getName().equals(name)) {
          throw new IllegalArgumentException("The asset type already exists");
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
   * @param newExpectedLifeSpanInDays a string containing the new expected life span of the asset
   *        type to be updated
   * @throws IllegalArgumentException if names are empty, expected lifespan less than 1, old asset
   *         type does not already exists, or new asset type already exists
   * @return empty string if no error detected and asset type updated
   */
  public static String updateAssetType(String oldName, String newName,
      int newExpectedLifeSpanInDays) {
    if (oldName == null || oldName.length() == 0 || newName == null || newName.length() == 0) {
      throw new IllegalArgumentException("The name must not be empty");
    }
    if (newExpectedLifeSpanInDays <= 0) {
      throw new IllegalArgumentException("The expected life span must be greater than 0 days");
    }

    if (assetPlus.hasAssetTypes()) {
      List<AssetType> assetTypes = assetPlus.getAssetTypes();
      int exist = 0;
      for (AssetType aType : assetTypes) {
        if (aType.getName().equals(newName)) {
          throw new IllegalArgumentException("The asset type already exists");
        }
        if (aType.getName().equals(oldName) && exist == 0) {
          exist = 1;
        }
      }
      if (exist == 0) {
        throw new IllegalArgumentException("The asset type does not exist");
      }
    } else {
      throw new IllegalArgumentException("The asset type does not exist");
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
   * @throws IllegalArgumentException if name is null or has a length of 0
   */
  public static void deleteAssetType(String name) {
    if (name == null || name.length() == 0) {
      throw new IllegalArgumentException("The name must not be empty");
    }

    AssetType assetType = AssetType.getWithName(name);

    if (assetType != null) {
      assetType.delete();;
    }
  }

}
