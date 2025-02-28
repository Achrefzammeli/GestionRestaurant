package tn.esprit.demo2.repositories;

import org.springframework.stereotype.Repository;
import tn.esprit.demo2.entities.ChaineRestauration;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ChaineRestaurationRepository extends JpaRepository<ChaineRestauration, Long> {
}
