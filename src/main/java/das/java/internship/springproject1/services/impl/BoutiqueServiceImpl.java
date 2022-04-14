package das.java.internship.springproject1.services.impl;

import das.java.internship.springproject1.dto.BoutiqueDTO;
import das.java.internship.springproject1.entities.Boutique;
import das.java.internship.springproject1.repositories.BoutiqueRepository;
import das.java.internship.springproject1.services.BoutiqueService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoutiqueServiceImpl implements BoutiqueService {
    private final ModelMapper modelMapper;
    private final MallServiceImpl mallService;
    private final BoutiqueRepository boutiqueRepository;
    @Override
    public BoutiqueDTO save(BoutiqueDTO boutiqueDTO) {
        Boutique boutique = modelMapper.map(boutiqueDTO, Boutique.class);
      //  faculty.setUniversity(modelMapper.map(universityService.getById(facultyDTO.getUniversityId()), University.class));
        Boutique savedBoutique = boutiqueRepository.save(boutique);
      //  return savedFaculty==null?HttpStatus.BAD_REQUEST:HttpStatus.OK;
        return modelMapper.map(savedBoutique, BoutiqueDTO.class);
    }

    @Override
    public List<BoutiqueDTO> getAll() {
        return boutiqueRepository.findAll().stream()
                .map(boutique -> modelMapper.map(boutique, BoutiqueDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public BoutiqueDTO getById(Long id) {
        return boutiqueRepository.findById(id)
                .map(boutique -> modelMapper.map(boutique, BoutiqueDTO.class))
                .orElse(null);
    }

    @Override
    public BoutiqueDTO update(BoutiqueDTO boutiqueDTO) {
        Boutique newBoutique = modelMapper.map(boutiqueDTO, Boutique.class);
        Optional<Boutique> optionalFaculty = boutiqueRepository.findById(newBoutique.getId());
        if(optionalFaculty.isPresent()){
            Boutique oldBoutique = optionalFaculty.get();

            newBoutique.setId(oldBoutique.getId());
            newBoutique.setMall(oldBoutique.getMall());

            boutiqueRepository.save(newBoutique);

            return modelMapper.map(newBoutique, BoutiqueDTO.class);
        }
        return null;
    }

    @Override
    public HttpStatus deleteById(Long id) {
        Optional<Boutique> oldBoutiqueOpt = boutiqueRepository.findById(id);
        if(oldBoutiqueOpt.isPresent()){
            boutiqueRepository.deleteById(id);
            return HttpStatus.OK;
        }
        return HttpStatus.BAD_REQUEST;
    }
}
