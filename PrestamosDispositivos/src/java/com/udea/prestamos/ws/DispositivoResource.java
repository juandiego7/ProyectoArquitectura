/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.prestamos.ws;

import com.udea.prestamos.dto.Dispositivo;
import com.udea.prestamos.email.sendEmail;
import com.udea.prestamos.model.Devices;
import com.udea.prestamos.model.dao.impl.DispositivoDAOImpl;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author jnda
 */
@Path("dispositivo")
public class DispositivoResource { 
    
    @Context
    private UriInfo context;
    
    DispositivoDAOImpl dispositivoDAOImpl = new DispositivoDAOImpl();

    /**
     * Creates a new instance of DispositivoResource
     */
    public DispositivoResource() {
    }

    /**
     * Retrieves representation of an instance of
     * com.udea.prestamos.ws.DispositivoResource
     *
     * @return an instance of java.lang.String
     */
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String postMensaje(@FormParam("nombre") String name,
            @FormParam("email") String email,
            @FormParam("mensaje") String mensaje) {

        try {
            System.out.println("Nombre" + name);
            System.out.println("Email" + email);
            System.out.println("Mensaje" + mensaje);

            String mens = "Bienvenido";
            sendEmail sm = new sendEmail();
            sm.setMessage(mens + "\n"
                    + "Correo de: " + email + "\n"
                    + "Nombre del emisor: " + name + "\n\n"
                    + "Mensaje: " + mensaje);
            sm.setAsunto("Correo de contact with us -- -- Prestamo de dispositivos");
            sm.start();
        } catch (Exception e) {
            return "Contactate con el administrador...";
        }
        return "El mensaje ha sido enviado";
    }

    /**
     * PUT method for updating or creating an instance of DispositivoResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Dispositivo> getTodos() throws RemoteException{
        
        List<Devices> dev = null;
        List<Dispositivo> dispositivos = null;
        try {
            dev = dispositivoDAOImpl.getDispositivos();
            dispositivos = new ArrayList<>();
            for (Devices devices : dev) {
                dispositivos.add(new Dispositivo(devices.getId(),
                                                 devices.getName(),
                                                 devices.getType(),
                                                 devices.getStatus(),
                                                 devices.getDetails()));
            }
            return dispositivos;
        } catch (Exception e) {
            throw new RemoteException("Problema consultando");
        }
    }
    
}
