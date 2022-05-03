package com.ProyectoFinal.FotoJob.servicios;
import com.ProyectoFinal.FotoJob.entidades.Fotografo;
import com.ProyectoFinal.FotoJob.repositorios.fotografoRepositorio;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
public class FotografoServicio {
    
    @Autowired
    private fotografoRepositorio fr;
    
    @Transactional
    public Fotografo save(String nombre, String apellido, String mail, String contrasenia, Integer telefono, Integer valoraciones, String especializacion, Boolean alta, Integer precio,ArrayList<String> galeria , ArrayList<String> miniatura) throws Exception{
        
        validator(nombre, apellido, mail, contrasenia, telefono, valoraciones, especializacion, alta, precio, galeria, miniatura);
        
        Fotografo fotografo = new Fotografo(nombre, apellido, mail, contrasenia, telefono, valoraciones, especializacion, alta, precio, galeria, miniatura);
        
        return fr.save(fotografo);
    }
    
    @Transactional
    public Fotografo edit(String id, String nombre, String apellido, String mail, String contrasenia, Integer telefono, Integer valoraciones, String especializacion, Boolean alta, Integer precio,ArrayList<String> galeria, ArrayList<String> miniatura) throws Exception {
        Optional<Fotografo> respuesta = fr.findById(id);
        
        if(respuesta.isPresent()){
            validator(nombre, apellido, mail, contrasenia, telefono, valoraciones, especializacion, alta, precio, galeria, miniatura);
            Fotografo f = respuesta.get();
            
            return fr.save(f);       
        }
        else{
            return null;
        }
        
         }
    
    public List<Fotografo>findAll()
    {
         return fr.findAll();
    }
       
       @Transactional
    public void delete(String id){
        fr.deleteById(id);
    }
    
    
    
    public void validator(String nombre, String apellido, String mail, String contrasenia, Integer telefono, Integer valoraciones, String especializacion, Boolean alta, Integer precio,ArrayList<String> galeria, ArrayList<String> miniatura) throws Exception
    {
        if(nombre == null || nombre.isEmpty()){
            throw new Exception("Nombre invalido");
        }
        
        if(apellido == null || apellido.isEmpty()){
            throw new Exception("Apellido invalido");
        }
        
        if(mail == null || mail.isEmpty()){
            throw new Exception("Mail invalido");
        }
        
        if(contrasenia == null || contrasenia.isEmpty()){
            throw new Exception("Contrase�a invalida");
        }
        
        if(telefono == null){
            throw new Exception("Telefono invalido");
        }
        
        if(valoraciones == null){
            throw new Exception("valoracion invalida");
        }
        
        if(especializacion == null || especializacion.isEmpty()){
            throw new Exception("esecializacion invalida");
        }
        
        if(alta == null){
            throw new Exception("alta invalida");
        }
        
        if(precio == null || nombre.isEmpty()){
            throw new Exception("precio invalido");
        }
        
        if(galeria == null || galeria.isEmpty()){
            throw new Exception("galeria invalida");
        }
 
        if(miniatura == null || miniatura.isEmpty()){
            throw new Exception("miniatura invalida");
        }
    }
    
}
