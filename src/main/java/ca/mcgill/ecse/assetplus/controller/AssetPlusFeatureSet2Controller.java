package ca.mcgill.ecse.assetplus.controller;

import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
import ca.mcgill.ecse.assetplus.model.AssetPlus;
import ca.mcgill.ecse.assetplus.model.AssetType;

public class AssetPlusFeatureSet2Controller {

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