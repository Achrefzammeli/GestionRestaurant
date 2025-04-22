package tn.esprit.demo2.Services.imp;
import tn.esprit.demo2.entities.Composant;
import tn.esprit.demo2.entities.Menu;
import tn.esprit.demo2.Services.MenuService;
import tn.esprit.demo2.repositories.ComposantRepository;
import tn.esprit.demo2.repositories.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MenuServiceImp implements MenuService {
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private ComposantRepository composantRepository;
    @Override
    public Menu createMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    @Override
    public List<Menu> getAllMenus() {
        return null;
    }

    @Override
    public Optional<Menu> getMenuById(Long id) {
        return Optional.empty();
    }

    @Override
    public Menu updateMenu(Long id, Menu menuDetails) {
        return null;
    }

    @Override
    public void deleteMenu(Long id) {

    }

    @Override
    public String  ajoutComposantsEtMiseAjourPrixMenu(Set<Composant> composants, Long idMenu) {
            Menu menu = menuRepository.findById(idMenu).orElseThrow(()->new RuntimeException("idmenu non trouvé"));
            menu.getComposants().addAll(composants);
        double nouveauPrix = 0.0;
        for (Composant composant : composants) {
            nouveauPrix += composant.getPrix();}

        if (nouveauPrix > 20.0) {
            return "Erreur";
            }
            menu.setPrixTotal(nouveauPrix);
            menuRepository.save(menu);
            return "succès";

    }
}