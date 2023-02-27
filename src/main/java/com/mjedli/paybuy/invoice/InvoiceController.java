/**
 * 
 */
package com.mjedli.paybuy.invoice;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mjedli.paybuy.SequenceGeneratorService;
import com.mjedli.paybuy.customer.CustomerService;
import com.mjedli.paybuy.customer.model.Customer;
import com.mjedli.paybuy.invoice.model.Invoice;
import com.mjedli.paybuy.invoice.model.Line;
import com.mjedli.paybuy.invoice.model.Result;
import com.mjedli.paybuy.invoice.model.SearchInvoice;
import com.mjedli.paybuy.stock.StockService;
import com.mjedli.paybuy.stock.model.Product;

/**
 * @author mjedli
 *
 */
@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"})
public class InvoiceController {

	private static final String HREF_BASE = "/paybay";
	
	@Autowired
	private InvoiceService invoiceService;
	
	@Autowired
	private StockService stockService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;
	
	@PostMapping(value = HREF_BASE + "/invoice/add")
	@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"})
	private Invoice addCustomer(@RequestBody Invoice invoice) {
		
		invoice.setId(sequenceGeneratorService.generateSequence(Invoice.SEQUENCE_NAME));
		
		for(Line line : invoice.getListline()) {
			Product product = stockService.getProductById(String.valueOf(line.getId()));
			if(Integer.valueOf(product.getAmount())>0) {
				product.setAmount(String.valueOf(Integer.valueOf(product.getAmount())-Integer.valueOf(line.getAmount())));
				stockService.updateProduct(product);
			}
		}
		
		customerService.addCreditToCustomer(invoice.getIdCustomer(), invoice.getNewCredit());
		
		return invoiceService.addInvoice(invoice);
	}
	
	@PostMapping(value = HREF_BASE + "/invoice/date")
	@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"})
	private List<Invoice> getInvoicesByDate(@RequestBody SearchInvoice searchInvoice) {
		
		return invoiceService.getInvoicesByDate(searchInvoice);
	
	}
	
	
	@GetMapping(value = HREF_BASE + "/invoice/{id}")
	@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"})
	private Invoice getInvoiceById(@PathVariable String id) {

		return invoiceService.getInvoiceById(id);
	
	}
	
	@PostMapping(value = HREF_BASE + "/invoice/result")
	@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"})
	private Result calReslut(@RequestBody Date date) {
		
		Result result = new Result();
		
		Long somme = (long) 0;
		List<Invoice> list = invoiceService.calReslutDay(date);
		for(Invoice invoice : list) {
			somme = somme + Long.valueOf(invoice.getTotalTva());
		}
		result.setResultDay(somme.toString());

		somme = (long) 0;
		list = invoiceService.calReslutMonth(date);
		for(Invoice invoice : list) {
			somme = somme + Long.valueOf(invoice.getTotalTva());
		}
		result.setResultMonth(somme.toString());

		somme = (long) 0;
		list = invoiceService.calReslutYear(date);
		for(Invoice invoice : list) {
			somme = somme + Long.valueOf(invoice.getTotalTva());
		}
		result.setResultYear(somme.toString());
		
		return result;
	
	}
	
}
