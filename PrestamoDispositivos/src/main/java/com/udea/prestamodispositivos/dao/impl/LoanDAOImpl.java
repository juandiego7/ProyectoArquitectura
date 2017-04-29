package com.udea.prestamodispositivos.dao.impl;

import com.udea.prestamodispositivos.config.HibernateUtil;
import com.udea.prestamodispositivos.dao.LoanDAO;
import com.udea.prestamodispositivos.dto.Loans;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Juan Diego
 */
public class LoanDAOImpl implements LoanDAO {

    @Override
    public ArrayList<Loans> getLoans() {
        List<Loans> lista = new ArrayList<>();
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        String hq1 = "from Loans";
        Query query = session.createQuery(hq1);
        lista = query.list();
        session.close();
        return (ArrayList<Loans>) lista;
    }

}
