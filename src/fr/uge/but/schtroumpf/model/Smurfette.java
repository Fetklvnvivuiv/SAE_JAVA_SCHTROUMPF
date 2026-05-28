package fr.uge.but.schtroumpf.model;

import java.util.Objects;
import java.util.Random;

public class Smurfette implements Character {

    private final String name;

    private final Random random;

    public Smurfette(String name) {

        Objects.requireNonNull(name);

        this.name = name;

        this.random = new Random();
    }

    public String getName() {

        return name;
    }

    // =========================
    // NEGOCIER AVEC LES VILLAGES VOISINS
    // +GOLD ou +SARSAPARILLA
    // =========================

    public void negotiateWithNeighboringVillages(SmurfVillage village) {

        Objects.requireNonNull(village);

        int chance = random.nextInt(2);

        if (chance == 0) {

            // +SARSAPARILLA

            village.addResource(ResourceType.SARSAPARILLA, 1);

        } else {

            // +GOLD

            village.addResource(ResourceType.GOLD, 1);
        }
    }

    // =========================
    // APAISER UN CONFLIT INTERNE
    // +MORALE
    // =========================

    public void resolveInternalConflict(SmurfVillage village) {

        Objects.requireNonNull(village);

        village.addResource(ResourceType.MORALE, 1);
    }

    // =========================
    // ORGANISER UNE FETE
    // -BERRIES / +MORALE temporaire
    // =========================

    public void organizeParty(SmurfVillage village) {

        Objects.requireNonNull(village);

        if (village.getResource(ResourceType.BERRIES) < 1) {

            throw new IllegalArgumentException(
                    "Pas assez de baies pour organiser une fete."
            );
        }

        village.removeResource(ResourceType.BERRIES, 1);

        village.addTemporaryMoraleBoost(2);
    }

    // =========================
    // CHOIX D'ACTION
    // =========================

    @Override
    public void playTurn(int choice, SmurfVillage village) {

        Objects.requireNonNull(village);

        switch (choice) {

            case 1 -> negotiateWithNeighboringVillages(village);

            case 2 -> resolveInternalConflict(village);

            case 3 -> organizeParty(village);

            default -> throw new IllegalArgumentException(
                    "Choix invalide."
            );
        }
    }

    @Override
    public String toString() {

        return name;
    }
}