package das.java.internship.springproject1.entities;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "boutique")
@Data
public class Boutique {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "short_name")
    private String shortName;

    @Column(name = "adress")
    private String adress;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mall_id", referencedColumnName = "id")
    private Mall mall;

}
