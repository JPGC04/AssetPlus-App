package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class DisaproveController {

    @FXML
    private Button cancelButton;

    @FXML
    private TextArea reasonInput;

    @FXML
    private Button submitButton;
    
    private Date date;
    
    private Integer ticketID;

    public void setYourVariable(Date value, Integer value1) {
        this.date = value;
        this.ticketID = value1;
    }

    @FXML
    void cancelClick(ActionEvent event) {

    }

    @FXML
    void submitClick(ActionEvent event) {

    }

}
