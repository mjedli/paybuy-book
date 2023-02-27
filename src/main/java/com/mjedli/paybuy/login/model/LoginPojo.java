/**
 * 
 */
package com.mjedli.paybuy.login.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

/**
 * @author mjedli
 *
 */
@Document
public class LoginPojo {
	
	@Id
	@NonNull
	private String id;

	private String name = "";
	
	@NonNull
	@Indexed(unique=true)
	private String email = "";
	private String password = "";
	
	private Boolean active = false;
	private String activeMailToken = "";
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the active
	 */
	public Boolean getActive() {
		return active;
	}
	/**
	 * @param active the active to set
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}
	/**
	 * @return the activeMailToken
	 */
	public String getActiveMailToken() {
		return activeMailToken;
	}
	/**
	 * @param activeMailToken the activeMailToken to set
	 */
	public void setActiveMailToken(String activeMailToken) {
		this.activeMailToken = activeMailToken;
	}
	
}
