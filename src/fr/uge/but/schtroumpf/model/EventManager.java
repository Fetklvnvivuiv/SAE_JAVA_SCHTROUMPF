package fr.uge.but.schtroumpf.model;
import module java.base;
//Partie de Mohamed

public class EventManager {

    private final List<Event> events;
    private final List<Event> history;
    private final Random random;

    public EventManager() {
        events = new ArrayList<>();
        history = new ArrayList<>();
        this.random = new Random();

        initEvents();
    }

    //Initialise les événements du jeu
    private void initEvents() {

        // Attaque de Gargamel
        Event e1 = new Event("Gargamel Attack", "Gargamel attacks the village");
        e1.addEffect(ResourceType.DEFENSE, -2);
        e1.addEffect(ResourceType.MORALE, -1);

        // Baies magiques
        Event e2 = new Event("Magic Berries", "You found magical berries");
        e2.addEffect(ResourceType.BERRIES, 2);
        e2.addEffect(ResourceType.SARSAPARILLA, 1);

        // Fête des Schtroumpfs
        Event e3 = new Event("Smurf Festival", "The smurfs celebrate");
        e3.addEffect(ResourceType.MORALE, 2);
        e3.addEffect(ResourceType.BERRIES, -1);

        // Tempête
        Event e4 = new Event("Storm", "A storm damages the village");
        e4.addEffect(ResourceType.TOOLS, -2);
        e4.addEffect(ResourceType.KNOWLEDGE, 1);

        // Ajout à la liste avec probabilités
        addEvent(e1);
        addEvent(e1);

        addEvent(e2);

        addEvent(e3);

        addEvent(e4);
        addEvent(e4); 
    }

   
    //Ajoute un événement
    public void addEvent(Event event) {
        Objects.requireNonNull(event);
        events.add(event);
    }

 
    //Tire un événement aléatoire
    public Event getRandomEvent() {
        if (events.isEmpty()) {
            throw new IllegalStateException("No events available");
        }

        int index = random.nextInt(events.size());
        Event event = events.get(index);

        history.add(event);
        return event;
    }


    //Retourne l'historique
    public List<Event> getHistory() {
        return List.copyOf(history);
    }
}