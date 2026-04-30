package fr.uge.but.schtroumpf.model;
//Ilyesse
import module java.base;

public class SmurfVillage {
    private static final int MIN_RESOURCE = 0;
    private static final int MAX_RESOURCE = 10;

    private final Difficulty difficulty;
    private final Map<ResourceType, Integer> resources;
    private final Map<ResourceType, Integer> totalConsumedResources;
    private final Random random;

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
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }

        addResource(type, -amount);
        totalConsumedResources.merge(type, amount, Integer::sum);
    }

    public void produceRandomResources() {
        ResourceType[] types = ResourceType.values();

        for (int i = 0; i < 2; i++) {
            ResourceType randomType = types[random.nextInt(types.length)];
            addResource(randomType, 1);
        }
    }

    public void consumeResources() {
        removeResource(ResourceType.BERRIES, 2);
        removeResource(ResourceType.GOLD, 1);
    }

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

    public int calculateFinalScore() {
        int score = 0;

        for (int value : resources.values()) {
            score += value;
        }

        return score;
    }

    private int clamp(int value) {
        if (value < MIN_RESOURCE) {
            return MIN_RESOURCE;
        }

        if (value > MAX_RESOURCE) {
            return MAX_RESOURCE;
        }

        return value;
    }
}