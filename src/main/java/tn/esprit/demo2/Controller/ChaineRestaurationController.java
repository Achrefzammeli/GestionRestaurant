package tn.esprit.demo2.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.demo2.entities.ChaineRestauration;
import tn.esprit.demo2.repositories.ChaineRestaurationRepository;

import java.time.LocalDate;

@RestController
@RequestMapping("/chaineRestauration")
public class ChaineRestaurationController {
    @Autowired
    private ChaineRestaurationRepository repository;

    @PostMapping("/add")
    public String addChaineRestauration() {
        ChaineRestauration chaine = new ChaineRestauration();
        chaine.setLibelle("La Bonne Cuisine");
        chaine.setDateCreation(LocalDate.now());

        repository.save(chaine);
        return "ChaineRestauration ajoutée avec succès !";
    }
}
