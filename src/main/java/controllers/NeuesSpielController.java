package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class NeuesSpielController {

    @FXML
    private void onEndGame(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onNewGame(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/spiel.fxml"));
        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(root);
    }
}

//fx:id="gameInstructionsButton" noch nicht geschrieben habe, weil wir keinen fxml haben
//und dies direkt im Code realisiert werden kann.