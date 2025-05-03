package tn.esprit.demo2.Services;

import tn.esprit.demo2.entities.*;
import java.util.List;
import java.util.Set;

public interface IRestaurantService {
    List<String> nomMenuParTypeMenuOrdonneParPrixTotal(TypeMenu typeMenu);
    List<Menu> listeMenuSelonTypeMenuEtprixComposantsSuperieurAUnMontant(TypeMenu typeMenu, Float prixTotal);
    Menu ajoutComposantsEtMiseAjourPrixMenu(Set<Composant> composants, Long idMenu);
    List<ChefCuisinier> listChefCuisinierByTypeChefAndRestaurant(TypeChef typeChef, String nomRestaurant);
    Restaurant affecterRestaurantAChaineRestauration(String nomRestaurant, String libelleChaine);
    Restaurant ajoutRestaurantEtMenuAssocies(Restaurant restaurant);
    ChefCuisinier affecterChefCuisinierAMenu(Long idChefCuisinier, Long idMenu);
    ChefCuisinier desaffecterChefCuisinierDuMenu(Long idMenu, Long idChefCuisinier);
    void ajouterCommandeEtaffecterAClientEtMenu(Commande commande, String identifiant, String libelleMenu);
    Commande affecterNoteACommande(Long idCommande, Long note);
} 