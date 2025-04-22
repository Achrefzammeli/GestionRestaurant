package tn.esprit.demo2.Services.imp;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.demo2.entities.Commande;
import tn.esprit.demo2.repositories.CommandeRepository;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

@Service
public class CommandeServiceImp  {
    private final CommandeRepository commandeRepository;

    public CommandeServiceImp(CommandeRepository commandeRepository) {
        this.commandeRepository = commandeRepository;
    }

        @Scheduled(cron = "0 0/30 11 * * *") // Exécution quotidienne à 9h
        public void findCurrentYearCommandesOrderByNote () {
            int currentYear = Year.now().getValue();
            LocalDate startDate = LocalDate.of(currentYear, 1, 1);
            LocalDate endDate = LocalDate.of(currentYear, 12, 31);

            List<Commande> commandes = commandeRepository
                    .findByDateCommandeBetweenOrderByNoteDesc(startDate, endDate);

            if (commandes.isEmpty()) {
                System.out.println("Aucune commande trouvée pour l'année " + currentYear);
                return;
            }

            System.out.println("Commandes de l'année " + currentYear + " ordonnées par note :");
            System.out.println("------------------------------------------------");

            commandes.forEach(commande -> {
                System.out.printf("ID: %d | Date: %s | Note: %.1f | Montant: %.2f€%n",
                        commande.getIdCommande(),
                        commande.getDateCommande().toString(),
                        commande.getNote(),
                        commande.getTotalCommande());
            });

            System.out.println("------------------------------------------------");
            System.out.println("Total: " + commandes.size() + " commandes");
        }
    }