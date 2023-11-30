package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

import static ca.mcgill.ecse.assetplus.javafx.fxml.controllers.ViewUtils.showError;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet3Controller;

public class AUDAssetController implements Initializable{
    @FXML
    private TableColumn<Asset, String> assetCol;

    @FXML
    private TextField assetID;

    @FXML
    private TextField assetType;

    @FXML
    private TableColumn<Asset, String> assetTypeCol;

    @FXML
    private TableColumn<Asset, String> floorCol;

    @FXML
    private TextField floorNumber;

    @FXML
    private TableColumn<Asset, String> purchaseCol;

    @FXML
    private DatePicker purchaseDate;

    @FXML
    private TableColumn<Asset, String> roomCol;

    @FXML
    private TextField roomNumber;

    @FXML
    private VBox screen;

    @FXML
    private TableView<Asset> table;

    @FXML
    private TextField textArea;
    
    private ObservableList<Asset> data;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        assetCol.setCellValueFactory(new PropertyValueFactory<Asset, String>("ID"));
        floorCol.setCellValueFactory(new PropertyValueFactory<Asset, String>("floor"));
        roomCol.setCellValueFactory(new PropertyValueFactory<Asset, String>("room"));
        purchaseCol.setCellValueFactory(new PropertyValueFactory<Asset, String>("date"));
        assetTypeCol.setCellValueFactory(new PropertyValueFactory<Asset, String>("assetType"));


        data = FXCollections.observableArrayList();
        List<Asset> test = AssetPlusFeatureSet3Controller.getSpecificAssets();
        for (Asset a: test) {
            data.add(a);
        }
        table.setItems(data);
    }

    @FXML
    void onAddBtnClick(ActionEvent event) {
        try {
            int id = Integer.parseInt(assetID.getText());
            LocalDate x = purchaseDate.getValue();
            Date date = java.sql.Date.valueOf(x);
            int floorNo = Integer.parseInt(floorNumber.getText());
            int roomNo = Integer.parseInt(roomNumber.getText());
            String aType = assetType.getText(); 
            String err = AssetPlusFeatureSet3Controller.addSpecificAsset(id, floorNo, roomNo, date, aType);
            System.out.println(err);
            if (err.isEmpty()) {
                data.add(new Asset(assetID.getText(), String.valueOf(date), floorNumber.getText(), roomNumber.getText(), aType));
                showError("Success!");
                assetID.clear();
                floorNumber.clear();
                roomNumber.clear();
                assetType.clear();
            } else {
                showError(err);
            }
        } catch (Exception e) {
            showError("Invalid Parameters");
        }
        
    }


    @FXML
    public void onUpdateBtnClick(ActionEvent event) {
        try {
            int id = Integer.parseInt(assetID.getText());
            LocalDate x = purchaseDate.getValue();
            Date date = java.sql.Date.valueOf(x);
            int floorNo = Integer.parseInt(floorNumber.getText());
            int roomNo = Integer.parseInt(roomNumber.getText());
            String aType = assetType.getText(); 
            String err = AssetPlusFeatureSet3Controller.updateSpecificAsset(id, floorNo, roomNo, date, aType);
            if (!err.isEmpty()) {
                findAndUpdateEntry(assetID.getText(), String.valueOf(date), floorNumber.getText(), roomNumber.getText(), aType);
                showError("Success!");
                assetID.clear();
                floorNumber.clear();
                roomNumber.clear();
                assetType.clear();
            } else {
                showError(err);
            }
        } catch (Exception e) {
            showError("Invalid Parameters");
        }
    }

    private void findAndUpdateEntry(String id, String date, String floor, String room, String assetType) {
        int i = 0;
        for (Asset asset: data) {
            if (asset.getID().equals(id)) {
                data.remove(i);
                data.add(i, new Asset(id, date, floor, room, assetType));
                break;
            }
            i++;
        }
        
    }

    @FXML
    public void onDeleteBtnClick(ActionEvent event) {
        try {
            String no = textArea.getText();
            int assetNumber = Integer.parseInt(no);
            AssetPlusFeatureSet3Controller.deleteSpecificAsset(assetNumber);
            textArea.clear();
            boolean deleted = false;
            for (Asset asset: data) {
                if (no.equals(asset.getID())) {
                    data.remove(asset);
                    showError("Delete succesful!");
                    deleted = true;
                    break;
                }
            }
            if (!deleted) {
                showError("Could not find asset with id: " + assetNumber);
            }
        } catch (Exception e) {
            showError("Invalid Input!");
        }
    }

}
