package fr.uge.but.schtroumpf.view;

import fr.uge.but.schtroumpf.model.Event;
import fr.uge.but.schtroumpf.model.EventManager;
import fr.uge.but.schtroumpf.model.ResourceType;
import fr.uge.but.schtroumpf.model.SmurfVillage;

import java.util.Map;

public class AffichageJeu {
	
	
	public void afficherMessage(String message) {
	    IO.print(message + "\n");
	}
	
    // =========================
    // MENU DIFFICULTE
    // =========================

    public void afficherChoixDifficulte() {

        IO.print("""
╔══════════════════════════════════════════════════════╗
║      CONSEIL DES SCHTROUMPFS - Jeu de Role          ║
╠══════════════════════════════════════════════════════╣
║  Guidez le village des Schtroumpfs pendant 12 mois  ║
║  face aux attaques de Gargamel et aux crises !      ║
╚══════════════════════════════════════════════════════╝

Choisissez votre niveau de difficulte :

1. Facile
2. Normal
3. Difficile

Votre choix : 
""");
    }

    // =========================
    // AFFICHAGE VILLAGE
    // =========================

    public void afficherVillage(SmurfVillage village, int month) {

        IO.print("\n");
        IO.print("════════════════════════════════════════════════════════\n");
        IO.print("              MOIS " + month + " / 12\n");
        IO.print("════════════════════════════════════════════════════════\n");

        IO.print("Saison : " + village.getSeason(month) + "\n\n");

        IO.print("┌─ Ressources du village ─────────────────────────────┐\n");

        for (Map.Entry<ResourceType, Integer> entry : village.getResourcesView().entrySet()) {

            String name = formatResourceName(entry.getKey());

            int value = entry.getValue();

            String bar = createBar(value);

            IO.print(String.format("│ %-15s %s %2d/10\n", name, bar, value));
        }

        IO.print("└─────────────────────────────────────────────────────┘\n");
    }

    // =========================
    // PHASES
    // =========================

    public void afficherPhaseProduction() {

        IO.print("\n[Phase 1 - Production des ressources]\n");
    }

    public void afficherPhaseEvenement() {

        IO.print("\n[Phase 2 - Evenement aleatoire]\n");
    }

    public void afficherPhaseConseil() {

        IO.print("\n[Phase 3 - Actions du Conseil]\n");
    }

    public void afficherPhaseConsommation() {

        IO.print("\n[Phase 4 - Consommation des ressources]\n");
    }

    public void afficherPhaseCrises() {

        IO.print("\n[Phase 5 - Verification des crises]\n");
    }

    // =========================
    // EVENEMENT
    // =========================

    public void afficherEvenement(Event event) {

        IO.print("\n");
        IO.print("[!] " + event.getName() + "\n");
        IO.print("    " + event.getDescription() + "\n");
    }

    // =========================
    // CHOIX SCHTROUMPF
    // =========================

    public void afficherChoixSchtroumpf() {

        IO.print("""
Choisissez un personnage :

1. Grand Schtroumpf
2. Schtroumpf Bricoleur
3. Schtroumpf Gourmand
4. Schtroumpfette
5. Schtroumpf Grognon
6. Passer

Votre choix :
""");
    }

    // =========================
    // ACTIONS GRAND SCHTROUMPF
    // =========================

    public void afficherActionsGrandSmurf() {

        IO.print("""
Actions de Grand Schtroumpf :

1. Consulter le grimoire
2. Organiser une reunion
3. Negocier avec les animaux

Votre choix :
""");
    }

    // =========================
    // ACTIONS BRICOLEUR
    // =========================

    public void afficherActionsBricoleur() {

        IO.print("""
Actions de Schtroumpf Bricoleur :

1. Reparer les maisons
2. Fabriquer un piege
3. Inventer un gadget

Votre choix :
""");
    }

    // =========================
    // ACTIONS GOURMAND
    // =========================

    public void afficherActionsGourmet() {

        IO.print("""
Actions de Schtroumpf Gourmand :

1. Cueillir des baies
2. Organiser un festin
3. Trouver un champignon rare

Votre choix :
""");
    }

    // =========================
    // ACTIONS SMURFETTE
    // =========================

    public void afficherActionsSmurfette() {

        IO.print("""
Actions de Smurfette :

1. Negocier avec les villages voisins
2. Apaiser un conflit interne
3. Organiser une fete

Votre choix :
""");
    }

    // =========================
    // ACTIONS GROGNON
    // =========================

    public void afficherActionsGrognon() {

        IO.print("""
Actions de Schtroumpf Grognon :

1. Surveiller les alentours
2. Denoncer un paresseux
3. Prevenir une attaque
4. Intimider les ennemis

Votre choix :
""");
    }

    // =========================
    // CRISE
    // =========================

    public void afficherCrise(String crise) {

        IO.print("[CRISE] " + crise + "\n");
    }

    // =========================
    // DEFAITE
    // =========================

    public void afficherDefaite(int month) {

        IO.print("""
╔══════════════════════════════════════════════════════╗
║                     DEFAITE                         ║
╚══════════════════════════════════════════════════════╝
""");

        IO.print("Le village a succombe aux crises au mois " + month + ".\n");
    }
    
    public void afficherDefaiteScore(int score) {

        IO.print("""
╔══════════════════════════════════════════════════════╗
║                     DEFAITE                         ║
╚══════════════════════════════════════════════════════╝
    """);

        IO.print("Le village a survecu aux 12 mois, mais son score final est insuffisant.\n");

        IO.print("Score final : " + score + "\n");
    }
    
    
    
    // =========================
    // VICTOIRE
    // =========================

    public void afficherVictoire(int score) {

        IO.print("""
╔══════════════════════════════════════════════════════╗
║                     VICTOIRE                        ║
╚══════════════════════════════════════════════════════╝
""");

        IO.print("Le village a survecu aux 12 mois.\n");

        IO.print("Score final : " + score + "\n");

        if (score >= 50) {

            IO.print("[OK] Excellent conseil des Schtroumpfs !\n");

        } else if (score >= 35) {

            IO.print("[OK] Le village est stable.\n");

        } else {

            IO.print("[!] Victoire difficile...\n");
        }
    }

    // =========================
    // HISTORIQUE
    // =========================

    public void afficherHistorique(EventManager manager) {

        IO.print("""
════════════════════════════════════════════════════════
                HISTORIQUE DES EVENEMENTS
════════════════════════════════════════════════════════
""");

        for (Event event : manager.getHistory()) {

            IO.print("- " + event.getName() + "\n");
        }
    }

    // =========================
    // BARRE VISUELLE
    // =========================

    private String createBar(int value) {

        StringBuilder builder = new StringBuilder();

        builder.append("[");

        for (int i = 0; i < 10; i++) {

            if (i < value) {

                builder.append("█");

            } else {

                builder.append("░");
            }
        }

        builder.append("]");

        return builder.toString();
    }

    // =========================
    // NOM RESSOURCE
    // =========================

    private String formatResourceName(ResourceType type) {

        return switch (type) {

            case BERRIES -> "Baies";

            case SARSAPARILLA -> "Salsepareille";

            case GOLD -> "Or";

            case TOOLS -> "Outils";

            case MORALE -> "Moral";

            case DEFENSE -> "Defense";

            case KNOWLEDGE -> "Savoir";
        };
    }
}