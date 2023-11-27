package ca.mcgill.ecse.assetplus.application;

import ca.mcgill.ecse.assetplus.model.AssetPlus;
import ca.mcgill.ecse.assetplus.persistence.AssetPlusPersistence;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;



public class AssetPlusApplication extends Application {

  private static AssetPlus assetPlus;

  public static void main(String[] args) {
    assetPlus = getAssetPlus();
    // To do Start the application user interface here
    launch(args);
    // test

  }

  public static AssetPlus getAssetPlus() {
    if (assetPlus == null) {
      assetPlus = AssetPlusPersistence.load();
    }
    return assetPlus;
  }

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Hello World!");
    Button btn = new Button();
    btn.setText("Say 'Hello World'");
    btn.setOnAction(new EventHandler<ActionEvent>() {

      @Override
      public void handle(ActionEvent event) {
        System.out.println("Hello World!");
      }

    });

    StackPane root = new StackPane();
    root.getChildren().add(btn);
    primaryStage.setScene(new Scene(root, 300, 250));
    primaryStage.show();
  }

}

