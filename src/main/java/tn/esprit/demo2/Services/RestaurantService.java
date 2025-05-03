package tn.esprit.demo2.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.demo2.entities.*;
import tn.esprit.demo2.repositories.*;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Optional;
import java.util.HashSet;

@Service
public class RestaurantService implements IRestaurantService {
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private ChefCuisinierRepository chefCuisinierRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private ChaineRestaurationRepository chaineRestaurationRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private CommandeRepository commandeRepository;

    @Override
    public List<String> nomMenuParTypeMenuOrdonneParPrixTotal(TypeMenu typeMenu) {
        return menuRepository.findNomMenuByTypeMenuOrderByPrixTotal(typeMenu);
    }

    @Override
    public List<Menu> listeMenuSelonTypeMenuEtprixComposantsSuperieurAUnMontant(TypeMenu typeMenu, Float prixTotal) {
        return menuRepository.findMenusByTypeMenuAndComposantPrixGreaterThan(typeMenu, prixTotal);
    }

    @Override
    public Menu ajoutComposantsEtMiseAjourPrixMenu(Set<Composant> composants, Long idMenu) {
        Menu menu = menuRepository.findById(idMenu).orElseThrow();
        float total = 0f;
        for (Composant c : composants) {
            total += c.getPrix();
            c.setMenu(menu); // si relation bidirectionnelle
        }
        if (total > 20f) throw new RuntimeException("Prix total > 20 dinars");
        menu.setComposants(new ArrayList<>(composants));
        menu.setPrixTotal(Double.valueOf(total));
        return menuRepository.save(menu);
    }

    @Override
    public List<ChefCuisinier> listChefCuisinierByTypeChefAndRestaurant(TypeChef typeChef, String nomRestaurant) {
        return chefCuisinierRepository.findChefCuisinierByTypeChefAndRestaurant(typeChef, nomRestaurant);
    }

    @Override
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
            ChefCuisinier chef = chefOpt.get();
            menu.setChefCuisinier(null);
            menuRepository.save(menu);
            return chef;
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

    @Override
    public List<String> classifierUtilisateursParInteraction() {
        List<Client> clients = clientRepository.findAll();
        List<String> result = new ArrayList<>();
        for (Client c : clients) {
            double score = 0;
            if (c.getCommandes() != null) {
                score = c.getCommandes().size() * 10;
            }
            c.setScore(score);

            // Classification
            if (score < 10) c.setClasseUtilisateur(ClasseUtilisateur.INACTIF);
            else if (score < 30) c.setClasseUtilisateur(ClasseUtilisateur.OCCASIONNEL);
            else if (score < 70) c.setClasseUtilisateur(ClasseUtilisateur.ACTIF);
            else c.setClasseUtilisateur(ClasseUtilisateur.VIP);

            // Privilèges
            Set<String> privileges = new HashSet<>();
            if (c.getClasseUtilisateur() == ClasseUtilisateur.ACTIF || c.getClasseUtilisateur() == ClasseUtilisateur.VIP) {
                privileges.add("REMISSE_EXCEP");
                privileges.add("VISUALISATION_AVANT");
            }
            c.setPrivileges(privileges);

            clientRepository.save(c);
            result.add(c.getNom() + " " + c.getPrenom() + " : " + score + " (" + c.getClasseUtilisateur() + ")");
        }
        return result;
    }
}