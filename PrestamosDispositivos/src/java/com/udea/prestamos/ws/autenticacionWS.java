/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.prestamos.ws;

import com.udea.prestamos.model.Users;
import com.udea.prestamos.model.dao.impl.UsuarioDAOImpl;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Jesus Gomez
 */
@WebService(serviceName = "autenticacionWS")
public class autenticacionWS {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "ingresarUsuario")
    public String ingresarUsuario(@WebParam(name = "username") String username, @WebParam(name = "typeId") String typeId, @WebParam(name = "numberId") String numberId, @WebParam(name = "name") String name, @WebParam(name = "lastName") String lastName, @WebParam(name = "email") String email, @WebParam(name = "password") String password, @WebParam(name = "role") String role) {
        Users users = new Users(username,typeId,numberId,name,password,role);
        UsuarioDAOImpl usuarioDAOImpl = new UsuarioDAOImpl();
        usuarioDAOImpl.registraUsuario(users);
        return "usuario ingresado";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "devuelveUsuario")
    public String devuelveUsuario(@WebParam(name = "username") String username, @WebParam(name = "password") String password) {
        UsuarioDAOImpl usuarioDAOImpl = new UsuarioDAOImpl();
        return usuarioDAOImpl.getUsuario(username).getName();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "devolverTodosUsuarios")
    public List<Users> devolverTodosUsuarios() {
        UsuarioDAOImpl usuarioDAOImpl = new UsuarioDAOImpl();
        List<Users> listaUsers = usuarioDAOImpl.getUsuarios();
        return listaUsers;
    }

   
}
