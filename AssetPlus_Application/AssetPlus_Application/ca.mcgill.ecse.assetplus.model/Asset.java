/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/



// line 75 "main.ump"
public class Asset
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextAssetNumber = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Asset Attributes
  private String purchaseDate;
  private int expectedLifespan;

  //Autounique Attributes
  private int assetNumber;

  //Asset Associations
  private Location assetLocation;
  private AssetPlus assetPlus;
  private Manager manager;
  private AssetType assetType;
  private MaintenanceTicket maintenanceTicket;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Asset(String aPurchaseDate, int aExpectedLifespan, Location aAssetLocation, AssetPlus aAssetPlus, Manager aManager, AssetType aAssetType, MaintenanceTicket aMaintenanceTicket)
  {
    purchaseDate = aPurchaseDate;
    expectedLifespan = aExpectedLifespan;
    assetNumber = nextAssetNumber++;
    if (aAssetLocation == null || aAssetLocation.getAsset() != null)
    {
      throw new RuntimeException("Unable to create Asset due to aAssetLocation. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    assetLocation = aAssetLocation;
    boolean didAddAssetPlus = setAssetPlus(aAssetPlus);
    if (!didAddAssetPlus)
    {
      throw new RuntimeException("Unable to create asset due to assetPlus. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddManager = setManager(aManager);
    if (!didAddManager)
    {
      throw new RuntimeException("Unable to create asset due to manager. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddAssetType = setAssetType(aAssetType);
    if (!didAddAssetType)
    {
      throw new RuntimeException("Unable to create asset due to assetType. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddMaintenanceTicket = setMaintenanceTicket(aMaintenanceTicket);
    if (!didAddMaintenanceTicket)
    {
      throw new RuntimeException("Unable to create asset due to maintenanceTicket. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  public Asset(String aPurchaseDate, int aExpectedLifespan, Floor aFloorsForAssetLocation, AssetPlus aAssetPlus, Manager aManager, AssetType aAssetType, MaintenanceTicket aMaintenanceTicket)
  {
    purchaseDate = aPurchaseDate;
    expectedLifespan = aExpectedLifespan;
    assetNumber = nextAssetNumber++;
    assetLocation = new Location(aFloorsForAssetLocation, this);
    boolean didAddAssetPlus = setAssetPlus(aAssetPlus);
    if (!didAddAssetPlus)
    {
      throw new RuntimeException("Unable to create asset due to assetPlus. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddManager = setManager(aManager);
    if (!didAddManager)
    {
      throw new RuntimeException("Unable to create asset due to manager. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddAssetType = setAssetType(aAssetType);
    if (!didAddAssetType)
    {
      throw new RuntimeException("Unable to create asset due to assetType. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddMaintenanceTicket = setMaintenanceTicket(aMaintenanceTicket);
    if (!didAddMaintenanceTicket)
    {
      throw new RuntimeException("Unable to create asset due to maintenanceTicket. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPurchaseDate(String aPurchaseDate)
  {
    boolean wasSet = false;
    purchaseDate = aPurchaseDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setExpectedLifespan(int aExpectedLifespan)
  {
    boolean wasSet = false;
    expectedLifespan = aExpectedLifespan;
    wasSet = true;
    return wasSet;
  }

  public String getPurchaseDate()
  {
    return purchaseDate;
  }

  public int getExpectedLifespan()
  {
    return expectedLifespan;
  }

  public int getAssetNumber()
  {
    return assetNumber;
  }
  /* Code from template association_GetOne */
  public Location getAssetLocation()
  {
    return assetLocation;
  }
  /* Code from template association_GetOne */
  public AssetPlus getAssetPlus()
  {
    return assetPlus;
  }
  /* Code from template association_GetOne */
  public Manager getManager()
  {
    return manager;
  }
  /* Code from template association_GetOne */
  public AssetType getAssetType()
  {
    return assetType;
  }
  /* Code from template association_GetOne */
  public MaintenanceTicket getMaintenanceTicket()
  {
    return maintenanceTicket;
  }
  /* Code from template association_SetOneToMany */
  public boolean setAssetPlus(AssetPlus aAssetPlus)
  {
    boolean wasSet = false;
    if (aAssetPlus == null)
    {
      return wasSet;
    }

    AssetPlus existingAssetPlus = assetPlus;
    assetPlus = aAssetPlus;
    if (existingAssetPlus != null && !existingAssetPlus.equals(aAssetPlus))
    {
      existingAssetPlus.removeAsset(this);
    }
    assetPlus.addAsset(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setManager(Manager aManager)
  {
    boolean wasSet = false;
    if (aManager == null)
    {
      return wasSet;
    }

    Manager existingManager = manager;
    manager = aManager;
    if (existingManager != null && !existingManager.equals(aManager))
    {
      existingManager.removeAsset(this);
    }
    manager.addAsset(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setAssetType(AssetType aAssetType)
  {
    boolean wasSet = false;
    if (aAssetType == null)
    {
      return wasSet;
    }

    AssetType existingAssetType = assetType;
    assetType = aAssetType;
    if (existingAssetType != null && !existingAssetType.equals(aAssetType))
    {
      existingAssetType.removeAsset(this);
    }
    assetType.addAsset(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setMaintenanceTicket(MaintenanceTicket aMaintenanceTicket)
  {
    boolean wasSet = false;
    if (aMaintenanceTicket == null)
    {
      return wasSet;
    }

    MaintenanceTicket existingMaintenanceTicket = maintenanceTicket;
    maintenanceTicket = aMaintenanceTicket;
    if (existingMaintenanceTicket != null && !existingMaintenanceTicket.equals(aMaintenanceTicket))
    {
      existingMaintenanceTicket.removeAsset(this);
    }
    maintenanceTicket.addAsset(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Location existingAssetLocation = assetLocation;
    assetLocation = null;
    if (existingAssetLocation != null)
    {
      existingAssetLocation.delete();
    }
    AssetPlus placeholderAssetPlus = assetPlus;
    this.assetPlus = null;
    if(placeholderAssetPlus != null)
    {
      placeholderAssetPlus.removeAsset(this);
    }
    Manager placeholderManager = manager;
    this.manager = null;
    if(placeholderManager != null)
    {
      placeholderManager.removeAsset(this);
    }
    AssetType placeholderAssetType = assetType;
    this.assetType = null;
    if(placeholderAssetType != null)
    {
      placeholderAssetType.removeAsset(this);
    }
    MaintenanceTicket placeholderMaintenanceTicket = maintenanceTicket;
    this.maintenanceTicket = null;
    if(placeholderMaintenanceTicket != null)
    {
      placeholderMaintenanceTicket.removeAsset(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "assetNumber" + ":" + getAssetNumber()+ "," +
            "purchaseDate" + ":" + getPurchaseDate()+ "," +
            "expectedLifespan" + ":" + getExpectedLifespan()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "assetLocation = "+(getAssetLocation()!=null?Integer.toHexString(System.identityHashCode(getAssetLocation())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "assetPlus = "+(getAssetPlus()!=null?Integer.toHexString(System.identityHashCode(getAssetPlus())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "manager = "+(getManager()!=null?Integer.toHexString(System.identityHashCode(getManager())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "assetType = "+(getAssetType()!=null?Integer.toHexString(System.identityHashCode(getAssetType())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "maintenanceTicket = "+(getMaintenanceTicket()!=null?Integer.toHexString(System.identityHashCode(getMaintenanceTicket())):"null");
  }
}