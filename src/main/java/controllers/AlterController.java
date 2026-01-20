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

public class AlterController {

    //Triggered when the user confirms they are old enough
     //Navigates to the new game setup screen.
    @FXML
    public void oldEnough(ActionEvent event) throws IOException {
        switchRoot(event, "/neues-spiel.fxml");
    }

    //Triggered when the user is not old enough.

    @FXML
    public void notOldEnough(ActionEvent event) throws IOException {
        switchRoot(event, "/alter-unter18.fxml");
    }

    //Helper method to handle scene switching with internationalization.
    private void switchRoot(ActionEvent event, String fxmlPath) throws IOException {
        // Updated to use "i18n.text" to remain consistent with your other controllers
        ResourceBundle bundle = ResourceBundle.getBundle("i18n.text");

        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath), bundle);
        Parent root = loader.load();

        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(root);
    }
}