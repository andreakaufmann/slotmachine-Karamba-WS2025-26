package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ResourceBundle;


 //Responsible for input validation and scene switching.
public class NeuesSpielController {

    @FXML
    private TextField creditsField;

    @FXML
    public void initialize() {
        creditsField.setPromptText("Numbers only!");
        creditsField.setText(""); // Feld leer bei Start
        creditsField.requestFocus();
    }
     @FXML
     public void startGame(ActionEvent event) throws IOException {
         String input = creditsField.getText();

         try {
             double startCredit = Double.parseDouble(input);

             // Prüfen, ob Guthaben positiv ist
             if(startCredit <= 0) {
                 creditsField.clear();
                 creditsField.setPromptText("Enter a positive number!");
                 return;
             } else if (startCredit > 10000) {
                 creditsField.clear();
                 creditsField.setPromptText("Maximum number is 10000!");
                 return;
             }

             ResourceBundle bundle = ResourceBundle.getBundle("i18n.text");
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/spiel.fxml"), bundle);
             Parent root = loader.load();

             // Controller für das Spiel initialisieren
             SpielController gameController = loader.getController();
             if (gameController != null) {
                 gameController.setInitialBalance(startCredit); // Now it will find the method!
             }

             Scene scene = ((Node) event.getSource()).getScene();
             scene.setRoot(root);

         } catch (NumberFormatException e) {
             creditsField.clear();
             creditsField.setPromptText("Numbers only!");
         }
     }

    @FXML
    public void endGame(ActionEvent event) throws IOException {
        switchRoot(event, "/exit-frage.fxml");
    }



    @FXML
    public void gameInstruction(ActionEvent event) throws IOException {
        switchRoot(event, "/spielanleitung.fxml");
    }

    private void switchRoot(ActionEvent event, String fxmlPath) throws IOException {
        ResourceBundle bundle = ResourceBundle.getBundle("i18n.text");
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath), bundle);
        Parent root = loader.load();
        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(root);
    }
}
