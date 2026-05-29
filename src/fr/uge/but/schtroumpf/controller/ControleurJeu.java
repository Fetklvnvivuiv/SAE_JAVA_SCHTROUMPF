package fr.uge.but.schtroumpf.controller;

import fr.uge.but.schtroumpf.model.*;
import fr.uge.but.schtroumpf.model.Character;
import fr.uge.but.schtroumpf.view.AffichageJeu;

import java.util.ArrayList;
import java.util.List;

public class ControleurJeu {

    private final List<Character> councilMembers;
    private final SmurfVillage village;
    private final EventManager eventManager;
    private final AffichageJeu affichage;
    public ControleurJeu() {

        this.affichage = new AffichageJeu();
        
        // =========================
        // CHOIX DIFFICULTE
        // =========================
        affichage.afficherChoixDifficulte();

        int difficultyChoice = Integer.parseInt(IO.readln());
        Difficulty difficulty;
        
        switch (difficultyChoice) {

            case 1 -> difficulty = Difficulty.EASY;
            case 2 -> difficulty = Difficulty.NORMAL;
            case 3 -> difficulty = Difficulty.HARD;

            default -> {
                IO.print("[!] Choix invalide. Difficulté NORMAL choisie.\n");
                difficulty = Difficulty.NORMAL;
            }
        }

        // =========================
        // INITIALISATION MODELE
        // =========================

        this.village = new SmurfVillage(difficulty);
        this.eventManager = new EventManager();

        // =========================
        // CONSEIL DES SCHTROUMPFS
        // =========================

        this.councilMembers = new ArrayList<>();
        councilMembers.add(new GrandSmurf("Papa Smurf"));
        councilMembers.add(new BuilderSmurf("Handy"));
        councilMembers.add(new GourmetSmurf("Gourmet"));
        councilMembers.add(new Smurfette("Smurfette"));
        councilMembers.add(new GrouchySmurf("Grouchy"));
    }

    public void lancerPartie() {

        // =========================
        // BOUCLE PRINCIPALE
        // =========================

        for (int month = 1; month <= 12; month++) {
            affichage.afficherVillage(village, month);
            // =========================
            // PHASE 1 : PRODUCTION
            // =========================
            affichage.afficherPhaseProduction();
            village.produceRandomResources();

            // =========================
            // PHASE 2 : EVENEMENT
            // =========================

            affichage.afficherPhaseEvenement();
            Event currentEvent = eventManager.getRandomEvent();
            affichage.afficherEvenement(currentEvent);
            currentEvent.apply(village);

            // =========================
            // PHASE 3 : CONSEIL
            // =========================

            affichage.afficherPhaseConseil();
            int actionsRestantes = 2;
            while (actionsRestantes > 0) {

                IO.print("\nActions restantes : " + actionsRestantes + "\n");
                affichage.afficherChoixSchtroumpf();
                int smurfChoice;
                
                try {
                    smurfChoice = Integer.parseInt(IO.readln());

                } catch (NumberFormatException e) {

                    IO.print("[!] Entrée invalide.\n");
                    continue;
                }

                // =========================
                // PASSER
                // =========================

                if (smurfChoice == 6) {
                    IO.print("[*] Fin des actions du tour.\n");
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

                        IO.print("[!] Choix invalide.\n");
                        continue;
                    }
                }

                // =========================
                // CHOIX ACTION
                // =========================

                int actionChoice;
                try {

                    if (selectedSmurf instanceof GrandSmurf) {
                        affichage.afficherActionsGrandSmurf();

                    } else if (selectedSmurf instanceof BuilderSmurf) {
                        affichage.afficherActionsBricoleur();

                    } else if (selectedSmurf instanceof GourmetSmurf) {
                        affichage.afficherActionsGourmet();

                    } else if (selectedSmurf instanceof Smurfette) {
                        affichage.afficherActionsSmurfette();

                    } else if (selectedSmurf instanceof GrouchySmurf) {
                        affichage.afficherActionsGrognon();
                    }

                    actionChoice = Integer.parseInt(IO.readln());

                } catch (NumberFormatException e) {
                	
                    IO.print("[!] Action invalide.\n");
                    continue;
                }

                // =========================
                // EXECUTION ACTION
                // =========================

                try {

                    selectedSmurf.playTurn(actionChoice, village);
                    IO.print("[OK] Action exécutée.\n");
                    actionsRestantes--;

                } catch (IllegalArgumentException e) {
                    IO.print("[!] " + e.getMessage() + "\n");
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
        affichage.afficherVictoire(finalScore);
        affichage.afficherHistorique(eventManager);
    }
}