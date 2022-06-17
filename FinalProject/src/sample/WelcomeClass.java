package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class WelcomeClass {
    @FXML
    private Button cancelBTN;

    @FXML
    private Button NextBTN;

    @FXML
    void NextBtnAction(ActionEvent event) throws IOException {
        Parent root;
        root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage RegStage=(Stage) NextBTN.getScene().getWindow();
        RegStage.setScene(new Scene(root,520,480));

    }
    @FXML
    void CancelBtnAction(ActionEvent event) {
        Stage stage=(Stage) cancelBTN.getScene().getWindow();
        stage.close();

    }
}
