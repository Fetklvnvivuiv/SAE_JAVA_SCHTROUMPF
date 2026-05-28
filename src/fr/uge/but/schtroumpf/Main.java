package fr.uge.but.schtroumpf;

import fr.uge.but.schtroumpf.controller.ControleurJeu;

public class Main {

    public static void main(String[] args) {

        ControleurJeu jeu = new ControleurJeu();

        jeu.lancerPartie();
    }
}