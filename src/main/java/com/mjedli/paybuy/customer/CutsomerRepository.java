package com.mjedli.paybuy.customer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mjedli.paybuy.customer.model.Customer;
import com.mongodb.client.result.DeleteResult;

import com.mjedli.paybuy.Tools;

@Repository
public class CutsomerRepository {

	@Autowired
	private MongoOperations mongoOperations;

	Tools tools = new Tools();
	
	public Customer addCustomer(Customer customer) {
		return mongoOperations.insert(customer);
	}

	public List<Customer> getSearchCustomer(String value) {

		List<Customer> result = new ArrayList<Customer>();
		
		Query searchQuery = new Query();
		searchQuery.addCriteria(Criteria.where("name").regex(tools.toLikeRegex(value),  "i"));
		List r = mongoOperations.find(searchQuery, Customer.class);
		result = (List<Customer>) Stream.concat(r.stream(), result.stream()).collect(Collectors.toList());
		
		searchQuery = new Query();
		searchQuery.addCriteria(Criteria.where("firstname").regex(tools.toLikeRegex(value),  "i"));
		r = mongoOperations.find(searchQuery, Customer.class);
		result = (List<Customer>) Stream.concat(r.stream(), result.stream()).collect(Collectors.toList());
		
		searchQuery = new Query();
		searchQuery.addCriteria(Criteria.where("lastname").regex(tools.toLikeRegex(value),  "i"));
		r = mongoOperations.find(searchQuery, Customer.class);
		result = (List<Customer>) Stream.concat(r.stream(), result.stream()).collect(Collectors.toList());
		
		searchQuery = new Query();
		searchQuery.addCriteria(Criteria.where("address").regex(tools.toLikeRegex(value),  "i"));
		r = mongoOperations.find(searchQuery, Customer.class);
		result = (List<Customer>) Stream.concat(r.stream(), result.stream()).collect(Collectors.toList());
		
		searchQuery = new Query();
		searchQuery.addCriteria(Criteria.where("mobile").regex(tools.toLikeRegex(value),  "i"));
		r = mongoOperations.find(searchQuery, Customer.class);
		result = (List<Customer>) Stream.concat(r.stream(), result.stream()).collect(Collectors.toList());
		
		if(tools.isNumeric(value)) {
			searchQuery = new Query();
			searchQuery.addCriteria(Criteria.where("id").is(Long.valueOf(value)));
			r = mongoOperations.find(searchQuery, Customer.class);
			result = (List<Customer>) Stream.concat(r.stream(), result.stream()).collect(Collectors.toList());
		}


		return result;
		
	}
	
	public Customer getCustomerById(String id) {
		Query searchQuery = new Query(Criteria.where("id").is(Long.valueOf(id)));
		return mongoOperations.findOne(searchQuery, Customer.class);
	}

	public Customer updateCustomer(Customer customer) {
		return mongoOperations.save(customer);
	}

	public DeleteResult removeCustomer(Customer customer) {
		return mongoOperations.remove(customer);
	}

	public void addCreditToCustomer(String idCustomer, String newCredit) {
		Customer customer = getCustomerById(idCustomer);
		customer.setCredit(newCredit);
		updateCustomer(customer);
	}
	
}
