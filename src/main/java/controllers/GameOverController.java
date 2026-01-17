package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

public class GameOverController {

    //Starts a new game session
    @FXML
    public void newGame(ActionEvent event) throws IOException {
        ResourceBundle bundle = ResourceBundle.getBundle("i18n.messages");

        // Loading FXML with the resource bundle
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/neues-spiel.fxml"), bundle);
        Parent root = loader.load();

        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(root);
    }

    /**
     * Closes the window.
     * Name must match onAction="#endGame" in FXML.
     */
    @FXML
    public void endGame(ActionEvent event) {
        // Closing the current pop-up as per teammate's instruction
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}