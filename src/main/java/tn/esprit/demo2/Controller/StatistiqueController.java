package tn.esprit.demo2.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.demo2.Services.IStatistiqueService;
import tn.esprit.demo2.entities.Menu;
import tn.esprit.demo2.entities.Restaurant;
import tn.esprit.demo2.entities.TypeMenu;

import java.util.List;

@RestController
@RequestMapping("/api/statistiques")
@Tag(name = "Statistiques", description = "API des statistiques")
public class StatistiqueController {

    @Autowired
    private IStatistiqueService statistiqueService;

    @Operation(summary = "Obtenir les menus par type", description = "Récupère tous les menus d'un type spécifique")
    @GetMapping("/menus/type/{typeMenu}")
    public List<Menu> getMenusByType(@PathVariable String typeMenu) {
        try {
            TypeMenu type = TypeMenu.valueOf(typeMenu);
            return statistiqueService.getMenusByType(type.name());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Type de menu invalide. Les types valides sont : PETITDEJUNER, DEJEUNER, DINER");
        }
    }

    @Operation(summary = "Obtenir les restaurants par chaîne", description = "Récupère tous les restaurants d'une chaîne spécifique")
    @GetMapping("/restaurants/chaine/{libelleChaine}")
    public List<Restaurant> getRestaurantsByChaine(@PathVariable String libelleChaine) {
        return statistiqueService.getRestaurantsByChaine(libelleChaine);
    }

    @Operation(summary = "Obtenir la moyenne des prix", description = "Calcule la moyenne des prix de tous les menus")
    @GetMapping("/menus/moyenne-prix")
    public Double getMoyennePrixMenus() {
        return statistiqueService.getMoyennePrixMenus();
    }

    @Operation(summary = "Obtenir les menus plus chers", description = "Récupère les menus dont le prix est supérieur à une valeur donnée")
    @GetMapping("/menus/plus-chers")
    public List<Menu> getMenusPlusChersQue(@RequestParam Double prix) {
        return statistiqueService.getMenusPlusChersQue(prix);
    }

    @Operation(summary = "Obtenir les restaurants avec plus de menus", description = "Récupère les restaurants ayant plus d'un certain nombre de menus")
    @GetMapping("/restaurants/menus")
    public List<Restaurant> getRestaurantsAvecPlusDeMenus(@RequestParam int nombreMinMenus) {
        return statistiqueService.getRestaurantsAvecPlusDeMenus(nombreMinMenus);
    }
} 