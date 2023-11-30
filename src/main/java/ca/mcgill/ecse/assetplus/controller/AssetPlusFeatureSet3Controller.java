package ca.mcgill.ecse.assetplus.controller;

import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
import ca.mcgill.ecse.assetplus.javafx.fxml.controllers.Asset;
import ca.mcgill.ecse.assetplus.model.AssetPlus;
import ca.mcgill.ecse.assetplus.model.AssetType;
import ca.mcgill.ecse.assetplus.model.SpecificAsset;
import ca.mcgill.ecse.assetplus.persistence.AssetPlusPersistence;

import java.sql.Date;
import java.util.List;
import java.util.ArrayList;

/**
 * AssetPlusFeatureSet3Controller is the main class we will use to Add, update, and delete Asset
 *
 * @author Seungyeon Lee
 * @version ECSE 223 - Group Project Iteration 3
 * @since ECSE 223 - Group Project Iteration 2a
 */
public class AssetPlusFeatureSet3Controller {

  private static AssetPlus assetPlus = AssetPlusApplication.getAssetPlus();

  /**
   * <p>
   * Input validation for the parameteres
   * </p>
   * 
   * @param assetNumber: # of asset
   * @param floorNumber: floor number of asset
   * @param roomNumber: room number of asset
   */
  private static String isValidAsset(int assetNumber, int floorNumber, int roomNumber) {
    var error = "";
    if (assetNumber < 1) {
      error = "The asset number shall not be less than 1";
    }
    if (floorNumber < 0) {
      error = "The floor number shall not be less than 0";
    }
    if (roomNumber < -1) {
      error = "The room number shall not be less than -1";
    }

    return error.trim();
  }

  /**
   * <p>Returns the AssetType, given its name</p>
   * 
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
   * <p>Used to get the index of a SpecificAsset</p>
   * 
   * @param assetNumber unique id to identify each asset
   * @return index of specificAsset object if found; -1 if not found
   */
  private static int getSpecificAsset(int assetNumber) {
    List<SpecificAsset> assets = assetPlus.getSpecificAssets();
    if (!assets.isEmpty()) {
      int i = 0;
      for (SpecificAsset sAsset : assets) {
        if (sAsset.getAssetNumber() == assetNumber) {
          return i;
        }
        i++;
      }
    }
    return -1;
  }

  /**
   * <p>Adds a SpecificAsset to the AssetPlus app.</p>
   * 
   * @param assetNumber asset number of new asset
   * @param floorNumber floor number of new asset
   * @param roomNumber room location of new asset
   * @param purchaseDate purchase date of new asset
   * @param assetTypeName asset type of new asset
   * @return The error message if a failure is encountered; empty string if successful.
   */
  public static String addSpecificAsset(int assetNumber, int floorNumber, int roomNumber,
      Date purchaseDate, String assetTypeName) {
    var error = isValidAsset(assetNumber, floorNumber, roomNumber);
    if (!error.isEmpty()) {
      return error;
    }
    AssetType assetType = getAssetType(assetTypeName);
    if (assetType != null) {
      try {
        SpecificAsset specificAsset = assetPlus.addSpecificAsset(assetNumber, floorNumber,
            roomNumber, purchaseDate, assetType);
        boolean added = assetPlus.addSpecificAsset(specificAsset);
        AssetPlusPersistence.save();
        if (!added) {
          error = "The specific asset cannot be added.";
        }
      } catch (Exception e) {
        return "ERROR: " + e;
      }
    } else {
      return "The asset type does not exist";
    }
    return error;
  }

  /**
   * <p>Updates a chosen SpecificAsset with new parameters</p>
   * 
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
      return "The asset type does not exist";
    }
    try {
      SpecificAsset asset = assetPlus.getSpecificAsset(index);
      asset.setFloorNumber(newFloorNumber);
      asset.setRoomNumber(newRoomNumber);
      asset.setPurchaseDate(newPurchaseDate);
      asset.setAssetType(newAssetType);
      AssetPlusPersistence.save();
    } catch (Exception e) {
      return "ERROR: " + e;
    }
    return error;
  }

  /**
   * <p>Deletes an asset given its asset number</p>
   * 
   * @param assetNumber asset number of SpecificAsset
   */
  public static void deleteSpecificAsset(int assetNumber) {
    int index = getSpecificAsset(assetNumber);
    try {
      if (index != -1) {
        assetPlus.getSpecificAsset(index).delete();
        AssetPlusPersistence.save();
      }
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public static List<Asset> getSpecificAssets() {
    List<SpecificAsset> assets = assetPlus.getSpecificAssets();
    List<Asset> res = new ArrayList<>();
    for (SpecificAsset asset: assets) {
      res.add(new Asset(String.valueOf(asset.getAssetNumber()), String.valueOf(asset.getPurchaseDate()), String.valueOf(asset.getFloorNumber()),
      String.valueOf(asset.getRoomNumber()), asset.getAssetType().getName()));
    }

    return res;
  }
}
