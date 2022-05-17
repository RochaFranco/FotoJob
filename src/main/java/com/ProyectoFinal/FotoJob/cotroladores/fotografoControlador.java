package com.ProyectoFinal.FotoJob.cotroladores;

import com.ProyectoFinal.FotoJob.entidades.Foto;
import com.ProyectoFinal.FotoJob.entidades.Fotografo;
import com.ProyectoFinal.FotoJob.servicios.FotoServicio;
import com.ProyectoFinal.FotoJob.servicios.FotografoServicio;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/fotografo")
public class fotografoControlador {
    
    @Autowired
    private FotografoServicio fotografoServicio;
    @Autowired
    private FotoServicio fotoServicio;
    
    @GetMapping("/subir-foto/{id}")
    public String subirFoto(ModelMap modelo, @PathVariable String id){
        modelo.put("fotografo", fotografoServicio.findById(id));
        return "subir-foto";
    }
    
    @PostMapping("/subir-foto/{id}")
    public String subirFoto(ModelMap modelo, @PathVariable String id, @RequestParam String imageUrl, @RequestParam String imageUrlMin){
        try {
            Fotografo f = fotografoServicio.findById(id);
            fotoServicio.save(new Foto(imageUrl, imageUrlMin, f));
            ArrayList<Foto> fotos = fotoServicio.getFotosByID(id);
            fotografoServicio.edit(id,f.getNombre(), f.getApellido(), f.getTelefono(), f.getEspecializacion(), f.getPrecio());
            modelo.put("fotografo", f);
            modelo.put("fotos", fotos);
        } catch (Exception e) {
            modelo.put("error",e.getMessage());
            return "redirect:/fotografo/perfil_fotografo/{id}";
        }
            return "redirect:/fotografo/perfil_fotografo/{id}";
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
          fotografoServicio.save(nombre, apellido, mail, contrasenia, telefono,especializacion, precio, galeria, miniatura);
        modelo.put("exito", "registro exitoso");
        return "registro-fotografo";
        
        } catch (Exception e) {
           modelo.put("error", e.getMessage());
           return "registro-fotografo";
        }
    
    }

     @GetMapping("/editar/{id}")
    public String editarPerfil(ModelMap modelo, @PathVariable String id) throws Exception{
    modelo.addAttribute("modelo",id);
    modelo.addAttribute("fotografo", fotografoServicio.findById(id));
    return "editar-perfil";
    }
    
    @PostMapping("/editar/{id}")
    public String editarPerfil(ModelMap modelo, @PathVariable String id, @RequestParam String nombre,@RequestParam String apellido, @RequestParam Integer telefono, @RequestParam String especializacion, @RequestParam String precio) throws Exception{
        try {
            fotografoServicio.edit(id,nombre, apellido,telefono,especializacion, precio);
            return "redirect:/inicio";
        } catch (Exception e) {
            modelo.addAttribute("fotografo", fotografoServicio.findById(id));
            modelo.put("error", "hubo un error con los datos");
            return "editar-perfil";
        }
    
    }
    


    @GetMapping("/perfil_fotografo/{id}")
    public String mostrarPerfil(ModelMap modelo, @PathVariable String id){
      try {
            Fotografo f = fotografoServicio.findById(id);
            modelo.put("fotografo", f);
            modelo.put("fotos", fotoServicio.getFotosByID(id));
           
        }catch (Exception e){
            modelo.put("Noperfil", e);
            return "inicio";
        }
        return "perfil_fotografo";  
    }
}
