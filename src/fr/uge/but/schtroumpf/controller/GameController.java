package fr.uge.but.schtroumpf.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;

public class GameController {

    @FXML
    private VBox gameSection;

    @FXML
    private void selectSmurfAction() {
        try {
            VBox nouvelleBox = FXMLLoader.load(
                getClass().getResource("/fr/uge/but/schtroumpf/view/selectSmurfAction.fxml")
            );

            gameSection.getChildren().clear();
            gameSection.getChildren().add(nouvelleBox);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void smurfActionResult() {
        try {
            VBox nouvelleBox = FXMLLoader.load(
                getClass().getResource("/fr/uge/but/schtroumpf/view/smurfActionResult.fxml")
            );

            gameSection.getChildren().clear();
            gameSection.getChildren().add(nouvelleBox);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
}