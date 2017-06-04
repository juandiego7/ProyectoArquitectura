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
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
    @Path("usuario")//Definicion de la ruta para invocar este metodo
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
    @Path("registrar")
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

    /**
     * Retorna los prestamos para un dispositvo en la fecha(dia) indicado
     *
     * @see RF02
     * @param code
     * @param copy
     * @param date
     * @return Lista de prestamos
     * @throws RemoteException
     */
    @GET
    @Path("dispositivo")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Prestamo> getLoans(
            @QueryParam("code") String code,
            @QueryParam("copy") String copy,
            @QueryParam("date") String date) throws RemoteException {
        Date d = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        List<Prestamo> prestamos = new ArrayList<>();
        try {
            d = simpleDateFormat.parse(date);

            for (Loans loan : prestamoDAOImpl.getPrestamos(new DevicesId(code, copy), d)) {
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

    /**
     * Actualiza el estado de un prestamo a PRESTADO (Aprobar prestamo)
     *
     * @see RF05
     * @param username
     * @param startDate
     * @param code
     * @param copy
     * @return Response - Confirmación de actualización
     */
    @PUT
    @Path("prestar")
    @Produces(MediaType.APPLICATION_JSON)
    public void updateStatusLoan(
            @QueryParam("username") String username,
            @QueryParam("startDate") String startDate,
            @QueryParam("code") String code,
            @QueryParam("copy") String copy) throws RemoteException {
        Date date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH");

        Loans loan = null;
        try {
            if (startDate != null && !"".equals(startDate)) {
                date = simpleDateFormat.parse(startDate);
            }
            loan = prestamoDAOImpl.getPrestamo(new LoansId(date, username, code, copy));
            loan.setStatus("PRESTADO");
            prestamoDAOImpl.updatePrestamo(loan);

        } catch (Exception e) {
            throw new RemoteException("Problema consultando");
        }
    }

    /**
     * Actualiza el estado(status) a DEVUELTO y fecha de devolución (returnDate)
     * de un préstamo a la fecha actual, lo que significa que el dispositvo fue
     * entregado en el Laboratorio
     *
     * @see RF16
     * @param username
     * @param startDate
     * @param code
     * @param copy
     * @return Response - Confirmación de actualización
     */
    @PUT
    @Path("devolver")
    @Produces(MediaType.APPLICATION_JSON)
    public void updateRetornoPrestamo(
            @QueryParam("username") String username,
            @QueryParam("startDate") String startDate,
            @QueryParam("code") String code,
            @QueryParam("copy") String copy) throws RemoteException {
        Date date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH");

        Loans loan = null;
        try {
            if (startDate != null && !"".equals(startDate)) {
                date = simpleDateFormat.parse(startDate);
            }
            loan = prestamoDAOImpl.getPrestamo(new LoansId(date, username, code, copy));
            loan.setStatus("DEVUELTO");
            loan.setReturnDate(new Date());
            prestamoDAOImpl.updatePrestamo(loan);

        } catch (Exception e) {
            throw new RemoteException("Problema consultando");
        }
    }

    /**
     * Obtiene la lista de solicitudes de prestamos para el usuario con el tipo
     * y número de identificación ingresados
     *
     * @see RF13
     * @param typeId
     * @param numberId
     * @return Lista de Prestamos
     * @throws RemoteException
     */
    @GET
    @Path("buscar")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Prestamo> searchLoan(
            @QueryParam("typeId") String typeId,
            @QueryParam("numberId") String numberId) throws RemoteException {
        List<Prestamo> prestamos = new ArrayList<>();
        try {
            for (Loans loan : prestamoDAOImpl.getPrestamos(typeId, numberId)) {
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
            throw new RemoteException("Error consultando los prestamos por tipo y numero de Id");
        }
    }

    /**
     * Deshace(elimina) una solicitud de prestamo
     *
     * @see RF15
     * @param username
     * @param startDate
     * @param code
     * @param copy
     * @return Response - Confirmación del delete
     * @throws RemoteException
     */
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public void borrarPrestamo(
            @QueryParam("username") String username,
            @QueryParam("startDate") String startDate,
            @QueryParam("code") String code,
            @QueryParam("copy") String copy) throws RemoteException {
        Date date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH");

        Loans loan = null;
        try {
            if (startDate != null && !"".equals(startDate)) {
                date = simpleDateFormat.parse(startDate);
            }
            loan = prestamoDAOImpl.getPrestamo(new LoansId(date, username, code, copy));
            if (loan.getStatus().equals("RESERVADO")) {
                prestamoDAOImpl.deletePrestamo(loan);
            }
        } catch (Exception e) {
            throw new RemoteException("Error consultando");
        }

    }

}
