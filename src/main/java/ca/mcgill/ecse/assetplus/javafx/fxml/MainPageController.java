package ca.mcgill.ecse.assetplus.javafx.fxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import java.io.IOException;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;

public class MainPageController {

    @FXML
    private Button AssetTypes;

    @FXML
    private Button Assets;

    @FXML
    private Button Tickets;

    @FXML
    private Button User;

    @FXML
    private BorderPane bp;

    @FXML
    private BorderPane bpout;

    @FXML
    private VBox sidebar;

    @FXML
    public void initialize() {
      loadPage("pages/AUDEmployeeGuest.fxml");
      
      String cssFilePath = getClass().getResource("MainPageStyle.css").toExternalForm();
      sidebar.getStylesheets().add(cssFilePath);
    }

    @FXML
    void AssetTypesButtonClicked(ActionEvent event) {
      loadPage("pages/AUDAssetType.fxml");
    }

    @FXML
    void AssetsButtonClicked(ActionEvent event) {
      loadPage("pages/AUDAsset.fxml");
    }

    @FXML
    void TicketsButtonClicked(ActionEvent event) {
      loadPage("pages/MaintenanceTicket.fxml");
    }

    @FXML
    void UserButtonClicked(ActionEvent event) {
      loadPage("pages/AUDEmployeeGuest.fxml");
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
