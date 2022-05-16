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
        simpleMailMessage.setTo(destinatario);
        simpleMailMessage.setSubject("Pedido de cotizacion");
        simpleMailMessage.setText("Telefono: "+telefono +"\nNombre: "+ nombre +"\nApellido: "+ apellido +"\nMail: "+ mail +"\n\n\n\n"+"Mensaje: \n"+ mensaje);

        javaMailSender.send(simpleMailMessage);
    }
    
}
