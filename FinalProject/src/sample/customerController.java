package sample;

import javafx.application.Application;
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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
//extends Application
public class customerController  implements Initializable {

    @FXML
    private Label nameId;

    @FXML
    private ComboBox<String> methodBox;

    @FXML
    private ComboBox<String> roomCombo;

    @FXML
    private TextField numberText;

    @FXML
    private TextField custName;

    @FXML
    private TextField custCountry;

    @FXML
    private TextField custCheckedIn;

    @FXML
    private TextField custDeposit;

    @FXML
    private RadioButton male;

    @FXML
    private RadioButton female;

    @FXML
    private Button addBTN;

    @FXML
    private Button backBTN;

    @FXML
    private Label showlabel;

    @FXML
    private Label showlabel1;

    @FXML
    private TextField custEmail;


    PreparedStatement pst=null;


    ObservableList<String> methodList = FXCollections.observableArrayList("Passport", "National Id", "Driving license");
    ObservableList<String> roomList = FXCollections.observableArrayList();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        methodBox.setItems(methodList);
        try {
            DatabaseConnection connectNow= new DatabaseConnection();
            Connection connectDB=connectNow.getConnection();
            String query = "SELECT `room_number` FROM `room` Where `availability`!='Occupied' ORDER BY `room_number` ASC";
            pst=connectDB.prepareStatement(query);
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                roomList.add(rs.getString("room_number"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        roomCombo.setItems(roomList);

    }
    @FXML
    void RoomComboAction(ActionEvent event) {

    }

    @FXML
    void addAction(ActionEvent event) {
         //bookingInfo();

    }

    @FXML
    void backAction(ActionEvent event) {
        String id=methodBox.getValue();
        String number = numberText.getText();
        String name = custName.getText();
        String email= custEmail.getText();
        String country=custCountry.getText();
        String roomNum=roomCombo.getValue();
        String checkIn = custCheckedIn.getText();
        String deposit = custDeposit.getText();
        String gender = null;

        if (male.isSelected()) {
            gender = "male";

        } else if (female.isSelected()) {
            gender = "female";
        }

        String msg=id+" "+number+" "+name + " " + country + " " + checkIn + " " + deposit + " "+ gender;
       /* try {
           // String msg= msgTextField.getText()+ "\n";
          //  msgTextField.setText("");
          *//*  writer.write(msg);
            writer.flush();
*//*

        }catch (IOException e){
            e.printStackTrace();
        }*/
    }

    @FXML
    void methodAction(ActionEvent event) {

    }

    public void bookingInfo() {
        String id = methodBox.getValue();
        String number = numberText.getText();
        String name = custName.getText();
        String email = custEmail.getText();
        String country = custCountry.getText();
        String roomNum = roomCombo.getValue();
        String checkIn = custCheckedIn.getText();
        String deposit = custDeposit.getText();
        String gender = null;
        if (male.isSelected()) {
            gender = "male";

        } else if (female.isSelected()) {
            gender = "female";
        }

        System.out.println(id + " " + number + " " + name + " " + country + " " + checkIn + " " + deposit + " " + gender);

        /*Database part*/
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        if (name.isBlank() == false && number.isBlank() == false && country.isBlank() == false && checkIn.isBlank() == false && gender.isBlank() == false && deposit.isBlank() == false && gender.isBlank() == false) {
            String insertFields = "INSERT INTO `customer`(`id` ,`number`,`name`,`email`,`gender`,`country`,`room_number`,`status`,`deposit`) VALUES('";
            String insertValues = id + "','" + number + "','" + name + "','" + email + "','" + gender + "','" + country + "','" + roomNum + "','" + checkIn + "','" + deposit + "')";
            String insertToRegister = insertFields + insertValues;
            String updateRoom = "update `room` set `availability` = 'Occupied' where `room_number` = " + roomNum;

            try {
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(insertToRegister);
                statement.executeUpdate(updateRoom);
                showlabel.setText("User has been registered!");
                // addBTN.setDisable(true);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            showlabel1.setText("Fill all the label properly");
        }
    }
}
