package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet4Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import java.sql.Date;

public class DisaproveController {

    @FXML
    private Button cancelButton;

    @FXML
    private TextArea reasonInput;

    @FXML
    private Button submitButton;

    private Date date;

    private int ticketID;

    public void setYourVariable(Date date1, Integer id) {
        this.date = date1;
        this.ticketID = id;
    }

    @FXML
    void cancelClick(ActionEvent event) {
        // Close the current window
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void submitClick(ActionEvent event) {
        if (!reasonInput.getText().equals("") && reasonInput.getText() != null) {
            if (ViewUtils.successful(AssetPlusFeatureSet4Controller.disapproveTicket(ticketID, date,
                    reasonInput.getText()))) {
                Stage stage = (Stage) submitButton.getScene().getWindow();
                stage.close();
            }


        } else {
            ViewUtils.showError("Cannot disapprove with empty note");
        }
    }

}
