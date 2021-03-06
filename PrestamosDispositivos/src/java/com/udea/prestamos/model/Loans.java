package com.udea.prestamos.model;
// Generated 30-abr-2017 2:26:16 by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Loans generated by hbm2java
 */
public class Loans  implements java.io.Serializable {


     private LoansId id;
     private Devices devices;
     private Users users;
     private Date endDate;
     private Date returnDate;
     private String status;

    public Loans() {
    }

    public Loans(LoansId id, Date endDate, Date returnDate, String status) {
        this.id = id;
        this.endDate = endDate;
        this.returnDate = returnDate;
        this.status = status;
    }
   
    public LoansId getId() {
        return this.id;
    }
    
    public void setId(LoansId id) {
        this.id = id;
    }
    public Devices getDevices() {
        return this.devices;
    }
    
    public void setDevices(Devices devices) {
        this.devices = devices;
    }
    public Users getUsers() {
        return this.users;
    }
    
    public void setUsers(Users users) {
        this.users = users;
    }
    public Date getEndDate() {
        return this.endDate;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public Date getReturnDate() {
        return this.returnDate;
    }
    
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }




}


