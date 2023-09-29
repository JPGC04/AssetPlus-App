/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

// line 43 "main.ump"
public class Hotel
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Hotel Attributes
  private String name;
  private String adress;
  private String phoneNumber;

  //Hotel Associations
  private List<Floor> floors;
  private AssetPlus assetPlus;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Hotel(String aName, String aAdress, String aPhoneNumber, AssetPlus aAssetPlus)
  {
    name = aName;
    adress = aAdress;
    phoneNumber = aPhoneNumber;
    floors = new ArrayList<Floor>();
    boolean didAddAssetPlus = setAssetPlus(aAssetPlus);
    if (!didAddAssetPlus)
    {
      throw new RuntimeException("Unable to create hotel due to assetPlus. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setAdress(String aAdress)
  {
    boolean wasSet = false;
    adress = aAdress;
    wasSet = true;
    return wasSet;
  }

  public boolean setPhoneNumber(String aPhoneNumber)
  {
    boolean wasSet = false;
    phoneNumber = aPhoneNumber;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public String getAdress()
  {
    return adress;
  }

  public String getPhoneNumber()
  {
    return phoneNumber;
  }
  /* Code from template association_GetMany */
  public Floor getFloor(int index)
  {
    Floor aFloor = floors.get(index);
    return aFloor;
  }

  public List<Floor> getFloors()
  {
    List<Floor> newFloors = Collections.unmodifiableList(floors);
    return newFloors;
  }

  public int numberOfFloors()
  {
    int number = floors.size();
    return number;
  }

  public boolean hasFloors()
  {
    boolean has = floors.size() > 0;
    return has;
  }

  public int indexOfFloor(Floor aFloor)
  {
    int index = floors.indexOf(aFloor);
    return index;
  }
  /* Code from template association_GetOne */
  public AssetPlus getAssetPlus()
  {
    return assetPlus;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfFloorsValid()
  {
    boolean isValid = numberOfFloors() >= minimumNumberOfFloors();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfFloors()
  {
    return 1;
  }
  /* Code from template association_AddMandatoryManyToOne */
  public Floor addFloor(int aLevel, Location aLocation)
  {
    Floor aNewFloor = new Floor(aLevel, this, aLocation);
    return aNewFloor;
  }

  public boolean addFloor(Floor aFloor)
  {
    boolean wasAdded = false;
    if (floors.contains(aFloor)) { return false; }
    Hotel existingHotel = aFloor.getHotel();
    boolean isNewHotel = existingHotel != null && !this.equals(existingHotel);

    if (isNewHotel && existingHotel.numberOfFloors() <= minimumNumberOfFloors())
    {
      return wasAdded;
    }
    if (isNewHotel)
    {
      aFloor.setHotel(this);
    }
    else
    {
      floors.add(aFloor);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeFloor(Floor aFloor)
  {
    boolean wasRemoved = false;
    //Unable to remove aFloor, as it must always have a hotel
    if (this.equals(aFloor.getHotel()))
    {
      return wasRemoved;
    }

    //hotel already at minimum (1)
    if (numberOfFloors() <= minimumNumberOfFloors())
    {
      return wasRemoved;
    }

    floors.remove(aFloor);
    wasRemoved = true;
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addFloorAt(Floor aFloor, int index)
  {  
    boolean wasAdded = false;
    if(addFloor(aFloor))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfFloors()) { index = numberOfFloors() - 1; }
      floors.remove(aFloor);
      floors.add(index, aFloor);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveFloorAt(Floor aFloor, int index)
  {
    boolean wasAdded = false;
    if(floors.contains(aFloor))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfFloors()) { index = numberOfFloors() - 1; }
      floors.remove(aFloor);
      floors.add(index, aFloor);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addFloorAt(aFloor, index);
    }
    return wasAdded;
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
      existingAssetPlus.removeHotel(this);
    }
    assetPlus.addHotel(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    while (floors.size() > 0)
    {
      Floor aFloor = floors.get(floors.size() - 1);
      aFloor.delete();
      floors.remove(aFloor);
    }
    
    AssetPlus placeholderAssetPlus = assetPlus;
    this.assetPlus = null;
    if(placeholderAssetPlus != null)
    {
      placeholderAssetPlus.removeHotel(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "adress" + ":" + getAdress()+ "," +
            "phoneNumber" + ":" + getPhoneNumber()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "assetPlus = "+(getAssetPlus()!=null?Integer.toHexString(System.identityHashCode(getAssetPlus())):"null");
  }
}