package tn.esprit.demo2.Services;
import tn.esprit.demo2.entities.Restaurant;
import java.util.List;
public interface RestaurantService {
    Restaurant createRestaurant(Restaurant restaurant);
    Restaurant getRestaurantById(Long id);
    List<Restaurant> getAllRestaurants();
    Restaurant updateRestaurant(Long id, Restaurant restaurant);
    void deleteRestaurant(Long id);
}
