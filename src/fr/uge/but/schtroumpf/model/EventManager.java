package fr.uge.but.schtroumpf.model;

// Partie de Mohamed

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class EventManager {

    private final List<Event> events;

    private final List<Event> history;

    private final Random random;

    public EventManager() {

        this.events = new ArrayList<>();

        this.history = new ArrayList<>();

        this.random = new Random();

        initEvents();
    }

    // =========================
    // INITIALISATION DES EVENEMENTS
    // =========================

    private void initEvents() {

        // =========================
        // Attaque de Gargamel
        // =========================

        Event e1 = new Event(
                "Attaque de Gargamel",
                "Gargamel attaque le village"
        );

        e1.addEffect(ResourceType.DEFENSE, -2);

        e1.addEffect(ResourceType.MORALE, -1);

        // =========================
        // Baies magiques
        // =========================

        Event e2 = new Event(
                "Baies Magiques",
                "Des baies magiques ont ete trouvees"
        );

        e2.addEffect(ResourceType.BERRIES, 2);

        e2.addEffect(ResourceType.SARSAPARILLA, 1);

        // =========================
        // Festival des Schtroumpfs
        // =========================

        Event e3 = new Event(
                "Festival des Schtroumpfs",
                "Les Schtroumpfs organisent une grande fete"
        );

        e3.addEffect(ResourceType.MORALE, 2);

        e3.addEffect(ResourceType.BERRIES, -1);

        // =========================
        // Tempete
        // =========================

        Event e4 = new Event(
                "Tempete",
                "Une tempete frappe le village"
        );

        e4.addEffect(ResourceType.TOOLS, -2);

        e4.addEffect(ResourceType.KNOWLEDGE, 1);

        // =========================
        // Decouverte ancienne
        // =========================

        Event e5 = new Event(
                "Ancien Savoir",
                "Le Grand Schtroumpf retrouve un ancien livre"
        );

        e5.addEffect(ResourceType.KNOWLEDGE, 2);

        // =========================
        // Village voisin
        // =========================

        Event e6 = new Event(
                "Village Voisin",
                "Un village voisin propose un echange"
        );

        e6.addEffect(ResourceType.GOLD, 2);

        e6.addEffect(ResourceType.MORALE, 1);

        // =========================
        // AJOUT DES EVENEMENTS
        // =========================

        addEvent(e1);

        addEvent(e2);

        addEvent(e3);

        addEvent(e4);

        addEvent(e5);

        addEvent(e6);
    }

    // =========================
    // AJOUT EVENEMENT
    // =========================

    public void addEvent(Event event) {

        Objects.requireNonNull(event);

        events.add(event);
    }

    // =========================
    // EVENEMENT ALEATOIRE
    // =========================

    public Event getRandomEvent() {

        if (events.isEmpty()) {

            throw new IllegalStateException("No events available.");
        }

        int index = random.nextInt(events.size());

        Event event = events.get(index);

        history.add(event);

        return event;
    }

    // =========================
    // HISTORIQUE
    // =========================

    public List<Event> getHistory() {

        return List.copyOf(history);
    }

    public void clearHistory() {

        history.clear();
    }

    public int getHistorySize() {

        return history.size();
    }
}