package das.java.internship.springproject1.entities;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "adress")
@Data
public class Adress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "adress")
    private String adress;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "boutique_id", referencedColumnName = "id")
    private Boutique boutique;
}