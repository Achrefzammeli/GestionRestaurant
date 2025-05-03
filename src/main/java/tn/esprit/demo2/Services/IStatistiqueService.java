package tn.esprit.demo2.Services;

import tn.esprit.demo2.entities.Menu;
import tn.esprit.demo2.entities.Restaurant;
import java.util.List;

public interface IStatistiqueService {
    List<Menu> getMenusByType(String typeMenu);
    List<Restaurant> getRestaurantsByChaine(String libelleChaine);
    Double getMoyennePrixMenus();
    List<Menu> getMenusPlusChersQue(Double prix);
    List<Restaurant> getRestaurantsAvecPlusDeMenus(int nombreMinMenus);
} 