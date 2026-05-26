package fr.uge.but.schtroumpf.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;

public class DefeatController {

    public void retourMenu(ActionEvent event) {

        try {

            Parent root = FXMLLoader.load(
                getClass().getResource("/fr/uge/but/schtroumpf/view/menu.fxml")
            );

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

    public void rejouer(ActionEvent event) {

        try {

            Parent root = FXMLLoader.load(
                getClass().getResource("/fr/uge/but/schtroumpf/view/choixDifficulte.fxml")
            );

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