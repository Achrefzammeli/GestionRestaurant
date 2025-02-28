package tn.esprit.demo2.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.demo2.entities.Menu;
@Repository
public interface MenuRepository  extends JpaRepository<Menu, Long> {
}
