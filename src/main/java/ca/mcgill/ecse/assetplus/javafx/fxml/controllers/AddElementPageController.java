package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

import static ca.mcgill.ecse.assetplus.javafx.fxml.controllers.ViewUtils.successful;
import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet2Controller;
import ca.mcgill.ecse.assetplus.javafx.fxml.AssetPlusFxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddElementPageController {

  @FXML private TextField driverNameTextField;
  @FXML private Button addDriverButton;

  // Event Listener on Button[#addDriverButton].onAction
  @FXML
  public void addDriverClicked(ActionEvent event) {
    String name = driverNameTextField.getText();
    if (name == null || name.trim().isEmpty()) {
      ViewUtils.showError("Please input a valid driver name");
    } else {
      // reset the driver text field if success
      if (successful(AssetPlusFeatureSet2Controller.addAssetType(name, 4))) {
        driverNameTextField.setText("");
      }
    }
  }
}
