/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.prestamodispositivos.dao;

import com.udea.prestamodispositivos.dto.Loans;
import java.util.ArrayList;

/**
 *
 * @author Juan Diego
 */
public interface LoanDAO {
    public ArrayList<Loans> getLoans();
}
