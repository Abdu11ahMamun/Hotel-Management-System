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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RequestInfo implements Initializable {
    @FXML
    private Button backBTN;
    @FXML
    private TableView<ModelTableBooking> requestInformation;

    @FXML
    private TableColumn<ModelTableBooking,String> colName;

    @FXML
    private TableColumn<ModelTableBooking,String> colEmail;

    @FXML
    private TableColumn<ModelTableBooking,String> colRoom;

    @FXML
    private TableColumn<ModelTableBooking,String> colDeposit;

    @FXML
    private TableColumn<ModelTableBooking,String> colDays;

    @FXML
    private TableColumn<ModelTableBooking,String> colStatus;

    ObservableList<ModelTableBooking> bookingList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();
            ResultSet rs=connectDB.createStatement().executeQuery("SELECT * FROM `booking` where status='pending' ");
            while(rs.next()){
                bookingList.add(new ModelTableBooking(
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("room"),
                        rs.getString("deposit"),
                        rs.getString("days"),
                        rs.getString("status")));
            }
        }catch (Exception ex){
            Logger.getLogger(EmployeeInfo.class.getName()).log(Level.SEVERE,null,ex);

        }

        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colRoom.setCellValueFactory(new PropertyValueFactory<>("room"));
        colDeposit.setCellValueFactory(new PropertyValueFactory<>("deposit"));
        colDays.setCellValueFactory(new PropertyValueFactory<>("days"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }


    @FXML
    private Button load;

    @FXML
    private Button accept;

    @FXML
    void funcLoadData(ActionEvent event) {
        requestInformation.setItems(bookingList);

    }

    @FXML
    void funcAccept(ActionEvent event) {
        try{
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();
            String str = "update booking set status = 'accept' where status='pending'";
            try {
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(str);
                accept.setDisable(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }catch (Exception ee){
            ee.printStackTrace();
        }

    }

    @FXML
    void backAction(ActionEvent event) throws Exception{
        Parent root;
        root = FXMLLoader.load(getClass().getResource("deshboard.fxml"));
        Stage RegStage=(Stage) backBTN.getScene().getWindow();
        RegStage.setScene(new Scene(root,595,365));
    }

}

