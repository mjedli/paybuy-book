/**
 * 
 */
package com.mjedli.paybuy.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mjedli.paybuy.login.model.LoginPojo;



/**
 * @author mjedli
 *
 */
@Repository
public class ParismonRepository {

	@Autowired
	private MongoOperations mongoOperations;
	
	public LoginPojo findByMail(String mail) {
		Query searchQuery = new Query(Criteria.where("email").is(mail));
		return mongoOperations.findOne(searchQuery, LoginPojo.class);
	}

	public LoginPojo insert(LoginPojo parismon) {
		LoginPojo b = findByMail(parismon.getEmail());
		if ( (b!=null) && (b.getEmail() != null) && (b.getEmail().equals(parismon.getEmail()) ) ) {
			b.setEmail("exist");
			return b;
		}
		return mongoOperations.insert(parismon);
	}
	
	public LoginPojo updateParismon(LoginPojo parismon) {		
		return mongoOperations.save(parismon);
	}
	
	public LoginPojo findParismonByActiveToken(String id) {
		Query searchQuery = new Query(Criteria.where("activeMailToken").is(id));
		return mongoOperations.findOne(searchQuery, LoginPojo.class);
	}

}
