package fr.uge.but.schtroumpf.model;

import java.util.Objects;
import java.util.Random;

public class Smurfette implements Character {
	
	private final String name;
	private final Random random;
	
	public Smurfette(String name){
		
		Objects.requireNonNull(name);
		this.name = name;
		this.random = new Random ();
	}
	
	public String getName() {
		return name;
	}
	
	// Négocier avec les villages voisins 
	// +GOLD ou +SARSAPARILLA
	
	public void negotiateWithNeighboringVillages(SmurfVillage village) {
		Objects.requireNonNull(village);
		
		int chance = random.nextInt(2);
		
		if (chance == 0) {
			// +SARSAPARILLA
			village.addResource(ResourceType.SARSAPARILLA, 1);		
		}else {
			// +GOLD
			village.addResource(ResourceType.GOLD, 1);
		}
	}
	
	// Apaiser un conflit interne
	// +MORALE
	
	public void resolveInternalConflict(SmurfVillage village) {
		Objects.requireNonNull(village);
		
		village.addResource(ResourceType.MORALE, 1);
	}
	
	// Organiser une fête
	// -BERRIES, +MORALE pour 2 tours
	public void organizeParty(SmurfVillage village) {
	    Objects.requireNonNull(village);

	    village.removeResource(ResourceType.BERRIES, 1);
	    village.addTemporaryMoraleBoost(2);
	}
	
	//Choix d'action
    
    @Override
    public void playTurn (SmurfVillage village) {
		Objects.requireNonNull(village);
		
		int action = random.nextInt(3);
		boolean canOrganizeParty = village.getResource(ResourceType.BERRIES) > 0;
		
		if (action == 0) {
			negotiateWithNeighboringVillages(village);
		}
		else if (action == 1 && canOrganizeParty) {
			organizeParty(village);
		} else {
			resolveInternalConflict(village);
		}

    }
	
}
