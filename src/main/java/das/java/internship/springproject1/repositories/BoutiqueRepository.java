package das.java.internship.springproject1.repositories;

import das.java.internship.springproject1.entities.Boutique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoutiqueRepository extends JpaRepository<Boutique, Long> {

}
