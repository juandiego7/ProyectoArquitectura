package com.udea.prestamos.ws;

import com.udea.prestamos.dto.Dispositivo;
import com.udea.prestamos.dto.Usuario;
import com.udea.prestamos.model.Devices;
import com.udea.prestamos.model.Users;
import com.udea.prestamos.model.dao.impl.UsuarioDAOImpl;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String login(@QueryParam("username") String username,
            @QueryParam("password") String password) {
        if (username == null) {
            return "N";
        }
        Users user = usuarioDAOImpl.getUsuario(username);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                return "S";
            }
        }

        return "N";
    }

    @POST//Metodo http con que responde este metodo
    @Produces(MediaType.APPLICATION_JSON)//Formato de respuesta
    public void registro(
            @QueryParam("username") String username,
            @QueryParam("typeId") String typeId,
            @QueryParam("numberId") String numberId,
            @QueryParam("name") String name,
            @QueryParam("lastName") String lastName,
            @QueryParam("email") String email,
            @QueryParam("password") String password,
            @QueryParam("role") String role,
            @QueryParam("manager") String manager) throws RemoteException {
        Users managerU = null;
        Users usuario = null;
        try {
            if (manager != null) {
                managerU = new Users();
                managerU.setUsername(username);
            }
            usuario = new Users(username, managerU, typeId, numberId, name, lastName, email, password, role);
            usuarioDAOImpl.registraUsuario(usuario);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Users manager = null;
//        if (usuario.getManager()!=null) {
//            manager = new Users();
//            manager.setUsername(usuario.getManager().getUsername());
//        }
//
//        Users user = new Users(usuario.getUsername(),
//                               manager,
//                               usuario.getTypeId(),
//                               usuario.getNumberId(),
//                               usuario.getName(),
//                               usuario.getLastName(),
//                               usuario.getEmail(),
//                               usuario.getPassword(), 
//                               usuario.getRole());
//      
//        System.out.println("sss "+user.getName());
//        usuarioDAOImpl.registraUsuario(user);
//        return usuario;

    }

    @GET
    @Path("todos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> getTodos() throws RemoteException {

        List<Users> users = null;
        List<Usuario> usuarios = null;
        try {
            users = usuarioDAOImpl.getUsuarios();
            usuarios = new ArrayList<>();
            for (Users usuario : users) {
                Usuario manager = null;
                if (usuario.getUsers() != null) {
                    manager = new Usuario();
                    manager.setUsername(usuario.getUsers().getUsername());
                }
                usuarios.add(new Usuario(usuario.getUsername(),
                        usuario.getTypeId(),
                        usuario.getNumberId(),
                        usuario.getName(),
                        usuario.getLastName(),
                        usuario.getEmail(),
                        usuario.getRole(),
                        usuario.getPassword(),
                        manager));
            }
            return usuarios;
        } catch (Exception e) {
            throw new RemoteException("Problema consultando");
        }
    }
}
