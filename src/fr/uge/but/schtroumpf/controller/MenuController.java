package fr.uge.but.schtroumpf.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;

public class MenuController {

    @FXML
    private void nouvellePartie(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(
            getClass().getResource("/fr/uge/but/schtroumpf/view/choixDifficulte.fxml")
        );

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root, 900, 600);

        String css = getClass()
                .getResource("/fr/uge/but/schtroumpf/view/style.css")
                .toExternalForm();

        scene.getStylesheets().add(css);

        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void handleContinue() {

    }
}