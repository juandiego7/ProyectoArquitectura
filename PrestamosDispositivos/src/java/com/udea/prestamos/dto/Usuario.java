package com.udea.prestamos.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Juan Diego Goez Durango - diego.goez@udea.edu.co
 * @version 2.0
 */
@XmlRootElement//para convertir automaticamente a formato XML o Json
public class Usuario {

    private String username;
    private String typeId;
    private String numberId;
    private String name;
    private String lastName;
    private String email;
    private String role;
    private String password;
    private Usuario manager;

    public Usuario(String username, String typeId, String numberId, String name, String lastName, String email, String role, String password, Usuario manager) {
        this.username = username;
        this.typeId = typeId;
        this.numberId = numberId;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.password = password;
        this.manager = manager;
    }

    public Usuario() {

    }

    /**
     * @param username
     * @param typeId
     * @param numberId
     * @param name
     * @param lastName
     * @param email
     * @param role
     */
    public Usuario(String username, String typeId, String numberId, String name, String lastName, String email,
            String role) {
        this.username = username;
        this.typeId = typeId;
        this.numberId = numberId;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }

    /**
     * @param username
     */
    public Usuario(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getNumberId() {
        return numberId;
    }

    public void setNumberId(String numberId) {
        this.numberId = numberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Usuario getManager() {
        return manager;
    }

    public void setManager(Usuario manager) {
        this.manager = manager;
    }
}
