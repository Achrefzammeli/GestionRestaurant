package tn.esprit.demo2.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.demo2.entities.*;
import tn.esprit.demo2.repositories.*;

import java.util.Optional;

@Service
public class RestaurantServiceImpl implements IRestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;
    
    @Autowired
    private ChaineRestaurationRepository chaineRestaurationRepository;
    
    @Autowired
    private MenuRepository menuRepository;
    
    @Autowired
    private ChefCuisinierRepository chefCuisinierRepository;
    
    @Autowired
    private ClientRepository clientRepository;
    
    @Autowired
    private CommandeRepository commandeRepository;

    @Override
    @Transactional
    public Restaurant affecterRestaurantAChaineRestauration(String nomRestaurant, String libelleChaine) {
        Restaurant restaurant = restaurantRepository.findByNom(nomRestaurant);
        ChaineRestauration chaine = chaineRestaurationRepository.findByLibelle(libelleChaine);
        
        if (restaurant != null && chaine != null) {
            restaurant.setChaineRestauration(chaine);
            return restaurantRepository.save(restaurant);
        }
        return null;
    }

    @Override
    @Transactional
    public Restaurant ajoutRestaurantEtMenuAssocies(Restaurant restaurant) {
        if (restaurant.getMenus() != null) {
            for (Menu menu : restaurant.getMenus()) {
                menu.setPrixTotal(0.0);
            }
        }
        return restaurantRepository.save(restaurant);
    }

    @Override
    @Transactional
    public ChefCuisinier affecterChefCuisinierAMenu(Long idChefCuisinier, Long idMenu) {
        Optional<ChefCuisinier> chefOpt = chefCuisinierRepository.findById(idChefCuisinier);
        Optional<Menu> menuOpt = menuRepository.findById(idMenu);
        
        if (chefOpt.isPresent() && menuOpt.isPresent()) {
            ChefCuisinier chef = chefOpt.get();
            Menu menu = menuOpt.get();
            menu.setChefCuisinier(chef);
            menuRepository.save(menu);
            return chef;
        }
        return null;
    }

    @Override
    @Transactional
    public ChefCuisinier desaffecterChefCuisinierDuMenu(Long idMenu, Long idChefCuisinier) {
        Optional<Menu> menuOpt = menuRepository.findById(idMenu);
        Optional<ChefCuisinier> chefOpt = chefCuisinierRepository.findById(idChefCuisinier);
        
        if (menuOpt.isPresent() && chefOpt.isPresent()) {
            Menu menu = menuOpt.get();
            if (menu.getChefCuisinier() != null && menu.getChefCuisinier().getId().equals(idChefCuisinier)) {
                menu.setChefCuisinier(null);
                menuRepository.save(menu);
                return chefOpt.get();
            }
        }
        return null;
    }

    @Override
    @Transactional
    public void ajouterCommandeEtaffecterAClientEtMenu(Commande commande, String identifiant, String libelleMenu) {
        Client client = clientRepository.findByIdentifiant(identifiant);
        Menu menu = menuRepository.findByLibelleMenu(libelleMenu);
        
        if (client != null && menu != null) {
            commande.setClient(client);
            commande.setMenu(menu);
            
            // Calcul du total commande et remise
            double prixMenu = menu.getPrixTotal();
            float pourcentageRemise = commande.getPourcentageRemise();
            float totalRemise = (float) ((prixMenu * pourcentageRemise) / 100);
            float totalCommande = (float) (prixMenu - totalRemise);
            
            commande.setTotalCommande(totalCommande);
            commande.setTotalRemise(totalRemise);
            
            commandeRepository.save(commande);
        }
    }

    @Override
    @Transactional
    public Commande affecterNoteACommande(Long idCommande, Long note) {
        Optional<Commande> commandeOpt = commandeRepository.findById(idCommande);
        if (commandeOpt.isPresent()) {
            Commande commande = commandeOpt.get();
            commande.setNote(note);
            return commandeRepository.save(commande);
        }
        return null;
    }
} 