/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

// line 91 "main.ump"
public class MaintenanceTicket
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum PriorityLevel { URGENT, NORMAL, LOW }
  public enum TimeEstimate { LESS_THAN_A_DAY, ONE_TO_THREE_DAYS, THREE_TO_SEVEN_DAYS, ONE_TO_THREE_WEEKS, THREE_PLUS_WEEKS }
  public enum Status { OPEN, CLOSED, IN_PROGRESS, WAITING_FOR_MANAGER_APPROVAL }

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextTicketNumber = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MaintenanceTicket Attributes
  private String maintenanceStart;
  private boolean managerApprovalRequired;
  private String creationDate;
  private String description;
  private Status status;
  private TimeEstimate timeEstimate;
  private PriorityLevel priorityLevel;
  private String maintenanceEnd;

  //Autounique Attributes
  private int ticketNumber;

  //MaintenanceTicket Associations
  private User author;
  private List<Asset> asset;
  private List<ImageUrl> imageURLs;
  private List<MaintenanceNote> maintenanceNotes;
  private Employee assignedEmployee;
  private AssetPlus assetPlus;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MaintenanceTicket(String aCreationDate, String aDescription, User aAuthor, AssetPlus aAssetPlus)
  {
    maintenanceStart = null;
    managerApprovalRequired = false;
    creationDate = aCreationDate;
    description = aDescription;
    status = null;
    maintenanceEnd = null;
    ticketNumber = nextTicketNumber++;
    boolean didAddAuthor = setAuthor(aAuthor);
    if (!didAddAuthor)
    {
      throw new RuntimeException("Unable to create maintenanceTicket due to author. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    asset = new ArrayList<Asset>();
    imageURLs = new ArrayList<ImageUrl>();
    maintenanceNotes = new ArrayList<MaintenanceNote>();
    boolean didAddAssetPlus = setAssetPlus(aAssetPlus);
    if (!didAddAssetPlus)
    {
      throw new RuntimeException("Unable to create ticket due to assetPlus. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setMaintenanceStart(String aMaintenanceStart)
  {
    boolean wasSet = false;
    maintenanceStart = aMaintenanceStart;
    wasSet = true;
    return wasSet;
  }

  public boolean setManagerApprovalRequired(boolean aManagerApprovalRequired)
  {
    boolean wasSet = false;
    managerApprovalRequired = aManagerApprovalRequired;
    wasSet = true;
    return wasSet;
  }

  public boolean setCreationDate(String aCreationDate)
  {
    boolean wasSet = false;
    creationDate = aCreationDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setDescription(String aDescription)
  {
    boolean wasSet = false;
    description = aDescription;
    wasSet = true;
    return wasSet;
  }

  public boolean setStatus(Status aStatus)
  {
    boolean wasSet = false;
    status = aStatus;
    wasSet = true;
    return wasSet;
  }

  public boolean setTimeEstimate(TimeEstimate aTimeEstimate)
  {
    boolean wasSet = false;
    timeEstimate = aTimeEstimate;
    wasSet = true;
    return wasSet;
  }

  public boolean setPriorityLevel(PriorityLevel aPriorityLevel)
  {
    boolean wasSet = false;
    priorityLevel = aPriorityLevel;
    wasSet = true;
    return wasSet;
  }

  public boolean setMaintenanceEnd(String aMaintenanceEnd)
  {
    boolean wasSet = false;
    maintenanceEnd = aMaintenanceEnd;
    wasSet = true;
    return wasSet;
  }

  public String getMaintenanceStart()
  {
    return maintenanceStart;
  }

  public boolean getManagerApprovalRequired()
  {
    return managerApprovalRequired;
  }

  public String getCreationDate()
  {
    return creationDate;
  }

  public String getDescription()
  {
    return description;
  }

  public Status getStatus()
  {
    return status;
  }

  public TimeEstimate getTimeEstimate()
  {
    return timeEstimate;
  }

  public PriorityLevel getPriorityLevel()
  {
    return priorityLevel;
  }

  public String getMaintenanceEnd()
  {
    return maintenanceEnd;
  }

  public int getTicketNumber()
  {
    return ticketNumber;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isManagerApprovalRequired()
  {
    return managerApprovalRequired;
  }
  /* Code from template association_GetOne */
  public User getAuthor()
  {
    return author;
  }
  /* Code from template association_GetMany */
  public Asset getAsset(int index)
  {
    Asset aAsset = asset.get(index);
    return aAsset;
  }

  public List<Asset> getAsset()
  {
    List<Asset> newAsset = Collections.unmodifiableList(asset);
    return newAsset;
  }

  public int numberOfAsset()
  {
    int number = asset.size();
    return number;
  }

  public boolean hasAsset()
  {
    boolean has = asset.size() > 0;
    return has;
  }

  public int indexOfAsset(Asset aAsset)
  {
    int index = asset.indexOf(aAsset);
    return index;
  }
  /* Code from template association_GetMany */
  public ImageUrl getImageURL(int index)
  {
    ImageUrl aImageURL = imageURLs.get(index);
    return aImageURL;
  }

  public List<ImageUrl> getImageURLs()
  {
    List<ImageUrl> newImageURLs = Collections.unmodifiableList(imageURLs);
    return newImageURLs;
  }

  public int numberOfImageURLs()
  {
    int number = imageURLs.size();
    return number;
  }

  public boolean hasImageURLs()
  {
    boolean has = imageURLs.size() > 0;
    return has;
  }

  public int indexOfImageURL(ImageUrl aImageURL)
  {
    int index = imageURLs.indexOf(aImageURL);
    return index;
  }
  /* Code from template association_GetMany */
  public MaintenanceNote getMaintenanceNote(int index)
  {
    MaintenanceNote aMaintenanceNote = maintenanceNotes.get(index);
    return aMaintenanceNote;
  }

  public List<MaintenanceNote> getMaintenanceNotes()
  {
    List<MaintenanceNote> newMaintenanceNotes = Collections.unmodifiableList(maintenanceNotes);
    return newMaintenanceNotes;
  }

  public int numberOfMaintenanceNotes()
  {
    int number = maintenanceNotes.size();
    return number;
  }

  public boolean hasMaintenanceNotes()
  {
    boolean has = maintenanceNotes.size() > 0;
    return has;
  }

  public int indexOfMaintenanceNote(MaintenanceNote aMaintenanceNote)
  {
    int index = maintenanceNotes.indexOf(aMaintenanceNote);
    return index;
  }
  /* Code from template association_GetOne */
  public Employee getAssignedEmployee()
  {
    return assignedEmployee;
  }

  public boolean hasAssignedEmployee()
  {
    boolean has = assignedEmployee != null;
    return has;
  }
  /* Code from template association_GetOne */
  public AssetPlus getAssetPlus()
  {
    return assetPlus;
  }
  /* Code from template association_SetOneToMany */
  public boolean setAuthor(User aAuthor)
  {
    boolean wasSet = false;
    if (aAuthor == null)
    {
      return wasSet;
    }

    User existingAuthor = author;
    author = aAuthor;
    if (existingAuthor != null && !existingAuthor.equals(aAuthor))
    {
      existingAuthor.removeMaintenanceTicket(this);
    }
    author.addMaintenanceTicket(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAsset()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Asset addAsset(String aPurchaseDate, int aExpectedLifespan, Location aAssetLocation, AssetPlus aAssetPlus, Manager aManager, AssetType aAssetType)
  {
    return new Asset(aPurchaseDate, aExpectedLifespan, aAssetLocation, aAssetPlus, aManager, aAssetType, this);
  }

  public boolean addAsset(Asset aAsset)
  {
    boolean wasAdded = false;
    if (asset.contains(aAsset)) { return false; }
    MaintenanceTicket existingMaintenanceTicket = aAsset.getMaintenanceTicket();
    boolean isNewMaintenanceTicket = existingMaintenanceTicket != null && !this.equals(existingMaintenanceTicket);
    if (isNewMaintenanceTicket)
    {
      aAsset.setMaintenanceTicket(this);
    }
    else
    {
      asset.add(aAsset);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAsset(Asset aAsset)
  {
    boolean wasRemoved = false;
    //Unable to remove aAsset, as it must always have a maintenanceTicket
    if (!this.equals(aAsset.getMaintenanceTicket()))
    {
      asset.remove(aAsset);
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
      if(index > numberOfAsset()) { index = numberOfAsset() - 1; }
      asset.remove(aAsset);
      asset.add(index, aAsset);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAssetAt(Asset aAsset, int index)
  {
    boolean wasAdded = false;
    if(asset.contains(aAsset))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAsset()) { index = numberOfAsset() - 1; }
      asset.remove(aAsset);
      asset.add(index, aAsset);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAssetAt(aAsset, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfImageURLs()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public ImageUrl addImageURL(String aUrl)
  {
    return new ImageUrl(aUrl, this);
  }

  public boolean addImageURL(ImageUrl aImageURL)
  {
    boolean wasAdded = false;
    if (imageURLs.contains(aImageURL)) { return false; }
    MaintenanceTicket existingMaintenanceTicket = aImageURL.getMaintenanceTicket();
    boolean isNewMaintenanceTicket = existingMaintenanceTicket != null && !this.equals(existingMaintenanceTicket);
    if (isNewMaintenanceTicket)
    {
      aImageURL.setMaintenanceTicket(this);
    }
    else
    {
      imageURLs.add(aImageURL);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeImageURL(ImageUrl aImageURL)
  {
    boolean wasRemoved = false;
    //Unable to remove aImageURL, as it must always have a maintenanceTicket
    if (!this.equals(aImageURL.getMaintenanceTicket()))
    {
      imageURLs.remove(aImageURL);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addImageURLAt(ImageUrl aImageURL, int index)
  {  
    boolean wasAdded = false;
    if(addImageURL(aImageURL))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfImageURLs()) { index = numberOfImageURLs() - 1; }
      imageURLs.remove(aImageURL);
      imageURLs.add(index, aImageURL);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveImageURLAt(ImageUrl aImageURL, int index)
  {
    boolean wasAdded = false;
    if(imageURLs.contains(aImageURL))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfImageURLs()) { index = numberOfImageURLs() - 1; }
      imageURLs.remove(aImageURL);
      imageURLs.add(index, aImageURL);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addImageURLAt(aImageURL, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfMaintenanceNotes()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public MaintenanceNote addMaintenanceNote(String aDate, String aDescription, Employee aEmployee)
  {
    return new MaintenanceNote(aDate, aDescription, aEmployee, this);
  }

  public boolean addMaintenanceNote(MaintenanceNote aMaintenanceNote)
  {
    boolean wasAdded = false;
    if (maintenanceNotes.contains(aMaintenanceNote)) { return false; }
    MaintenanceTicket existingMaintenanceTicket = aMaintenanceNote.getMaintenanceTicket();
    boolean isNewMaintenanceTicket = existingMaintenanceTicket != null && !this.equals(existingMaintenanceTicket);
    if (isNewMaintenanceTicket)
    {
      aMaintenanceNote.setMaintenanceTicket(this);
    }
    else
    {
      maintenanceNotes.add(aMaintenanceNote);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeMaintenanceNote(MaintenanceNote aMaintenanceNote)
  {
    boolean wasRemoved = false;
    //Unable to remove aMaintenanceNote, as it must always have a maintenanceTicket
    if (!this.equals(aMaintenanceNote.getMaintenanceTicket()))
    {
      maintenanceNotes.remove(aMaintenanceNote);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addMaintenanceNoteAt(MaintenanceNote aMaintenanceNote, int index)
  {  
    boolean wasAdded = false;
    if(addMaintenanceNote(aMaintenanceNote))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMaintenanceNotes()) { index = numberOfMaintenanceNotes() - 1; }
      maintenanceNotes.remove(aMaintenanceNote);
      maintenanceNotes.add(index, aMaintenanceNote);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveMaintenanceNoteAt(MaintenanceNote aMaintenanceNote, int index)
  {
    boolean wasAdded = false;
    if(maintenanceNotes.contains(aMaintenanceNote))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMaintenanceNotes()) { index = numberOfMaintenanceNotes() - 1; }
      maintenanceNotes.remove(aMaintenanceNote);
      maintenanceNotes.add(index, aMaintenanceNote);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addMaintenanceNoteAt(aMaintenanceNote, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOptionalOneToOne */
  public boolean setAssignedEmployee(Employee aNewAssignedEmployee)
  {
    boolean wasSet = false;
    if (assignedEmployee != null && !assignedEmployee.equals(aNewAssignedEmployee) && equals(assignedEmployee.getMaintenanceTicket()))
    {
      //Unable to setAssignedEmployee, as existing assignedEmployee would become an orphan
      return wasSet;
    }

    assignedEmployee = aNewAssignedEmployee;
    MaintenanceTicket anOldMaintenanceTicket = aNewAssignedEmployee != null ? aNewAssignedEmployee.getMaintenanceTicket() : null;

    if (!this.equals(anOldMaintenanceTicket))
    {
      if (anOldMaintenanceTicket != null)
      {
        anOldMaintenanceTicket.assignedEmployee = null;
      }
      if (assignedEmployee != null)
      {
        assignedEmployee.setMaintenanceTicket(this);
      }
    }
    wasSet = true;
    return wasSet;
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
      existingAssetPlus.removeTicket(this);
    }
    assetPlus.addTicket(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    User placeholderAuthor = author;
    this.author = null;
    if(placeholderAuthor != null)
    {
      placeholderAuthor.removeMaintenanceTicket(this);
    }
    for(int i=asset.size(); i > 0; i--)
    {
      Asset aAsset = asset.get(i - 1);
      aAsset.delete();
    }
    for(int i=imageURLs.size(); i > 0; i--)
    {
      ImageUrl aImageURL = imageURLs.get(i - 1);
      aImageURL.delete();
    }
    while (maintenanceNotes.size() > 0)
    {
      MaintenanceNote aMaintenanceNote = maintenanceNotes.get(maintenanceNotes.size() - 1);
      aMaintenanceNote.delete();
      maintenanceNotes.remove(aMaintenanceNote);
    }
    
    Employee existingAssignedEmployee = assignedEmployee;
    assignedEmployee = null;
    if (existingAssignedEmployee != null)
    {
      existingAssignedEmployee.delete();
    }
    AssetPlus placeholderAssetPlus = assetPlus;
    this.assetPlus = null;
    if(placeholderAssetPlus != null)
    {
      placeholderAssetPlus.removeTicket(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "ticketNumber" + ":" + getTicketNumber()+ "," +
            "maintenanceStart" + ":" + getMaintenanceStart()+ "," +
            "managerApprovalRequired" + ":" + getManagerApprovalRequired()+ "," +
            "creationDate" + ":" + getCreationDate()+ "," +
            "description" + ":" + getDescription()+ "," +
            "maintenanceEnd" + ":" + getMaintenanceEnd()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "status" + "=" + (getStatus() != null ? !getStatus().equals(this)  ? getStatus().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "timeEstimate" + "=" + (getTimeEstimate() != null ? !getTimeEstimate().equals(this)  ? getTimeEstimate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "priorityLevel" + "=" + (getPriorityLevel() != null ? !getPriorityLevel().equals(this)  ? getPriorityLevel().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "author = "+(getAuthor()!=null?Integer.toHexString(System.identityHashCode(getAuthor())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "assignedEmployee = "+(getAssignedEmployee()!=null?Integer.toHexString(System.identityHashCode(getAssignedEmployee())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "assetPlus = "+(getAssetPlus()!=null?Integer.toHexString(System.identityHashCode(getAssetPlus())):"null");
  }
}