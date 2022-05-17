package com.ProyectoFinal.FotoJob.servicios;
import com.ProyectoFinal.FotoJob.entidades.Cliente;
import com.ProyectoFinal.FotoJob.repositorios.clienteRepositorio;
import enums.Role;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class ClienteServicio {
    
    @Autowired
    private clienteRepositorio cr;
    
    @Transactional
    public Cliente save(String nombre, String apellido, String mail, Integer telefono) throws Exception{
        
       validator(nombre, apellido, mail, telefono);
        
        Cliente cliente = new Cliente(nombre, apellido, mail, telefono);
        cliente.setRole(Role.CLIENTE);
        return cr.save(cliente);   
    }
    
    @Transactional
    public Cliente edit(String id, String nombre, String apellido, String mail, Integer telefono) throws Exception{
        
        Optional<Cliente> respuesta = cr.findById(id);
        
        if(respuesta.isPresent()){
            
            validator(nombre, apellido, mail, telefono);
            Cliente c = respuesta.get();
            
            return cr.save(c);       
        }
        else{
            return null;
        }

    }
    
        public List<Cliente>findAll(){
        return cr.findAll();
    }
    
    @Transactional
    public void delete(String id){
        cr.deleteById(id);
    }
    
    public void validator(String nombre, String apellido, String mail, Integer telefono) throws Exception{
        
        if(nombre == null || nombre.isEmpty()){
            throw new Exception("Nombre invalido");
        }
        
        if(apellido == null || apellido.isEmpty()){
            throw new Exception("Apellido invalido");
        }
        
        if(mail == null || mail.isEmpty()){
            throw new Exception("Mail invalido");
        }
        
        if(telefono == null){
            throw new Exception("Telefono invalido");
        }
    }
    
}
