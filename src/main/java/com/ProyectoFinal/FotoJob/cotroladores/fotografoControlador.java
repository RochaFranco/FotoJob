package com.ProyectoFinal.FotoJob.cotroladores;

import com.ProyectoFinal.FotoJob.entidades.Fotografo;
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
    
    @GetMapping("/subir-foto/{id}")
    public String subirFoto(ModelMap modelo, @PathVariable String id){
        modelo.put("fotografo", fotografoServicio.findById(id));
        return "subir-foto";
    }
    
    @PostMapping("/subir-foto/{id}")
    public String subirFoto(ModelMap modelo, @PathVariable String id, @RequestParam String imageUrl, @RequestParam String imageUrlMin){
    
       Fotografo f = fotografoServicio.findById(id);
     
            ArrayList<String> fotosGrandes = f.getGaleria();
            ArrayList<String> fotosCortadas = f.getMiniatura();
            fotosGrandes.add(imageUrl);
            fotosCortadas.add(imageUrlMin);
            f.setGaleria(fotosGrandes);
            f.setGaleria(fotosCortadas);
            modelo.put("fotografo", f);
            return "perfil_fotografo";
     
            
    
    
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
    public String editarPerfil(ModelMap modelo, @PathVariable String id, @RequestParam String nombre,@RequestParam String apellido, @RequestParam String mail,@RequestParam String contrasenia,@RequestParam Integer telefono, @RequestParam String especializacion, @RequestParam String precio) throws Exception{
        try {
            fotografoServicio.edit(id,nombre, apellido, mail, contrasenia, telefono,especializacion, precio);
            return "redirect:/perfil_fotografo";
        } catch (Exception e) {
            modelo.addAttribute("fotografo", fotografoServicio.findById(id));
            modelo.put("error", "hubo un error con los datos");
            return "editar-perfil";
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
    
    @GetMapping("/perfil_fotografo/{id}")
    public String mostrarPerfil(ModelMap modelo, @PathVariable String id){
      try {
            Fotografo f = fotografoServicio.findById(id);
            modelo.put("fotografo", f);
           
        }catch (Exception e){
            modelo.put("Noperfil", e);
            return "inicio";
        }
        return "perfil_fotografo";  
    }

    
}