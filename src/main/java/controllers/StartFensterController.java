package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;
import java.util.ResourceBundle;


public class StartFensterController {

    //Exits to the confirmation screen
    @FXML
    public void endGame(ActionEvent event) throws IOException {
        switchRoot(event, "/exit-frage.fxml");
    }

    //Navigates to the age verification screen.
    @FXML
    public void newGame(ActionEvent event) throws IOException {
        switchRoot(event, "/alter.fxml");
    }

    //Navigates to the language selection screen
    @FXML
    public void languages(ActionEvent event) throws IOException {
        switchRoot(event, "/sprache.fxml");
    }

    //Navigates to the about screen
    @FXML
    public void aboutGame(ActionEvent event) throws IOException {
        switchRoot(event, "/about.fxml");
    }

    //Helper method to handle scene switching with internationalization.
    private void switchRoot(ActionEvent event, String fxmlPath) throws IOException {
        //Updated bundle path to match your i18n/text.properties location
        ResourceBundle bundle = ResourceBundle.getBundle("i18n.text");

        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath), bundle);
        Parent root = loader.load();

        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(root);
    }
}