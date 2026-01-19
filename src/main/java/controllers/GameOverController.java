package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import java.io.IOException;
import java.util.ResourceBundle;


public class GameOverController {

    @FXML private Button analyseGameButton;
    @FXML private Button newGameButton;
    @FXML private Button endGameButton;

    //Navigates to the game analysis screen
    @FXML
    public void analyseGame(ActionEvent event) throws IOException {
        switchRoot(event, "/spielanalyse.fxml");
    }

    //Returns to the new game
    @FXML
    public void newGame(ActionEvent event) throws IOException {
        switchRoot(event, "/neues-spiel.fxml");
    }

    //Opens the exit confirmation dialog
    @FXML
    public void endGame(ActionEvent event) throws IOException {
        switchRoot(event, "/exit-frage.fxml");
    }

    //method to switch scenes and apply the current language.
    private void switchRoot(ActionEvent event, String fxmlPath) throws IOException {
        ResourceBundle bundle = ResourceBundle.getBundle("i18n.text");
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath), bundle);
        Parent root = loader.load();

        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(root);
    }
}