/**
 * 
 */
package com.mjedli.paybuy.stock;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.tomcat.util.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mjedli.paybuy.Tools;
import com.mjedli.paybuy.customer.model.Customer;
import com.mjedli.paybuy.stock.model.Product;
import com.mongodb.client.result.DeleteResult;

/**
 * @author mjedli
 *
 */
@Repository
public class StockRepository {

	@Autowired
	private MongoOperations mongoOperations;

	Tools tools = new Tools();
	
	public Product addProduct(Product product) {
		return mongoOperations.insert(product);
		
	}

	public List<Product> getSearchStock(String value) {
		
		List<Product> result = new ArrayList<Product>();
		
		Query searchQuery = new Query();
		searchQuery.addCriteria(Criteria.where("name").regex(tools.toLikeRegex(value),  "i"));
		List r = mongoOperations.find(searchQuery, Product.class);
		result = (List<Product>) Stream.concat(r.stream(), result.stream()).collect(Collectors.toList());

		if(tools.isNumeric(value)) {
			searchQuery = new Query();
			searchQuery.addCriteria(Criteria.where("id").is(Long.valueOf(value)));
			r = mongoOperations.find(searchQuery, Product.class);
			result = (List<Product>) Stream.concat(r.stream(), result.stream()).collect(Collectors.toList());
		}


		return result;
	}

	public Product getProductById(String id) {
		Query searchQuery = new Query(Criteria.where("id").is(Long.valueOf(id)));
		return mongoOperations.findOne(searchQuery, Product.class);
	}

	public Product updateProduct(Product product) {
		return mongoOperations.save(product);
	}

	public DeleteResult removeProduct(Product product) {
		return mongoOperations.remove(product);
	}

	public List<Product> getSearchEmptyProduct() {
		Query searchQuery = new Query(Criteria.where("amount").is("0"));
		return mongoOperations.find(searchQuery, Product.class);
	}

}
