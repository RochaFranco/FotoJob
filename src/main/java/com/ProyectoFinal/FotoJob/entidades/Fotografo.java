package com.ProyectoFinal.FotoJob.entidades;

import enums.Role;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Id;

@Entity
public class Fotografo {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @javax.persistence.Id
    private String id;
    private String nombre;
    private String apellido;
    private String mail;
    private String contrasenia;
    private Integer telefono;
    private String muestra1;
    private String muestra2;
    private String muestra3;
    //private Integer valoraciones;// atributo que da promedio de valoracion con estrellas
    //private String comentario;
    private String especializacion; // categoria de fotografia en la que se especializa
    private Boolean alta;
    private String precio; //corresponde a: 1 :$accesible 2: $$ moderado 3: $$$ caro
    private ArrayList<String> galeria; // un array de Strings para guardar las fotos (cada string es una foto)
    private ArrayList<String> miniatura;
    @Enumerated(EnumType.STRING)
    private Role role;
    

    
    
    public Fotografo(String nombre, String apellido, String mail, String contrasenia, Integer telefono, String especializacion, String precio, ArrayList<String> galeria, ArrayList<String> miniatura) {
    
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.contrasenia = contrasenia;
        this.telefono = telefono;
        //this.valoraciones = valoraciones;
        //this.comentario = comentario;
        this.especializacion = especializacion;
        this.alta = true;
        this.precio = precio;
        this.galeria = galeria;
        this.miniatura = miniatura;
    }

    public Fotografo() {
       
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

//    public Integer getValoraciones() {
//        return valoraciones;
//    }
//
//    public void setValoraciones(Integer valoraciones) {
//        this.valoraciones = valoraciones;
//    }

//    public String getComentario() {
//        return comentario;
//    }
//
//    public void setComentario(String comentario) {
//        this.comentario = comentario;
//    }

    
    public String getEspecializacion() {
        return especializacion;
    }

    public void setEspecializacion(String especializacion) {
        this.especializacion = especializacion;
    }

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public ArrayList<String> getGaleria() {
        return galeria;
    }

    public void setGaleria(ArrayList<String> galeria) {
        this.galeria = galeria;
    }

    public ArrayList<String> getMiniatura() {
        return miniatura;
    }

    public void setMiniatura(ArrayList<String> miniatura) {
        this.miniatura = miniatura;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getMuestra1() {
        return muestra1;
    }

    public void setMuestra1(String muestra1) {
        this.muestra1 = muestra1;
    }

    public String getMuestra2() {
        return muestra2;
    }

    public void setMuestra2(String muestra2) {
        this.muestra2 = muestra2;
    }

    public String getMuestra3() {
        return muestra3;
    }

    public void setMuestra3(String muestra3) {
        this.muestra3 = muestra3;
    }



}
