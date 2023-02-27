/**
 * 
 */
package com.mjedli.paybuy.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mjedli.paybuy.customer.model.Customer;
import com.mongodb.client.result.DeleteResult;

/**
 * @author mjedli
 *
 */

@Service
public class CustomerService {

	@Autowired
	private CutsomerRepository cutsomerRepository;
	
	public Customer addCustomer(Customer customer) {
		return cutsomerRepository.addCustomer(customer);
	}

	public List<Customer> getSearchCustomer(String value) {
		return cutsomerRepository.getSearchCustomer(value);
	}

	public Customer getCustomerById(String id) {
		return cutsomerRepository.getCustomerById(id);
	}

	public Customer updateCustomer(Customer customer) {
		return cutsomerRepository.updateCustomer(customer);
	}

	public DeleteResult removeCustomer(Customer customer) {
		return cutsomerRepository.removeCustomer(customer);
	}

	public void addCreditToCustomer(String idCustomer, String newCredit) {
		cutsomerRepository.addCreditToCustomer(idCustomer, newCredit);
		
	}
	
}
