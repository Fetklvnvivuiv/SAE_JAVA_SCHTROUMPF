package fr.uge.but.schtroumpf.model;

import java.util.Objects;
import java.util.Random;

public class HandySmurf implements Character {

    private final String name;

    private final Random random;

    public HandySmurf(String name) {

        Objects.requireNonNull(name);

        this.name = name;

        this.random = new Random();
    }

    public String getName() {

        return name;
    }

    // =========================
    // REPARER LES MAISONS
    // +TOOLS / -SARSAPARILLA
    // =========================

    public void repairHouses(SmurfVillage village) {

        Objects.requireNonNull(village);

        if (village.getResource(ResourceType.SARSAPARILLA) < 1) {

            throw new IllegalArgumentException(
                    "Pas assez de salsepareille."
            );
        }

        village.addResource(ResourceType.TOOLS, 1);

        village.removeResource(ResourceType.SARSAPARILLA, 1);
    }

    // =========================
    // FABRIQUER UN PIEGE
    // +DEFENSE / -TOOLS
    // =========================

    public void buildTrap(SmurfVillage village) {

        Objects.requireNonNull(village);

        if (village.getResource(ResourceType.TOOLS) < 1) {

            throw new IllegalArgumentException(
                    "Pas assez d'outils."
            );
        }

        village.addResource(ResourceType.DEFENSE, 1);

        village.removeResource(ResourceType.TOOLS, 1);
    }

    // =========================
    // INVENTER UN GADGET
    // EFFET ALEATOIRE
    // =========================

    public void inventGadget(SmurfVillage village) {

        Objects.requireNonNull(village);

        int value = random.nextInt(4);

        if (value == 0) {

            village.addResource(ResourceType.TOOLS, 2);

        } else if (value == 1) {

            village.addResource(ResourceType.DEFENSE, 2);

        } else if (value == 2) {

            village.addResource(ResourceType.KNOWLEDGE, 1);

        } else {

            village.removeResource(ResourceType.MORALE, 1);
        }
    }

    // =========================
    // CHOIX D'ACTION
    // =========================

    @Override
    public void playTurn(int choice, SmurfVillage village) {

        Objects.requireNonNull(village);

        switch (choice) {

            case 1 -> repairHouses(village);

            case 2 -> buildTrap(village);

            case 3 -> inventGadget(village);

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