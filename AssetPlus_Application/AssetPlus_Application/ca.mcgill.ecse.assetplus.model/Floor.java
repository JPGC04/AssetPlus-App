/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

// line 51 "main.ump"
public class Floor
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Floor Attributes
  private int level;

  //Floor Associations
  private List<Room> rooms;
  private Hotel hotel;
  private Location location;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Floor(int aLevel, Hotel aHotel, Location aLocation)
  {
    level = aLevel;
    rooms = new ArrayList<Room>();
    boolean didAddHotel = setHotel(aHotel);
    if (!didAddHotel)
    {
      throw new RuntimeException("Unable to create floor due to hotel. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (aLocation == null || aLocation.getFloors() != null)
    {
      throw new RuntimeException("Unable to create Floor due to aLocation. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    location = aLocation;
  }

  public Floor(int aLevel, Hotel aHotel, Asset aAssetForLocation)
  {
    level = aLevel;
    rooms = new ArrayList<Room>();
    boolean didAddHotel = setHotel(aHotel);
    if (!didAddHotel)
    {
      throw new RuntimeException("Unable to create floor due to hotel. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    location = new Location(this, aAssetForLocation);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setLevel(int aLevel)
  {
    boolean wasSet = false;
    level = aLevel;
    wasSet = true;
    return wasSet;
  }

  public int getLevel()
  {
    return level;
  }
  /* Code from template association_GetMany */
  public Room getRoom(int index)
  {
    Room aRoom = rooms.get(index);
    return aRoom;
  }

  public List<Room> getRooms()
  {
    List<Room> newRooms = Collections.unmodifiableList(rooms);
    return newRooms;
  }

  public int numberOfRooms()
  {
    int number = rooms.size();
    return number;
  }

  public boolean hasRooms()
  {
    boolean has = rooms.size() > 0;
    return has;
  }

  public int indexOfRoom(Room aRoom)
  {
    int index = rooms.indexOf(aRoom);
    return index;
  }
  /* Code from template association_GetOne */
  public Hotel getHotel()
  {
    return hotel;
  }
  /* Code from template association_GetOne */
  public Location getLocation()
  {
    return location;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfRoomsValid()
  {
    boolean isValid = numberOfRooms() >= minimumNumberOfRooms();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfRooms()
  {
    return 1;
  }
  /* Code from template association_AddMandatoryManyToOne */
  public Room addRoom(int aNumber, Location aLocation)
  {
    Room aNewRoom = new Room(aNumber, this, aLocation);
    return aNewRoom;
  }

  public boolean addRoom(Room aRoom)
  {
    boolean wasAdded = false;
    if (rooms.contains(aRoom)) { return false; }
    Floor existingFloor = aRoom.getFloor();
    boolean isNewFloor = existingFloor != null && !this.equals(existingFloor);

    if (isNewFloor && existingFloor.numberOfRooms() <= minimumNumberOfRooms())
    {
      return wasAdded;
    }
    if (isNewFloor)
    {
      aRoom.setFloor(this);
    }
    else
    {
      rooms.add(aRoom);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeRoom(Room aRoom)
  {
    boolean wasRemoved = false;
    //Unable to remove aRoom, as it must always have a floor
    if (this.equals(aRoom.getFloor()))
    {
      return wasRemoved;
    }

    //floor already at minimum (1)
    if (numberOfRooms() <= minimumNumberOfRooms())
    {
      return wasRemoved;
    }

    rooms.remove(aRoom);
    wasRemoved = true;
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addRoomAt(Room aRoom, int index)
  {  
    boolean wasAdded = false;
    if(addRoom(aRoom))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRooms()) { index = numberOfRooms() - 1; }
      rooms.remove(aRoom);
      rooms.add(index, aRoom);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveRoomAt(Room aRoom, int index)
  {
    boolean wasAdded = false;
    if(rooms.contains(aRoom))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRooms()) { index = numberOfRooms() - 1; }
      rooms.remove(aRoom);
      rooms.add(index, aRoom);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addRoomAt(aRoom, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMandatoryMany */
  public boolean setHotel(Hotel aHotel)
  {
    boolean wasSet = false;
    //Must provide hotel to floor
    if (aHotel == null)
    {
      return wasSet;
    }

    if (hotel != null && hotel.numberOfFloors() <= Hotel.minimumNumberOfFloors())
    {
      return wasSet;
    }

    Hotel existingHotel = hotel;
    hotel = aHotel;
    if (existingHotel != null && !existingHotel.equals(aHotel))
    {
      boolean didRemove = existingHotel.removeFloor(this);
      if (!didRemove)
      {
        hotel = existingHotel;
        return wasSet;
      }
    }
    hotel.addFloor(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    while (rooms.size() > 0)
    {
      Room aRoom = rooms.get(rooms.size() - 1);
      aRoom.delete();
      rooms.remove(aRoom);
    }
    
    Hotel placeholderHotel = hotel;
    this.hotel = null;
    if(placeholderHotel != null)
    {
      placeholderHotel.removeFloor(this);
    }
    Location existingLocation = location;
    location = null;
    if (existingLocation != null)
    {
      existingLocation.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "level" + ":" + getLevel()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "hotel = "+(getHotel()!=null?Integer.toHexString(System.identityHashCode(getHotel())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "location = "+(getLocation()!=null?Integer.toHexString(System.identityHashCode(getLocation())):"null");
  }
}