package com.udea.prestamodispositivos.config;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Juan Diego
 */
public class DataSource {

    private static DataSource instancia;//Singleton
    private SessionFactory factory = null;
    private Configuration conf = new Configuration();

    //constructor privado para el Singleton
    private DataSource() {

    }

    public static DataSource getInstancia() {
        if (instancia == null) {
            instancia = new DataSource();
        }
        return instancia;
    }

    public Session getSession() throws Exception {
        try {
            if (factory == null) {
                conf.configure("hibernate.cfg.xml");
                factory = conf.buildSessionFactory();
            }
        } catch (HibernateException e) {
            throw new Exception("Error configurando la session", e);
        }
        return factory.openSession();
    }
}
