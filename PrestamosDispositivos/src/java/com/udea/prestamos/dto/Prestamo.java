
package com.udea.prestamos.dto;

/**
 *
 * @author Juan Diego
 */
import com.udea.prestamos.model.LoansId;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Modelo relacional (pojo) de los préstamos
 * @author Raul Antonio Martinez Silgado - rantonio.martinez@udea.edu.co
 * @author Juan Diego Goez Durango - diego.goez@udea.edu.co
 * @version 2.0
 */
@XmlRootElement
public class Prestamo {

	private LoansId loanId;
	private Date endDate;
	private Date returnDate;
	private String status;

	public Prestamo() {
		
	}
	/**
	 * @param loanId
	 * @param endDate
	 * @param returnDate
	 * @param status
	 */
	public Prestamo(LoansId loanId, Date endDate, Date returnDate, String status) {
		this.loanId = loanId;
		this.endDate = endDate;
		this.returnDate = returnDate;
		this.status = status;
	}
	/**
	 * @return the loanId
	 */
	public LoansId getLoanId() {
		return loanId;
	}
	/**
	 * @param loanId the loanId to set
	 */
	public void setLoanId(LoansId loanId) {
		this.loanId = loanId;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}