package tn.esprit.demo2.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.demo2.entities.Composant;
import tn.esprit.demo2.entities.Menu;
import tn.esprit.demo2.entities.TypeMenu;

import java.util.List;
import java.util.Set;

@Repository
public interface MenuRepository  extends JpaRepository<Menu, Long> {
    Menu findByLibelleMenu(String libelleMenu);

    @Query("SELECT m FROM Menu m WHERE m.typeMenu = :typeMenu")
    List<Menu> findByTypeMenu(@Param("typeMenu") TypeMenu typeMenu);

    @Query("SELECT AVG(m.prixTotal) FROM Menu m")
    Double getMoyennePrixMenus();

    @Query("SELECT m FROM Menu m WHERE m.prixTotal > :prix ORDER BY m.prixTotal DESC")
    List<Menu> findMenusPlusChersQue(@Param("prix") Double prix);
}
