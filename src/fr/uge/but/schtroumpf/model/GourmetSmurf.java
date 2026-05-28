package fr.uge.but.schtroumpf.model;

import java.util.Objects;
import java.util.Random;

public class GourmetSmurf implements Character {

    private final String name;

    private final Random random;

    public GourmetSmurf(String name) {

        Objects.requireNonNull(name);

        this.name = name;

        this.random = new Random();
    }

    public String getName() {

        return name;
    }

    // =========================
    // CUEILLIR DES BAIES
    // +BERRIES
    // =========================

    public void collectBerries(SmurfVillage village) {

        Objects.requireNonNull(village);

        // Ajout de 2 baies

        village.addResource(ResourceType.BERRIES, 2);
    }

    // =========================
    // ORGANISER UN FESTIN
    // -BERRIES / +MORALE
    // =========================

    public void organizeFeast(SmurfVillage village) {

        Objects.requireNonNull(village);

        // Minimum 2 baies nécessaires

        if (village.getResource(ResourceType.BERRIES) < 2) {

            throw new IllegalArgumentException(
                    "Pas assez de baies pour organiser un festin."
            );
        }

        village.removeResource(ResourceType.BERRIES, 2);

        village.addResource(ResourceType.MORALE, 1);
    }

    // =========================
    // TROUVER UN CHAMPIGNON RARE
    // BONUS ALEATOIRE
    // =========================

    public void findRareMushroom(SmurfVillage village) {

        Objects.requireNonNull(village);

        int chance = random.nextInt(4);

        if (chance == 0) {

            village.addResource(ResourceType.BERRIES, 2);

        } else if (chance == 1) {

            village.addResource(ResourceType.SARSAPARILLA, 2);

        } else if (chance == 2) {

            village.addResource(ResourceType.DEFENSE, 1);

        } else {

            village.addResource(ResourceType.MORALE, 1);
        }
    }

    // =========================
    // CHOIX D'ACTION
    // =========================

    @Override
    public void playTurn(int choice, SmurfVillage village) {

        Objects.requireNonNull(village);

        switch (choice) {

            case 1 -> collectBerries(village);

            case 2 -> organizeFeast(village);

            case 3 -> findRareMushroom(village);

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