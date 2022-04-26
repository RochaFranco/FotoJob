
import com.ProyectoFinal.FotoJob.entidades.Fotografo;
import com.ProyectoFinal.FotoJob.repositorios.fotografoRepositorio;
import java.util.ArrayList;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FotografoServicio {
    
    @Autowired
    private fotografoRepositorio fp;
    
    @Transactional
    public Fotografo save(String nombre, String apellido, String mail, String contrasenia, Integer telefono, Integer valoraciones, String especializacion, Boolean alta, Integer precio,ArrayList<String> galeria){
        
        Fotografo fotografo = new Fotografo(nombre, apellido, mail, contrasenia, telefono, valoraciones, especializacion, alta, precio, galeria);
        
        return fp.save(fotografo);
    }
    
}
