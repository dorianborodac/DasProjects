package das.java.internship.springproject1.controllers;

import das.java.internship.springproject1.dto.BoutiqueDTO;
import das.java.internship.springproject1.services.BoutiqueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service/boutique")
@RequiredArgsConstructor
public class BoutiqueController {
    private final BoutiqueService boutiqueService;

    @PostMapping("/save")
    public BoutiqueDTO save(@RequestBody BoutiqueDTO boutiqueDTO){
        return boutiqueService.save(boutiqueDTO);
    }

    @GetMapping("/all")
    public List<BoutiqueDTO> getAll(){
        return boutiqueService.getAll();
    }

    @GetMapping("/getById/{id}")
    public BoutiqueDTO getById(@PathVariable Long id){
        return boutiqueService.getById(id);
    }

    @PutMapping("/update")
    public BoutiqueDTO update(@RequestBody BoutiqueDTO boutiqueDTO){
        return boutiqueService.update(boutiqueDTO);
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteById(@PathVariable Long id) {
        return boutiqueService.deleteById(id);
    }
}
