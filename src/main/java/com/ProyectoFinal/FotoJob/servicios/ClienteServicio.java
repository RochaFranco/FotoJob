
import com.ProyectoFinal.FotoJob.entidades.Cliente;
import com.ProyectoFinal.FotoJob.repositorios.clienteRepositorio;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ClienteServicio {
    
    private clienteRepositorio cp;
    
    @Transactional
    private Cliente save(String nombre, String apellido, String mail, Integer telefono) throws Exception{
        
        validator(nombre, apellido, mail, telefono);
        
        Cliente cliente = new Cliente(nombre, apellido, mail, telefono);
        
        return cp.save(cliente);   
    }
    
    @Transactional
    public Cliente edit(String id, String nombre, String apellido, String mail, Integer telefono) throws Exception{
        
        Optional<Cliente> respuesta = cp.findById(id);
        
        if(respuesta.isPresent()){
            
            validator(nombre, apellido, mail, telefono);
            Cliente c = respuesta.get();
            
            return cp.save(c);       
        }
        else{
            return null;
        }

    }
    
        public List<Cliente>findAll(){
        return cp.findAll();
    }
    
    @Transactional
    public void delete(String id){
        cp.deleteById(id);
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
