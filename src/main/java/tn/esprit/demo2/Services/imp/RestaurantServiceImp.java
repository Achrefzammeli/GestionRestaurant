package tn.esprit.demo2.Services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.demo2.Services.RestaurantService;
import tn.esprit.demo2.entities.Restaurant;
import tn.esprit.demo2.repositories.RestaurantRepository;

import java.util.List;

@Service
public class RestaurantServiceImp implements RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Override
    public Restaurant createRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant getRestaurantById(Long id) {
        return restaurantRepository.findById(id).orElse(null);
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant updateRestaurant(Long id, Restaurant restaurant) {
        return restaurant;
    }

    @Override
    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }

}