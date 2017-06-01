/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.prestamos.ws;

import com.udea.prestamos.model.Users;
import com.udea.prestamos.model.dao.impl.UsuarioDAOImpl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Juan Diego
 */
@Path("usuario")
public class UsuarioResource {
    
    UsuarioDAOImpl usuarioDAOImpl = new UsuarioDAOImpl();

   
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String login(@QueryParam("username")String username,
                        @QueryParam("password")String password){
        if (username==null) {
            return "N";
        }
        
        Users user = usuarioDAOImpl.getUsuario(username);
        if(user!=null){
            if(user.getPassword().equals(password)){
                return "S";
            }
        }
        
        return "N";
    }

}
