package com.udea.prestamos.model.dao.impl;

import com.udea.prestamos.model.Users;
import com.udea.prestamos.model.dao.UsuarioDAO;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Juan Diego
 *
 */
public class UsuarioDAOImpl implements UsuarioDAO{
	@Autowired
	SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public List<Users> getUsuarios(){
		List<Users> lista = new ArrayList<Users>();
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();//session provista por spring
			Criteria criteria = session.createCriteria(Users.class);
			lista = criteria.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public void registraUsuario(Users user){
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			session.save(user);
		} catch (HibernateException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public Users getUsuario(String username){
		Users user = null;
		Session session = null;
		try{
			session = sessionFactory.getCurrentSession();
			user = (Users) session.get(Users.class,username);
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		return user;
	}
	
}
