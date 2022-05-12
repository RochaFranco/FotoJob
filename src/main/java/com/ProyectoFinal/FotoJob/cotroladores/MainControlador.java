package com.ProyectoFinal.FotoJob.cotroladores;

import com.ProyectoFinal.FotoJob.servicios.ClienteServicio;
//import com.ProyectoFinal.FotoJob.servicios.FotografoServicio;
import com.ProyectoFinal.FotoJob.servicios.MailServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    

    
    @GetMapping("/inicio")
    public String inicio(){
    return "inicio";
    }
    
    
    
    @Autowired
    private MailServicio mailServicio;
   // @Autowired
   // private FotografoServicio fotografoServicio;
    @Autowired 
    private ClienteServicio clienteServicio;
    
     @GetMapping("/cotizar")
    public String cotizar(){
        //fotografoServicio.findById(id);
    return "formulario-cotizacion";
    }
    
      @PostMapping("/cotizar")
    public String guardarDatosYEnviarMail(ModelMap modelo, @RequestParam String nombre,@RequestParam String apellido, @RequestParam String mail, @RequestParam Integer telefono,@RequestParam String mensaje) throws Exception{
       // destinatario = "rochafrancoagustin@hotmail.com";
        //destinatario = this.fotografoServicio.traerMailPorId(id);
       
        try {
         clienteServicio.save(nombre, apellido, mail, telefono);
         mailServicio.enviarMail( "RochaFrancoAgustin@gmail.com" ,telefono ,nombre, apellido, mail, mensaje);
         modelo.put("exito", "cotizacion exitosa");
        return "formulario-cotizacion";
        
        } catch (Exception e) {
           modelo.put("error", e.getMessage());
           return "formulario-cotizacion";
        }
     
    }
    
    
    
}
