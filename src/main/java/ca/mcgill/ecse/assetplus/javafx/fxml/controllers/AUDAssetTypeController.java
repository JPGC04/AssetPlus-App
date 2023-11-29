package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet2Controller;
import static ca.mcgill.ecse.assetplus.javafx.fxml.controllers.ViewUtils.successful;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import ca.mcgill.ecse.assetplus.javafx.fxml.AssetPlusFxmlView;

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
    private ChoiceBox<?> DeleteAssetTypeName;

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
        // the choice boxes listen to the refresh event
        UpdateAssetTypeOldName.addEventHandler(AssetPlusFxmlView.REFRESH_EVENT, e -> {
        UpdateAssetTypeOldName.setItems(ViewUtils.getAssetTypes());
        // reset the choice
        UpdateAssetTypeOldName.setValue(null);
        });

        // let the application be aware of the refreshable node
        AssetPlusFxmlView.getInstance().registerRefreshEvent(UpdateAssetTypeOldName);
    }

    @FXML
    void AddAssetTypeClicked(ActionEvent event) {
        if (AddAssetTypeName.getText() == null || AddAssetTypeName.getText().trim().isEmpty() || AddAssetTypeName.getText().length() == 0) {
            ViewUtils.showError("The name must not be empty");
        } else if (AddAssetTypeLife.getText().trim().isEmpty() || Integer.parseInt(AddAssetTypeLife.getText()) <= 0) {
            ViewUtils.showError("The expected life span must be greater than 0 days");
        } else {
            String name = AddAssetTypeName.getText();
            int expectedLifeSpanInDays = Integer.parseInt(AddAssetTypeLife.getText());
            if (successful(AssetPlusFeatureSet2Controller.addAssetType(name, expectedLifeSpanInDays))) {
                AddAssetTypeName.setText("");
                AddAssetTypeLife.setText("");
            }
        }
    }

    @FXML
    void DeleteAssetTypeClicked(ActionEvent event) {

    }

    @FXML
    void UpdateAssetTypeClicked(ActionEvent event) {

    }

}
