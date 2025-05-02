package tn.esprit.demo2.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMenu;
    private String libelleMenu;
    @Enumerated(EnumType.STRING)
    private TypeMenu typeMenu;
    private double prixTotal;
    @ManyToOne
    private Restaurant restaurant;
    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    private List<Commande> commandeList;
    @OneToMany(mappedBy = "menu",cascade = CascadeType.ALL)
    private List<Composant> composants;
    @ManyToMany
    @JoinTable(name = "menu_chef",
            joinColumns = @JoinColumn(name = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "chef_id"))
    private List<ChefCuisinier> chefs;

    @ManyToOne
    private ChefCuisinier chefCuisinier;

    public Long getId() {
        return idMenu;
    }

    public void setId(Long id) {
        this.idMenu = id;
    }

    public String getLibelle() {
        return libelleMenu;
    }

    public void setLibelle(String libelle) {
        this.libelleMenu = libelle;
    }

    public Double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(Double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public ChefCuisinier getChefCuisinier() {
        return chefCuisinier;
    }

    public void setChefCuisinier(ChefCuisinier chefCuisinier) {
        this.chefCuisinier = chefCuisinier;
    }
}
