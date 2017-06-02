package com.udea.prestamos.model.dao;

import com.udea.prestamos.model.DevicesId;
import com.udea.prestamos.model.Loans;
import com.udea.prestamos.model.LoansId;
import com.udea.prestamos.model.Users;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Juan Diego
 */
public interface PrestamoDAO {

    public List<Loans> getPrestamos();

    public void registraPrestamo(Loans loan);

    /**
     * Metodo para obtener los prestamos de un dispositivo en cierta fecha
     *
     * @param deviceId
     * @param date
     * @return List<Loans>
     */
    public List<Loans> getPrestamos(DevicesId deviceId, Date date);

    /**
     * Metodo para obtener un prestamo por medio de su clave primaria
     *
     * @param loansId
     * @return Loans
     */
    public Loans getPrestamo(LoansId loanId);

    /**
     * Metodo para actualizar un prestamo
     *
     * @param loan
     */
    public void updatePrestamo(Loans loan);

    /**
     * Metodo para obtener los prestamos de un usuario con cierto estado
     *
     * @param user
     * @param status
     * @return List<Loans>
     */
    public List<Loans> getLoans(Users user, String status);

    /**
     * Metodo para obtener los prestamos asociados a un tipo y numero de
     * identificaion de un investigador
     *
     * @param typeId
     * @param numberId
     * @return List<Loans>
     */
    public List<Loans> getPrestamos(String typeId, String numberId);

    /**
     * Metodo para borrar un prestamo
     *
     * @param loan
     */
    public void deletePrestamo(Loans loan);
}
