package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

import static ca.mcgill.ecse.assetplus.javafx.fxml.controllers.ViewUtils.showError;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet4Controller;
import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet7Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
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
    private DatePicker dateInput;

    @FXML
    private Button deleteButton;

    @FXML
    private Button editButton;

    @FXML
    private TableView<AMaintenanceNote> notes;

    @FXML
    private TextField noteAuthor;

    @FXML
    private TextField noteDesc;

    @FXML
    private TextField ticketInput;

    @FXML
    private TableColumn<AMaintenanceNote, Integer> ticketIDCol;

    @FXML
    private TableColumn<AMaintenanceNote, String> dateCol;

    @FXML
    private TableColumn<AMaintenanceNote, String> descCol;

    @FXML
    private TableColumn<AMaintenanceNote, String> authorCol;

    private ObservableList<AMaintenanceNote> data;

    private Integer ticketID;

    private Integer current_id;

    public void setYourVariable(Integer value) {
        this.ticketID = value;
    }


    @FXML
    void createClick(ActionEvent event) {
      System.out.println("entering");
      try {
        String author = noteAuthor.getText();
        String description = noteDesc.getText();
        
        Date date = Date.valueOf(dateInput.getValue());
        String ticketString = ticketInput.getText();
        System.out.println("This is ticketID: " + ticketID);
        String error = AssetPlusFeatureSet7Controller.addMaintenanceNote(date, description, ticketID, author);
        System.out.println("cleared");
        if (error.isEmpty()) {
          System.out.println(current_id);
          data.add(new AMaintenanceNote(description, String.valueOf(date), author, current_id));
          current_id++;
          noteAuthor.clear();
          noteDesc.clear();
        } else {
          showError(error);
        }

      } catch (Exception e) {
        showError("Invalid parameters!");
      }
      notes.refresh();
    }

    @FXML
    void deleteClick(ActionEvent event) {
      int selectedID = notes.getSelectionModel().getSelectedIndex();
      notes.getItems().remove(selectedID);

      for (AMaintenanceNote ticket : data){
          if (ticket.getNoteIndex() == selectedID) {
              data.remove(ticket);
          }
      }

      AssetPlusFeatureSet7Controller.deleteMaintenanceNote(ticketID, selectedID);
      notes.refresh();
    }

    @FXML
    void editClick(ActionEvent event) {
      try {
        ObservableList<AMaintenanceNote> currentTableData = notes.getItems();
        int currentTicketId = Integer.parseInt(ticketInput.getText());

        for (AMaintenanceNote note : currentTableData) {
            if (note.getNoteIndex() == currentTicketId) {
                
                Date date = Date.valueOf(dateInput.getValue());
                String newDesc = noteDesc.getText();
                String newEmail = noteAuthor.getText();
                int noteIndex = Integer.parseInt(ticketInput.getText());

                String error = AssetPlusFeatureSet7Controller.updateMaintenanceNote(ticketID, noteIndex, date, newDesc, newEmail);
                if (error.isEmpty()) {
                    note.setDate(String.valueOf(dateInput.getValue()));
                    note.setNoteTaker(newEmail);
                    note.setDescription(newDesc);
                    notes.setItems(currentTableData);
                    notes.refresh();
                } else {
                    showError(error);
                }
                break;
            }
        }
    } catch (Exception e) {
        showError("Invalid parameters!");
    }
    }

    @FXML
    void rowClicked(MouseEvent event) {
      AMaintenanceNote clickedTicket = notes.getSelectionModel().getSelectedItem();
      ticketInput.setText(String.valueOf(clickedTicket.getNoteIndex()));
      // dateInput.setText(String.valueOf(clickedTicket.getDate()));
      noteAuthor.setText(String.valueOf(clickedTicket.getNoteTaker()));
      noteDesc.setText(String.valueOf(clickedTicket.getDescription()));
      
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
      // TODO Auto-generated method stub
      ticketIDCol.setCellValueFactory(new PropertyValueFactory<AMaintenanceNote, Integer>("noteIndex"));
      dateCol.setCellValueFactory(new PropertyValueFactory<AMaintenanceNote, String>("date"));
      authorCol.setCellValueFactory(new PropertyValueFactory<AMaintenanceNote, String>("noteTaker"));
      descCol.setCellValueFactory(new PropertyValueFactory<AMaintenanceNote, String>("description"));

      data = FXCollections.observableArrayList();
      List<AMaintenanceNote> myList = AssetPlusFeatureSet7Controller.getSpecificNotes(ticketID);
      if (myList != null && !myList.isEmpty()) {
        for (AMaintenanceNote note : myList){
          data.add(note);
          current_id = note.getNoteIndex() + 1;
        }
      } else {
        current_id = 0;
      }
      notes.setItems(data);
      notes.refresh();
      System.out.println(ticketID);
    }

}
