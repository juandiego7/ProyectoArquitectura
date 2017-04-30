package com.udea.prestamos.controller;

import com.udea.prestamos.model.HibernateUtil;
import com.udea.prestamos.model.Loans;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author Juan Diego
 */
public class UserController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        ModelAndView mv = new ModelAndView("user");
        String out = "All User Details";

        List<Loans> lista = new ArrayList<>();
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        String hq1 = "from Users";
        Query query = session.createQuery(hq1);
        lista = query.list();
        mv.addObject("users",lista);
        session.close();

        mv.addObject("message", out);
        return mv;
    }

}
