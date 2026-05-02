package fr.uge.but.schtroumpf.model;
import module java.base;
//Partie de Mohamed

public class Event {

    private final String name;
    private final String description;
    private final Map<ResourceType, Integer> effects;

    public Event(String name, String description) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(description);

        this.name = name;
        this.description = description;
        effects = new HashMap<>();
    }
    
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Map<ResourceType, Integer> getEffects() {
        return Map.copyOf(effects);
    }
    
    public void addEffect(ResourceType type, int value) {
        Objects.requireNonNull(type);
        effects.put(type, value);
    }
    
    
    //Applique les effets de l'événement au village
    public void apply(SmurfVillage village) {
        Objects.requireNonNull(village);

        for (var entry : effects.entrySet()) {
            ResourceType type = entry.getKey();
            int value = entry.getValue();

            if (value >= 0) {
                village.addResource(type, value);
            } else {
                village.removeResource(type, -value);
            }
        }
    }

    @Override
    public String toString() {
        return name + " : " + description;
    }
}