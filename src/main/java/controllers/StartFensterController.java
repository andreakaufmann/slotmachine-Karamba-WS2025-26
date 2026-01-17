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

public class StartFensterController {


    @FXML
    public void endGame(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }


    @FXML
    public void newGame(ActionEvent event) throws IOException {
        switchRoot(event, "/alter.fxml");
    }


    @FXML
    public void languages(ActionEvent event) throws IOException {
        switchRoot(event, "/sprache.fxml");
    }


    @FXML
    public void aboutGame(ActionEvent event) throws IOException {
        switchRoot(event, "/About.fxml");
    }


    private void switchRoot(ActionEvent event, String fxmlPath) throws IOException {
        //Loading the resource bundle
        ResourceBundle bundle = ResourceBundle.getBundle("i18n.messages");

        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath), bundle);
        Parent root = loader.load();

        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(root);
    }
}