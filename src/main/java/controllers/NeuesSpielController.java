package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

public class NeuesSpielController {

    @FXML
    private TextField rightCreditValue;


    @FXML
    public void endGame(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    //Starts the game session and loads the game board.
    @FXML
    public void startGame(ActionEvent event) throws IOException {
        // According to teammate: use ResourceBundle for all FXML loads
        switchRoot(event, "/spiel.fxml");
    }

    //Opens the game instructions screen.
    @FXML
    public void gameInstruction(ActionEvent event) throws IOException {
        switchRoot(event, "/spielanleitung.fxml");
    }

    //Helper method to switch scenes while preserving localized text.
    private void switchRoot(ActionEvent event, String fxmlPath) throws IOException {
        //bundle
        ResourceBundle bundle = ResourceBundle.getBundle("i18n.text");

        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath), bundle);
        Parent root = loader.load();

        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(root);
    }
}