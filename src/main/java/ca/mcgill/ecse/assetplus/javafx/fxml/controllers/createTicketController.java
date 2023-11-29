package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ca.mcgill.ecse.assetplus.javafx.fxml.controllers.ViewUtils;

public class createTicketController {

    @FXML
    private TextArea DescriptionInput;

    @FXML
    private ChoiceBox<?> assetNameSelect;

    @FXML
    private Button cancelChangeButton;

    @FXML
    private TextField locationInput;

    @FXML
    private Button submitButton;

    @FXML
    void cancelChange(ActionEvent event) {

    }

    @FXML
    void clickSubmitButton(ActionEvent event) {
        ViewUtils.makePopupWindow("test", "this is a test");


    }

    
}


