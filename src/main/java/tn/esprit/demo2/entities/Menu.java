package tn.esprit.demo2.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMenu;
    private String libelleMenu;
    @Enumerated(EnumType.STRING)
    private TypeMenu typeMenu;
    private Float prixTotal;
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
}
