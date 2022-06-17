package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class addRoomClass implements Initializable {

    @FXML
    private TextField availabilityTextField;

    @FXML
    private TextField RoomNumberTextField;

    @FXML
    private TextField PriceTextField;
    @FXML
    private Button BackToDashboardBtn;

    @FXML
    private ComboBox<String> cleaningComboBox;
    ObservableList<String>status= FXCollections.observableArrayList("Clean","Dirty");


    @FXML
    private ComboBox<String> bedTypeComboBox;
    ObservableList<String>bedType= FXCollections.observableArrayList("single","double");
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            cleaningComboBox.setItems(status);
            bedTypeComboBox.setItems(bedType);
    }

    @FXML
    void BackToDeshboardAction(ActionEvent event) throws Exception{
        Parent root;
        root = FXMLLoader.load(getClass().getResource("deshboard.fxml"));
        Stage RegStage=(Stage) BackToDashboardBtn.getScene().getWindow();
        RegStage.setScene(new Scene(root,595,365));
    }

    @FXML
    void saveBtnAction(ActionEvent event) {

    }


}
