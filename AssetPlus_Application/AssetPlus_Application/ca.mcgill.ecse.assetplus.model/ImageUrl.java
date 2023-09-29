/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/



// line 161 "main.ump"
public class ImageUrl
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ImageUrl Attributes
  private String url;

  //ImageUrl Associations
  private MaintenanceTicket maintenanceTicket;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ImageUrl(String aUrl, MaintenanceTicket aMaintenanceTicket)
  {
    url = aUrl;
    boolean didAddMaintenanceTicket = setMaintenanceTicket(aMaintenanceTicket);
    if (!didAddMaintenanceTicket)
    {
      throw new RuntimeException("Unable to create imageURL due to maintenanceTicket. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setUrl(String aUrl)
  {
    boolean wasSet = false;
    url = aUrl;
    wasSet = true;
    return wasSet;
  }

  public String getUrl()
  {
    return url;
  }
  /* Code from template association_GetOne */
  public MaintenanceTicket getMaintenanceTicket()
  {
    return maintenanceTicket;
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
      existingMaintenanceTicket.removeImageURL(this);
    }
    maintenanceTicket.addImageURL(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    MaintenanceTicket placeholderMaintenanceTicket = maintenanceTicket;
    this.maintenanceTicket = null;
    if(placeholderMaintenanceTicket != null)
    {
      placeholderMaintenanceTicket.removeImageURL(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "url" + ":" + getUrl()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "maintenanceTicket = "+(getMaintenanceTicket()!=null?Integer.toHexString(System.identityHashCode(getMaintenanceTicket())):"null");
  }
}