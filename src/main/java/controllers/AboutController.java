package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;
import java.util.ResourceBundle;

public class AboutController {

    //Closes About info and navigates back to the start screen
    @FXML
    public void exit(ActionEvent event) throws IOException {
        switchRoot(event, "/start-fenster.fxml");
    }

    //method to switch scenes and apply the current application language
    private void switchRoot(ActionEvent event, String fxmlPath) throws IOException {

        ResourceBundle bundle = ResourceBundle.getBundle("i18n.text");
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath), bundle);
        Parent root = loader.load();

        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(root);
    }
}