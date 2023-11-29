package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

import static ca.mcgill.ecse.assetplus.javafx.fxml.controllers.ViewUtils.showError;
import java.sql.Date;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet3Controller;

public class AssetController {

    @FXML
    private TextField textArea;

    @FXML
    public void initialize() {

    }
    @FXML
    private VBox screen;

    private List<HBox> popup= new ArrayList<>();

    private void addAsset(TextField purchaseDate, TextField floorNumber, TextField roomNumber, TextField assetType, String assetID) {
        //add asset
        //except error
    }

    private void deleteAsset(int i, String assetID) {
        //delete button from database
        //if successful
        try {
            int assetNumber = Integer.parseInt(assetID);
            AssetPlusFeatureSet3Controller.deleteSpecificAsset(assetNumber);
        } catch (Exception e) {
            showError("Invalid parameters");
        }
        screen.getChildren().remove(popup.get(i));
    }

    private void updateAsset(int i, TextField purchaseDate, TextField floorNumber, TextField roomNumber, TextField assetType, String assetID) {
        //update asset
        //if succesful
        try {
            int floorNo = Integer.parseInt(floorNumber.getText());
            int roomNo = Integer.parseInt(roomNumber.getText());
            Date newPurchaseDate = Date.valueOf(purchaseDate.getText());
            String newAssetType = assetType.getText();
            int assetNumber = Integer.parseInt(assetID);
            String error = AssetPlusFeatureSet3Controller.updateSpecificAsset(assetNumber, floorNo, roomNo, newPurchaseDate, newAssetType);
            if (error.isBlank()) {
                screen.getChildren().remove(popup.get(i));
            }
        } catch (Exception e) {
            //popup screen that says invalid parameters
            showError("Invalid parameters.");

        }
        
   
    }

    private HBox createPreConfiguredPane() {
        // Create or load your pre-configured Pane
        // For example, create a Pane with labels
        HBox newBox = new HBox();
        Label assetID = new Label("Label 1");
        assetID.setText("this is label1");
        String idString = "";
        TextField purchaseDate = new TextField();
        TextField floorNumber = new TextField();
        TextField roomNumber = new TextField();
        TextField assetType = new TextField();
        Button delete = new Button();
        delete.setText("X");
        delete.setStyle("-fx-background-color: #ff0000; -fx-text-fill: #000000; -fx-font-size: 14px;");
        Button update = new Button();
        update.setText("Update");
        update.setStyle("-fx-background-color: #fffb00; -fx-text-fill: #000000; -fx-font-size: 14px;");
        
        int index = popup.size();
        delete.setOnAction(e -> deleteAsset(index, idString));
        update.setOnAction(e -> updateAsset(index, purchaseDate, floorNumber, roomNumber, assetType, idString));

        popup.add(newBox);
        // Add labels and other nodes to the Pane as needed
        newBox.getChildren().addAll(assetID, purchaseDate, floorNumber, roomNumber, assetType, delete, update);

        return newBox;
    }
    @FXML
    protected void onTextButtonClick() {
        String content = textArea.getText();
        textArea.clear();

        Label one = new Label("HELOOOOOOOOOOOOOO");
        one.setText("IIIIIIIIIIIIIIIII");
        HBox newBox = createPreConfiguredPane();
        
        if (!popup.isEmpty()) {
            screen.getChildren().add(newBox);
        }
    }


}

