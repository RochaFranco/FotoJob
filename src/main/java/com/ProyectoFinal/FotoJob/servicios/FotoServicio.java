package com.ProyectoFinal.FotoJob.servicios;

import com.ProyectoFinal.FotoJob.entidades.Foto;
import com.ProyectoFinal.FotoJob.repositorios.FotoRepositorio;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FotoServicio {
    @Autowired
    FotoRepositorio fotoRepo;
    
    public ArrayList<Foto> getFotosByID(String id) {
        return fotoRepo.getFotosByID(id);
    }
    
    public void save(Foto foto) {
        fotoRepo.save(foto);
    }
}
