package com.udea.prestamos.model.dao.impl;

import com.udea.prestamos.model.HibernateUtil;
import com.udea.prestamos.model.Users;
import com.udea.prestamos.model.dao.UsuarioDAO;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * @author Juan Diego
 *
 */
public class UsuarioDAOImpl implements UsuarioDAO{
	
	@Override
	public List<Users> getUsuarios(){
		List<Users> lista = new ArrayList<Users>();    
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();//session provista por spring
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
			session = HibernateUtil.getSessionFactory().openSession();
                        session.beginTransaction();  
			session.save(user);
                        session.getTransaction().commit();
                        session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public Users getUsuario(String username){
		Users user = null;
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			user = (Users) session.get(Users.class,username);
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		return user;
	}
	
}
