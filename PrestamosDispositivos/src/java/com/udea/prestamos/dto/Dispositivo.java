/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.prestamos.dto;

/**
 *
 * @author Juan Diego
 */
import com.udea.prestamos.model.DevicesId;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Modelo relacional (pojo) de los dispositivos
 * @author Raul Antonio Martinez Silgado - rantonio.martinez@udea.edu.co
 * @author Juan Diego Goez Durango - diego.goez@udea.edu.co
 * @version 2.0
 */
@XmlRootElement
public class Dispositivo {

	private DevicesId deviceId;
	private String name;
	private String type;
	private String status;
	private String details;
	
	public Dispositivo() {

	}
	/**
	 * @param deviceId
	 * @param name
	 * @param type
	 * @param status
	 * @param details
	 */
	public Dispositivo(DevicesId deviceId, String name, String type, String status, String details) {
		this.deviceId = deviceId;
		this.name = name;
		this.type = type;
		this.status = status;
		this.details = details;
	}
	public DevicesId getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(DevicesId deviceId) {
		this.deviceId = deviceId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	
}