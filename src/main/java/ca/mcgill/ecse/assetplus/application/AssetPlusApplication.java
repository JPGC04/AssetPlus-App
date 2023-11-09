package ca.mcgill.ecse.assetplus.application;

import ca.mcgill.ecse.assetplus.model.AssetPlus;

public class AssetPlusApplication {

  private static AssetPlus assetPlus;

  public static void main(String[] args) {
    // TODO Start the application user interface here
  }

  public static AssetPlus getAssetPlus() {
    if (assetPlus == null) {
      assetPlus = AssetPlusPersistence.load();
    }
    return assetPlus;
  }

}
