package com.example.demo2.api;

import com.example.demo2.model.Tovar;
import com.example.demo2.service.TovarService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class MegaTovarController {

    private final TovarService tovarService;

    public MegaTovarController(TovarService tovarService) {
        this.tovarService = tovarService;
    }


    @GetMapping(path="/tovar/{id}")
    public String getTovarbyId(@PathVariable("id") UUID id, Model model){
        Optional<Tovar> tovar = tovarService.getTovarById(id);
        model.addAttribute(tovar.get());
        return "gettovar"; //String.format("bonjour %s",tovarService.getAllTovars() );
    }

    @GetMapping(path = "/tovar")
    public String getAllTovars(Model model){
        Iterable<Tovar> tovars = tovarService.getAllTovars();
        model.addAttribute("alltovars",tovars);
        return "alltovars";
    }

/*    @RequestMapping(path = "/tovar",method = RequestMethod.POST)
    public ModelAndView processSubmit(@ModelAttribute("alltovars") Tovar tovar, BindingResult result){
        ModelAndView mv = new ModelAndView();
        mv.addObject("alltovars", tovar);
        return mv;
    }*/
}
