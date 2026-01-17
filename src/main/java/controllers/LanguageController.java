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

    @FXML
    public void exit(ActionEvent event) throws IOException {

        ResourceBundle bundle = ResourceBundle.getBundle("i18n.text");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/start-fenster.fxml"), bundle);
        Parent root = loader.load();

        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(root);
    }

    private void updateLanguage(ActionEvent event, String langCode) throws IOException {
        Locale locale = new Locale(langCode);
        Locale.setDefault(locale);


        ResourceBundle bundle = ResourceBundle.getBundle("i18n.text", locale);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sprache.fxml"), bundle);
        Parent root = loader.load();

        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(root);
    }
}