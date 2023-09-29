/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/



// line 152 "main.ump"
public class MaintenanceNote
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MaintenanceNote Attributes
  private String date;
  private String description;

  //MaintenanceNote Associations
  private Employee employee;
  private MaintenanceTicket maintenanceTicket;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MaintenanceNote(String aDate, String aDescription, Employee aEmployee, MaintenanceTicket aMaintenanceTicket)
  {
    date = aDate;
    description = aDescription;
    boolean didAddEmployee = setEmployee(aEmployee);
    if (!didAddEmployee)
    {
      throw new RuntimeException("Unable to create maintenanceNote due to employee. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddMaintenanceTicket = setMaintenanceTicket(aMaintenanceTicket);
    if (!didAddMaintenanceTicket)
    {
      throw new RuntimeException("Unable to create maintenanceNote due to maintenanceTicket. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDate(String aDate)
  {
    boolean wasSet = false;
    date = aDate;
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

  public String getDate()
  {
    return date;
  }

  public String getDescription()
  {
    return description;
  }
  /* Code from template association_GetOne */
  public Employee getEmployee()
  {
    return employee;
  }
  /* Code from template association_GetOne */
  public MaintenanceTicket getMaintenanceTicket()
  {
    return maintenanceTicket;
  }
  /* Code from template association_SetOneToMany */
  public boolean setEmployee(Employee aEmployee)
  {
    boolean wasSet = false;
    if (aEmployee == null)
    {
      return wasSet;
    }

    Employee existingEmployee = employee;
    employee = aEmployee;
    if (existingEmployee != null && !existingEmployee.equals(aEmployee))
    {
      existingEmployee.removeMaintenanceNote(this);
    }
    employee.addMaintenanceNote(this);
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
      existingMaintenanceTicket.removeMaintenanceNote(this);
    }
    maintenanceTicket.addMaintenanceNote(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Employee placeholderEmployee = employee;
    this.employee = null;
    if(placeholderEmployee != null)
    {
      placeholderEmployee.removeMaintenanceNote(this);
    }
    MaintenanceTicket placeholderMaintenanceTicket = maintenanceTicket;
    this.maintenanceTicket = null;
    if(placeholderMaintenanceTicket != null)
    {
      placeholderMaintenanceTicket.removeMaintenanceNote(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "date" + ":" + getDate()+ "," +
            "description" + ":" + getDescription()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "employee = "+(getEmployee()!=null?Integer.toHexString(System.identityHashCode(getEmployee())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "maintenanceTicket = "+(getMaintenanceTicket()!=null?Integer.toHexString(System.identityHashCode(getMaintenanceTicket())):"null");
  }
}