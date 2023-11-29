package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class EditTicketController {

    @FXML
    private Text assetName;

    @FXML
    private ChoiceBox<?> assetNameSelect;

    @FXML
    private Text buildingLocation;

    @FXML
    private Button cancelChangeButton;

    @FXML
    private Text date;

    @FXML
    private TextField locationInput;

    @FXML
    private TextArea newDescriptionInput;

    @FXML
    private TextField newTicketRaiserInput;

    @FXML
    private Text oldDescription;

    @FXML
    private Button saveChangesButton;

    @FXML
    private Text ticketNumber;

    @FXML
    private Text ticketRaiser;

}
