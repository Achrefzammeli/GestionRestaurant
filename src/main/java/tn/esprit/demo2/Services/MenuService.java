package tn.esprit.demo2.Services;
import tn.esprit.demo2.entities.Menu;
import java.util.List;
import java.util.Optional;
public interface MenuService {
    Menu createMenu(Menu menu);
    List<Menu> getAllMenus();
    Optional<Menu> getMenuById(Long id);
    Menu updateMenu(Long id, Menu menuDetails);
    void deleteMenu(Long id);
}
