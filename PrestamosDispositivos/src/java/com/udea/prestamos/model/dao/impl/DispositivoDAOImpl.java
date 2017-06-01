/**
 *
 */
package com.udea.prestamos.model.dao.impl;

import com.udea.prestamos.model.Devices;
import com.udea.prestamos.model.DevicesId;
import com.udea.prestamos.model.HibernateUtil;
import com.udea.prestamos.model.dao.DispositivoDAO;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * @author Juan Diego
 *
 */
public class DispositivoDAOImpl implements DispositivoDAO {

    @Override
    public List<Devices> getDispositivos() {
        List<Devices> lista = new ArrayList<>();
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();//session provista por spring
            Criteria criteria = session.createCriteria(Devices.class);
            lista = criteria.list();
            //session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void ActualizaDispositivo(Devices device) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.update(device);
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Devices getDispositivo(DevicesId deviceId){
        Devices device = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            device = (Devices) session.get(Devices.class, deviceId);
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return device;
    }

    @Override
    public void registraDispositivo(Devices device){
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.save(device);
            session.close();
        } catch (HibernateException e) {
            
        }
    }

    @Override
    public List<Devices> buscaDispositivo(String code, String name, String type){
        List<Devices> lista = new ArrayList<>();
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();//session provista por spring
            Criteria criteria = session.createCriteria(Devices.class);
            if (code != null && !"".equals(code)) {
                criteria.add(Restrictions.eq("deviceId.code", code));
            }
            if (name != null && !"".equals(name)) {
                criteria.add(Restrictions.eq("name", name));
            }
            if (type != null && !"".equals(type)) {
                criteria.add(Restrictions.eq("type", type));
            }
            lista = criteria.list();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return lista;
    }

}
