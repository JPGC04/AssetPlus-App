package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

import static ca.mcgill.ecse.assetplus.javafx.fxml.controllers.ViewUtils.successful;
import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet2Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class AUDAssetTypeController {

    @FXML
    private Button addAssetTypeButton;

    @FXML
    private TableView<?> assetTypesList;

    @FXML
    private Button deleteAssetTypeButton;

    @FXML
    private TextField newAssetTypeName;

    @FXML
    private Button updateAssetTypeButton;

    @FXML
    void addNewAssetType(ActionEvent event) {
        String name = newAssetTypeName.getText();
        if (name == null || name.trim().isEmpty()) {
            ViewUtils.showError("Please input a valid driver name");
        } else {
            // reset the driver text field if success
            if (successful(AssetPlusFeatureSet2Controller.addAssetType(name, 4))) {
                newAssetTypeName.setText("");
            }
        }

    }

    @FXML
    void deleteNewAssetType(ActionEvent event) {
        return;
    }

    @FXML
    void updateNewAssetType(ActionEvent event) {
        return;
    }

}
