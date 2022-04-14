package das.java.internship.springproject1.controllers;

import das.java.internship.springproject1.dto.MallDTO;
import das.java.internship.springproject1.services.MallService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/service/mall")
public class MallController {
    private final MallService mallService;

    @PostMapping("/save")
    public MallDTO saveMall(@RequestBody MallDTO mallDTO) {
        return mallService.saveMall(mallDTO);
    }
    @GetMapping("/all")
    public List<MallDTO> getAll(){
        return mallService.getAll();
    }
    @GetMapping("/getById/{id}")
    public MallDTO getById(@PathVariable Long id){
        return mallService.getById(id);
    }
    @PutMapping("/update")
    public MallDTO update(@RequestBody MallDTO mallDTO){
        return mallService.update(mallDTO);
    }
    @DeleteMapping("/deleteById/{id}")
    public HttpStatus deleteById(@PathVariable Long id){
        return mallService.deleteById(id);
    }

}
