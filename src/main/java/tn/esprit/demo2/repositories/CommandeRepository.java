package tn.esprit.demo2.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.demo2.entities.Commande;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CommandeRepository  extends JpaRepository<Commande, Long>{
    List<Commande> findByDateCommandeBetweenOrderByNoteDesc(LocalDate startDate, LocalDate endDate);
    @Query("SELECT c.menu.libelleMenu, COUNT(c) FROM Commande c GROUP BY c.menu.libelleMenu ORDER BY COUNT(c) DESC")
    List<Object[]> findMenuPlusCommande();  // retourne une liste d'objets [nom, count]
}
