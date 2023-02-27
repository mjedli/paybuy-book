/**
 * 
 */
package com.mjedli.paybuy.login.model;

/**
 * @author mjedli
 *
 */
public class LoginObject {

	private String email;
	private String password;
	private String repassword;
	private String activeMailToken;
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
	 * @return the repassword
	 */
	public String getRepassword() {
		return repassword;
	}
	/**
	 * @param repassword the repassword to set
	 */
	public void setRepassword(String repassword) {
		this.repassword = repassword;
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
