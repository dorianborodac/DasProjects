package das.java.internship.springproject1.repositories;


import das.java.internship.springproject1.entities.Mall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MallRepository extends JpaRepository<Mall, Long> {
}
