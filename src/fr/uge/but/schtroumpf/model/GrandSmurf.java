package fr.uge.but.schtroumpf.model;

import java.util.Objects;
import java.util.Random;

public class GrandSmurf implements Character {

    private final String name;

    private final Random random;

    public GrandSmurf(String name) {

        Objects.requireNonNull(name);

        this.name = name;

        this.random = new Random();
    }

    public String getName() {

        return name;
    }

    // =========================
    // CONSULTER LE GRIMOIRE
    // +KNOWLEDGE ou -MORALE
    // =========================

    public void consultGrimoire(SmurfVillage village) {

        Objects.requireNonNull(village);

        int chance = random.nextInt(100);

        if (chance < 70) {

            village.addResource(ResourceType.KNOWLEDGE, 2);

        } else {

            if (village.getResource(ResourceType.MORALE) > 0) {

                village.removeResource(ResourceType.MORALE, 1);
            }
        }
    }

    // =========================
    // ORGANISER UNE REUNION
    // +MORALE
    // =========================

    public void organizeMeeting(SmurfVillage village) {

        Objects.requireNonNull(village);

        village.addResource(ResourceType.MORALE, 2);
    }

    // =========================
    // NEGOCIER AVEC LES ANIMAUX
    // +GOLD ou +DEFENSE
    // =========================

    public void negotiateWithAnimals(SmurfVillage village) {

        Objects.requireNonNull(village);

        int chance = random.nextInt(2);

        if (chance == 0) {

            village.addResource(ResourceType.GOLD, 2);

        } else {

            village.addResource(ResourceType.DEFENSE, 2);
        }
    }

    // =========================
    // CHOIX D'ACTION
    // =========================

    @Override
    public void playTurn(int choice, SmurfVillage village) {

        Objects.requireNonNull(village);

        switch (choice) {

            case 1 -> consultGrimoire(village);

            case 2 -> organizeMeeting(village);

            case 3 -> negotiateWithAnimals(village);

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