package tn.esprit.demo2.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.demo2.entities.Commande;

@Repository
public interface CommandeRepository  extends JpaRepository<Commande, Long>{
}
