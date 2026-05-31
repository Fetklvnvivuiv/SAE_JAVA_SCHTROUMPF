package fr.uge.but.schtroumpf.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class GameController {

    @FXML
    private VBox gameSection;

    @FXML
    private VBox cardGourmand;

    @FXML
    private VBox cardGrognon;

    @FXML
    private VBox cardSchtroumpfette;

    @FXML
    private VBox cardBricoleur;

    @FXML
    private VBox cardGrandSchtroumpf;

    private String smurfChoisi = "gourmand";

    @FXML
    private void selectSmurfAction(ActionEvent event) {
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
    private void smurfActionResult(ActionEvent event) {
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

    @FXML
    private void smurfCardSelected(MouseEvent event) {
        Node carteCliquee = (Node) event.getSource();

        resetSmurfCards();

        carteCliquee.getStyleClass().remove("smurf_card_off");

        if (!carteCliquee.getStyleClass().contains("smurf_card_on")) {
            carteCliquee.getStyleClass().add("smurf_card_on");
        }

        smurfChoisi = carteCliquee.getId();

        System.out.println("Schtroumpf sélectionné : " + smurfChoisi);
    }

    private void resetSmurfCards() {
        mettreCarteOff(cardGourmand);
        mettreCarteOff(cardGrognon);
        mettreCarteOff(cardSchtroumpfette);
        mettreCarteOff(cardBricoleur);
        mettreCarteOff(cardGrandSchtroumpf);
    }

    private void mettreCarteOff(Node carte) {
        if (carte == null) {
            System.out.println("Carte null : vérifie le fx:id dans le FXML");
            return;
        }

        carte.getStyleClass().remove("smurf_card_on");

        if (!carte.getStyleClass().contains("smurf_card_off")) {
            carte.getStyleClass().add("smurf_card_off");
        }
    }
}