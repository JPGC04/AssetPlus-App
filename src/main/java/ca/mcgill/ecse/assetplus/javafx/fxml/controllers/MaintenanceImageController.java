
package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;
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

public class MaintenanceImageController implements Initializable {

    @FXML
    private Button addButton;

    @FXML
    private TextField dateInput;

    @FXML
    private Button deleteButton;

    @FXML
    private Button editButton;

    @FXML
    private TextField imageURLinput;

    @FXML
    private TableView<Image> images;

    @FXML
    private TextField ticketInput;

    @FXML
    private TableColumn<Image, String> urlTable;

    private ObservableList<Image> list = FXCollections.observableArrayList();

    private int current_index;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        urlTable.setCellValueFactory(new PropertyValueFactory<Image, String>("url"));
        images.setItems(list);

    }


    @FXML
    void createClick(ActionEvent event) {

        Image createdImage = new Image(imageURLinput.getText(), current_index);

        list.add(createdImage);
        current_index++;

        images.refresh();
    }

    @FXML
    void deleteClick(ActionEvent event) {
        int selectedID = images.getSelectionModel().getSelectedIndex();
        images.getItems().remove(selectedID);
        current_index--;

    }

    @FXML
    void editClick(ActionEvent event) {
        //TODO

    }

    @FXML
    void rowClicked(MouseEvent event) {
        Image clickedImage = images.getSelectionModel().getSelectedItem();
        imageURLinput.setText(clickedImage.getImageUrl());

    }



}
