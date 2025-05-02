package tn.esprit.demo2.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRestaurant;
    private String nom ;
    private Long nbPlacesMax;
    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL)
    private List<Menu> menuList;
    @ManyToOne
    private ChaineRestauration chaineRestauration;
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private Set<Menu> menus;

    // Getters and Setters
    public Long getId() {
        return idRestaurant;
    }

    public void setId(Long id) {
        this.idRestaurant = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ChaineRestauration getChaineRestauration() {
        return chaineRestauration;
    }

    public void setChaineRestauration(ChaineRestauration chaineRestauration) {
        this.chaineRestauration = chaineRestauration;
    }

    public Set<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }
}
