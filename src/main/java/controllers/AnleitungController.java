package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;
import java.util.ResourceBundle;


public class AnleitungController {

    //Closes the instructions view and returns the user to the main menu.
     //This method is triggered by onAction="#exit"
    @FXML
    public void exit(ActionEvent event) throws IOException {
        ResourceBundle bundle = ResourceBundle.getBundle("i18n.text");

        //Initializes the loader for the main menu
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/start-fenster.fxml"), bundle);
        Parent root = loader.load();

        //Gets the current scene and replace its root with the main menu content
        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(root);
    }
}