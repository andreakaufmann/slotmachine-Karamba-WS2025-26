package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.ResourceBundle;

public class AlterController {

    //Triggered when the user is old enough
    @FXML
    public void oldEnough(ActionEvent event) throws IOException {
        //redirects to the new game settings
        switchRoot(event, "/neues-spiel.fxml");
    }

    /**
     * Triggered when the user is not old enough.
     */
    @FXML
    public void notOldEnough(ActionEvent event) throws IOException {
        //redirects to the underage warning screen
        switchRoot(event, "/alter-unter18.fxml");
    }

    private void switchRoot(ActionEvent event, String fxmlPath) throws IOException {
        // Loading the resource bundle for translations
        ResourceBundle bundle = ResourceBundle.getBundle("i18n.messages");

        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath), bundle);
        Parent root = loader.load();

        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(root);
    }
}