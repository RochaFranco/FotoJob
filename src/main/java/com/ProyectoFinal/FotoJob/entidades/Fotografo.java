 package com.ProyectoFinal.FotoJob.entidades;

import java.util.ArrayList;
import javax.persistence.Entity;
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
    private Integer valoraciones;  // atributo que da promedio de valoracion con estrellas
    private String especializacion; // categoria de fotografia en la que se especializa
    private Boolean alta;
    private Integer precio; //corresponde a: 1 :$accesible 2: $$ moderado 3: $$$ caro
    private ArrayList<String> galeria; // un array de Strings para guardar las fotos (cada string es una foto)
    
    public Fotografo(String nombre, String apellido, String mail, String contrasenia, Integer telefono, Integer valoraciones, String especializacion, Boolean alta, Integer precio, ArrayList<String> galeria) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.contrasenia = contrasenia;
        this.telefono = telefono;
        this.valoraciones = valoraciones;
        this.especializacion = especializacion;
        this.alta = alta;
        this.precio = precio;
        this.galeria = galeria;
        
    }

    public Fotografo() {
        this.alta = true;
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

    public Integer getValoraciones() {
        return valoraciones;
    }

    public void setValoraciones(Integer valoraciones) {
        this.valoraciones = valoraciones;
    }

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


    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public ArrayList<String> getGaleria() {
        return galeria;
    }

    public void setGaleria(ArrayList<String> galeria) {
        this.galeria = galeria;
    }



}
