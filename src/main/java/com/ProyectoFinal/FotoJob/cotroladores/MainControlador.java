package com.ProyectoFinal.FotoJob.cotroladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class MainControlador {
    
    @GetMapping("")
    public String index(@RequestParam(required = false) String login, ModelMap model){
        if(login != null) {
            model.put("login", "Logeado con exito");
        }
        return "bienvenida";
    }
    
    
    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, ModelMap model){
        if(error != null) {
            model.put("error","Usuario o contraseña incorrectos");
        }
        if(logout != null) {
            model.put("logout", "Desconectado correctamente");
        }
        return "bienvenida";
    
    }

}
