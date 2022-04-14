package das.java.internship.springproject1.services;

import das.java.internship.springproject1.dto.AdressDTO;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface AdressService {
    HttpStatus save(AdressDTO adressDTO);

    List<AdressDTO> getAll();

    AdressDTO getById(Long id);

    AdressDTO update(AdressDTO boutiqueDTO);

    HttpStatus deleteById(Long id);
}
