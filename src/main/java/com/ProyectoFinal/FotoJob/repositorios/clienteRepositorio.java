package com.ProyectoFinal.FotoJob.repositorios;
import com.ProyectoFinal.FotoJob.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface clienteRepositorio extends JpaRepository<Cliente,String>{

    @Query("SELECT c FROM Cliente c WHERE c.mail= :mail")
    public Cliente findByMail(@Param("mail") String mail);
}
