package com.ProyectoFinal.FotoJob.cotroladores;

import com.ProyectoFinal.FotoJob.servicios.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cliente")
public class clienteControlador {
    
    @Autowired 
    private ClienteServicio clienteServicio;
    
     @GetMapping("/cotizar")
    public String cotizar(){
    return "formulario-cotizacion";
    }
    
      @PostMapping("/cotizar")
    public String guardarDatos(ModelMap modelo, @RequestParam String nombre,@RequestParam String apellido, @RequestParam String mail, @RequestParam Integer telefono) throws Exception{
      
        try {
         clienteServicio.save(nombre, apellido, mail, telefono);
         modelo.put("exito", "cotizacion exitosa");
        return "formulario-cotizacion";
        
        } catch (Exception e) {
           modelo.put("error", e.getMessage());
           return "formulario-cotizacion";
        }
     
    }
    
}
