package fr.uge.but.schtroumpf.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;

public class ChoixDifficulteController {

    @FXML
    private void retourMenu(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(
            getClass().getResource("/fr/uge/but/schtroumpf/view/menu.fxml")
        );

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root, 1280, 720);

        String css = getClass()
                .getResource("/fr/uge/but/schtroumpf/view/style.css")
                .toExternalForm();

        scene.getStylesheets().add(css);

        stage.setScene(scene);
        stage.show();
    }
}