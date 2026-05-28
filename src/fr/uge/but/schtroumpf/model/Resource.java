package fr.uge.but.schtroumpf.model;
import java.util.Objects;

//Kyaz
public record Resource(ResourceType type, int value) {
	public Resource {
        Objects.requireNonNull(type);
        // on s'assure que valeur reste dans les bornes [0, 10]
        if (value < 0) value = 0;
        if (value > 10) value = 10;
    }
    
    //retourne description de la crise associée si la ressource vient à manquer.
     
    public String getCrisisDescription() {
        return switch (type) {
            case BERRIES -> "Famine : moins d'actions ou de ressources aux prochains tours.";
            case SARSAPARILLA -> "Épidémie : les actions seront moins efficaces.";
            case MORALE -> "Révolte : les Schtroumpfs sont découragés.";
            case DEFENSE -> "Invasion : Gargamel a brisé nos défenses !";
            case KNOWLEDGE -> "Oubli : le Grand Schtroumpf perd ses recettes.";
            default -> "Crise mineure dans le village.";
        };
    }

    //conso de base par tour pour certaines ressources.
    
    public int getBaseConsumption() {
        return switch (type) {
            case BERRIES -> 2; // schtroumpfs mangent bcp
            case GOLD -> 1;    // entretien village
            default -> 0;
        };
    }
    
    @Override
    public String toString() {
        return type + " : " + value;
    }
}
