/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

// line 29 "main.ump"
public class Manager extends Employee
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Manager theInstance = null;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Manager Attributes
  private String email;
  private String passwor;

  //Manager Associations
  private List<Asset> assets;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  private Manager(String aEmail, String aPassword, AssetPlus aAssetPlus, MaintenanceTicket aMaintenanceTicket)
  {
    super(aEmail, aPassword, aAssetPlus, aMaintenanceTicket);
    email = "manager@map.com";
    resetPasswor();
    assets = new ArrayList<Asset>();
  }

  public static Manager getInstance()
  {
    if(theInstance == null)
    {
      theInstance = new Manager();
    }
    return theInstance;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setEmail(String aEmail)
  {
    boolean wasSet = false;
    email = aEmail;
    wasSet = true;
    return wasSet;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setPasswor(String aPasswor)
  {
    boolean wasSet = false;
    passwor = aPasswor;
    wasSet = true;
    return wasSet;
  }

  public boolean resetPasswor()
  {
    boolean wasReset = false;
    passwor = getDefaultPasswor();
    wasReset = true;
    return wasReset;
  }

  public String getEmail()
  {
    return email;
  }

  public String getPasswor()
  {
    return passwor;
  }
  /* Code from template attribute_GetDefaulted */
  public String getDefaultPasswor()
  {
    return "manager";
  }
  /* Code from template association_GetMany */
  public Asset getAsset(int index)
  {
    Asset aAsset = assets.get(index);
    return aAsset;
  }

  public List<Asset> getAssets()
  {
    List<Asset> newAssets = Collections.unmodifiableList(assets);
    return newAssets;
  }

  public int numberOfAssets()
  {
    int number = assets.size();
    return number;
  }

  public boolean hasAssets()
  {
    boolean has = assets.size() > 0;
    return has;
  }

  public int indexOfAsset(Asset aAsset)
  {
    int index = assets.indexOf(aAsset);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAssets()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Asset addAsset(String aPurchaseDate, int aExpectedLifespan, Location aAssetLocation, AssetPlus aAssetPlus, AssetType aAssetType, MaintenanceTicket aMaintenanceTicket)
  {
    return new Asset(aPurchaseDate, aExpectedLifespan, aAssetLocation, aAssetPlus, this, aAssetType, aMaintenanceTicket);
  }

  public boolean addAsset(Asset aAsset)
  {
    boolean wasAdded = false;
    if (assets.contains(aAsset)) { return false; }
    Manager existingManager = aAsset.getManager();
    boolean isNewManager = existingManager != null && !this.equals(existingManager);
    if (isNewManager)
    {
      aAsset.setManager(this);
    }
    else
    {
      assets.add(aAsset);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAsset(Asset aAsset)
  {
    boolean wasRemoved = false;
    //Unable to remove aAsset, as it must always have a manager
    if (!this.equals(aAsset.getManager()))
    {
      assets.remove(aAsset);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAssetAt(Asset aAsset, int index)
  {  
    boolean wasAdded = false;
    if(addAsset(aAsset))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAssets()) { index = numberOfAssets() - 1; }
      assets.remove(aAsset);
      assets.add(index, aAsset);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAssetAt(Asset aAsset, int index)
  {
    boolean wasAdded = false;
    if(assets.contains(aAsset))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAssets()) { index = numberOfAssets() - 1; }
      assets.remove(aAsset);
      assets.add(index, aAsset);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAssetAt(aAsset, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=assets.size(); i > 0; i--)
    {
      Asset aAsset = assets.get(i - 1);
      aAsset.delete();
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "email" + ":" + getEmail()+ "," +
            "passwor" + ":" + getPasswor()+ "]";
  }
}