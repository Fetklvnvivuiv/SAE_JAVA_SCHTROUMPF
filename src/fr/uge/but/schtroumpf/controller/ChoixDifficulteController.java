package fr.uge.but.schtroumpf.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
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
        scene.getStylesheets().add(
            getClass().getResource("/fr/uge/but/schtroumpf/view/style.css").toExternalForm()
        );

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

        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/fr/uge/but/schtroumpf/view/interface.fxml")
        );

        Parent root = loader.load();

        GameController gameController = loader.getController();

        // Écran affiché après le choix de difficulté
        gameController.afficherRessourceCollect();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root, 900, 600);
        scene.getStylesheets().add(
            getClass().getResource("/fr/uge/but/schtroumpf/view/style.css").toExternalForm()
        );

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void cardSelected(MouseEvent event) {
        Node carteCliquee = (Node) event.getSource();

        for (Node carte : difficultyContainer.getChildren()) {
            carte.getStyleClass().remove("difficulty_card_on");

            if (!carte.getStyleClass().contains("difficulty_card_off")) {
                carte.getStyleClass().add("difficulty_card_off");
            }
        }

        carteCliquee.getStyleClass().remove("difficulty_card_off");

        if (!carteCliquee.getStyleClass().contains("difficulty_card_on")) {
            carteCliquee.getStyleClass().add("difficulty_card_on");
        }

        difficulteChoisie = carteCliquee.getId();

        System.out.println("Carte cliquée : " + difficulteChoisie);
    }
}