package tn.esprit.demo2.Services.imp;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.demo2.repositories.CommandeRepository;

import java.util.List;

@Service
public class StatistiqueService {
    private final CommandeRepository commandeRepository;

    public StatistiqueService(CommandeRepository commandeRepository) {
        this.commandeRepository = commandeRepository;
    }

    @Scheduled(fixedRate = 10000) // Exécution toutes les 10 secondes (modifiable)
    public void menuPlusCommande() {
        List<Object[]> resultats = commandeRepository.findMenuPlusCommande();

        if (!resultats.isEmpty()) {
            Object[] top = resultats.get(0);
            String libelleMenu = (String) top[0];
            Long nombreCommandes = (Long) top[1];

            System.out.printf("Le menu le plus commandé dans votre restaurant est %s commandé %d fois%n",
                    libelleMenu, nombreCommandes);
        } else {
            System.out.println("Aucune commande enregistrée.");
        }
    }
}

