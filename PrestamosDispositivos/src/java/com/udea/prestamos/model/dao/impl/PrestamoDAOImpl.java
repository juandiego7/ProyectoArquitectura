package com.udea.prestamos.model.dao.impl;

import com.udea.prestamos.model.Devices;
import com.udea.prestamos.model.DevicesId;
import com.udea.prestamos.model.HibernateUtil;
import com.udea.prestamos.model.Loans;
import com.udea.prestamos.model.LoansId;
import com.udea.prestamos.model.Users;
import com.udea.prestamos.model.dao.PrestamoDAO;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * @author Juan Diego
 *
 */
public class PrestamoDAOImpl implements PrestamoDAO {

    @Override
    public List<Loans> getPrestamos() {
        List<Loans> lista = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();//session provista por spring
            Criteria criteria = session.createCriteria(Loans.class);
            lista = criteria.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void registraPrestamo(Loans loan) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(loan);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Loans> getPrestamos(DevicesId deviceId, Date date) {
        List<Loans> lista = null;
        Devices device = null;
        Session session = null;
        Date startDateToday = null;
        Date endDateToday = null;
        Calendar calendar = null;
        try {
            calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR, 1);
            startDateToday = calendar.getTime();
            calendar.set(Calendar.HOUR, 23);
            endDateToday = calendar.getTime();
            device = new Devices();
            device.setId(deviceId);
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Loans.class);
            criteria.add(Restrictions.eq("loanId.device", device));
            criteria.add(Restrictions.between("loanId.startDate", startDateToday, endDateToday));
            lista = criteria.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public Loans getPrestamo(LoansId loanId) {
        Loans loan = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            loan = (Loans) session.get(Loans.class, loanId);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return loan;
    }

    @Override
    public void updatePrestamo(Loans loan) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(loan);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Loans> getLoans(Users user, String status) {
        List<Loans> lista = null;
        Session session = null;
        try {
            session =  HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Loans.class);
            criteria.add(Restrictions.eq("loanId.username", user));
            criteria.add(Restrictions.eq("status", status));
            lista = criteria.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public List<Loans> getPrestamos(String typeId, String numberId) {
        List<Loans> lista = null;
        Users user = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Users.class);
            criteria.add(Restrictions.eq("typeId", typeId));
            criteria.add(Restrictions.eq("numberId", numberId));
            user = (Users) criteria.uniqueResult();
            lista = getLoans(user, "RESERVADO");
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void deletePrestamo(Loans loan) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(loan);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

}
