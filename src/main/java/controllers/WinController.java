package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Node;

//Controller for the win popup. Displays the win amount
public class WinController {

    @FXML
    private Label winAmount;


     //Updates the label with the amount won.
     //Called from SpielController before showing the window.
    public void setWinAmount(String amount) {
        if (winAmount != null) {
            winAmount.setText(amount);
        }
    }

    //Closes the popup
    @FXML
    public void closePopup(MouseEvent event) {

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}