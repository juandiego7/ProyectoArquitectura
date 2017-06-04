/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.prestamos.ws;

import com.udea.prestamos.dto.Prestamo;
import com.udea.prestamos.model.Devices;
import com.udea.prestamos.model.DevicesId;
import com.udea.prestamos.model.Loans;
import com.udea.prestamos.model.LoansId;
import com.udea.prestamos.model.Users;
import com.udea.prestamos.model.dao.impl.DispositivoDAOImpl;
import com.udea.prestamos.model.dao.impl.PrestamoDAOImpl;
import com.udea.prestamos.model.dao.impl.UsuarioDAOImpl;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Juan Diego
 */
@Path("prestamo")
public class PrestamoResource {

    PrestamoDAOImpl prestamoDAOImpl = new PrestamoDAOImpl();
    UsuarioDAOImpl usuarioDAOImpl = new UsuarioDAOImpl();
    DispositivoDAOImpl dispositivoDAOImpl = new DispositivoDAOImpl();

    @GET//Metodo http con que responde este metodo
    @Path("todo")//Definicion de la ruta para invocar este metodo
    @Produces(MediaType.APPLICATION_JSON)//Formato de respuesta
    public List<Prestamo> getTodos() throws RemoteException {
        List<Prestamo> prestamos = new ArrayList<>();
        try {
            for (Loans loan : prestamoDAOImpl.getPrestamos()) {
                Prestamo prestamo = new Prestamo(
                        loan.getId(),
                        loan.getEndDate(),
                        loan.getReturnDate(),
                        loan.getStatus()
                );
                prestamos.add(prestamo);
            }
            return prestamos;
        } catch (Exception e) {
            throw new RemoteException("Problema consultando");
        }
    }

    @GET//Metodo http con que responde este metodo
    @Path("reserva")//Definicion de la ruta para invocar este metodo
    @Produces(MediaType.APPLICATION_JSON)//Formato de respuesta
    public List<Prestamo> getPrestamosReservados(
            @QueryParam("username") String username,
            @QueryParam("status") String status) throws RemoteException {

        List<Prestamo> prestamos = new ArrayList<>();
        try {
            Users user = usuarioDAOImpl.getUsuario(username);
            if (user != null) {
                for (Loans loan : prestamoDAOImpl.getLoans(user, status)) {
                    Prestamo prestamo = new Prestamo(
                            loan.getId(),
                            loan.getEndDate(),
                            loan.getReturnDate(),
                            loan.getStatus()
                    );
                    prestamos.add(prestamo);
                }
            }
            return prestamos;
        } catch (Exception e) {
            throw new RemoteException("Problema consultando");
        }
    }

    /**
     * Servicio para registrar(crear) un prestamo
     *
     * @see RF04 with status=RESERVADO
     * @see RF14 with status=PRESTADO
     * @param username
     * @param startDate
     * @param endDate
     * @param status
     * @param code
     * @param copy
     * @return Response - Confirmación de creación
     * @throws RemoteException
     */
    @POST
    @Path("registra")
    @Produces(MediaType.APPLICATION_JSON)
    public void resgistrarPrestamo(
            @QueryParam("username") String username,
            @QueryParam("startDate") String startDate,
            @QueryParam("endDate") String endDate,
            @QueryParam("status") String status,
            @QueryParam("code") String code,
            @QueryParam("copy") String copy) throws RemoteException, ParseException {

        Date startD = null;
        Date endD = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH");
        try {
            startD = simpleDateFormat.parse(startDate);
            endD = simpleDateFormat.parse(endDate);

            if (prestamoDAOImpl.getPrestamo(new LoansId(startD, username, code, copy)) != null) {
                throw new Exception("Problema consultando");
            }
            Users user = usuarioDAOImpl.getUsuario(username);
            if (user == null) {
                throw new Exception("Problema consultando");
            }
            
            DevicesId deviceId = new DevicesId(code, copy);
            Devices device = dispositivoDAOImpl.getDispositivo(deviceId);
            if (device == null) {
                return;
            }
            
            LoansId loanId = new LoansId(startD, username, code, copy);
            Loans loans = new Loans(loanId, endD, null, status);
            prestamoDAOImpl.registraPrestamo(loans);
            System.out.println("asadasdsad");

        } catch (Exception e) {
            throw new RemoteException("Problema consultando");
        }
    }
}
