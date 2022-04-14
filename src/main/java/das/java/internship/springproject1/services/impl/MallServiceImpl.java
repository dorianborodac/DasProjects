package das.java.internship.springproject1.services.impl;

import das.java.internship.springproject1.dto.MallDTO;
import das.java.internship.springproject1.entities.Mall;
import das.java.internship.springproject1.repositories.MallRepository;
import das.java.internship.springproject1.services.MallService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MallServiceImpl implements MallService {
    private final MallRepository mallRepository;
    private final ModelMapper modelMapper;

    @Override
    public MallDTO saveMall(MallDTO mallDTO) {
        Mall mall = modelMapper.map(mallDTO, Mall.class);
        mall.setRegistrationNumber("###");
        Mall savedMall = mallRepository.save(mall);
        return modelMapper.map(savedMall, MallDTO.class);
    }

    @Override
    public List<MallDTO> getAll() {
        return mallRepository.findAll().stream()
                .map(mall -> modelMapper.map(mall, MallDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public MallDTO getById(Long id) {
        return mallRepository.findById(id)
                .map(val -> modelMapper.map(val, MallDTO.class))
                .orElse(null);
    }


    @Override
    public MallDTO update(MallDTO mallDTO) {
        Mall newMall = modelMapper.map(mallDTO, Mall.class);
        Optional<Mall> optionalMall = mallRepository.findById(newMall.getId());
        if(optionalMall.isPresent()){
            Mall oldMall = optionalMall.get();

            newMall.setId(oldMall.getId());
            newMall.setRegistrationNumber(oldMall.getRegistrationNumber());

            mallRepository.save(newMall);

            return modelMapper.map(newMall, MallDTO.class);
        }
        return null;
    }
    @Override
    public HttpStatus deleteById(Long id){
        Optional<Mall> mall = mallRepository.findById(id);
        if (mall.isPresent()){
            mallRepository.deleteById(id);
            return HttpStatus.OK;
        }
        return HttpStatus.BAD_REQUEST;
    }
}
