package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StartFensterController {

    @FXML
    private void onEndGame(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onNewGame(ActionEvent event) throws IOException {
        switchRoot(event, "/alter.fxml");
    }

    @FXML
    private void onLanguage(ActionEvent event) throws IOException {
        switchRoot(event, "/sprache.fxml");
    }

    private void switchRoot(ActionEvent event, String fxmlPath) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(root);
    }
}
// Für den "About"-Button ist aktuell keine Event-Logik vorhanden,
// da die zugehörige View noch nicht umgesetzt wurde.
