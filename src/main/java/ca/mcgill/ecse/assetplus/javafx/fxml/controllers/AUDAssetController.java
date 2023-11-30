package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet3Controller;
import ca.mcgill.ecse.assetplus.javafx.fxml.AssetPlusFxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.util.List;
import static ca.mcgill.ecse.assetplus.javafx.fxml.controllers.ViewUtils.successful;

import java.sql.Date;

public class AUDAssetController {

    @FXML
    private Button AddAssetButton;

    @FXML
    private DatePicker AddAssetDate;

    @FXML
    private TextField AddAssetFloorNumber;

    @FXML
    private TextField AddAssetID;

    @FXML
    private ChoiceBox<String> AddAssetName;

    @FXML
    private TextField AddAssetRoomNumber;

    @FXML
    private Button DeleteAssetButton;

    @FXML
    private TextField DeleteAssetID;

    @FXML
    private Button UpdateAssetButton;

    @FXML
    private DatePicker UpdateAssetDate;

    @FXML
    private TextField UpdateAssetFloorNumber;

    @FXML
    private TextField UpdateAssetID;

    @FXML
    private ChoiceBox<String> UpdateAssetName;

    @FXML
    private TextField UpdateAssetRoomNumber;

    @FXML
    public void initialize() {
        AddAssetName.addEventHandler(AssetPlusFxmlView.REFRESH_EVENT, e -> {
        AddAssetName.setItems(ViewUtils.getAssetTypes());
        AddAssetName.setValue(null);
        });

        UpdateAssetName.addEventHandler(AssetPlusFxmlView.REFRESH_EVENT, e -> {
        UpdateAssetName.setItems(ViewUtils.getAssetTypes());
        UpdateAssetName.setValue(null);
        });

        List<String> inSystemAssetTypes = ViewUtils.getAssetTypes();
        for (String a: inSystemAssetTypes) {
            AddAssetName.getItems().addAll(a);
            UpdateAssetName.getItems().addAll(a);
        }

        AssetPlusFxmlView.getInstance().registerRefreshEvent(AddAssetName, UpdateAssetName);
    }

    @FXML
    void AddAssetClicked(ActionEvent event) {
        int AssetID = Integer.parseInt(AddAssetID.getText());
        int AssetFloor = Integer.parseInt(AddAssetFloorNumber.getText());
        int AssetRoom = Integer.parseInt(AddAssetRoomNumber.getText());
        String AssetName = AddAssetName.getValue();
        Date AssetDate = Date.valueOf(AddAssetDate.getValue());
        if (successful(AssetPlusFeatureSet3Controller.addSpecificAsset(AssetID, AssetFloor, AssetRoom, AssetDate, AssetName))) {
            AddAssetID.setText("");
            AddAssetFloorNumber.setText("");
            AddAssetRoomNumber.setText("");
            AddAssetName.valueProperty().set(null);
            AddAssetDate.valueProperty().set(null);
        }
    }

    @FXML
    void DeleteAssetClicked(ActionEvent event) {
        int AssetID = Integer.parseInt(DeleteAssetID.getText());
        AssetPlusFeatureSet3Controller.deleteSpecificAsset(AssetID);
        DeleteAssetID.setText("");
    }

    @FXML
    void UpdateAssetClicked(ActionEvent event) {
        int AssetID = Integer.parseInt(UpdateAssetID.getText());
        int AssetFloor = Integer.parseInt(UpdateAssetFloorNumber.getText());
        int AssetRoom = Integer.parseInt(UpdateAssetRoomNumber.getText());
        String AssetName = UpdateAssetName.getValue();
        Date AssetDate = Date.valueOf(UpdateAssetDate.getValue());
        if (successful(AssetPlusFeatureSet3Controller.updateSpecificAsset(AssetID, AssetFloor, AssetRoom, AssetDate, AssetName))) {
            UpdateAssetID.setText("");
            UpdateAssetFloorNumber.setText("");
            UpdateAssetRoomNumber.setText("");
            UpdateAssetName.valueProperty().set(null);
            UpdateAssetDate.valueProperty().set(null);
        }
    }

}
