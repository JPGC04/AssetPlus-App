package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

import ca.mcgill.ecse.assetplus.model.AssetType;
import ca.mcgill.ecse.assetplus.model.MaintenanceTicket;
import ca.mcgill.ecse.assetplus.controller.*;
import java.util.Date;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableCell;

public class ViewStatusMTicketController implements Initializable{

    @FXML
    private ComboBox<String> assignedEmployeeSearchBox;

    @FXML
    private Button createTicketButton;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Button imageButton;

    @FXML
    private CheckBox managerApprovalCheckBox;

    @FXML
    private ComboBox<Integer> ticketNumberSearchComboBox;

    @FXML
    private TableView<?> ticketTable;

    @FXML
    private TableColumn<MaintenanceTicket, String> assetCol;

    @FXML
    private TableColumn<MaintenanceTicket, Date> endDateCol;

    @FXML
    private TableColumn<MaintenanceTicket, Integer> locationCol;

    @FXML
    private TableColumn<?, ?> priorityCol;

    @FXML
    private TableColumn<?, ?> startDateCol;

    @FXML
    private TableColumn<?, ?> statusCol;

    @FXML
    private Button viewDescriptionButton;

    @FXML
    private Button viewNotesButton;

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        // initialize ticket number search box
        ticketNumberSearchComboBox.setItems(FXCollections.observableArrayList(Utils.getTicketNumbers()));
        //
        assetCol.setCellValueFactory(data -> Bindings.createStringBinding(
            () -> data.getValue().getAsset().getAssetType().getName()));
        
    }

    @FXML
    void getTicketWithNumber(ActionEvent event) {
        int ticketNumber = ticketNumberSearchComboBox.getValue();
    }

    @FXML
    void getAssigedEmpolyee(ActionEvent event) {

    }

    @FXML
    void getDescription(ActionEvent event) {

    }

    @FXML
    void getImages(ActionEvent event) {

    }

    @FXML
    void getNotes(ActionEvent event) {

    }

    @FXML
    void ifManagerApproval(ActionEvent event) {

    }

    @FXML
    void selectDate(ActionEvent event) {

    }

    @FXML
    void createTicket(ActionEvent event) {

    }

}
