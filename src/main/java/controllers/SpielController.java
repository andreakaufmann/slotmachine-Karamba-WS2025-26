package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import slotmachine.model.Player;
import slotmachine.model.GameLogic;
import slotmachine.model.Reel;
import slotmachine.model.Symbol;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

public class SpielController {

    @FXML private Label balanceLabel, winLabel, symbol1, symbol2, symbol3, symbol4, spinsLeftLabel;
    @FXML private TextField betField;
    @FXML private Button spinButton, increaseBet, decreaseBet, endGameButton;
    @FXML private Button spinTwenty, spinFifty, spinHundred;

    private GameLogic gameLogic;
    private Player player;
    private Reel[] reels;

    @FXML
    public void initialize() {
        Symbol[] symbols = {
                new Symbol("Cherry", 2),
                new Symbol("Lemon", 3),
                new Symbol("Orange", 4),
                new Symbol("Plum", 5),
                new Symbol("Bell", 10),
                new Symbol("Star", 20)
        };

        // 4 Reels (Walzen)
        reels = new Reel[4];
        for (int i = 0; i < 4; i++) {
            reels[i] = new Reel(List.of(symbols));
        }
    }

    /**
     * Receives the initial balance from NeuesSpielController.
     * This method MUST exist to fix the red error.
     */
    public void setInitialBalance(double balance) {
        player = new Player(balance);
        gameLogic = new GameLogic(reels, player);

        if (balanceLabel != null) {
            balanceLabel.setText(String.format("%.2f €", player.getBalance()));
        }
    }

    // Spin-Methoden
    // Manueller Spin
    @FXML
    public void spin(ActionEvent event) throws IOException{
        spinReelsAndUpdateUI(true);
    }

    // Auto-Spins
    @FXML
    public void spinTwenty(ActionEvent event) throws IOException { autoSpin(20); }
    @FXML
    public void spinFifty(ActionEvent event) throws IOException { autoSpin(50); }
    @FXML
    public void spinHundred(ActionEvent event) throws IOException { autoSpin(100); }

    private void autoSpin(int count) throws IOException {
        for (int i = 0; i < count; i++) {
            spinReelsAndUpdateUI(false);
        }
    }

    // Führt Spin aus, prüft Einsatz, berechnet Gewinne und aktualisiert UI
    private void spinReelsAndUpdateUI(boolean showPopup) throws IOException {
        int betAmount = 1;
        try {
            betAmount = Integer.parseInt(betField.getText());
        } catch (NumberFormatException ignored) {}

        // Spielrunde über GameLogic spielen
        Symbol[] spinResult;
        try {
            spinResult = gameLogic.playRound(betAmount);
        } catch (IllegalArgumentException e) {
            showNoCreditsScreen(null);
            return;
        }

        // Gewinn anzeigen, falls vorhanen
        int winnings = gameLogic.calculateWinnings(spinResult, betAmount);
        if (winnings > 0 && showPopup) {
            handleWin(winnings + " €");
        }

        // UI aktualisieren
        symbol1.setText(spinResult[0].getName());
        symbol2.setText(spinResult[1].getName());
        symbol3.setText(spinResult[2].getName());
        symbol4.setText(spinResult[3].getName());
        balanceLabel.setText(String.format("%.2f €", player.getBalance()));
        winLabel.setText(winnings > 0 ? winnings + " €" : "");

    }

    // Gewinn Popup
    public void handleWin(String amount) throws IOException {
        displayWinAmount(amount);
        showWinPopup(amount);
    }

    private void showWinPopup(String amount) throws IOException {
        ResourceBundle bundle = ResourceBundle.getBundle("i18n.text");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gewinn.fxml"), bundle);
        Parent root = loader.load();

        WinController popupController = loader.getController();
        if (popupController != null) {
            popupController.setWinAmount(amount);
        }

        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setScene(new Scene(root));
        popupStage.show();
    }

    public void updateGameUI(String balance, String bet, String lastWin) {
        balanceLabel.setText(balance);
        betField.setText(bet);
        winLabel.setText(lastWin);
    }

    public void displayWinAmount(String amount) {
        winLabel.setText(amount);
    }

    // Bet-Buttons
    @FXML public void increaseBet() { }
    @FXML public void decreaseBet() { }


    // Navigation
    @FXML
    public void endGame(ActionEvent event) throws IOException {
        switchRoot(event, "/exit-frage.fxml");
    }

    public void showNoCreditsScreen(ActionEvent event) throws IOException {
        switchRoot(event, "/credits-aus.fxml");
    }

    private void switchRoot(ActionEvent event, String fxmlPath) throws IOException {
        ResourceBundle bundle = ResourceBundle.getBundle("i18n.text");
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath), bundle);
        Parent root = loader.load();
        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(root);
    }

}