package com.ProyectoFinal.FotoJob.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Foto {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String link;
    private String linkMin;
    @OneToOne
    private Fotografo fotografo;

    public Foto() {
    }

    public Foto(String link, String linkMin, Fotografo fotografo) {
        this.link = link;
        this.linkMin = linkMin;
        this.fotografo = fotografo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLinkMin() {
        return linkMin;
    }

    public void setLinkMink(String linkMin) {
        this.linkMin = linkMin;
    }

    public Fotografo getFotografo() {
        return fotografo;
    }

    public void setFotografo(Fotografo fotografo) {
        this.fotografo = fotografo;
    }
    
    
    
    
}
