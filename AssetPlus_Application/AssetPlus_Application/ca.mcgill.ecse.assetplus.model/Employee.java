/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

// line 19 "main.ump"
public class Employee extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Employee Associations
  private MaintenanceTicket maintenanceTicket;
  private List<MaintenanceNote> maintenanceNotes;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Employee(String aEmail, String aPassword, AssetPlus aAssetPlus, MaintenanceTicket aMaintenanceTicket)
  {
    super(aEmail, aPassword, aAssetPlus);
    boolean didAddMaintenanceTicket = setMaintenanceTicket(aMaintenanceTicket);
    if (!didAddMaintenanceTicket)
    {
      throw new RuntimeException("Unable to create assignedEmployee due to maintenanceTicket. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    maintenanceNotes = new ArrayList<MaintenanceNote>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public MaintenanceTicket getMaintenanceTicket()
  {
    return maintenanceTicket;
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
  /* Code from template association_SetOneToOptionalOne */
  public boolean setMaintenanceTicket(MaintenanceTicket aNewMaintenanceTicket)
  {
    boolean wasSet = false;
    if (aNewMaintenanceTicket == null)
    {
      //Unable to setMaintenanceTicket to null, as assignedEmployee must always be associated to a maintenanceTicket
      return wasSet;
    }
    
    Employee existingAssignedEmployee = aNewMaintenanceTicket.getAssignedEmployee();
    if (existingAssignedEmployee != null && !equals(existingAssignedEmployee))
    {
      //Unable to setMaintenanceTicket, the current maintenanceTicket already has a assignedEmployee, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    MaintenanceTicket anOldMaintenanceTicket = maintenanceTicket;
    maintenanceTicket = aNewMaintenanceTicket;
    maintenanceTicket.setAssignedEmployee(this);

    if (anOldMaintenanceTicket != null)
    {
      anOldMaintenanceTicket.setAssignedEmployee(null);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfMaintenanceNotes()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public MaintenanceNote addMaintenanceNote(String aDate, String aDescription, MaintenanceTicket aMaintenanceTicket)
  {
    return new MaintenanceNote(aDate, aDescription, this, aMaintenanceTicket);
  }

  public boolean addMaintenanceNote(MaintenanceNote aMaintenanceNote)
  {
    boolean wasAdded = false;
    if (maintenanceNotes.contains(aMaintenanceNote)) { return false; }
    Employee existingEmployee = aMaintenanceNote.getEmployee();
    boolean isNewEmployee = existingEmployee != null && !this.equals(existingEmployee);
    if (isNewEmployee)
    {
      aMaintenanceNote.setEmployee(this);
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
    //Unable to remove aMaintenanceNote, as it must always have a employee
    if (!this.equals(aMaintenanceNote.getEmployee()))
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

  public void delete()
  {
    MaintenanceTicket existingMaintenanceTicket = maintenanceTicket;
    maintenanceTicket = null;
    if (existingMaintenanceTicket != null)
    {
      existingMaintenanceTicket.setAssignedEmployee(null);
    }
    for(int i=maintenanceNotes.size(); i > 0; i--)
    {
      MaintenanceNote aMaintenanceNote = maintenanceNotes.get(i - 1);
      aMaintenanceNote.delete();
    }
    super.delete();
  }

}