package ca.mcgill.ecse.assetplus.controller;

import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
import ca.mcgill.ecse.assetplus.model.AssetType;

public class AssetPlusFeatureSet2Controller {

  public static String addAssetType(String name, int expectedLifeSpanInDays) {
    if (name == null) {
      throw new IllegalArgumentException("Enter approporaite name");
    }
    if (expectedLifeSpanInDays <= null) {
      throw new IllegalArgumentException("Enter approporaite Life Span");
    }

    AssetPlus assetPlus = AssetPlusApplication.getAssetPlus();
    assetPlus.assetType(name, expectedLifeSpanInDays, assetPlus);
    
    // Remove this exception when you implement this method
    // throw new UnsupportedOperationException("Not Implemented!");
  }

  public static String updateAssetType(String oldName, String newName, int newExpectedLifeSpanInDays) {
    if (oldName == null) {
      throw new IllegalArgumentException("Enter approporaite old name");
    }
    if (oldName == null) {
      throw new IllegalArgumentException("Enter approporaite new name");
    }
    if (newExpectedLifeSpanInDays == null) {
      throw new IllegalArgumentException("Enter approporaite expected lifespan");
    }

    oldName.setName(newName);
    newName.setExpectedLifeSpan(newExpectedLifeSpanInDays);

    // Remove this exception when you implement this method
    // throw new UnsupportedOperationException("Not Implemented!");
  }

  public static void deleteAssetType(String name) {
    if (name == null) {
      throw new IllegalArgumentException("Enter approporaite name");
    }
    name.delete();

    // Remove this exception when you implement this method
    // throw new UnsupportedOperationException("Not Implemented!");
  }

}