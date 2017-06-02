package com.udea.prestamos.ws;

import com.udea.prestamos.dto.Dispositivo;
import com.udea.prestamos.email.sendEmail;
import com.udea.prestamos.model.Devices;
import com.udea.prestamos.model.DevicesId;
import com.udea.prestamos.model.dao.impl.DispositivoDAOImpl;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author jnda
 */
@Path("dispositivo")
public class DispositivoResource {

    DispositivoDAOImpl dispositivoDAOImpl = new DispositivoDAOImpl();

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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Dispositivo> getTodos() throws RemoteException {

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
                System.out.println("details "+devices.getDetails());
            }
            return dispositivos;
        } catch (Exception e) {
            throw new RemoteException("Problema consultando");
        }
    }

    @POST//Metodo http con que responde este metodo
    @Path("registra")
    @Consumes(MediaType.APPLICATION_JSON)//Formato de respuesta
    @Produces(MediaType.APPLICATION_JSON)//Formato de respuesta
    public Dispositivo registro(Dispositivo dispositivo) throws RemoteException {

        Devices device = new Devices(dispositivo.getDeviceId(),
                dispositivo.getName(),
                dispositivo.getType(),
                dispositivo.getDetails(),
                dispositivo.getStatus());
        try {
            dispositivoDAOImpl.registraDispositivo(device);
            return dispositivo;
        } catch (Exception e) {
            throw new RemoteException("Problema consultando");
        }

    }

    /**
     * Servicio para actualizar el estado de un dispositivo
     *
     * @throws RemoteException
     */
    @PUT//Metodo http con que responde este metodo
    @Path("estado")//Definicion de la ruta para invocar este metodo
    @Produces(MediaType.APPLICATION_JSON)//Formato de respuesta
    public void updateStatus(@QueryParam("code") String code,
            @QueryParam("copy") String copy,
            @QueryParam("status") String status) throws RemoteException {
        if (code == null || "".equals(code)) {
            return;
        }
        if (copy == null || "".equals(copy)) {
            return;
        }
        if (status == null || "".equals(status)) {
            return;
        }
        DevicesId devicesId = new DevicesId(code, copy);
        try {
            Devices device = dispositivoDAOImpl.getDispositivo(devicesId);
            if (device == null) {
                return;
            }
            device.setStatus(status);
            dispositivoDAOImpl.updateDispositivo(device);
        } catch (Exception e) {
            throw new RemoteException("Problema consultando");
        }
    }

    /**
     * Servicio para obtener el dispositivo que corresponde con un codigo y
     * copia ingresados como parametros
     *
     * @param code
     * @param copy
     * @return Device
     * @throws RemoteException
     */
    @GET//Metodo http con que responde este metodo
    @Path("id")//Definicion de la ruta para invocar este metodo
    @Produces(MediaType.APPLICATION_JSON)//Formato de respuesta
    public Devices getDevice(@QueryParam("code") String code,
            @QueryParam("copy") String copy) throws RemoteException {
        try {
            return dispositivoDAOImpl.getDispositivo(new DevicesId(code, copy));
        } catch (Exception e) {
            throw new RemoteException("Problema consultando");
        }
    }

    /**
     * Servicio para buscar un dispositivo por medio del codigo, nombre y tipo
     *
     * @param code
     * @param name
     * @param type
     * @return List<Devices>
     * @throws RemoteException
     */
    @GET//Metodo http con que responde este metodo
    @Path("buscar")//Definicion de la ruta para invocar este metodo
    @Produces(MediaType.APPLICATION_JSON)//Formato de respuesta
    public List<Devices> buscarDispositivo(@QueryParam("code") String code,
            @QueryParam("name") String name,
            @QueryParam("type") String type) throws RemoteException {
        try {
            return dispositivoDAOImpl.getDispositivo(code, name, type);
        } catch (Exception e) {
            throw new RemoteException("Problema consultando");
        }
    }

    /**
     * Actualiza los datos de un dispositivo
     *
     * @param code
     * @param copy
     * @param name
     * @param type
     * @param status
     * @param details
     * @throws RemoteException
     */
    @PUT
    @Path("actualiza")
    @Produces(MediaType.APPLICATION_JSON)
    public void actualizarDispositivo(
            @QueryParam("code") String code,
            @QueryParam("copy") String copy,
            @QueryParam("name") String name,
            @QueryParam("type") String type,
            @QueryParam("status") String status,
            @QueryParam("details") String details) throws RemoteException {

        if (code == null || "".equals(code)) {
            return;
        }
        if (copy == null || "".equals(copy)) {
            return;
        }
        DevicesId devicesId = new DevicesId(code, copy);
        try {
            Devices device = dispositivoDAOImpl.getDispositivo(devicesId);
            if (device == null) {
                return;
            }
            if (name != null) {
                device.setName(name);
            }
            if (type != null) {
                device.setType(type);
            }
            if (status != null) {
                device.setStatus(status);
            }
            if (details != null) {
                device.setDetails(details);
            }
            dispositivoDAOImpl.updateDispositivo(device);
        } catch (Exception e) {
            throw new RemoteException("Problema consultando");
        }
    }
}
