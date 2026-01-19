package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Controller for the Game Analysis window.
 * This class displays the final game statistics to the user.
 */
public class SpielAnalyseController {

    // These labels connect to the FXML file to display the numbers
    @FXML private Label spinsGesamtLabel;  // Displays total spins
    @FXML private Label einsatzGesamtLabel; // Displays total money spent
    @FXML private Label gewinnGesamtLabel; // Displays total money won
    @FXML private Label nettoLabel;        // Displays net profit/loss

    //Resets all visual labels to zero on the screen.
    //Shows zero but do not erase information from the memory
    @FXML
    public void resetAnalysis() {
        //Update the UI to show zero values
        spinsGesamtLabel.setText("0");
        einsatzGesamtLabel.setText("0.00 €");
        gewinnGesamtLabel.setText("0.00 €");
        nettoLabel.setText("0.00 €");

    }

    //Closes the analysis view and returns to the Game Over screen
    @FXML
    public void exit(ActionEvent event) throws IOException {
        switchRoot(event, "/game-over.fxml");
    }

    //Helper method to switch scenes and apply the current language.
    private void switchRoot(ActionEvent event, String fxmlPath) throws IOException {
        ResourceBundle bundle = ResourceBundle.getBundle("i18n.text");

        // Prepare the new window content
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath), bundle);
        Parent root = loader.load();

        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(root);
    }
}