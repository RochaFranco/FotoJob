package com.ProyectoFinal.FotoJob.cotroladores;

import com.ProyectoFinal.FotoJob.entidades.Fotografo;
import com.ProyectoFinal.FotoJob.servicios.FotografoServicio;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/fotografo")
public class fotografoControlador {
    
    @Autowired
    private FotografoServicio fotografoServicio;
    
    @GetMapping("/subir-foto")
    public String subirFoto(){
    
        return "subir-foto";
    
    }
    
    
    @GetMapping("/registrarse")
    public String registrar(){
    return "registro-fotografo";
    }
    
  
    @PostMapping("/registrarse")
    public String guardarRegistro(ModelMap modelo, @RequestParam String nombre,@RequestParam String apellido, @RequestParam Integer telefono, @RequestParam String mail, @RequestParam String contrasenia, @RequestParam String especializacion , @RequestParam String precio) throws Exception{
        ArrayList<String>galeria = new ArrayList();
        ArrayList<String>miniatura = new ArrayList();
        try {
          fotografoServicio.save(nombre, apellido, mail, contrasenia, telefono, especializacion, precio, galeria, miniatura);
        modelo.put("exito", "registro exitoso");
        return "registro-fotografo";
        
        } catch (Exception e) {
           modelo.put("error", e.getMessage());
           return "registro-fotografo";
        }
     
    }
    
    @GetMapping("/inicio")
    public String mostrarinicio(ModelMap modelo){
        try {
            ArrayList<Fotografo> fotografos = (ArrayList<Fotografo>) fotografoServicio.findAll();
            modelo.put("fotografos", fotografos);
            return "inicio";
        } catch (Exception e) {
            modelo.put("error", "Hubo un error al cargar los fotografos");
            return "inicio";
        }
    }
    
}
