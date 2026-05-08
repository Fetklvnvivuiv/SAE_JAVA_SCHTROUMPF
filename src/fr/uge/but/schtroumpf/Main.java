package fr.uge.but.schtroumpf;

import fr.uge.but.schtroumpf.model.Difficulty;
import fr.uge.but.schtroumpf.model.SmurfVillage;

public class Main {
    public static void main(String args[]) {
        SmurfVillage village = new SmurfVillage(Difficulty.NORMAL);

        village.produceRandomResources();
        village.consumeResources();

        IO.println(village.getResourcesView());
        IO.println("Crises: " + village.countCrises());
        IO.println("Défaite: " + village.hasLostByCrises());
        IO.println("Score final: " + village.calculateFinalScore());
    }
}