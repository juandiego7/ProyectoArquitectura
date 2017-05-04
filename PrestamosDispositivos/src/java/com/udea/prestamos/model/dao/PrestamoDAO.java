/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.prestamos.model.dao;

import com.udea.prestamos.model.Loans;
import java.util.List;

/**
 *
 * @author Juan Diego
 */
public interface PrestamoDAO {

    public List<Loans> getPrestamos();

    public void registraPrestamo(Loans loan);
}
