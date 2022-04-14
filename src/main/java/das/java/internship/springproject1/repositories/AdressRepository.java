package das.java.internship.springproject1.repositories;

import das.java.internship.springproject1.entities.Adress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdressRepository extends JpaRepository<Adress, Long> {
}
