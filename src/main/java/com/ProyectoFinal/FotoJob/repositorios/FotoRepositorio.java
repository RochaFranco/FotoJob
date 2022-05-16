package com.ProyectoFinal.FotoJob.repositorios;

import com.ProyectoFinal.FotoJob.entidades.Foto;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FotoRepositorio extends JpaRepository<Foto,String>{
    
    @Query("SELECT f FROM Foto f WHERE f.fotografo.id =:id")
    public ArrayList<Foto> getFotosByID(@Param("id") String id);
    
}
