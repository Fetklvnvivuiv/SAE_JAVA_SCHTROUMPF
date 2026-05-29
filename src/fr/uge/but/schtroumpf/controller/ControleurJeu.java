package fr.uge.but.schtroumpf.controller;

import fr.uge.but.schtroumpf.model.*;
import fr.uge.but.schtroumpf.model.Character;
import fr.uge.but.schtroumpf.view.AffichageJeu;
import fr.uge.but.schtroumpf.view.LecteurSaisie;

import java.util.ArrayList;
import java.util.List;

public class ControleurJeu {

    private final List<Character> councilMembers;
    private final SmurfVillage village;
    private final EventManager eventManager;
    private final AffichageJeu affichage;
    private final LecteurSaisie lecteur;
    public ControleurJeu() {

        this.affichage = new AffichageJeu(); //fait la vue console
        this.lecteur = new LecteurSaisie(); // initialise le lecteur de choix
        
        // choix de la difficulte
        affichage.afficherChoixDifficulte();

        int difficultyChoice = lecteur.lireEntierEntre(1, 3);
        
        Difficulty difficulty = switch (difficultyChoice) {

            case 1 -> Difficulty.EASY;
            case 3 -> Difficulty.HARD;
            default -> Difficulty.NORMAL;

        };

        
        // on fait le village de difficulte choisie grace au modele 
        
        this.village = new SmurfVillage(difficulty);
        this.eventManager = new EventManager(); // gestionnaire d'evenements

        // on demarre le conseil avec une arrayList et on ajoute ses membres

        this.councilMembers = new ArrayList<>();
        councilMembers.add(new GrandSmurf("Papa Smurf"));
        councilMembers.add(new HandySmurf("Handy"));
        councilMembers.add(new GourmetSmurf("Gourmet"));
        councilMembers.add(new Smurfette("Smurfette"));
        councilMembers.add(new GrouchySmurf("Grouchy"));
    }

    public void lancerPartie() {

        // boucle principale des 12 mois du jeu

        for (int month = 1; month <= 12; month++) {
            affichage.afficherVillage(village, month);
            // on realise toutes les phases attendues : 
            
            // phase 1, on produit les ressources
            affichage.afficherPhaseProduction();
            village.produceRandomResources();

            // phase 2, on appelle et affichage les evenements aleatoires
            affichage.afficherPhaseEvenement();
            Event currentEvent = eventManager.getRandomEvent();
            affichage.afficherEvenement(currentEvent);
            currentEvent.apply(village);

            // phase 3, le conseil a lieu : on choisit un schtroumpf et son action
            affichage.afficherPhaseConseil();
            int actionsRestantes = 2;
            while (actionsRestantes > 0) {

            	affichage.afficherMessage("\nActions restantes : " + actionsRestantes);
                affichage.afficherChoixSchtroumpf();                
                
                int smurfChoice = lecteur.lireEntierEntre(1, 6);
                // lecteur pour lire le choix utilisateur provenant directement de la vue
            
                // si 6 choisit = option pour skip

                if (smurfChoice == 6) {
                	affichage.afficherMessage("[*] Fin des actions du tour."); 
                	break;
                }

                Character selectedSmurf;
                switch (smurfChoice) {

                    case 1 -> selectedSmurf = councilMembers.get(0);
                    case 2 -> selectedSmurf = councilMembers.get(1);
                    case 3 -> selectedSmurf = councilMembers.get(2);
                    case 4 -> selectedSmurf = councilMembers.get(3);
                    case 5 -> selectedSmurf = councilMembers.get(4);

                    default -> {

                    	affichage.afficherMessage("[!] Choix invalide.");
                        continue;
                    }
                }

                // =========================
                // CHOIX ACTION
                // =========================

                int actionChoice;

                    if (selectedSmurf instanceof GrandSmurf) {
                        affichage.afficherActionsGrandSmurf();

                    } else if (selectedSmurf instanceof HandySmurf) {
                        affichage.afficherActionsBricoleur();

                    } else if (selectedSmurf instanceof GourmetSmurf) {
                        affichage.afficherActionsGourmet();

                    } else if (selectedSmurf instanceof Smurfette) {
                        affichage.afficherActionsSmurfette();

                    } else if (selectedSmurf instanceof GrouchySmurf) {
                        affichage.afficherActionsGrognon();
                    }

                    actionChoice = lecteur.lireEntier();


                // =========================
                // EXECUTION ACTION
                // =========================

                try {

                    selectedSmurf.playTurn(actionChoice, village);
                    affichage.afficherMessage("[OK] Action exécutée.");
                    actionsRestantes--;

                } catch (IllegalArgumentException e) {
                	affichage.afficherMessage("[!] " + e.getMessage());
                }
            }

            // =========================
            // PHASE 4 : CONSOMMATION
            // =========================

            affichage.afficherPhaseConsommation();
            village.consumeResources();
            

            // =========================
            // PHASE 5 : CRISES
            // =========================

            affichage.afficherPhaseCrises();
            for (String crise : village.getCurrentCrises()) {
                affichage.afficherCrise(crise);
            }

            // =========================
            // VERIFICATION DEFAITE
            // =========================

            if (village.hasLostByCrises()) {
                affichage.afficherDefaite(month);

                return;
            }

            // =========================
            // FIN TOUR
            // =========================

            village.endTurnCleanup();
        }

        
        // =========================
        // FIN PARTIE
        // =========================

        int finalScore = village.calculateFinalScore();
        if (village.hasWon()) {
            affichage.afficherVictoire(finalScore);
        } else {
            affichage.afficherDefaiteScore(finalScore);
        }
        affichage.afficherHistorique(eventManager);

    }
}