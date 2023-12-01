package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ResourceBundle;

import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet1Controller;
import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet6Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import java.net.URL;
import java.util.List;
import javafx.fxml.Initializable;
public class AUDEmployeeGuestController implements Initializable {

    @FXML
    private Button Add;

    @FXML
    private Button Delete;

    @FXML
    private TableColumn<UserString, String> EmployeeGuest;

    @FXML
    private Button Update;

    @FXML
    private TextField emailTextField;

    @FXML
    private CheckBox guestCheckBox;

    @FXML
    private TableColumn<UserString, String> name;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField newPassword;

    @FXML
    private TextField numberTextField;

    @FXML
    private TableColumn<UserString, String> password;

    @FXML
    private TableColumn<UserString, String> email;

    @FXML
    private TextField passwordTextField;

    @FXML
    private TableColumn<UserString, String> phoneNumber;

    

    @FXML
    private TableView<UserString> table;

    @FXML
    private Button updatePassword;

    private ObservableList<UserString> list = FXCollections.observableArrayList();

public void updatePasswordClicked(ActionEvent event) {
    String name = newPassword.getText();
    if (name == null || name.trim().isEmpty()) {
      ViewUtils.showError("Please input a password.");
    } else {
      // reset the driver text field if success
      if (ViewUtils.successful(AssetPlusFeatureSet1Controller.updateManager(name))) {
        newPassword.setText("");
      }
    }
  }
  public void add(ActionEvent event) {
    String name = nameTextField.getText();
    String email = emailTextField.getText();
    // emailList.add(email);
    String password = passwordTextField.getText();
    String number = numberTextField.getText();
    if (name == null || name.trim().isEmpty()) {
      ViewUtils.showError("Please input a name");
    } else {
      // reset the driver text field if success
      if (ViewUtils.successful(AssetPlusFeatureSet1Controller.addEmployeeOrGuest(email, password, name, number, !guestCheckBox.isSelected()))) {
        UserString newData=new UserString(name, email, password, number,"");

      if(guestCheckBox.isSelected()){
        newData.setEmployeeGuest("Guest");
      }
      else{
        newData.setEmployeeGuest("Employee");
      }
      list.add(newData);
      table.setItems(list);
      nameTextField.setText("");
      emailTextField.setText("");
      passwordTextField.setText("");
      numberTextField.setText("");
      guestCheckBox.setSelected(false);
      }
    }
  table.refresh();
  }
  public void update(ActionEvent event) {
    String name = nameTextField.getText();
    String email = emailTextField.getText();
    String password = passwordTextField.getText();
    String number = numberTextField.getText();
    if (name == null || name.trim().isEmpty()) {
      ViewUtils.showError("Please input a valid driver name");
    } else {
      // reset the driver text field if success
      if (ViewUtils.successful(AssetPlusFeatureSet1Controller.updateEmployeeOrGuest(email, password, name, number))) {
        nameTextField.setText("");
        emailTextField.setText("");
        passwordTextField.setText("");
        numberTextField.setText("");
        guestCheckBox.setSelected(false);
      }
    }
    table.setItems(list);
    table.refresh();
  }
  public void delete(ActionEvent event) {
    String email = emailTextField.getText();
    if (email == null || email.trim().isEmpty()) {
      ViewUtils.showError("Please input a valid email to delete.");
    } else {
      AssetPlusFeatureSet6Controller.deleteEmployeeOrGuest(email);
      emailTextField.setText("");
    }
    table.setItems(list);
    table.refresh();
  }
  
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.setCellValueFactory(new PropertyValueFactory<UserString, String>("name"));
        email.setCellValueFactory(new PropertyValueFactory<UserString, String>("email"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<UserString, String>("number"));
        password.setCellValueFactory(new PropertyValueFactory<UserString, String>("password"));
        EmployeeGuest.setCellValueFactory(new PropertyValueFactory<UserString, String>("EmployeeGuest"));
        List<UserString> userStringList=AssetPlusFeatureSet1Controller.getUserString();
        for(UserString userString: userStringList ){
          list.add(userString);
        }
          table.setItems(list);
        

      
    }
    
}
