package tn.esprit.demo2.Services.imp;
import tn.esprit.demo2.entities.Menu;
import tn.esprit.demo2.Services.MenuService;
import tn.esprit.demo2.repositories.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class MenuServiceImp implements MenuService {
    @Autowired
    private MenuRepository menuRepository;

    @Override
    public   Menu createMenu(Menu menu) {
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
}
