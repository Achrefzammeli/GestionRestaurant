package tn.esprit.demo2.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient ;
    private String identifiant;
    private Date datePremiereVisite;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Commande> commandeList;
}
