package fr.uge.but.schtroumpf.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;

public class VictoryController {

    public void retourMenu(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fr/uge/but/schtroumpf/view/menu.fxml")
            );

            Parent root = loader.load();

            Scene scene = new Scene(root);

            scene.getStylesheets().add(
                getClass().getResource("/fr/uge/but/schtroumpf/view/style.css").toExternalForm()
            );

            Stage stage = (Stage) ((Node) event.getSource())
                    .getScene()
                    .getWindow();

            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}