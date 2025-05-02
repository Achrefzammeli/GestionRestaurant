package tn.esprit.demo2.Services;

import tn.esprit.demo2.entities.Restaurant;
import tn.esprit.demo2.entities.ChefCuisinier;
import tn.esprit.demo2.entities.Commande;

public interface IRestaurantService {
    Restaurant affecterRestaurantAChaineRestauration(String nomRestaurant, String libelleChaine);
    Restaurant ajoutRestaurantEtMenuAssocies(Restaurant restaurant);
    ChefCuisinier affecterChefCuisinierAMenu(Long idChefCuisinier, Long idMenu);
    ChefCuisinier desaffecterChefCuisinierDuMenu(Long idMenu, Long idChefCuisinier);
    void ajouterCommandeEtaffecterAClientEtMenu(Commande commande, String identifiant, String libelleMenu);
    Commande affecterNoteACommande(Long idCommande, Long note);
} 