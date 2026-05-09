package fr.uge.but.schtroumpf.controller;

import fr.uge.but.schtroumpf.model.*;
import fr.uge.but.schtroumpf.model.Character;
import fr.uge.but.schtroumpf.view.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ControleurJeu {
	private final List<Character> councilMembers;
	private final SmurfVillage village;
    private final EventManager eventManager;

	public ControleurJeu() {
		// 1. Initialisation du village (Modèle d'Ilyesse)
        this.village = new SmurfVillage(Difficulty.NORMAL);
        
        // 2. Initialisation des événements (Modèle de Mohamed)
        this.eventManager = new EventManager();
        
        // 3. On fait le Conseil (Tous les Schtroumpfs du groupe)
        this.councilMembers = new ArrayList<>();
        councilMembers.add(new BuilderSmurf("Handy"));
        councilMembers.add(new GourmetSmurf("Gourmet"));
        councilMembers.add(new GrouchySmurf("Grouchy"));
        // On ajoutera Smurfette et PapaSmurf quand ils seront prêts
	}
	
	public void lancerPartie() {
		// Le jeu dure 12 tours (12 mois)
        for (int month = 1; month <= 12; month++) {
        	
        	// ÉTAPE 1 : Production des ressources
            village.produceRandomResources();
            
         // ÉTAPE 2 : Événement aléatoire [cite: 29, 54]
            Event currentEvent = eventManager.getRandomEvent();
            
         // On affiche l'événement (via la Vue plus tard)
            System.out.println("Mois " + month + " - Événement : " + currentEvent);
            
         // La méthode apply() de Mohamed gère déjà isProtected en interne ! 
            currentEvent.apply(village);
            
         // ÉTAPE 3 : Choix d'actions du Conseil
         // On fait jouer chaque membre du conseil
            
            for (Character smurf : councilMembers) {
                // Pour l'instant playTurn est en random, 
                // il deviendra interactif avec la Vue de Nassim T.
                smurf.playTurn(village);
            }
            
         // ÉTAPE 4 : Consommation des ressources 
            village.consumeResources();
            
         // ÉTAPE 5 : Vérification des crises
            
            if (village.hasLostByCrises()) {
                    System.out.println("DOMMAGE ! Le village a succombé aux crises au mois " + month);
                    return; // Arrêt immédiat
                }
            
         // ÉTAPE 6 : Nettoyage automatique
            village.endTurnCleanup();
        }
        
     // ÉTAPE 7 : Fin de partie et score
        int finalScore = village.calculateFinalScore();
        System.out.println("VICTOIRE ! Score final : " + finalScore);
	}
}
