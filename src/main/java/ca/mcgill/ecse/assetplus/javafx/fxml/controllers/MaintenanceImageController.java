
package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

import static ca.mcgill.ecse.assetplus.javafx.fxml.controllers.ViewUtils.showError;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet5Controller;
import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet5Controller.Image;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class MaintenanceImageController implements Initializable {

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TextArea imageURLinput;

    @FXML
    private TableView<Image> images;

    @FXML
    private TableColumn<Image, String> urlTable;


    private ObservableList<Image> list = FXCollections.observableArrayList();

    private int current_index;

    private Integer ticketID;

    public void setYourVariable(Integer value) {
        this.ticketID = value;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        urlTable.setCellValueFactory(new PropertyValueFactory<Image, String>("url"));
        List<Image> t = AssetPlusFeatureSet5Controller.getSpecificImages(ticketID);
        for (Image img : t) {
            list.add(img);
            current_index = img.getIndex() + 1;
        }

        images.setItems(list);
        images.refresh();
        imageURLinput.setWrapText(true);

    }


    @FXML
    void createClick(ActionEvent event) {
        try {
            String url = imageURLinput.getText();

            String error =
                    AssetPlusFeatureSet5Controller.addImageToMaintenanceTicket(url, ticketID);
            if (error.isEmpty()) {
                list.add(new Image(url, current_index));
                imageURLinput.clear();
                current_index++;
            } else {
                showError(error);
            }

        } catch (Exception e) {
            showError("Invalid Parameters!");
        }

        images.refresh();
    }

    @FXML
    void deleteClick(ActionEvent event) {
        int selectedID = images.getSelectionModel().getSelectedIndex();
        images.getItems().remove(selectedID);

        for (Image img : list) {
            if (img.getIndex() == selectedID) {
                list.remove(img);
                break;
            }
        }

        AssetPlusFeatureSet5Controller.deleteImageFromMaintenanceTicket(imageURLinput.getText(),
                ticketID);
        imageURLinput.clear();
        images.refresh();
    }


    @FXML
    void rowClicked(MouseEvent event) {
        Image clickedImage = images.getSelectionModel().getSelectedItem();
        imageURLinput.setText(clickedImage.getUrl());


    }



}
