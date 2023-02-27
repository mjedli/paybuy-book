/**
 * 
 */
package com.mjedli.paybuy.invoice;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mjedli.paybuy.invoice.model.Invoice;
import com.mjedli.paybuy.invoice.model.Result;
import com.mjedli.paybuy.invoice.model.SearchInvoice;

/**
 * @author mjedli
 *
 */
@Service
public class InvoiceService {

	@Autowired
	private InvoiceRepository invoiceRepository;

	public Invoice addInvoice(Invoice invoice) {
		return invoiceRepository.addInvoice(invoice);
	}

	public List<Invoice> getInvoicesByDate(SearchInvoice searchInvoice) {
		return invoiceRepository.getInvoicesByDate(searchInvoice);
	}

	public Invoice getInvoiceById(String id) {
		return invoiceRepository.getInvoiceById(id);
	}

	public List<Invoice> calReslutMonth(Date date) {
		return invoiceRepository.calReslutMonth(date);
	}
	
	public List<Invoice> calReslutYear(Date date) {
		return invoiceRepository.calReslutYear(date);
	}
	
	public List<Invoice> calReslutDay(Date date) {
		return invoiceRepository.calReslutDay(date);
	}
	
}
