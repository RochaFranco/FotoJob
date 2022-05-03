


package com.ProyectoFinal.FotoJob.repositorios;

import com.ProyectoFinal.FotoJob.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface clienteRepositorio extends JpaRepository<Cliente,String>{

    
}
