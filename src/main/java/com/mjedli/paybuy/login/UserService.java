/**
 * 
 */
package com.mjedli.paybuy.login;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mjedli.paybuy.login.model.LoginPojo;


/**
 * @author mjedli
 *
 */
@Service
public class UserService implements UserDetailsService  {

	@Autowired
	ParismonRepository parismonRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		LoginPojo barber = parismonRepository.findByMail(username);
		
		final UserDetails userDetails = new UserDetails() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isEnabled() {
				// TODO Auto-generated method stub
				return true;
			}
			
			@Override
			public boolean isCredentialsNonExpired() {
				// TODO Auto-generated method stub
				return true;
			}
			
			@Override
			public boolean isAccountNonLocked() {
				// TODO Auto-generated method stub
				return true;
			}
			
			@Override
			public boolean isAccountNonExpired() {
				// TODO Auto-generated method stub
				return true;
			}
			
			@Override
			public String getUsername() {
				// TODO Auto-generated method stub
				if (barber != null && barber.getActive()==true) {
				return barber.getEmail();
				}
				
				return "";
			}
			
			@Override
			public String getPassword() {
				// TODO Auto-generated method stub
				if (barber != null && barber.getActive()==true) {
				return barber.getPassword();
					}
				
				return "";
			}
			
			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {
				// TODO Auto-generated method stub
				return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
			}
		};
		
		
		return userDetails;
	}
	
	

}
