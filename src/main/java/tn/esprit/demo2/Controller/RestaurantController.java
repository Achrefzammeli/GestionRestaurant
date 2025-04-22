package tn.esprit.demo2.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.demo2.Services.MenuService;
import tn.esprit.demo2.entities.Composant;
import tn.esprit.demo2.entities.Menu;
import tn.esprit.demo2.entities.Restaurant;
import tn.esprit.demo2.repositories.RestaurantRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private MenuService menuService;
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
}
