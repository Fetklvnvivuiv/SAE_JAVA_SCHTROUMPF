package fr.uge.but.schtroumpf.model;

import module java.base;

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

    // Consulter le grimoire
    // Succès : +KNOWLEDGE
    // Échec : -MORALE
    public void consultGrimoire(SmurfVillage village) {
        Objects.requireNonNull(village);

        int chance = random.nextInt(100);

        if (chance < 70) {
            village.addResource(ResourceType.KNOWLEDGE, 2);
        } else {
            village.removeResource(ResourceType.MORALE, 1);
        }
    }

    // Organiser une réunion
    // +MORALE
    public void organizeMeeting(SmurfVillage village) {
        Objects.requireNonNull(village);

        village.addResource(ResourceType.MORALE, 2);
    }

    // Négocier avec les animaux
    // Bonus aléatoire : +GOLD ou +DEFENSE
    public void negotiateWithAnimals(SmurfVillage village) {
        Objects.requireNonNull(village);

        int chance = random.nextInt(2);

        if (chance == 0) {
            village.addResource(ResourceType.GOLD, 2);
        } else {
            village.addResource(ResourceType.DEFENSE, 2);
        }
    }

    // Choix d'action
    @Override
    public void playTurn(SmurfVillage village) {
        Objects.requireNonNull(village);

        int action = random.nextInt(3);

        if (action == 0) {
            consultGrimoire(village);
        } else if (action == 1) {
            organizeMeeting(village);
        } else {
            negotiateWithAnimals(village);
        }
    }
}