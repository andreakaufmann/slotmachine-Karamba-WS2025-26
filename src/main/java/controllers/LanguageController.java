package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;


public class LanguageController {

    @FXML
    public void germanVersion(ActionEvent event) throws IOException {
        updateLanguage(event, "de");
    }

    @FXML
    public void englishVersion(ActionEvent event) throws IOException {
        updateLanguage(event, "en");
    }

    //Returns the user to the Start Window.
    @FXML
    public void exit(ActionEvent event) throws IOException {
        switchRoot(event, "/start-fenster.fxml");
    }

    //Sets the global application language and reloads the current screen.
    private void updateLanguage(ActionEvent event, String langCode) throws IOException {
        Locale locale = new Locale(langCode);
        Locale.setDefault(locale); // Sets it for the whole app

        // Explicitly refresh with the new locale to ensure the UI updates
        switchRoot(event, "/neues-spiel.fxml");
    }

    //Switches the window content and updates the text to the selected language.
    private void switchRoot(ActionEvent event, String fxmlPath) throws IOException {
        // Using the current default locale set in updateLanguage
        ResourceBundle.clearCache();
        ResourceBundle bundle = ResourceBundle.getBundle("i18n.text", Locale.getDefault());

        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath), bundle);
        Parent root = loader.load();

        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(root);
    }
}