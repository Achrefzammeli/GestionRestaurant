package tn.esprit.demo2.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
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
}
