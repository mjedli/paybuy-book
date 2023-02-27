package com.mjedli.paybuy.invoice.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

@Document
public class Invoice {

    @Transient
    public static final String SEQUENCE_NAME = "invoice_sequence";
	
	@Id
	@NonNull
	private long id;
	
	private String idCustomer = "";
	private String date = "";
	  
	private List<Line> listline ;
	private String total = "";
	private String totalTva = "";
	private String credit = "";
	private String newCredit = "";
	private String paid = "";
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getIdCustomer() {
		return idCustomer;
	}
	public void setIdCustomer(String idCustomer) {
		this.idCustomer = idCustomer;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public List<Line> getListline() {
		return listline;
	}
	public void setListline(List<Line> listline) {
		this.listline = listline;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getTotalTva() {
		return totalTva;
	}
	public void setTotalTva(String totalTva) {
		this.totalTva = totalTva;
	}
	public String getCredit() {
		return credit;
	}
	public void setCredit(String credit) {
		this.credit = credit;
	}
	public String getNewCredit() {
		return newCredit;
	}
	public void setNewCredit(String newCredit) {
		this.newCredit = newCredit;
	}
	public String getPaid() {
		return paid;
	}
	public void setPaid(String paid) {
		this.paid = paid;
	}
	
	
}
