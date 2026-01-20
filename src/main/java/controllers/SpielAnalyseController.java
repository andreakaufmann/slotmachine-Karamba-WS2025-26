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

    private int totalSpins = 0;
    private double totalBet = 0;
    private double totalWin = 0;

    // von SpielController aufgerufen
    public void setStatistics(int spins, double bet, double win) {
        this.totalSpins = spins;
        this.totalBet = bet;
        this.totalWin = win;
        updateLabels();
    }

    @FXML
    public void initialize() {
        updateLabels();
    }

    private void updateLabels() {
        spinsGesamtLabel.setText(String.valueOf(totalSpins));
        einsatzGesamtLabel.setText(String.format("%.2f €", totalBet));
        gewinnGesamtLabel.setText(String.format("%.2f €", totalWin));
        nettoLabel.setText(String.format("%.2f €", totalWin - totalBet));
    }

    @FXML
    public void resetAnalysis() {
        totalSpins = 0;
        totalBet = 0;
        totalWin = 0;
        updateLabels();
    }



    //Closes the analysis view and returns to the Game Over screen
    @FXML
    public void exit(ActionEvent event) throws IOException {
        ResourceBundle bundle = ResourceBundle.getBundle("i18n.text");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/game-over.fxml"), bundle);
        Parent root = loader.load();

        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(root);
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