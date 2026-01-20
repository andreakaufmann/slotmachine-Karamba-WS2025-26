package controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

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
        //Load the new screen data first
        ResourceBundle bundle = ResourceBundle.getBundle("i18n.text");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/neues-spiel.fxml"), bundle);
        Parent root = loader.load();

        //Identify the primary Stage
        Stage primaryStage = (Stage) Stage.getWindows().stream()
                .filter(window -> window instanceof Stage)
                .findFirst()
                .orElse(null);

        if (primaryStage != null) {
            // Update the Main Window
            primaryStage.getScene().setRoot(root);
            primaryStage.requestFocus();

            //Close every single other window
            Object[] windows = Stage.getWindows().toArray();
            for (Object window : windows) {
                if (window instanceof Stage && window != primaryStage) {
                    ((Stage) window).close();
                }
            }
        }
    }

    //Exit
    @FXML
    public void endGame(ActionEvent event) {
        Platform.exit();
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