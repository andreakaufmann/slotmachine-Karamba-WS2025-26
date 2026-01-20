package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextField;
import javafx.scene.media.AudioClip;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import slotmachine.model.*;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

public class SpielController {

    @FXML private Label balanceLabel, winLabel, spinsLeftLabel;
    @FXML private ImageView symbol1, symbol2, symbol3, symbol4;
    @FXML private TextField betField;
    @FXML private Button spinButton, increaseBet, decreaseBet, endGameButton;
    @FXML private Button spinTwenty, spinFifty, spinHundred;

    private GameLogic gameLogic;
    private Player player;
    private Reel[] reels;
    private Timeline autoSpinTimeline;

    @FXML
    public void initialize() {

        // 4 Reels (Walzen)
        reels = new Reel[4];
        for (int i = 0; i < 4; i++) {
            reels[i] = new Reel();
        }
        betField.setText("1"); // Default-Einsatz
        winLabel.setText("");

    }


    /**
     * Receives the initial balance from NeuesSpielController.
     * This method MUST exist to fix the red error.
     */
    public void setInitialBalance(double balance) {
        player = new Player(balance);
        gameLogic = new GameLogic(reels, player);
        updateBalanceLabel();

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

    private void autoSpin(int count) {
        disableSpinButtons(true);

        if (autoSpinTimeline != null) {
            autoSpinTimeline.stop();
        }

        autoSpinTimeline = new Timeline();
        autoSpinTimeline.setCycleCount(count);

        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1.4), e -> {
            try {
                int betAmount;
                try {
                    betAmount = Integer.parseInt(betField.getText());
                } catch (NumberFormatException ex) {
                    betAmount = 1;
                }
                if (!player.hasEnoughCredits(betAmount)) {
                    autoSpinTimeline.stop();
                    showNoCreditsScreen();
                    return;
                }
                spinReelsAndUpdateUI(true);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        autoSpinTimeline.getKeyFrames().add(keyFrame);
        autoSpinTimeline.setOnFinished(e -> disableSpinButtons(false));
        autoSpinTimeline.play();
    }

    // Führt Spin aus, prüft Einsatz, berechnet Gewinne und aktualisiert UI
    private void spinReelsAndUpdateUI(boolean showPopup) throws IOException {
        playSound("spin.wav");
        int betAmount = 1;
        try {
            betAmount = Integer.parseInt(betField.getText());
        } catch (NumberFormatException e) {
            betAmount = 1;
        }

        // Spielrunde über GameLogic spielen
        SymbolEnum[] spinResult = gameLogic.playRound(betAmount);

        if(spinResult == null){
            if(autoSpinTimeline != null){
                autoSpinTimeline.stop();
            }
            showNoCreditsScreen();
            return;
        }



        // Gewinn anzeigen, falls vorhanen
        int winnings = gameLogic.calculateWinnings(spinResult, betAmount);
        if (winnings > 0){
            playSound("win.wav");
        }
        if (winnings > 0 && showPopup) {
            handleWin(winnings + " €");
        }

        // UI aktualisieren
        symbol1.setImage(new Image(getClass().getResourceAsStream(spinResult[0].getIconPath())));
        symbol2.setImage(new Image(getClass().getResourceAsStream(spinResult[1].getIconPath())));
        symbol3.setImage(new Image(getClass().getResourceAsStream(spinResult[2].getIconPath())));
        symbol4.setImage(new Image(getClass().getResourceAsStream(spinResult[3].getIconPath())));
        updateBalanceLabel();
        winLabel.setText(winnings > 0 ? winnings + " €" : "");

    }

    private void playSound(String fileName) {
        try {
            AudioClip audioClip = new AudioClip(getClass().getResource("/sounds/" + fileName).toExternalForm());
            audioClip.play();
        } catch (Exception e) {
            System.err.println("Sound konnte nicht geladen werden: " + fileName);
        }
    }


    private void updateBalanceLabel() {
        balanceLabel.setText(String.format("%.2f €", player.getBalance()));

    }
    private void disableSpinButtons(boolean disable) {
        spinButton.setDisable(disable);
        spinTwenty.setDisable(disable);
        spinFifty.setDisable(disable);
        spinHundred.setDisable(disable);
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
    @FXML public void increaseBet() {
        int bet = Integer.parseInt(betField.getText());
        bet++;
        betField.setText(Integer.toString(bet));
    }
    @FXML public void decreaseBet() {
        int bet = Integer.parseInt(betField.getText());
        if (bet > 1) {
            bet--;
            betField.setText(Integer.toString(bet));
        }
    }


    // Navigation
    @FXML
    public void endGame(ActionEvent event) throws IOException {
        switchRoot(event, "/exit-frage.fxml");
    }

    public void showNoCreditsScreen() {
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("i18n.text");
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/credits-aus.fxml"), bundle);
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Game Over");
            stage.setScene(new Scene(root));
            stage.show();

            spinButton.setDisable(true);
            spinTwenty.setDisable(true);
            spinFifty.setDisable(true);
            spinHundred.setDisable(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void switchRoot(ActionEvent event, String fxmlPath) throws IOException {
        ResourceBundle bundle = ResourceBundle.getBundle("i18n.text");
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath), bundle);
        Parent root = loader.load();
        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(root);
    }

}