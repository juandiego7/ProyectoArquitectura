/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.prestamodispositivos.dao.impl;

import com.udea.prestamodispositivos.dao.LoanDAO;
import com.udea.prestamodispositivos.dto.Loans;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Juan Diego
 */
public class LoanDAOImplTest {

    @Test
    public void testObtener() {
        LoanDAO loanDAO = null;//Declaracion de interfaz para implementar los metodos con la BD
        List<Loans> lista = null;//Lista donde se almacenan las ciudades

        try {
            loanDAO = new LoanDAOImpl();
            lista = loanDAO.getLoans();
			for(Loans ciudad: lista){
				System.out.println("status: "+ciudad.getStatus() +
									"-1-"+ciudad.getEndDate()+
									//"2-"+ciudad.getLoanId().getDevice().getName()+
									"3-"+ciudad.getId().getUsername()+
									//"-4-"+ciudad.getLoanId().getStartDate()+
									"-5-"+ciudad.getReturnDate());
			}
            assertTrue(lista.size() > 0);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

}
