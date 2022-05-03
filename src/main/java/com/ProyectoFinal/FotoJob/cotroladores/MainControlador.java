package com.ProyectoFinal.FotoJob.cotroladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainControlador {
    
     @GetMapping("")
    public String inicio(){
    return "bienvenida";
    
    }
    
}
