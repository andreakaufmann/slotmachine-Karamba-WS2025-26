package controllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.application.Platform;

//Controller for the age restriction screen (too young).
 //Exits the application upon clicking anywhere on the screen.

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