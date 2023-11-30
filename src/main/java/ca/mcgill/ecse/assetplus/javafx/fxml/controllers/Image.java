package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

public class Image {

  private String url;
  private int index;


  public Image(String url, int index){
    this.url = url;
    this.index = index;
  }

  public int getImageIndex(){
    return this.index;
  }

  public void setImageIndex(int newIndex) {
    this.index = newIndex;

  }

  public String getImageUrl(){
    return this.url;

  }

  public void setImageUrl(String newUrl){

    this.url = newUrl;
  }
  
}
