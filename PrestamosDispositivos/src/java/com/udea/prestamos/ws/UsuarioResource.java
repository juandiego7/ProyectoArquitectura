package com.udea.prestamos.ws;

import com.udea.prestamos.model.Users;
import com.udea.prestamos.model.dao.impl.UsuarioDAOImpl;
import java.rmi.RemoteException;
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
    

    @POST//Metodo http con que responde este metodo
    @Consumes(MediaType.APPLICATION_JSON)//Formato de respuesta
    @Produces(MediaType.APPLICATION_JSON)//Formato de respuesta
    public void registro(Users user) throws RemoteException{
//                    @QueryParam("username")String username,
//                    @QueryParam("typeId")String typeId,
//                    @QueryParam("numberId")String numberId,
//                    @QueryParam("name")String name,
//                    @QueryParam("lastName")String lastName,
//                    @QueryParam("email")String email,
//                    @QueryParam("password")String password,
//                    @QueryParam("role")String role,
//                    @QueryParam("manager")String manager
            //registro
        usuarioDAOImpl.registraUsuario(user);
//            Users managerU = null;
//            Users usuario = null;
//            try {
//                    if (manager != null) {
//                            managerU = new Users();
//                            managerU.setUsername(username);	
//                    }
//                    usuario = new Users(username, usuario, typeId, numberId, name, lastName, email, password, role, null, null);
//                    usuarioDAOImpl.registraUsuario(usuario);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
    }
}
