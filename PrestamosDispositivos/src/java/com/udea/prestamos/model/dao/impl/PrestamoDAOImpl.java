package com.udea.prestamos.model.dao.impl;

import com.udea.prestamos.model.Loans;
import com.udea.prestamos.model.dao.PrestamoDAO;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
/**
 * @author Juan Diego
 *
 */
public class PrestamoDAOImpl implements PrestamoDAO {
	
	SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public List<Loans> getPrestamos(){
		List<Loans> lista = new ArrayList<Loans>();
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();//session provista por spring
			Criteria criteria = session.createCriteria(Loans.class);
			lista = criteria.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public void registraPrestamo(Loans loan){
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			session.save(loan);
		} catch (HibernateException e) {
			e.printStackTrace();
		}	
	}

}
