/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.prestamos.ws;

import com.udea.prestamos.email.sendEmail;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
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
}
