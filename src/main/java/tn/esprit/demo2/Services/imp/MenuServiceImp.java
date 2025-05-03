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
        return menuRepository.findAll();
    }

    @Override
    public Optional<Menu> getMenuById(Long id) {
        return menuRepository.findById(id);
    }

    @Override
    public Menu updateMenu(Long id, Menu menu) {
        if (menuRepository.existsById(id)) {
            menu.setId(id);
            return menuRepository.save(menu);
        }
        return null;
    }

    @Override
    public void deleteMenu(Long id) {
        menuRepository.deleteById(id);
    }

    @Override
    public String ajoutComposantsEtMiseAjourPrixMenu(Set<Composant> composants, Long idMenu) {
        Optional<Menu> menuOpt = menuRepository.findById(idMenu);
        if (menuOpt.isPresent()) {
            Menu menu = menuOpt.get();
            menu.getComposants().addAll(composants);
            double nouveauPrix = menu.getPrixTotal() + composants.stream()
                    .mapToDouble(Composant::getPrix)
                    .sum();
            menu.setPrixTotal(nouveauPrix);
            menuRepository.save(menu);
            return "Composants ajoutés avec succès et prix mis à jour";
        }
        return "Menu non trouvé";
    }
}