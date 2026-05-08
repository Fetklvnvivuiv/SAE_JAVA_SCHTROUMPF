package fr.uge.but.schtroumpf.model;

import java.util.Objects;
import java.util.Random;

public class GrouchySmurf implements Character {
	private final String name;
    private final Random random;
	
    public GrouchySmurf (String name) {
        Objects.requireNonNull(name);
        this.name = name;
        this.random = new Random();
    }
    
    public String getName() {
        return name;
    }
    
    // surveiller le village : on gagne de la defense 
    public void monitorSurroundings(SmurfVillage village) {
        Objects.requireNonNull(village);
        village.addResource(ResourceType.DEFENSE, 1);
    }

    // denoncer un schtroumpf feignant : on gagne de l'or mais on perd 
    // de la morale --> ce qui decourage les schtroumpfs
    public void reportLazySmurf(SmurfVillage village) {
    	Objects.requireNonNull(village);
    	village.removeResource(ResourceType.MORALE, 1);
    	village.addResource(ResourceType.GOLD, 1);
    }
    
    // prevenir une attaque : annule un evenement negatif
    public void preventAttack(SmurfVillage village) {
    	Objects.requireNonNull(village);
    	// if true, le village est mis en protection, le prochain evenement negatif est annule
    	village.setProtected(true);
    	// setProtected a
    }
    
	@Override
	public void playTurn(SmurfVillage village) {
		// Simulation d'un choix en attendant l'interaction console (Vue) Nassim T.
	    int action = random.nextInt(3); 
	    
	    switch (action) {
	        case 0 -> monitorSurroundings(village);
	        case 1 -> reportLazySmurf(village);
	        case 2 -> preventAttack(village);
	    }
	}
}

