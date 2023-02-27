package com.mjedli.paybuy.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mjedli.paybuy.SequenceGeneratorService;
import com.mjedli.paybuy.customer.model.Customer;

/**
 * @author mjedli
 *
 */

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"})
public class CustomerController {

	private static final String HREF_BASE = "/paybay";
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;
	
	@PostMapping(value = HREF_BASE + "/customer/add")
	@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"})
	private Customer addCustomer(@RequestBody Customer customer) {
		
		customer.setId(sequenceGeneratorService.generateSequence(Customer.SEQUENCE_NAME));
		
		return customerService.addCustomer(customer);
	
	}
	
	@PostMapping(value = HREF_BASE + "/customer")
	@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"})
	private List<Customer> getSearchCustomer(@RequestBody String value) {

		return customerService.getSearchCustomer(value);
	
	}
	
	@GetMapping(value = HREF_BASE + "/customer/{id}")
	@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"})
	private Customer getCustomerById(@PathVariable String id) {

		return customerService.getCustomerById(id);
	
	}
	
	@PostMapping(value = "/paybay/customer/update")
	@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"})
	private Customer updateCustomer(@RequestBody Customer customer) {

		return customerService.updateCustomer(customer);
	
	}
	
	@PostMapping(value = HREF_BASE + "/customer/remove")
	@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"})
	private int removeCustomer(@RequestBody Customer customer) {

		if(customerService.removeCustomer(customer).getDeletedCount()==1) {
			return 1;
		};
		
		return 0;
	
	}
}
