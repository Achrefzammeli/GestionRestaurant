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
    @PostMapping("/menu")
    public Menu addMenu(Menu menu){
        return menuService.createMenu(menu);
    }
    @PostMapping("/{idMenu}/composants")
    public String ajouterComposantsAuMenu(@PathVariable Long idMenu, @RequestBody Set<Composant> composants) {
        return menuService.ajoutComposantsEtMiseAjourPrixMenu(composants, idMenu);
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
