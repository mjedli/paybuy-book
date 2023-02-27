/**
 * 
 */
package com.mjedli.paybuy.invoice.model;

/**
 * @author mjedli
 *
 */
public class SearchInvoice {

	public String idCustomer = "";
	public String startDate = "";
	public String endDate = "";
	
	public String getIdCustomer() {
		return idCustomer;
	}
	public void setIdCustomer(String idCustomer) {
		this.idCustomer = idCustomer;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
}
