package fr.uge.but.schtroumpf.model;

// Ilyesse

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class SmurfVillage {

    private static final int MIN_RESOURCE = 0;

    private static final int MAX_RESOURCE = 10;

    private final Difficulty difficulty;

    private final Map<ResourceType, Integer> resources;

    private final Map<ResourceType, Integer> totalConsumedResources;

    private final Random random;

    // ajout de Nassim L. pour methode "preventAttack" de son schtroumpf grognon
    // si isProtected = true, alors le prochain evenement negatif n'est pas applique

    private boolean isProtected = false;

    // Ajout de Nassim.T
    // Nombre de tours pendant lesquels le bonus temporaire de moral est actif

    private int moraleBoostTurns = 0;

    public SmurfVillage(Difficulty difficulty) {

        this.difficulty = Objects.requireNonNull(difficulty);

        this.resources = new EnumMap<>(ResourceType.class);

        this.totalConsumedResources = new EnumMap<>(ResourceType.class);

        this.random = new Random();

        initializeResources();
    }

    private void initializeResources() {

        int initialValue = switch (difficulty) {

            case EASY -> 7;

            case NORMAL -> 5;

            case HARD -> 3;
        };

        for (ResourceType type : ResourceType.values()) {

            resources.put(type, initialValue);

            totalConsumedResources.put(type, 0);
        }
    }

    public Difficulty getDifficulty() {

        return difficulty;
    }

    public int getResource(ResourceType type) {

        Objects.requireNonNull(type);

        return resources.get(type);
    }

    public Map<ResourceType, Integer> getResourcesView() {

        return Map.copyOf(resources);
    }

    public Map<ResourceType, Integer> getTotalConsumedResourcesView() {

        return Map.copyOf(totalConsumedResources);
    }

    public void addResource(ResourceType type, int amount) {

        Objects.requireNonNull(type);

        int currentValue = resources.get(type);

        int newValue = clamp(currentValue + amount);

        resources.put(type, newValue);
    }

    public void removeResource(ResourceType type, int amount) {

        Objects.requireNonNull(type);

        if (amount < 0) {

            throw new IllegalArgumentException("Amount must be positive.");
        }

        addResource(type, -amount);

        totalConsumedResources.merge(type, amount, Integer::sum);
    }

    // =========================
    // PRODUCTION DES RESSOURCES
    // =========================

    public void produceRandomResources() {

        ResourceType[] types = ResourceType.values();

        // Production aléatoire de 3 ressources par tour

        for (int i = 0; i < 3; i++) {

            ResourceType randomType = types[random.nextInt(types.length)];

            addResource(randomType, 1);
        }
    }

    // =========================
    // CONSOMMATION DES RESSOURCES
    // =========================

    public void consumeResources() {

        // Nourriture consommée chaque tour

        removeResource(ResourceType.BERRIES, 1);

        // Usure des outils

        removeResource(ResourceType.TOOLS, 1);
    }

    // =========================
    // GESTION DES CRISES
    // =========================

    public int countCrises() {

        int crises = 0;

        for (int value : resources.values()) {

            if (value == MIN_RESOURCE) {

                crises++;
            }
        }

        return crises;
    }

    public boolean hasLostByCrises() {

        return countCrises() >= 3;
    }

    // =========================
    // SAISONS
    // =========================

    public Season getSeason(int month) {

        if (month < 1 || month > 12) {

            throw new IllegalArgumentException("Month must be between 1 and 12.");
        }

        return switch (month) {

            case 3, 4, 5 -> Season.SPRING;

            case 6, 7, 8 -> Season.SUMMER;

            case 9, 10, 11 -> Season.AUTUMN;

            default -> Season.WINTER;
        };
    }

    // =========================
    // SCORE FINAL
    // =========================

    public int calculateFinalScore() {

        int score = 0;

        for (int value : resources.values()) {

            score += value;
        }

        return score;
    }

    // =========================
    // LIMITE DES RESSOURCES
    // =========================

    private int clamp(int value) {

        if (value < MIN_RESOURCE) {

            return MIN_RESOURCE;
        }

        if (value > MAX_RESOURCE) {

            return MAX_RESOURCE;
        }

        return value;
    }

    // =========================
    // BONUS TEMPORAIRE MORAL
    // =========================

    // Ajout de Nassim.T
    // Applique un bonus temporaire de moral au village pendant un nombre de tours donné

    public void addTemporaryMoraleBoost(int turns) {

        if (turns <= 0) {

            throw new IllegalArgumentException("Turns must be positive.");
        }

        addResource(ResourceType.MORALE, 1);

        moraleBoostTurns += turns;
    }

    // =========================
    // PROTECTION EVENEMENT NEGATIF
    // =========================

    // Ajout de Nassim L.

    public void setProtected(boolean isProtected) {

        this.isProtected = isProtected;
    }

    // Méthode pour vérifier l'état
    // utilisée par le système d'événements

    public boolean isProtected() {

        return isProtected;
    }

    // =========================
    // FIN DE TOUR
    // =========================

    public void endTurnCleanup() {

        // Ajout de Nassim.T
        // Gestion du bonus temporaire de moral

        if (moraleBoostTurns > 0) {

            moraleBoostTurns--;

            if (moraleBoostTurns == 0) {

                addResource(ResourceType.MORALE, -1);
            }
        }
    }

    // =========================
    // LISTE DES CRISES ACTUELLES
    // =========================

    public List<String> getCurrentCrises() {

        List<String> crises = new ArrayList<>();

        for (ResourceType type : ResourceType.values()) {

            if (getResource(type) == 0) {

                Resource resource = new Resource(type, 0);

                crises.add(resource.getCrisisDescription());
            }
        }

        return crises;
    }

    public boolean isInCrisis(ResourceType type) {

        return getResource(type) == 0;
    }

    // =========================
    // CONDITION DE VICTOIRE
    // =========================

    public boolean hasWon() {

        return calculateFinalScore() >= 35;
    }

    @Override
    public String toString() {

        return resources.toString();
    }
}