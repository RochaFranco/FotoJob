package com.ProyectoFinal.FotoJob.servicios;
import com.ProyectoFinal.FotoJob.entidades.Cliente;
import com.ProyectoFinal.FotoJob.entidades.Foto;
import com.ProyectoFinal.FotoJob.entidades.Fotografo;
import com.ProyectoFinal.FotoJob.repositorios.clienteRepositorio;
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
    private FotoServicio fs;
    @Autowired
    private fotografoRepositorio fr;
    @Autowired
    private clienteRepositorio cr;
    @Transactional
    public Fotografo save(String nombre, String apellido, String mail, String contrasenia, Integer telefono, String especializacion,String precio,ArrayList<String> galeria , ArrayList<String> miniatura) throws Exception{

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        validator(nombre, apellido, mail, contrasenia, telefono, especializacion, precio);
        

        Fotografo fotografo = new Fotografo(nombre, apellido, mail, encoder.encode(contrasenia), telefono, especializacion, precio, galeria, miniatura);
        fotografo.setRole(Role.FOTOGRAFO);

        return fr.save(fotografo);
        
    }
    
    @Transactional
    public Fotografo edit(String id, String nombre, String apellido,Integer telefono,String especializacion,String precio) throws Exception {
    
        Optional<Fotografo> respuesta = fr.findById(id);
        
        if(respuesta.isPresent()){
            validadorSin(nombre, apellido,telefono, especializacion,precio);
            Fotografo f = respuesta.get();
            f.setNombre(nombre);
            f.setApellido(apellido);
            f.setTelefono(telefono);
            f.setEspecializacion(especializacion);
            f.setPrecio(precio);
            setearMuestras(id);
            return fr.save(f);       
        }
        else{
            return null;
        }
        
    }
    @Transactional
    public void setearMuestras(String id){
      Fotografo f = fr.getById(id);
      ArrayList<Foto>fotos = fs.getFotosByID(id);
      f.setMuestra1(fotos.get(0).getLinkMin());
      f.setMuestra2(fotos.get(1).getLinkMin());
      f.setMuestra3(fotos.get(2).getLinkMin());
    }
    
    
    public List<Fotografo>findAll()
    {
         return fr.findAll();
    }
       
    @Transactional
    public void delete(String id){
        fr.deleteById(id);
    }
    
    @Transactional
    public String traerMailPorId(String id){
     
     return fr.traerMailPorId(id);
    }
    
    @Transactional
    public Fotografo findById(String id){
     return  fr.getById(id);
    }
    
    public void validadorSin(String nombre, String apellido,Integer telefono, String especializacion,String precio) throws Exception{
    
        if(nombre == null || nombre.isEmpty()){
            throw new Exception("Nombre invalido");
        }
        
        if(apellido == null || apellido.isEmpty()){
            throw new Exception("Apellido invalido");
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
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        
        Fotografo usuariof = fr.findByEmail(mail);
        if (usuariof == null) {
             Cliente usuarioc = cr.findByMail(mail);
                if(usuarioc != null){
                     List<GrantedAuthority> permisos = new ArrayList<>();

                     GrantedAuthority p1 = new SimpleGrantedAuthority("ROLE_" + usuarioc.getRole());//ROLE_ADMIN O ROLE_FOTOGRAFO o ROLE_CLIENTE
                     permisos.add(p1);

                     //Esto me permite guardar el OBJETO USUARIO LOG, para luego ser utilizado
                     ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
                     HttpSession session = attr.getRequest().getSession(true);
                     session.setAttribute("usuariosession", usuarioc);

                     User user = new User(usuarioc.getMail(), usuarioc.getContrasenia(), permisos);
                        return user;
                }
        } else if (usuariof != null){
            List<GrantedAuthority> permisos = new ArrayList<>();

            GrantedAuthority p1 = new SimpleGrantedAuthority("ROLE_" + usuariof.getRole());//ROLE_ADMIN O ROLE_FOTOGRAFO o ROLE_CLIENTE
            permisos.add(p1);

            //Esto me permite guardar el OBJETO USUARIO LOG, para luego ser utilizado
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("usuariosession", usuariof);

            User user = new User(usuariof.getMail(), usuariof.getContrasenia(), permisos);
            return user;
            
        }
                
        
        return null;
    }
    
    public Fotografo getbyId(String id) {
        Fotografo f; 
        try {
            f = fr.getById(id);
        } catch (Exception e) {
            throw e;
        }
        return f;
    }

    

    
    
}
