package tn.esprit.demo2.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.demo2.entities.Restaurant;
@Repository
public interface RestaurantRepository extends JpaRepository <Restaurant, Long>{
}
