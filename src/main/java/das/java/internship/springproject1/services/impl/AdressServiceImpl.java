package das.java.internship.springproject1.services.impl;

import das.java.internship.springproject1.dto.AdressDTO;
import das.java.internship.springproject1.entities.Adress;
import das.java.internship.springproject1.entities.Boutique;
import das.java.internship.springproject1.repositories.AdressRepository;
import das.java.internship.springproject1.services.AdressService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdressServiceImpl implements AdressService {
    private final ModelMapper modelMapper;
    private final BoutiqueServiceImpl boutiqueService;
    private final AdressRepository adressRepository;

    @Override
    public HttpStatus save(AdressDTO adressDTO) {
        Adress adress = modelMapper.map(adressDTO, Adress.class);
        adress.setBoutique(modelMapper.map(boutiqueService.getById(adressDTO.getBoutiqueId()), Boutique.class));
        Adress savedEnchant = adressRepository.save(adress);
        return savedEnchant==null?HttpStatus.BAD_REQUEST:HttpStatus.OK;
    }

    @Override
    public List<AdressDTO> getAll() {
        return adressRepository.findAll().stream()
                .map(adress -> modelMapper.map(adress, AdressDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public AdressDTO getById(Long id) {
        return adressRepository.findById(id)
                .map(adress -> modelMapper.map(adress, AdressDTO.class))
                .orElse(null);
    }

    @Override
    public AdressDTO update(AdressDTO adressDTO) {
        Adress newAdress = modelMapper.map(adressDTO, Adress.class);
        Optional<Adress> optionalAdress = adressRepository.findById(newAdress.getId());
        if(optionalAdress.isPresent()){
            Adress oldEnchant = optionalAdress.get();

            newAdress.setId(oldEnchant.getId());
            newAdress.setBoutique(oldEnchant.getBoutique());

            adressRepository.save(newAdress);

            return modelMapper.map(newAdress, AdressDTO.class);
        }
        return null;
    }

    @Override
    public HttpStatus deleteById(Long id) {
        Optional<Adress> oldAdressOpt = adressRepository.findById(id);
        if(oldAdressOpt.isPresent()){
           adressRepository.deleteById(id);
            return HttpStatus.OK;
        }
        return HttpStatus.BAD_REQUEST;
    }
}
