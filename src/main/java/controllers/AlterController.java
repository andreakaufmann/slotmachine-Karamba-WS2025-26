package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AlterController {

    @FXML
    private void onYes(ActionEvent event) throws IOException {
        switchRoot(event, "/neues-speil.fxml");
    }

    @FXML
    private void onNo(ActionEvent event) throws IOException {
        switchRoot(event, "/start-fenster.fxml");
    }


    private void switchRoot(ActionEvent event, String fxmlPath) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(root);
    }
}
