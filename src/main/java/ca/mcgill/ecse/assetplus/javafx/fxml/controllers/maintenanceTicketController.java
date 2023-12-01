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
import java.time.format.DateTimeFormatter;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import com.thoughtworks.xstream.mapper.LocalConversionMapper;
import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet4Controller;
import java.time.LocalDate;
import static ca.mcgill.ecse.assetplus.javafx.fxml.controllers.ViewUtils.showError;


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
    private DatePicker dateInput;

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

    @FXML 
    private RadioButton managerApprovalButton;

 

    private int current_id;

    private ObservableList<MaintenanceTicketString> list = FXCollections.observableArrayList();

    private String[] priorityLevels = {"Low", "Normal", "Urgent"};

    private String[] timeToResolve = {"LessThanADay", "OneToThreeDays", "ThreeToSevenDays", "OneToThreeWeeks", "ThreeOrMoreWeeks"};


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
        timeToResolveInput.getItems().addAll(timeToResolve);
        priorityInput.getItems().addAll(priorityLevels);

        List<MaintenanceTicketString> myList = AssetPlusFeatureSet4Controller.getSpecificTickets();

        for (MaintenanceTicketString ticket : myList){
         list.add(ticket);
         current_id = Integer.parseInt(ticket.getId()) + 1;
        
        }

        

        tickets.setItems(list);
        System.out.println("Initialze ticket called");
        
    }



    @FXML
    void assignClick(ActionEvent event) {
        ObservableList<MaintenanceTicketString> currentTableData = tickets.getItems();
        int currentTicketId = Integer.parseInt(ticketInput.getText());

        try {
            for (MaintenanceTicketString ticket : currentTableData) {
            if (Integer.parseInt(ticket.getId()) == currentTicketId) {

                String fixer = fixerInput.getText();
                String timeToResolve = timeToResolveInput.getValue();
                String priority = priorityInput.getValue(); 
                boolean requiresApproval = managerApprovalButton.isSelected();


                String error = AssetPlusFeatureSet4Controller.assignMaintenanceTicket(currentTicketId, fixer, timeToResolve, priority, requiresApproval);

                if (error.isEmpty()) {
                    ticket.setFixer(fixer);
                    ticket.setStatus("Assigned");
                    ticket.setPriorityLevel(priority);
                    ticket.setTimeToResolve(timeToResolve);
                    ticket.setRequiresApproval(requiresApproval);
                    
                    tickets.setItems(currentTableData);
                    tickets.refresh();        
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
    void rowClicked(MouseEvent event){
        MaintenanceTicketString clickedTicket = tickets.getSelectionModel().getSelectedItem();
        ticketInput.setText(String.valueOf(clickedTicket.getId()));
        // dateInput.setValue(clickedTicket.getDate());
        raisedByInput.setText(String.valueOf(clickedTicket.getTicketRaiser()));
        descriptionInput.setText(String.valueOf(clickedTicket.getDescription()));
        assetInput.setText(String.valueOf(clickedTicket.getAsset()));

        LocalDate date = LocalDate.parse(clickedTicket.getDate());
        dateInput.setValue(date);

        fixerInput.setText(clickedTicket.getFixer());
        timeToResolveInput.setValue(clickedTicket.getTimeToResolve());
        priorityInput.setValue(clickedTicket.getPriorityLevel());
        managerApprovalButton.setSelected(clickedTicket.getRequiresApproval());
        
    }

    @FXML
    void createClick(ActionEvent event) {
        try {
            ;
            Date date = Date.valueOf(dateInput.getValue());
            String description = descriptionInput.getText();
            int assetNumber = Integer.parseInt(assetInput.getText());
            String email = raisedByInput.getText();
            String error = AssetPlusFeatureSet4Controller.addMaintenanceTicket(current_id, date, description, email, assetNumber);

            if (error.isEmpty()) {
                MaintenanceTicketString createdTicket = new MaintenanceTicketString(String.valueOf(current_id), String.valueOf(date),raisedByInput.getText(), descriptionInput.getText(), "Open", assetInput.getText());
    
                list.add(createdTicket); 
                current_id++;
                raisedByInput.clear();
                descriptionInput.clear();
                assetInput.clear();
                dateInput.setValue(null);
                ticketInput.clear();
            } else {
                showError(error);
            }
        
        } catch (Exception e) {
            showError("Invalid Parameters");
        }


        tickets.refresh();

        



    }

    @FXML
    void deleteClick(ActionEvent event) {
        int selectedID = tickets.getSelectionModel().getSelectedIndex();
        tickets.getItems().remove(selectedID);

        int currentTicketId = Integer.parseInt(ticketInput.getText());

        for (MaintenanceTicketString ticket : list){
            if (Integer.parseInt(ticket.getId()) == currentTicketId) {
                list.remove(ticket);
            }
        }
        AssetPlusFeatureSet4Controller.deleteMaintenanceTicket(currentTicketId);
        tickets.refresh();

        raisedByInput.clear();
        descriptionInput.clear();
        assetInput.clear();
        dateInput.setValue(null);
        ticketInput.clear();

    }


   @FXML
    void disaproveClick(ActionEvent event) {
        //TODO create a pop that prompts for a disaproval note
        try {
            String status = statusTable.getText();
            if (status == "Resolved") {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Disaprove.fxml"));

                DisaproveController controller = new DisaproveController();
                controller.setYourVariable(Date.valueOf(dateInput.getValue()), Integer.parseInt(ticketInput.getText()));
                fxmlLoader.setControllerFactory(c -> controller);

                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));  
                stage.show();
            } else {
                showError("Ticket is not resolved yet");
            }
        
        } catch (Exception e) {
            System.out.println("Cant open new window");
        }



    }


    @FXML
    void editClick(ActionEvent event) {

        try {
            ObservableList<MaintenanceTicketString> currentTableData = tickets.getItems();
            int currentTicketId = Integer.parseInt(ticketInput.getText());

            for (MaintenanceTicketString ticket : currentTableData) {
                if (Integer.parseInt(ticket.getId()) == currentTicketId) {

                    Date date = Date.valueOf(dateInput.getValue());
                    String newDesc = descriptionInput.getText();
                    String newEmail = raisedByInput.getText();
                    int assetNumber = Integer.parseInt(assetInput.getText());

                    String error = AssetPlusFeatureSet4Controller.updateMaintenanceTicket(currentTicketId, date, newDesc, newEmail, assetNumber);
                    if (error.isEmpty()) {
                        ticket.setDate(String.valueOf(dateInput.getValue()));
                        ticket.setTicketRaiser(raisedByInput.getText());
                        ticket.setDescription(descriptionInput.getText());
                        ticket.setStatus("Open");
                        ticket.setAsset(assetInput.getText());

                        tickets.setItems(currentTableData);
                        tickets.refresh();

                        raisedByInput.clear();
                        descriptionInput.clear();
                        assetInput.clear();
                        dateInput.setValue(null);
                        ticketInput.clear();
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
    void imageClick(ActionEvent event) {
        try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MaintenanceImage.fxml"));
        MaintenanceImageController controller = new MaintenanceImageController();
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
    void notesClick(ActionEvent event) {
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
                String current_status = AssetPlusFeatureSet4Controller.getTicketStatus(currentTicketId);
                String error;

                if (current_status.equals("Assigned")) {
                    try {
                        error = AssetPlusFeatureSet4Controller.startTicketProgress(currentTicketId);
                        if (error.isEmpty()){
                            ticket.setStatus("InProgress");
                            
                        } else {
                            showError(error);
                        }
                        
                    } catch (Exception e) {
                        System.out.println("Something went wrong");
                    }
                }


                if (current_status.equals("InProgress")) {
                    try {
                        error = AssetPlusFeatureSet4Controller.completeTicket(currentTicketId);
                        if (error.isEmpty()){
                            ticket.setStatus("Resolved");
                        } else {
                            showError(error);
                        }
                        
                    } catch (Exception e) {
                        System.out.println("Something went wrong 2");
                    }
                }

                tickets.setItems(currentTableData);
                tickets.refresh();
                break;
            }
        }

    }

    @FXML
    void approveClick(ActionEvent event) {
        ObservableList<MaintenanceTicketString> currentTableData = tickets.getItems();
        int currentTicketId = Integer.parseInt(ticketInput.getText());

        for (MaintenanceTicketString ticket : currentTableData) {
            if (Integer.parseInt(ticket.getId()) == currentTicketId) {
                String current_status = AssetPlusFeatureSet4Controller.getTicketStatus(currentTicketId);
                String error;


                if (current_status.equals("Resolved")) {
                    try {
                        error = AssetPlusFeatureSet4Controller.completeTicket(currentTicketId);
                        if (error.isEmpty()){
                            ticket.setStatus("Closed");
                        } else {
                            showError(error);
                        }
                        
                    } catch (Exception e) {
                        System.out.println("Something went wrong");
                    }

                }
                

                }
                tickets.setItems(currentTableData);
                tickets.refresh();
                break;
            }



        }
} 
