package ca.mcgill.ecse.assetplus.javafx.fxml;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.Parent;

public class thecontroller {
    @FXML
    private Button another;

    @FXML
    private AnchorPane ap;

    @FXML
    private Button assetplus;

    @FXML
    private BorderPane bp;

    @FXML
    private Button home;

    @FXML
    private Button oiterh;

    @FXML
    void anotherclick(ActionEvent event) {
      
    }

    @FXML
    void apclick(ActionEvent event) {
      loadPage("pages/AUDAssetType.fxml");
    }

    @FXML
    void homeclick(ActionEvent event) {
      loadPage("pages/AUDEmployeeGuest.fxml");
    }

    @FXML
    void otehrclick(ActionEvent event) {

    }

    private void loadPage(String page) {
      Parent root = null;

      try {
        root = FXMLLoader.load(getClass().getResource(page));
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }  

      bp.setCenter(root);
    }
}
