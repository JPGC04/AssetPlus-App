/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/



// line 58 "main.ump"
public class Room
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Room Attributes
  private int number;

  //Room Associations
  private Floor floor;
  private Location location;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Room(int aNumber, Floor aFloor, Location aLocation)
  {
    number = aNumber;
    boolean didAddFloor = setFloor(aFloor);
    if (!didAddFloor)
    {
      throw new RuntimeException("Unable to create room due to floor. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddLocation = setLocation(aLocation);
    if (!didAddLocation)
    {
      throw new RuntimeException("Unable to create room due to location. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNumber(int aNumber)
  {
    boolean wasSet = false;
    number = aNumber;
    wasSet = true;
    return wasSet;
  }

  public int getNumber()
  {
    return number;
  }
  /* Code from template association_GetOne */
  public Floor getFloor()
  {
    return floor;
  }
  /* Code from template association_GetOne */
  public Location getLocation()
  {
    return location;
  }
  /* Code from template association_SetOneToMandatoryMany */
  public boolean setFloor(Floor aFloor)
  {
    boolean wasSet = false;
    //Must provide floor to room
    if (aFloor == null)
    {
      return wasSet;
    }

    if (floor != null && floor.numberOfRooms() <= Floor.minimumNumberOfRooms())
    {
      return wasSet;
    }

    Floor existingFloor = floor;
    floor = aFloor;
    if (existingFloor != null && !existingFloor.equals(aFloor))
    {
      boolean didRemove = existingFloor.removeRoom(this);
      if (!didRemove)
      {
        floor = existingFloor;
        return wasSet;
      }
    }
    floor.addRoom(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setLocation(Location aNewLocation)
  {
    boolean wasSet = false;
    if (aNewLocation == null)
    {
      //Unable to setLocation to null, as room must always be associated to a location
      return wasSet;
    }
    
    Room existingRoom = aNewLocation.getRoom();
    if (existingRoom != null && !equals(existingRoom))
    {
      //Unable to setLocation, the current location already has a room, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    Location anOldLocation = location;
    location = aNewLocation;
    location.setRoom(this);

    if (anOldLocation != null)
    {
      anOldLocation.setRoom(null);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Floor placeholderFloor = floor;
    this.floor = null;
    if(placeholderFloor != null)
    {
      placeholderFloor.removeRoom(this);
    }
    Location existingLocation = location;
    location = null;
    if (existingLocation != null)
    {
      existingLocation.setRoom(null);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "number" + ":" + getNumber()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "floor = "+(getFloor()!=null?Integer.toHexString(System.identityHashCode(getFloor())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "location = "+(getLocation()!=null?Integer.toHexString(System.identityHashCode(getLocation())):"null");
  }
}