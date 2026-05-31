package fr.uge.but.schtroumpf.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ChoixDifficulteController {

    @FXML
    private HBox difficultyContainer;

    private String difficulteChoisie;

    @FXML
    private void retourMenu(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(
            getClass().getResource("/fr/uge/but/schtroumpf/view/menu.fxml")
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
    private void validerChoix(ActionEvent event) throws IOException {

        if (difficulteChoisie == null) {
            System.out.println("Aucune difficulté sélectionnée !");
            return;
        }

        System.out.println("Difficulté validée : " + difficulteChoisie);

        Parent root = FXMLLoader.load(
            getClass().getResource("/fr/uge/but/schtroumpf/view/interface.fxml")
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
    private void cardSelected(MouseEvent event) {
        Node carteCliquee = (Node) event.getSource();

        // 1. Toutes les cartes repassent en OFF
        for (Node carte : difficultyContainer.getChildren()) {
            carte.getStyleClass().remove("difficulty_card_on");

            if (!carte.getStyleClass().contains("difficulty_card_off")) {
                carte.getStyleClass().add("difficulty_card_off");
            }
        }

        // 2. La carte cliquée passe en ON
        carteCliquee.getStyleClass().remove("difficulty_card_off");

        if (!carteCliquee.getStyleClass().contains("difficulty_card_on")) {
            carteCliquee.getStyleClass().add("difficulty_card_on");
        }

        // 3. On récupère l'id de la carte
        difficulteChoisie = carteCliquee.getId();

        System.out.println("Carte cliquée : " + difficulteChoisie);
    }
}