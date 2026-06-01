package fr.uge.but.schtroumpf.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
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

    @FXML
    private Label c1;

    @FXML
    private Label c2;

    @FXML
    private Label c3;

    private String smurfChoisi = "gourmand";

    private String choixAction;

    public void afficherConseil() {
        chargerDansGameSection("/fr/uge/but/schtroumpf/view/conseil.fxml");
    }

    public void afficherRessourceCollect() {
        chargerDansGameSection("/fr/uge/but/schtroumpf/view/ressourceCollect.fxml");
    }

    public void afficherSelectSmurfAction() {
        chargerDansGameSection("/fr/uge/but/schtroumpf/view/selectSmurfAction.fxml");
    }

    public void afficherSmurfActionResult() {
        chargerDansGameSection("/fr/uge/but/schtroumpf/view/smurfActionResult.fxml");
    }

    @FXML
    private void conseil(ActionEvent event) {
        afficherConseil();
    }

    @FXML
    private void ressourceCollect(ActionEvent event) {
        afficherRessourceCollect();
    }

    @FXML
    private void selectSmurfAction(ActionEvent event) {
        afficherSelectSmurfAction();
    }

    @FXML
    private void smurfActionResult(ActionEvent event) {
        if (choixAction == null) {
            System.out.println("Aucune action sélectionnée !");
            return;
        }

        System.out.println("Action validée : " + choixAction);
        afficherSmurfActionResult();
    }

    private void chargerDansGameSection(String cheminFXML) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(cheminFXML));
            loader.setController(this);

            VBox nouvelleBox = loader.load();

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
            System.out.println("Carte null : vérifie le fx:id dans conseil.fxml");
            return;
        }

        carte.getStyleClass().remove("smurf_card_on");

        if (!carte.getStyleClass().contains("smurf_card_off")) {
            carte.getStyleClass().add("smurf_card_off");
        }
    }

    @FXML
    private void choiceSelected(MouseEvent event) {
        Node choixClique = (Node) event.getSource();

        resetChoices();

        choixClique.getStyleClass().remove("smurf_card_off");

        if (!choixClique.getStyleClass().contains("smurf_card_on")) {
            choixClique.getStyleClass().add("smurf_card_on");
        }

        choixAction = choixClique.getId();

        System.out.println("Choix sélectionné : " + choixAction);
    }

    private void resetChoices() {
        mettreChoixOff(c1);
        mettreChoixOff(c2);
        mettreChoixOff(c3);
    }

    private void mettreChoixOff(Node choix) {
        if (choix == null) {
            return;
        }

        choix.getStyleClass().remove("smurf_card_on");

        if (!choix.getStyleClass().contains("smurf_card_off")) {
            choix.getStyleClass().add("smurf_card_off");
        }
    }

    public String getSmurfChoisi() {
        return smurfChoisi;
    }

    public String getChoixAction() {
        return choixAction;
    }
}