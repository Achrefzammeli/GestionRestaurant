package tn.esprit.demo2.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.demo2.entities.ChefCuisinier;
import tn.esprit.demo2.entities.TypeChef;

import java.util.List;

@Repository
public interface ChefCuisinierRepository extends JpaRepository<ChefCuisinier, Long>{
    @Query("SELECT DISTINCT c FROM ChefCuisinier c JOIN c.menus m WHERE c.typeChef = :typeChef AND m.restaurant.nom = :nomRestaurant")
    List<ChefCuisinier> findChefCuisinierByTypeChefAndRestaurant(@Param("typeChef") TypeChef typeChef, @Param("nomRestaurant") String nomRestaurant);
}
