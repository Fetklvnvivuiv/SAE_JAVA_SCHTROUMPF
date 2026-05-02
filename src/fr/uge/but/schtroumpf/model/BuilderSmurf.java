package fr.uge.but.schtroumpf.model;

import java.util.Objects;
import java.util.Random;

public class BuilderSmurf implements Character {

    private final String name;
    private final Random random;

    public BuilderSmurf(String name) {
        Objects.requireNonNull(name);
        this.name = name;
        this.random = new Random();
    }

    public String getName() {
        return name;
    }

    //Réparer les maisons
    //+TOOLS, -SARSAPARILLA
    public void repairHouses(SmurfVillage village) {
        Objects.requireNonNull(village);

        if (village.getResource(ResourceType.SARSAPARILLA) >= 1) {
            village.addResource(ResourceType.TOOLS, 1);
            village.removeResource(ResourceType.SARSAPARILLA, 1);
        }
    }

    //Fabriquer un piège
    //+DEFENSE, -TOOLS
    public void buildTrap(SmurfVillage village) {
        Objects.requireNonNull(village);

        if (village.getResource(ResourceType.TOOLS) >= 1) {
            village.addResource(ResourceType.DEFENSE, 1);
            village.removeResource(ResourceType.TOOLS, 1);
        }
    }

    //Inventer un gadget (effet aléatoire)
    public void inventGadget(SmurfVillage village) {
        Objects.requireNonNull(village);

        int value = random.nextInt(3);

        if (value == 0) {
            village.addResource(ResourceType.TOOLS, 1);
        } else if (value == 1) {
            village.addResource(ResourceType.DEFENSE, 1);
        } else {
            village.addResource(ResourceType.KNOWLEDGE, 1);
        }
    }

    //Choix d'action 
    @Override
    public void playTurn(SmurfVillage village) {
        Objects.requireNonNull(village);

        boolean canRepair = village.getResource(ResourceType.SARSAPARILLA) >= 1;
        boolean canTrap = village.getResource(ResourceType.TOOLS) >= 1;

        int action = random.nextInt(3);

        if (action == 0 && canRepair) {
            repairHouses(village);
        } else if (action == 1 && canTrap) {
            buildTrap(village);
        } else {
            inventGadget(village);
        }
    }
}