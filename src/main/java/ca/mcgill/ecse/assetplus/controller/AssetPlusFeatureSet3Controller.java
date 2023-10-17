package ca.mcgill.ecse.assetplus.controller;
import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
import ca.mcgill.ecse.assetplus.model.AssetPlus;
import ca.mcgill.ecse.assetplus.model.AssetType;
import ca.mcgill.ecse.assetplus.model.SpecificAsset;

import java.sql.Date;
import java.util.List;


public class AssetPlusFeatureSet3Controller {
  
  private static AssetPlus assetPlus = AssetPlusApplication.getAssetPlus();

  private static String isValidAsset(int assetNumber, int floorNumber, int roomNumber) {
    var error = "";
    if (assetNumber < 1) {
      error = "The Asset number must be greater than or equal to one.";
    }
    if (floorNumber < 0) {
      error = "The floor number must be greater than or equal to zero.";
    }
    if (roomNumber < -1) {
      error = "The room number must be greater than or equal to minus one.";
    }

    return error.trim();
  }
  /**
   * <p>Returns the AssetType, given its name</p>
   * @param assetTypeName asset type's name as a String
   * @return AssetType object if found; null if not.
   */
  private static AssetType getAssetType(String assetTypeName) {
    try {

      if (assetPlus.hasAssetTypes()) {
        List<AssetType> assetTypes = assetPlus.getAssetTypes();
        for (AssetType aType : assetTypes) {
          if (aType.getName().equals(assetTypeName)) {
            return aType;
          }
        }
      }
    } catch (Exception e) {
      System.out.println(e);
      return null;
    }
    return null;
  }

  /**
   *<p>Used to get the index of a SpecificAsset</p>
   * @param assetNumber unique id to identify each asset
   * @return index of specificAsset object if found; -1 if not found
   */
  private static int getSpecificAsset(int assetNumber) {
    List<SpecificAsset> assets = assetPlus.getSpecificAssets();
    if (!assets.isEmpty()) {
      int i = 0;
      for (SpecificAsset sAsset: assets) {
        if (sAsset.getAssetNumber() == assetNumber) {
          return i;
        }
        i++;
      }
    }
    return -1;
  }
  /**
   *<p>Adds a SpecificAsset to the AssetPlus app.</p>
   * @param assetNumber asset number of new asset
   * @param floorNumber floor number of new asset
   * @param roomNumber room location of new asset
   * @param purchaseDate purchase date of new asset
   * @param assetTypeName asset type of new asset
   * @return The error message if a failure is encountered; empty string if successful.
   */
  public static String addSpecificAsset(int assetNumber, int floorNumber, int roomNumber, Date purchaseDate, String assetTypeName) {
    //input validation
    var error = isValidAsset(assetNumber, floorNumber, roomNumber);
    if (!error.isEmpty()) {
      return error;
    }
    AssetType assetType = getAssetType(assetTypeName);
    if (assetType != null) {
        SpecificAsset specificAsset = assetPlus.addSpecificAsset(assetNumber, floorNumber, roomNumber, purchaseDate, assetType);
        boolean added = assetPlus.addSpecificAsset(specificAsset);
        if (!added) {
          error = "The specific asset cannot be added.";
        }
    } else {
      return "The asset type: " + assetTypeName + " Does not exist.";
    }
    return error;
  }

  /**
   * <p>Updates a chosen SpecificAsset with new parameters</p>
   * @param assetNumber asset number of SpecificAsset
   * @param newFloorNumber updated floor number of asset
   * @param newRoomNumber updated room number of asset
   * @param newPurchaseDate updated purchase date of asset
   * @param newAssetTypeName updated asset type of asset
   * @return The error message if a failure is encountered; empty string if successful.
   */
  public static String updateSpecificAsset(int assetNumber, int newFloorNumber, int newRoomNumber,
      Date newPurchaseDate, String newAssetTypeName) {
    var error = isValidAsset(assetNumber, newFloorNumber, newRoomNumber);
    if (!error.isEmpty()) {
      return error;
    }
    int index = getSpecificAsset(assetNumber);
    if (index == -1) {
      return "The Specific Asset could not be found.";
    }
    AssetType newAssetType = getAssetType(newAssetTypeName);
    if (newAssetType == null) {
      return "The asset type: " + newAssetTypeName + " Does not exist.";
    }
    SpecificAsset asset = assetPlus.getSpecificAsset(index);
    asset.setFloorNumber(newFloorNumber);
    asset.setRoomNumber(newRoomNumber);
    asset.setPurchaseDate(newPurchaseDate);
    asset.setAssetType(newAssetType);

    return error;
  }

  public static void deleteSpecificAsset(int assetNumber) {
        int index = getSpecificAsset(assetNumber);
    if (index != -1) {
      SpecificAsset removableAsset = assetPlus.getSpecificAsset(index);
      assetPlus.removeSpecificAsset(removableAsset);
    }
  }
}
