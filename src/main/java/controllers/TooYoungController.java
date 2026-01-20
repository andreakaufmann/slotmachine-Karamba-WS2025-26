package controllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.application.Platform;


public class TooYoungController {

    //Terminates the application when the user clicks the screen.

    @FXML
    public void closeApplication(MouseEvent event) {
        // Shuts down the JavaFX platform
        Platform.exit();
        // Force terminates the JVM process to ensure complete closure
        System.exit(0);
    }
}