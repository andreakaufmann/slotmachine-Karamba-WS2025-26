package controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

public class ExitFrageController {

    //YES button:closes the application.
    @FXML
    public void endGame(ActionEvent event) {
        Platform.exit();
    }

    //NO button: closes this window and returns to the previous step
    @FXML
    public void moveForward(ActionEvent event) {
        //find the current window and close it.
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}