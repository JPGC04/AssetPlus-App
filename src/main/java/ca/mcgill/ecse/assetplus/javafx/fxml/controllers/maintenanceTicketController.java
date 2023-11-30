package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet4Controller;


public class maintenanceTicketController implements Initializable{

    @FXML TableView<MaintenanceTicketString> tickets;

    @FXML
    private Button approveButton;

    @FXML
    private TextField assetInput;

    @FXML
    private TableColumn<MaintenanceTicketString, String> assetTable;

    @FXML
    private Button assignButton;

    @FXML
    private Button createButton;

    @FXML
    private TextField dateInput;

    @FXML
    private TableColumn<MaintenanceTicketString, String> dateTable;

    @FXML
    private Button deleteButton;

    @FXML
    private TextArea descriptionInput;

    @FXML
    private TableColumn<MaintenanceTicketString, String> descriptionTable;

    @FXML
    private Button disaproveButton;

    @FXML
    private Button editButton;

    @FXML
    private TextField fixerInput;

    @FXML
    private TableColumn<MaintenanceTicketString, String> fixerTable;

    @FXML
    private Button imageButton;

    @FXML
    private Button notesButton;

    @FXML
    private ChoiceBox<String> priorityInput;

    @FXML
    private TextField raisedByInput;

    @FXML
    private TableColumn<MaintenanceTicketString, String> raisedByTable;

    @FXML
    private TableColumn<MaintenanceTicketString, String> statusTable;

    @FXML
    private TextField ticketInput;

    @FXML
    private TableColumn<MaintenanceTicketString, String> ticketTable;

    @FXML
    private ChoiceBox<String> timeToResolveInput;

    @FXML
    private Button updateButton;

    private int current_id;

    private ObservableList<MaintenanceTicketString> list = FXCollections.observableArrayList();

    private String[] priorityLevels = {"Low", "Medium", "Urgent"};

    private String[] timeToResolve = {"OneWeek", "TwoWeeks", "ThreeWeeks"};


    //Load the database
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        ticketTable.setCellValueFactory(new PropertyValueFactory<MaintenanceTicketString, String>("id"));
        dateTable.setCellValueFactory(new PropertyValueFactory<MaintenanceTicketString, String>("date"));
        raisedByTable.setCellValueFactory(new PropertyValueFactory<MaintenanceTicketString, String>("ticketRaiser"));
        descriptionTable.setCellValueFactory(new PropertyValueFactory<MaintenanceTicketString, String>("description"));
        statusTable.setCellValueFactory(new PropertyValueFactory<MaintenanceTicketString, String>("status"));
        assetTable.setCellValueFactory(new PropertyValueFactory<MaintenanceTicketString, String>("asset"));
        fixerTable.setCellValueFactory(new PropertyValueFactory<MaintenanceTicketString, String>("fixer"));
        tickets.setItems(list);

        timeToResolveInput.getItems().addAll(timeToResolve);
        priorityInput.getItems().addAll(priorityLevels);
        
        
    }


    @FXML
    void approveClick(ActionEvent event) {
        ObservableList<MaintenanceTicketString> currentTableData = tickets.getItems();
        int currentTicketId = Integer.parseInt(ticketInput.getText());

        for (MaintenanceTicketString ticket : currentTableData) {
            if (Integer.parseInt(ticket.getId()) == currentTicketId) {
                ticket.setStatus("Closed");

                tickets.setItems(currentTableData);
                tickets.refresh();
                break;
            }
        }




    }

    @FXML
    void assignClick(ActionEvent event) {
        ObservableList<MaintenanceTicketString> currentTableData = tickets.getItems();
        int currentTicketId = Integer.parseInt(ticketInput.getText());

        for (MaintenanceTicketString ticket : currentTableData) {
            if (Integer.parseInt(ticket.getId()) == currentTicketId) {
                ticket.setFixer(fixerInput.getText());
                ticket.setStatus("Assigned");
                ticket.setTimeToResolve(timeToResolveInput.getValue());
                ticket.setPriorityLevel(priorityInput.getValue());

                tickets.setItems(currentTableData);
                tickets.refresh();
                break;
            }
        }

    }

    @FXML
    void rowClicked(MouseEvent event){
        MaintenanceTicketString clickedTicket = tickets.getSelectionModel().getSelectedItem();
        ticketInput.setText(String.valueOf(clickedTicket.getId()));
        dateInput.setText(String.valueOf(clickedTicket.getDate()));
        raisedByInput.setText(String.valueOf(clickedTicket.getTicketRaiser()));
        descriptionInput.setText(String.valueOf(clickedTicket.getDescription()));
        assetInput.setText(String.valueOf(clickedTicket.getAsset()));

        if (clickedTicket.getFixer() != null){
            fixerInput.setText(clickedTicket.getFixer());
            timeToResolveInput.setValue(clickedTicket.getTimeToResolve());
            priorityInput.setValue(clickedTicket.getPriorityLevel());
        }
        
    }

    @FXML
    void createClick(ActionEvent event) {

        MaintenanceTicketString createdTicket = new MaintenanceTicketString(String.valueOf(current_id), "2023-11-30",raisedByInput.getText(), descriptionInput.getText(), "Open", assetInput.getText(), "");

        list.add(createdTicket);
        current_id++;

        tickets.refresh();

        



    }

    @FXML
    void deleteClick(ActionEvent event) {
        int selectedID = tickets.getSelectionModel().getSelectedIndex();
        tickets.getItems().remove(selectedID);

    }


    @FXML
    void disaproveClick(ActionEvent event) {
        //TODO create a pop that prompts for a disaproval note
        try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Disaprove.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();
        } catch (Exception e) {
            System.out.println("Cant open new window");
        }



    }

    @FXML
    void editClick(ActionEvent event) {
        ObservableList<MaintenanceTicketString> currentTableData = tickets.getItems();
        int currentTicketId = Integer.parseInt(ticketInput.getText());

        for (MaintenanceTicketString ticket : currentTableData) {
            if (Integer.parseInt(ticket.getId()) == currentTicketId) {
                ticket.setDate(dateInput.getText());
                ticket.setTicketRaiser(raisedByInput.getText());
                ticket.setDescription(descriptionInput.getText());
                ticket.setStatus("Open");
                ticket.setAsset(assetInput.getText());

                tickets.setItems(currentTableData);
                tickets.refresh();
                break;
            }
        }

    }

    @FXML
    void imageClick(ActionEvent event) {
        try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MaintenanceImage.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();
        } catch (Exception e) {
            System.out.println("Cant open new window");
        }
        //TODO open view image window
        

    }
    @FXML
    void notesClick(ActionEvent event) {
        //TODO open view notes image windo
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MaintenanceNote.fxml"));
            MaintenanceNoteController controller = new MaintenanceNoteController();
            controller.setYourVariable(Integer.parseInt(ticketInput.getText()));
            fxmlLoader.setControllerFactory(c -> controller);

            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));  
            stage.show();
            } catch (Exception e) {
                System.out.println("Cant open new window");
            }

    }

    @FXML
    void updateClick(ActionEvent event) {
        ObservableList<MaintenanceTicketString> currentTableData = tickets.getItems();
        int currentTicketId = Integer.parseInt(ticketInput.getText());

        for (MaintenanceTicketString ticket : currentTableData) {
            if (Integer.parseInt(ticket.getId()) == currentTicketId) {

                if (ticket.getStatus().equals("Assigned")) {
                    ticket.setStatus("In Progress");

                }

                if (ticket.getStatus().equals("In Progress")) {
                    ticket.setStatus("Resolved");

                }
                

                tickets.setItems(currentTableData);
                tickets.refresh();
                break;
            }
        }

    }



}
