package tn.esprit.demo2.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import java.util.Date;
import java.util.Set;

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

    private String nom;
    private String prenom;

    private Double score;

    @Enumerated(EnumType.STRING)
    private ClasseUtilisateur classeUtilisateur;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> privileges;

    // Pour compatibilité avec le service
    public List<Commande> getCommandes() { return commandeList; }
    public void setCommandes(List<Commande> commandes) { this.commandeList = commandes; }
}
