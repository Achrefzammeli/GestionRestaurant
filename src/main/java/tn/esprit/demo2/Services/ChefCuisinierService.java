package tn.esprit.demo2.Services;

import tn.esprit.demo2.entities.ChaineRestauration;
import tn.esprit.demo2.entities.ChefCuisinier;
import tn.esprit.demo2.entities.TypeChef;

import java.util.List;
import java.util.Optional;

public interface ChefCuisinierService {
    List<ChefCuisinier> listChefCuisinierByTypeChefAndRestaurant(TypeChef typeChef, String
            nomRestaurant);
}
