package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

public class Asset {

  private String ID;
  private String date;
  private String floor;
  private String room;
  private String assetType;


  public Asset(String ID, String date, String floor, String room, String assetType) {
    this.ID = ID;
    this.date = date;
    this.floor = floor;
    this.room = room;
    this.assetType = assetType;

  }

  public String getID() {
    return ID;
  }

  public String getDate() {
    return date;
  }

  public String getFloor() {
    return floor;
  }

  public String getRoom() {
    return room;
  }

  public String getAssetType() {
    return assetType;
  }


  public void setID(String ID) {
    this.ID = ID;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public void setFloor(String floor) {
    this.floor = floor;
  }

  public void setRoom(String room) {
    this.room = room;
  }

  public void setAssetType(String assetType) {
    this.assetType = assetType;
  }

}
