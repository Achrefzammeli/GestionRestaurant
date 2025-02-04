package tn.esprit.demo2.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Composant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComposant;
    private String nomComposant;
    private Float prix;
    @ManyToOne
    private Menu menu;
    @OneToOne(mappedBy = "composant",cascade = CascadeType.ALL)
    private DetailComposant detailComposant;
}
