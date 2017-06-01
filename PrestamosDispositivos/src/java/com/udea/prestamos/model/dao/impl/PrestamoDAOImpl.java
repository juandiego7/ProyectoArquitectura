package com.udea.prestamos.model.dao.impl;

import com.udea.prestamos.model.HibernateUtil;
import com.udea.prestamos.model.Loans;
import com.udea.prestamos.model.dao.PrestamoDAO;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
/**
 * @author Juan Diego
 *
 */
public class PrestamoDAOImpl implements PrestamoDAO {
		
	@Override
	public List<Loans> getPrestamos(){
		List<Loans> lista = new ArrayList<Loans>();
		Session session = null;
		try {
			session =  HibernateUtil.getSessionFactory().openSession();//session provista por spring
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
			session =  HibernateUtil.getSessionFactory().openSession();
			session.save(loan);
		} catch (HibernateException e) {
			e.printStackTrace();
		}	
	}

}
