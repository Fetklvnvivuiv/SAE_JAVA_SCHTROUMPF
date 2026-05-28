package fr.uge.but.schtroumpf;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        try {

            Parent root = FXMLLoader.load(
                    getClass().getResource(
                            "/fr/uge/but/schtroumpf/view/interface.fxml"));

            Scene scene = new Scene(root, 1280, 720);

            String css = getClass().getResource(
                    "/fr/uge/but/schtroumpf/view/style.css")
                    .toExternalForm();

            scene.getStylesheets().add(css);

            primaryStage.setTitle("Conseil des Schtroumpfs");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}