package com.ProyectoFinal.FotoJob.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServicio {

    @Autowired
    private JavaMailSender javaMailSender;
    
    public void enviarMail(String destinatario,Integer telefono,String nombre, String apellido, String mail, String mensaje){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo("RochaFrancoAgustin@gmail.com");
        simpleMailMessage.setSubject("Pedido de cotizacion");
        simpleMailMessage.setText("Telefono: "+telefono +" Nombre: "+ nombre +" Apellido: "+ apellido +" Mail: "+ mail +"\n\n\n\n"+" Mensaje: "+ mensaje);

        javaMailSender.send(simpleMailMessage);
    }
    
}
