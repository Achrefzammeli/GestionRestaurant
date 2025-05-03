package tn.esprit.demo2.Services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.demo2.Services.IStatistiqueService;
import tn.esprit.demo2.entities.Menu;
import tn.esprit.demo2.entities.Restaurant;
import tn.esprit.demo2.entities.TypeMenu;
import tn.esprit.demo2.repositories.MenuRepository;
import tn.esprit.demo2.repositories.RestaurantRepository;

import java.util.List;

@Service
public class StatistiqueService implements IStatistiqueService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public List<Menu> getMenusByType(String typeMenu) {
        try {
            TypeMenu type = TypeMenu.valueOf(typeMenu);
            return menuRepository.findByTypeMenu(type);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Type de menu invalide. Les types valides sont : PETITDEJUNER, DEJEUNER, DINER");
        }
    }

    @Override
    public List<Restaurant> getRestaurantsByChaine(String libelleChaine) {
        return restaurantRepository.findByChaineRestaurationLibelle(libelleChaine);
    }

    @Override
    public Double getMoyennePrixMenus() {
        return menuRepository.getMoyennePrixMenus();
    }

    @Override
    public List<Menu> getMenusPlusChersQue(Double prix) {
        return menuRepository.findMenusPlusChersQue(prix);
    }

    @Override
    public List<Restaurant> getRestaurantsAvecPlusDeMenus(int nombreMinMenus) {
        return restaurantRepository.findRestaurantsAvecPlusDeMenus(nombreMinMenus);
    }
}

