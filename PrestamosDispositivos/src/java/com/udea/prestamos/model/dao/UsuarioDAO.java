/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.prestamos.model.dao;

import com.udea.prestamos.model.Users;
import java.util.List;

/**
 *
 * @author Juan Diego
 */
public interface UsuarioDAO {

    public List<Users> getUsuarios();

    public void registraUsuario(Users user);

    public Users getUsuario(String username);
}
