package das.java.internship.springproject1.entities;

import lombok.Data;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;


@Entity
@Table(name = "mall")
@Data
public class Mall {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "short_name")
    private String shortName;

    @Column(name = "registration_number")
    private String registrationNumber;

    @OneToMany(mappedBy = "mall", cascade = CascadeType.ALL)
    private List<Boutique> faculties;
}
