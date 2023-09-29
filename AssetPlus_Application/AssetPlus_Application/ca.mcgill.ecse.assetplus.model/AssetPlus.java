/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

// line 1 "main.ump"
public class AssetPlus
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //AssetPlus Associations
  private List<User> users;
  private List<MaintenanceTicket> ticket;
  private List<Hotel> hotels;
  private List<Asset> assets;
  private List<AssetType> assetTypes;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public AssetPlus()
  {
    users = new ArrayList<User>();
    ticket = new ArrayList<MaintenanceTicket>();
    hotels = new ArrayList<Hotel>();
    assets = new ArrayList<Asset>();
    assetTypes = new ArrayList<AssetType>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public User getUser(int index)
  {
    User aUser = users.get(index);
    return aUser;
  }

  public List<User> getUsers()
  {
    List<User> newUsers = Collections.unmodifiableList(users);
    return newUsers;
  }

  public int numberOfUsers()
  {
    int number = users.size();
    return number;
  }

  public boolean hasUsers()
  {
    boolean has = users.size() > 0;
    return has;
  }

  public int indexOfUser(User aUser)
  {
    int index = users.indexOf(aUser);
    return index;
  }
  /* Code from template association_GetMany */
  public MaintenanceTicket getTicket(int index)
  {
    MaintenanceTicket aTicket = ticket.get(index);
    return aTicket;
  }

  public List<MaintenanceTicket> getTicket()
  {
    List<MaintenanceTicket> newTicket = Collections.unmodifiableList(ticket);
    return newTicket;
  }

  public int numberOfTicket()
  {
    int number = ticket.size();
    return number;
  }

  public boolean hasTicket()
  {
    boolean has = ticket.size() > 0;
    return has;
  }

  public int indexOfTicket(MaintenanceTicket aTicket)
  {
    int index = ticket.indexOf(aTicket);
    return index;
  }
  /* Code from template association_GetMany */
  public Hotel getHotel(int index)
  {
    Hotel aHotel = hotels.get(index);
    return aHotel;
  }

  public List<Hotel> getHotels()
  {
    List<Hotel> newHotels = Collections.unmodifiableList(hotels);
    return newHotels;
  }

  public int numberOfHotels()
  {
    int number = hotels.size();
    return number;
  }

  public boolean hasHotels()
  {
    boolean has = hotels.size() > 0;
    return has;
  }

  public int indexOfHotel(Hotel aHotel)
  {
    int index = hotels.indexOf(aHotel);
    return index;
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
  /* Code from template association_GetMany */
  public AssetType getAssetType(int index)
  {
    AssetType aAssetType = assetTypes.get(index);
    return aAssetType;
  }

  public List<AssetType> getAssetTypes()
  {
    List<AssetType> newAssetTypes = Collections.unmodifiableList(assetTypes);
    return newAssetTypes;
  }

  public int numberOfAssetTypes()
  {
    int number = assetTypes.size();
    return number;
  }

  public boolean hasAssetTypes()
  {
    boolean has = assetTypes.size() > 0;
    return has;
  }

  public int indexOfAssetType(AssetType aAssetType)
  {
    int index = assetTypes.indexOf(aAssetType);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfUsers()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */


  public boolean addUser(User aUser)
  {
    boolean wasAdded = false;
    if (users.contains(aUser)) { return false; }
    AssetPlus existingAssetPlus = aUser.getAssetPlus();
    boolean isNewAssetPlus = existingAssetPlus != null && !this.equals(existingAssetPlus);
    if (isNewAssetPlus)
    {
      aUser.setAssetPlus(this);
    }
    else
    {
      users.add(aUser);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeUser(User aUser)
  {
    boolean wasRemoved = false;
    //Unable to remove aUser, as it must always have a assetPlus
    if (!this.equals(aUser.getAssetPlus()))
    {
      users.remove(aUser);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addUserAt(User aUser, int index)
  {  
    boolean wasAdded = false;
    if(addUser(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUsers()) { index = numberOfUsers() - 1; }
      users.remove(aUser);
      users.add(index, aUser);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveUserAt(User aUser, int index)
  {
    boolean wasAdded = false;
    if(users.contains(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUsers()) { index = numberOfUsers() - 1; }
      users.remove(aUser);
      users.add(index, aUser);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addUserAt(aUser, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfTicket()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public MaintenanceTicket addTicket(String aCreationDate, String aDescription, User aAuthor)
  {
    return new MaintenanceTicket(aCreationDate, aDescription, aAuthor, this);
  }

  public boolean addTicket(MaintenanceTicket aTicket)
  {
    boolean wasAdded = false;
    if (ticket.contains(aTicket)) { return false; }
    AssetPlus existingAssetPlus = aTicket.getAssetPlus();
    boolean isNewAssetPlus = existingAssetPlus != null && !this.equals(existingAssetPlus);
    if (isNewAssetPlus)
    {
      aTicket.setAssetPlus(this);
    }
    else
    {
      ticket.add(aTicket);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTicket(MaintenanceTicket aTicket)
  {
    boolean wasRemoved = false;
    //Unable to remove aTicket, as it must always have a assetPlus
    if (!this.equals(aTicket.getAssetPlus()))
    {
      ticket.remove(aTicket);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addTicketAt(MaintenanceTicket aTicket, int index)
  {  
    boolean wasAdded = false;
    if(addTicket(aTicket))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTicket()) { index = numberOfTicket() - 1; }
      ticket.remove(aTicket);
      ticket.add(index, aTicket);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTicketAt(MaintenanceTicket aTicket, int index)
  {
    boolean wasAdded = false;
    if(ticket.contains(aTicket))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTicket()) { index = numberOfTicket() - 1; }
      ticket.remove(aTicket);
      ticket.add(index, aTicket);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTicketAt(aTicket, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfHotels()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Hotel addHotel(String aName, String aAdress, String aPhoneNumber)
  {
    return new Hotel(aName, aAdress, aPhoneNumber, this);
  }

  public boolean addHotel(Hotel aHotel)
  {
    boolean wasAdded = false;
    if (hotels.contains(aHotel)) { return false; }
    AssetPlus existingAssetPlus = aHotel.getAssetPlus();
    boolean isNewAssetPlus = existingAssetPlus != null && !this.equals(existingAssetPlus);
    if (isNewAssetPlus)
    {
      aHotel.setAssetPlus(this);
    }
    else
    {
      hotels.add(aHotel);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeHotel(Hotel aHotel)
  {
    boolean wasRemoved = false;
    //Unable to remove aHotel, as it must always have a assetPlus
    if (!this.equals(aHotel.getAssetPlus()))
    {
      hotels.remove(aHotel);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addHotelAt(Hotel aHotel, int index)
  {  
    boolean wasAdded = false;
    if(addHotel(aHotel))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfHotels()) { index = numberOfHotels() - 1; }
      hotels.remove(aHotel);
      hotels.add(index, aHotel);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveHotelAt(Hotel aHotel, int index)
  {
    boolean wasAdded = false;
    if(hotels.contains(aHotel))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfHotels()) { index = numberOfHotels() - 1; }
      hotels.remove(aHotel);
      hotels.add(index, aHotel);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addHotelAt(aHotel, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAssets()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Asset addAsset(String aPurchaseDate, int aExpectedLifespan, Location aAssetLocation, Manager aManager, AssetType aAssetType, MaintenanceTicket aMaintenanceTicket)
  {
    return new Asset(aPurchaseDate, aExpectedLifespan, aAssetLocation, this, aManager, aAssetType, aMaintenanceTicket);
  }

  public boolean addAsset(Asset aAsset)
  {
    boolean wasAdded = false;
    if (assets.contains(aAsset)) { return false; }
    AssetPlus existingAssetPlus = aAsset.getAssetPlus();
    boolean isNewAssetPlus = existingAssetPlus != null && !this.equals(existingAssetPlus);
    if (isNewAssetPlus)
    {
      aAsset.setAssetPlus(this);
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
    //Unable to remove aAsset, as it must always have a assetPlus
    if (!this.equals(aAsset.getAssetPlus()))
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAssetTypes()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public AssetType addAssetType(String aName)
  {
    return new AssetType(aName, this);
  }

  public boolean addAssetType(AssetType aAssetType)
  {
    boolean wasAdded = false;
    if (assetTypes.contains(aAssetType)) { return false; }
    AssetPlus existingAssetPlus = aAssetType.getAssetPlus();
    boolean isNewAssetPlus = existingAssetPlus != null && !this.equals(existingAssetPlus);
    if (isNewAssetPlus)
    {
      aAssetType.setAssetPlus(this);
    }
    else
    {
      assetTypes.add(aAssetType);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAssetType(AssetType aAssetType)
  {
    boolean wasRemoved = false;
    //Unable to remove aAssetType, as it must always have a assetPlus
    if (!this.equals(aAssetType.getAssetPlus()))
    {
      assetTypes.remove(aAssetType);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAssetTypeAt(AssetType aAssetType, int index)
  {  
    boolean wasAdded = false;
    if(addAssetType(aAssetType))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAssetTypes()) { index = numberOfAssetTypes() - 1; }
      assetTypes.remove(aAssetType);
      assetTypes.add(index, aAssetType);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAssetTypeAt(AssetType aAssetType, int index)
  {
    boolean wasAdded = false;
    if(assetTypes.contains(aAssetType))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAssetTypes()) { index = numberOfAssetTypes() - 1; }
      assetTypes.remove(aAssetType);
      assetTypes.add(index, aAssetType);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAssetTypeAt(aAssetType, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (users.size() > 0)
    {
      User aUser = users.get(users.size() - 1);
      aUser.delete();
      users.remove(aUser);
    }
    
    while (ticket.size() > 0)
    {
      MaintenanceTicket aTicket = ticket.get(ticket.size() - 1);
      aTicket.delete();
      ticket.remove(aTicket);
    }
    
    while (hotels.size() > 0)
    {
      Hotel aHotel = hotels.get(hotels.size() - 1);
      aHotel.delete();
      hotels.remove(aHotel);
    }
    
    while (assets.size() > 0)
    {
      Asset aAsset = assets.get(assets.size() - 1);
      aAsset.delete();
      assets.remove(aAsset);
    }
    
    while (assetTypes.size() > 0)
    {
      AssetType aAssetType = assetTypes.get(assetTypes.size() - 1);
      aAssetType.delete();
      assetTypes.remove(aAssetType);
    }
    
  }

}