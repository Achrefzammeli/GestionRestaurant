package tn.esprit.demo2.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.demo2.Services.MenuService;
import tn.esprit.demo2.entities.Composant;
import tn.esprit.demo2.entities.Menu;
import tn.esprit.demo2.entities.Restaurant;
import tn.esprit.demo2.repositories.RestaurantRepository;
import tn.esprit.demo2.Services.IRestaurantService;
import tn.esprit.demo2.entities.ChefCuisinier;
import tn.esprit.demo2.entities.Commande;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private MenuService menuService;
    @Autowired
    private IRestaurantService restaurantService;

    // Endpoints pour les menus
    @GetMapping("/menu")
    public List<Menu> getAllMenus() {
        return menuService.getAllMenus();
    }

    @GetMapping("/menu/{id}")
    public ResponseEntity<Menu> getMenuById(@PathVariable Long id) {
        Optional<Menu> menu = menuService.getMenuById(id);
        return menu.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/menu")
    public Menu createMenu(@RequestBody Menu menu) {
        return menuService.createMenu(menu);
    }

    @PutMapping("/menu/{id}")
    public ResponseEntity<Menu> updateMenu(@PathVariable Long id, @RequestBody Menu menu) {
        Menu updatedMenu = menuService.updateMenu(id, menu);
        return updatedMenu != null ? ResponseEntity.ok(updatedMenu) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/menu/{id}")
    public ResponseEntity<Void> deleteMenu(@PathVariable Long id) {
        if (menuService.getMenuById(id).isPresent()) {
            menuService.deleteMenu(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Get all restaurants
    @GetMapping
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable Long id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        return restaurant.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping
    public Restaurant createRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable Long id, @RequestBody Restaurant restaurant) {
        if (!restaurantRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        restaurant.setId(id);
        return ResponseEntity.ok(restaurantRepository.save(restaurant));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id) {
        if (!restaurantRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        restaurantRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/affecter-chaine")
    public Restaurant affecterRestaurantAChaineRestauration(
            @RequestParam String nomRestaurant,
            @RequestParam String libelleChaine) {
        return restaurantService.affecterRestaurantAChaineRestauration(nomRestaurant, libelleChaine);
    }

    @PostMapping("/ajouter-avec-menus")
    public Restaurant ajoutRestaurantEtMenuAssocies(@RequestBody Restaurant restaurant) {
        return restaurantService.ajoutRestaurantEtMenuAssocies(restaurant);
    }

    @PostMapping("/affecter-chef-menu")
    public ChefCuisinier affecterChefCuisinierAMenu(
            @RequestParam Long idChefCuisinier,
            @RequestParam Long idMenu) {
        return restaurantService.affecterChefCuisinierAMenu(idChefCuisinier, idMenu);
    }

    @PostMapping("/desaffecter-chef-menu")
    public ChefCuisinier desaffecterChefCuisinierDuMenu(
            @RequestParam Long idMenu,
            @RequestParam Long idChefCuisinier) {
        return restaurantService.desaffecterChefCuisinierDuMenu(idMenu, idChefCuisinier);
    }

    @PostMapping("/ajouter-commande")
    public void ajouterCommandeEtaffecterAClientEtMenu(
            @RequestBody Commande commande,
            @RequestParam String identifiant,
            @RequestParam String libelleMenu) {
        restaurantService.ajouterCommandeEtaffecterAClientEtMenu(commande, identifiant, libelleMenu);
    }

    @PostMapping("/affecter-note-commande")
    public Commande affecterNoteACommande(
            @RequestParam Long idCommande,
            @RequestParam Long note) {
        return restaurantService.affecterNoteACommande(idCommande, note);
    }
}
