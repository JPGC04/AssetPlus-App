/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/



// line 64 "main.ump"
public class Location
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Location Associations
  private Room room;
  private Floor floors;
  private Asset asset;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Location(Floor aFloors, Asset aAsset)
  {
    if (aFloors == null || aFloors.getLocation() != null)
    {
      throw new RuntimeException("Unable to create Location due to aFloors. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    floors = aFloors;
    if (aAsset == null || aAsset.getAssetLocation() != null)
    {
      throw new RuntimeException("Unable to create Location due to aAsset. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    asset = aAsset;
  }

  public Location(int aLevelForFloors, Hotel aHotelForFloors, String aPurchaseDateForAsset, int aExpectedLifespanForAsset, AssetPlus aAssetPlusForAsset, Manager aManagerForAsset, AssetType aAssetTypeForAsset, MaintenanceTicket aMaintenanceTicketForAsset)
  {
    floors = new Floor(aLevelForFloors, aHotelForFloors, this);
    asset = new Asset(aPurchaseDateForAsset, aExpectedLifespanForAsset, this, aAssetPlusForAsset, aManagerForAsset, aAssetTypeForAsset, aMaintenanceTicketForAsset);
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public Room getRoom()
  {
    return room;
  }

  public boolean hasRoom()
  {
    boolean has = room != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Floor getFloors()
  {
    return floors;
  }
  /* Code from template association_GetOne */
  public Asset getAsset()
  {
    return asset;
  }
  /* Code from template association_SetOptionalOneToOne */
  public boolean setRoom(Room aNewRoom)
  {
    boolean wasSet = false;
    if (room != null && !room.equals(aNewRoom) && equals(room.getLocation()))
    {
      //Unable to setRoom, as existing room would become an orphan
      return wasSet;
    }

    room = aNewRoom;
    Location anOldLocation = aNewRoom != null ? aNewRoom.getLocation() : null;

    if (!this.equals(anOldLocation))
    {
      if (anOldLocation != null)
      {
        anOldLocation.room = null;
      }
      if (room != null)
      {
        room.setLocation(this);
      }
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Room existingRoom = room;
    room = null;
    if (existingRoom != null)
    {
      existingRoom.delete();
    }
    Floor existingFloors = floors;
    floors = null;
    if (existingFloors != null)
    {
      existingFloors.delete();
    }
    Asset existingAsset = asset;
    asset = null;
    if (existingAsset != null)
    {
      existingAsset.delete();
    }
  }

}