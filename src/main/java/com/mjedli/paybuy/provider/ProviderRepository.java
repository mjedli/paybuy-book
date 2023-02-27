/**
 * 
 */
package com.mjedli.paybuy.provider;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mjedli.paybuy.Tools;
import com.mjedli.paybuy.provider.model.Provider;
import com.mongodb.client.result.DeleteResult;

/**
 * @author mjedli
 *
 */
@Repository
public class ProviderRepository {

	@Autowired
	private MongoOperations mongoOperations;

	Tools tools = new Tools();

	public Provider addProvider(Provider provider) {
		return mongoOperations.insert(provider);
	}

	public List<Provider> getSearchProvider(String value) {
		
		List<Provider> result = new ArrayList<Provider>();
		
		Query searchQuery = new Query();
		searchQuery.addCriteria(Criteria.where("name").regex(tools.toLikeRegex(value),  "i"));
		List r = mongoOperations.find(searchQuery, Provider.class);
		result = (List<Provider>) Stream.concat(r.stream(), result.stream()).collect(Collectors.toList());
		
		searchQuery = new Query();
		searchQuery.addCriteria(Criteria.where("firstname").regex(tools.toLikeRegex(value),  "i"));
		r = mongoOperations.find(searchQuery, Provider.class);
		result = (List<Provider>) Stream.concat(r.stream(), result.stream()).collect(Collectors.toList());
		
		searchQuery = new Query();
		searchQuery.addCriteria(Criteria.where("lastname").regex(tools.toLikeRegex(value),  "i"));
		r = mongoOperations.find(searchQuery, Provider.class);
		result = (List<Provider>) Stream.concat(r.stream(), result.stream()).collect(Collectors.toList());
		
		searchQuery = new Query();
		searchQuery.addCriteria(Criteria.where("address").regex(tools.toLikeRegex(value),  "i"));
		r = mongoOperations.find(searchQuery, Provider.class);
		result = (List<Provider>) Stream.concat(r.stream(), result.stream()).collect(Collectors.toList());
		
		searchQuery = new Query();
		searchQuery.addCriteria(Criteria.where("mobile").regex(tools.toLikeRegex(value),  "i"));
		r = mongoOperations.find(searchQuery, Provider.class);
		result = (List<Provider>) Stream.concat(r.stream(), result.stream()).collect(Collectors.toList());
		
		if(tools.isNumeric(value)) {
			searchQuery = new Query();
			searchQuery.addCriteria(Criteria.where("id").is(Long.valueOf(value)));
			r = mongoOperations.find(searchQuery, Provider.class);
			result = (List<Provider>) Stream.concat(r.stream(), result.stream()).collect(Collectors.toList());
		}


		return result;
		
	}

	public Provider getProviderById(String id) {
		Query searchQuery = new Query(Criteria.where("id").is(Long.valueOf(id)));
		return mongoOperations.findOne(searchQuery, Provider.class);
	}

	public Provider updateProvider(Provider provider) {
		return mongoOperations.save(provider);
	}

	public DeleteResult removeProvider(Provider provider) {
		return mongoOperations.remove(provider);
	}
	
	
}
