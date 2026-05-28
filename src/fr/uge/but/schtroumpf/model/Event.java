package fr.uge.but.schtroumpf.model;

// Partie de Mohamed

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Event {

    private final String name;

    private final String description;

    private final Map<ResourceType, Integer> effects;

    public Event(String name, String description) {

        Objects.requireNonNull(name);

        Objects.requireNonNull(description);

        this.name = name;

        this.description = description;

        this.effects = new HashMap<>();
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

        effects.merge(type, value, Integer::sum);
    }

    // =========================
    // APPLICATION DE L'EVENEMENT
    // =========================

    public void apply(SmurfVillage village) {

        Objects.requireNonNull(village);

        // --- AJOUT de Nassim L. ---
        // Protection du Schtroumpf Grognon
        // Le prochain événement négatif est annulé

        if (village.isProtected() && isNegative()) {

            return;
        }

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

    // =========================
    // VERIFICATION EVENEMENT NEGATIF
    // =========================

    public boolean isNegative() {

        for (int value : effects.values()) {

            if (value < 0) {

                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {

        return name + " : " + description;
    }
}