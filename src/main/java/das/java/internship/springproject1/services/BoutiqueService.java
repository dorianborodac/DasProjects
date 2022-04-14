package das.java.internship.springproject1.services;

import das.java.internship.springproject1.dto.BoutiqueDTO;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface BoutiqueService {
    BoutiqueDTO save(BoutiqueDTO boutiqueDTO);

    List<BoutiqueDTO> getAll();

    BoutiqueDTO getById(Long id);

    BoutiqueDTO update(BoutiqueDTO boutiqueDTO);

    HttpStatus deleteById(Long id);
}
