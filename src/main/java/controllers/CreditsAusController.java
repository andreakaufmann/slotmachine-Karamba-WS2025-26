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


public class CreditsAusController {

    //Navigates the user back to the new game screen
    @FXML
    public void newGame(ActionEvent event) throws IOException {
        // Find the Stage
        // Access the primary stage
        Stage primaryStage = (Stage) Stage.getWindows().filtered(window -> window instanceof Stage && window.isShowing()).get(0);

        // Switch the content of the MAIN window
        ResourceBundle bundle = ResourceBundle.getBundle("i18n.text");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/neues-spiel.fxml"), bundle);
        Parent root = loader.load();
        primaryStage.getScene().setRoot(root);

        // Close CreditsAus window and start New Game
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
    }

    //Opens the exit screen
    @FXML
    public void endGame(ActionEvent event) throws IOException {
        // Creation of a new separate pop-up window
        ResourceBundle bundle = ResourceBundle.getBundle("i18n.text");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/game-over.fxml"), bundle);
        Parent root = loader.load();

        Stage popupStage = new Stage();
        popupStage.setTitle("Exit?");
        popupStage.setScene(new Scene(root));

        // Make the window modal
        popupStage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
        popupStage.show();
    }

    //Method to switch scenes and apply the current language.
    private void switchRoot(ActionEvent event, String fxmlPath) throws IOException {
        ResourceBundle bundle = ResourceBundle.getBundle("i18n.text");
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath), bundle);
        Parent root = loader.load();

        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(root);
    }
}
