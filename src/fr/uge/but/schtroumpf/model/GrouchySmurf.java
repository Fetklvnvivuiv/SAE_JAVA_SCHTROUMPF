package fr.uge.but.schtroumpf.model;

import java.util.Objects;

public class GrouchySmurf implements Character {

    private final String name;

    public GrouchySmurf(String name) {

        Objects.requireNonNull(name);

        this.name = name;
    }

    public String getName() {

        return name;
    }

    // =========================
    // SURVEILLER LE VILLAGE
    // +DEFENSE
    // =========================

    public void monitorSurroundings(SmurfVillage village) {

        Objects.requireNonNull(village);

        village.addResource(ResourceType.DEFENSE, 1);
    }

    // =========================
    // DENONCER UN FEIGNANT
    // +GOLD / -MORALE
    // =========================

    public void reportLazySmurf(SmurfVillage village) {

        Objects.requireNonNull(village);

        if (village.getResource(ResourceType.MORALE) < 1) {

            throw new IllegalArgumentException(
                    "Le moral du village est deja trop faible."
            );
        }

        village.removeResource(ResourceType.MORALE, 1);

        village.addResource(ResourceType.GOLD, 1);
    }

    // =========================
    // PREVENIR UNE ATTAQUE
    // Le prochain evenement negatif est annule
    // =========================

    public void preventAttack(SmurfVillage village) {

        Objects.requireNonNull(village);

        village.setProtected(true);
    }

    // =========================
    // INTIMIDER LES ENNEMIS
    // +DEFENSE / -MORALE
    // =========================

    public void intimidateEnemies(SmurfVillage village) {

        Objects.requireNonNull(village);

        if (village.getResource(ResourceType.MORALE) < 1) {

            throw new IllegalArgumentException(
                    "Pas assez de moral pour intimider les ennemis."
            );
        }

        village.addResource(ResourceType.DEFENSE, 2);

        village.removeResource(ResourceType.MORALE, 1);
    }

    // =========================
    // CHOIX D'ACTION
    // =========================

    @Override
    public void playTurn(int choice, SmurfVillage village) {

        Objects.requireNonNull(village);

        switch (choice) {

            case 1 -> monitorSurroundings(village);

            case 2 -> reportLazySmurf(village);

            case 3 -> preventAttack(village);

            case 4 -> intimidateEnemies(village);

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