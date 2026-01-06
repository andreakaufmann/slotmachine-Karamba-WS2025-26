package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

public class SpielController {

    @FXML
    private void onEndGame(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
// Weitere Button-Aktionen sind aktuell nicht implementiert,
// da bewusst noch keine placeholders verwendet werden.
