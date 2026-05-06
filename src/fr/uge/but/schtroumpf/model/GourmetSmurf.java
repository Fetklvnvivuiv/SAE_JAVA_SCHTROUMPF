package fr.uge.but.schtroumpf.model;

import java.util.Objects;
import java.util.Random;



public class GourmetSmurf implements Character  {

	private final String name;
	private final Random random;
	
	public GourmetSmurf (String name)  {
		Objects.requireNonNull(name);
		this.name = name;
		this.random = new Random ();
	}
	
	public String getName() {
		return name;
	}
	
	// Cueillir Baies
    // +BERRIES
    public void collectBerries(SmurfVillage village) {
        Objects.requireNonNull(village);
        // ajt 2 baies au village
        village.addResource(ResourceType.BERRIES, 2);
    }
    
    
    // Organiser Festin
    // -BERRIES, +MORALE
    public void organizeFeast(SmurfVillage village) {
        Objects.requireNonNull(village);

        // minimum 2 baies pour festin
        if (village.getResource(ResourceType.BERRIES) >= 2) {
            village.removeResource(ResourceType.BERRIES, 2);
            village.addResource(ResourceType.MORALE, 1);
        }
    }
    
 // Trouver champignon rare (bonus aléatoire)
    public void findRareMushroom(SmurfVillage village) {
        Objects.requireNonNull(village);

        int chance = random.nextInt(3);
        if (chance == 0) {
            // Trv baie supplémentaire
            village.addResource(ResourceType.BERRIES, 1);
        } else if (chance == 1) {
            // Trv salsepareille
            village.addResource(ResourceType.SARSAPARILLA, 1);
        } else {
            // Trouve un objet de défense (épine ou autre)
            village.addResource(ResourceType.DEFENSE, 1);
        }
    }
    
    //Choix d'action
    
    @Override
    public void playTurn (SmurfVillage village) {
		Objects.requireNonNull(village);
		
		boolean canFeast = village.getResource(ResourceType.BERRIES)>=2;
		int action = random.nextInt(3);
		
		if (action == 0) {
			collectBerries(village);
		}
		else if (action == 1 && canFeast) {
			organizeFeast(village);
		} else {
			findRareMushroom(village);
		}

    }
}

