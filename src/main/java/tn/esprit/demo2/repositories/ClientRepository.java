package tn.esprit.demo2.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.demo2.entities.Client;
@Repository
public interface ClientRepository extends JpaRepository<Client, Long>
        {
}
