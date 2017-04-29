package com.udea.prestamodispositivos.dao.impl;

import com.udea.prestamodispositivos.dao.LoanDAO;
import com.udea.prestamodispositivos.dto.Loan;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Juan Diego
 */
public class LoanDAOImpl implements LoanDAO {

    SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Loan> getLoans() {
        
        
        List<Loan> lista = new ArrayList<>();
//        try {
//            Session session = HibernateUtil.getSessionFactory().openSession();
//            Criteria criteria = session.createCriteria(Loan.class);
//            lista = criteria.list();
//            session.close();
//        } catch (Exception e) {
//            System.out.println("Error consultando loans: " + e);
//        }
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();//session provista por spring
            Criteria criteria = session.createCriteria(Loan.class);
            lista = criteria.list();
        } catch (HibernateException e) {
            System.out.println("Error consultando loans: " + e);
        }
        return lista;

    }

}
