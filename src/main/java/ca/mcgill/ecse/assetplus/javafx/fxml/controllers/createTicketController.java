package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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

}
