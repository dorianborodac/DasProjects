package das.java.internship.springproject1.services;

import das.java.internship.springproject1.dto.MallDTO;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface MallService {
    MallDTO saveMall(MallDTO universityDTO);

    List<MallDTO> getAll();

    MallDTO getById(Long id);

    HttpStatus deleteById(Long id);

    MallDTO update(MallDTO universityDTO);
}
