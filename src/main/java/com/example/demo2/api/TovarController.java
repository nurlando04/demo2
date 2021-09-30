package com.example.demo2.api;

import ch.qos.logback.core.joran.spi.NoAutoStart;
import com.example.demo2.model.Tovar;
import com.example.demo2.service.TovarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/tovar")
@RestController
public class TovarController {

    private final TovarService tovarService;

    @Autowired
    public TovarController(TovarService tovarService) {
        this.tovarService = tovarService;
    }

    @PostMapping
    public void addTovar(@NonNull @RequestBody Tovar tovar){
        tovarService.addTovar(tovar);
    }

    @GetMapping
    public List<Tovar> getAllTovars(){
        return tovarService.getAllTovars();
    }

    @GetMapping(path="{id}")
    public Tovar getTovarById(@PathVariable("id") UUID id){
        return tovarService.getTovarById(id).orElse(null);
    }

    @DeleteMapping(path="{id}")
    public void deleteTovarByID(@PathVariable("id") UUID id){
        tovarService.deleteTovar(id);
    }

    @PutMapping(path="{id}")
    public void updateTovarByID(@PathVariable("id") UUID id, @NonNull @RequestBody Tovar tovarToUpdate){
        tovarService.updateTovar(id,tovarToUpdate);
    }


}
