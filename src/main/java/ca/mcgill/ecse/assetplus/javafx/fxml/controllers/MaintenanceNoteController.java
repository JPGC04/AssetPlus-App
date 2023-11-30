package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

import static ca.mcgill.ecse.assetplus.javafx.fxml.controllers.ViewUtils.showError;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import java.sql.Date;

public class MaintenanceNoteController implements Initializable{

    @FXML
    private Button addButton;

    @FXML
    private TextField dateInput;

    @FXML
    private Button deleteButton;

    @FXML
    private Button editButton;

    @FXML
    private TableView<MaintenanceNote> notes;

    @FXML
    private TextField noteAuthor;

    @FXML
    private TextField noteDesc;

    @FXML
    private TextField ticketInput;

    @FXML
    private TableColumn<MaintenanceNote, Integer> ticketIDCol;

    @FXML
    private TableColumn<MaintenanceNote, String> dateCol;

    @FXML
    private TableColumn<MaintenanceNote, String> descCol;

    @FXML
    private TableColumn<MaintenanceNote, String> authorCol;

    private ObservableList<MaintenanceNote> data;

    private Integer ticketID;

    public void setYourVariable(Integer value) {
        this.ticketID = value;
    }


    @FXML
    void createClick(ActionEvent event) {
      try {
        String author = noteAuthor.getText();
        String description = noteDesc.getText();
        Date date = Date.valueOf(dateInput.getText());
        String tickeString = ticketInput.getText();

      } catch (Exception e) {
        showError("Invalid parameters!");
      }
    }

    @FXML
    void deleteClick(ActionEvent event) {

    }

    @FXML
    void editClick(ActionEvent event) {

    }

    @FXML
    void rowClicked(MouseEvent event) {

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
      // TODO Auto-generated method stub
      ticketIDCol.setCellValueFactory(new PropertyValueFactory<MaintenanceNote, Integer>("ticketID"));
      dateCol.setCellValueFactory(new PropertyValueFactory<MaintenanceNote, String>("date"));
      authorCol.setCellValueFactory(new PropertyValueFactory<MaintenanceNote, String>("author"));
      descCol.setCellValueFactory(new PropertyValueFactory<MaintenanceNote, String>("description"));

      data = FXCollections.observableArrayList();
      notes.setItems(data);

      System.out.println(ticketID);
    }

}
