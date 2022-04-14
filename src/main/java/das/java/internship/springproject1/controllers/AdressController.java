package das.java.internship.springproject1.controllers;

import das.java.internship.springproject1.dto.AdressDTO;
import das.java.internship.springproject1.dto.MallDTO;
import das.java.internship.springproject1.services.AdressService;
import das.java.internship.springproject1.services.MallService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/service/adress")
public class AdressController {

    private final AdressService adressService;
    @PostMapping("/save")
    public HttpStatus save(@RequestBody AdressDTO adressDTO) {
        return adressService.save(adressDTO);
    }
    @GetMapping("/all")
    public List<AdressDTO> getAll(){
        return adressService.getAll();
    }
    @GetMapping("/getById/{id}")
    public AdressDTO getById(@PathVariable Long id){
        return adressService.getById(id);
    }
    @PutMapping("/update")
    public AdressDTO update(@RequestBody AdressDTO adressDTO){
        return adressService.update(adressDTO);
    }
    @DeleteMapping("/deleteById/{id}")
    public HttpStatus deleteById(@PathVariable Long id){
        return adressService.deleteById(id);
    }
}
