package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet2Controller;
import static ca.mcgill.ecse.assetplus.javafx.fxml.controllers.ViewUtils.successful;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import ca.mcgill.ecse.assetplus.javafx.fxml.AssetPlusFxmlView;
import java.util.List;

public class AUDAssetTypeController {

    @FXML
    private Button AddAssetTypeButton;

    @FXML
    private TextField AddAssetTypeLife;

    @FXML
    private TextField AddAssetTypeName;

    @FXML
    private Button DeleteAssetTypeButton;

    @FXML
    private ChoiceBox<String> DeleteAssetTypeName;

    @FXML
    private Button UpdateAssetTypeButton;

    @FXML
    private TextField UpdateAssetTypeNewLife;

    @FXML
    private TextField UpdateAssetTypeNewName;

    @FXML
    private ChoiceBox<String> UpdateAssetTypeOldName;

    @FXML
    public void initialize() {
        UpdateAssetTypeOldName.addEventHandler(AssetPlusFxmlView.REFRESH_EVENT, e -> {
            UpdateAssetTypeOldName.setItems(ViewUtils.getAssetTypes());
            UpdateAssetTypeOldName.setValue(null);
        });

        DeleteAssetTypeName.addEventHandler(AssetPlusFxmlView.REFRESH_EVENT, e -> {
            DeleteAssetTypeName.setItems(ViewUtils.getAssetTypes());
            DeleteAssetTypeName.setValue(null);
        });

        List<String> inSystemAssetTypes = ViewUtils.getAssetTypes();
        for (String a : inSystemAssetTypes) {
            UpdateAssetTypeOldName.getItems().addAll(a);
            DeleteAssetTypeName.getItems().addAll(a);
        }

        AssetPlusFxmlView.getInstance().registerRefreshEvent(UpdateAssetTypeOldName,
                DeleteAssetTypeName);
    }

    @FXML
    void AddAssetTypeClicked(ActionEvent event) {
        if (AddAssetTypeName.getText() == null || AddAssetTypeName.getText().trim().isEmpty()
                || AddAssetTypeName.getText().length() == 0) {
            ViewUtils.showError("The name must not be empty");
        } else if (AddAssetTypeLife.getText().trim().isEmpty()
                || Integer.parseInt(AddAssetTypeLife.getText()) <= 0) {
            ViewUtils.showError("The expected life span must be greater than 0 days");
        } else {
            String name = AddAssetTypeName.getText();
            int expectedLifeSpanInDays = Integer.parseInt(AddAssetTypeLife.getText());
            if (successful(
                    AssetPlusFeatureSet2Controller.addAssetType(name, expectedLifeSpanInDays))) {
                AddAssetTypeName.setText("");
                AddAssetTypeLife.setText("");
            }
        }
    }

    @FXML
    void DeleteAssetTypeClicked(ActionEvent event) {
        if (DeleteAssetTypeName.getValue() == null
                || DeleteAssetTypeName.getValue().trim().isEmpty()
                || DeleteAssetTypeName.getValue().length() == 0) {
            ViewUtils.showError("The name must not be empty");
        } else {
            String toRemove = DeleteAssetTypeName.getValue();
            AssetPlusFeatureSet2Controller.deleteAssetType(DeleteAssetTypeName.getValue());
            DeleteAssetTypeName.valueProperty().set(null);
            DeleteAssetTypeName.getItems().remove(toRemove);
            UpdateAssetTypeOldName.getItems().remove(toRemove);
        }
    }

    @FXML
    void UpdateAssetTypeClicked(ActionEvent event) {
        if (UpdateAssetTypeOldName.getValue() == null
                || UpdateAssetTypeOldName.getValue().trim().isEmpty()
                || UpdateAssetTypeOldName.getValue().length() == 0) {
            ViewUtils.showError("The name must not be empty");
        } else if (UpdateAssetTypeNewName.getText() == null
                || UpdateAssetTypeNewName.getText().trim().isEmpty()
                || UpdateAssetTypeNewName.getText().length() == 0) {
            ViewUtils.showError("The name must not be empty");
        } else if (UpdateAssetTypeNewLife.getText().trim().isEmpty()
                || Integer.parseInt(UpdateAssetTypeNewLife.getText()) <= 0) {
            ViewUtils.showError("The expected life span must be greater than 0 days");
        } else {
            if (successful(AssetPlusFeatureSet2Controller.updateAssetType(
                    UpdateAssetTypeOldName.getValue(), UpdateAssetTypeNewName.getText(),
                    Integer.parseInt(UpdateAssetTypeNewLife.getText())))) {
                UpdateAssetTypeNewName.setText("");
                UpdateAssetTypeNewLife.setText("");
                UpdateAssetTypeOldName.valueProperty().set(null);
            }
        }
    }

}
