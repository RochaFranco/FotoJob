package com.ProyectoFinal.FotoJob.servicios;
import com.ProyectoFinal.FotoJob.entidades.Fotografo;
import com.ProyectoFinal.FotoJob.repositorios.fotografoRepositorio;
import enums.Role;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Service
public class FotografoServicio implements UserDetailsService{
    
    @Autowired
    private fotografoRepositorio fr;
    
    @Transactional
    public Fotografo save(String nombre, String apellido, String mail, String contrasenia, Integer telefono, String especializacion,String precio,ArrayList<String> galeria , ArrayList<String> miniatura) throws Exception{
        
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        validator(nombre, apellido, mail, contrasenia, telefono, especializacion, precio);
        
        Fotografo fotografo = new Fotografo(nombre, apellido, mail, encoder.encode(contrasenia), telefono, especializacion, precio, galeria, miniatura);
        fotografo.setRole(Role.FOTOGRAFO);
        return fr.save(fotografo);
    }
    
    @Transactional
    public Fotografo edit(String id, String nombre, String apellido, String mail, String contrasenia, Integer telefono, Integer valoraciones, String especializacion,String precio,ArrayList<String> galeria, ArrayList<String> miniatura) throws Exception {
        Optional<Fotografo> respuesta = fr.findById(id);
        
        if(respuesta.isPresent()){
            validator(nombre, apellido, mail, contrasenia, telefono, especializacion,precio);
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
    
    
    
    public void validator(String nombre, String apellido, String mail, String contrasenia, Integer telefono, String especializacion,String precio) throws Exception
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
        
        if(especializacion == null || especializacion.isEmpty()){
            throw new Exception("esecializacion invalida");
        }
        
        if(precio == null || nombre.isEmpty()){
            throw new Exception("precio invalido");
        }

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Fotografo usuario = fr.findByEmail(email);
        if (usuario != null) {
            List<GrantedAuthority> permisos = new ArrayList<>();

            GrantedAuthority p1 = new SimpleGrantedAuthority("ROLE_" + usuario.getRole());//ROLE_ADMIN O ROLE_FOTOGRAFO o ROLE_CLIENTE
            permisos.add(p1);

            //Esto me permite guardar el OBJETO USUARIO LOG, para luego ser utilizado
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("usuariosession", usuario);

            User user = new User(usuario.getMail(), usuario.getContrasenia(), permisos);
            return user;

        } else {
            return null;
        }
    }
    
}
