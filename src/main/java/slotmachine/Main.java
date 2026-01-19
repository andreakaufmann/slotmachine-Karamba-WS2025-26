package slotmachine;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ResourceBundle;
import java.util.Locale;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        ResourceBundle resources = ResourceBundle.getBundle("i18n.text");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/start-fenster.fxml"), resources);
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root, 700, 650);
        primaryStage.setTitle(resources.getString("slotMachine"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
