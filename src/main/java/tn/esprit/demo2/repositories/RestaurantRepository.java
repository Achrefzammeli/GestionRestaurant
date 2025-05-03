package tn.esprit.demo2.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.demo2.entities.Restaurant;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository <Restaurant, Long>{
    Restaurant findByNom(String nom);

    @Query("SELECT r FROM Restaurant r WHERE r.chaineRestauration.libelle = :libelleChaine")
    List<Restaurant> findByChaineRestaurationLibelle(@Param("libelleChaine") String libelleChaine);

    @Query("SELECT r FROM Restaurant r WHERE SIZE(r.menus) > :nombreMinMenus")
    List<Restaurant> findRestaurantsAvecPlusDeMenus(@Param("nombreMinMenus") int nombreMinMenus);
}
