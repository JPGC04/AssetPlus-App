package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

public class Image {

  private String url;
  private int index;


  public Image(String url, int index){
    this.url = url;
    this.index = index;
  }

  public int getIndex(){
    return this.index;
  }

  public void setIndex(int newIndex) {
    this.index = newIndex;

  }

  public String getUrl(){
    return this.url;

  }

  public void setUrl(String newUrl){

    this.url = newUrl;
  }
  
}

