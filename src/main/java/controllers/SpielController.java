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
import java.io.IOException;
import java.util.ResourceBundle;

public class SpielController {

    @FXML private Label balanceLabel, winLabel, symbol1, symbol2, symbol3, symbol4, spinsLeftLabel;
    @FXML private TextField betField;
    @FXML private Button spinButton, increaseBet, decreaseBet, endGameButton;
    @FXML private Button spinTwenty, spinFifty, spinHundred;

    /**
     * Receives the initial balance from NeuesSpielController.
     * This method MUST exist to fix the red error.
     */
    public void setInitialBalance(double balance) {
        if (balanceLabel != null) {
            balanceLabel.setText(String.format("%.2f €", balance));
        }
    }

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

    @FXML public void increaseBet() { }
    @FXML public void decreaseBet() { }

    @FXML
    public void spin(ActionEvent event) throws IOException {
        winLabel.setText("");
    }

    @FXML public void spinTwenty() { }
    @FXML public void spinFifty() { }
    @FXML public void spinHundred() { }

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