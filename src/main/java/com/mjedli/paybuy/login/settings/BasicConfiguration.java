package com.mjedli.paybuy.login.settings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.mjedli.paybuy.login.UserService;



@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class BasicConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	UserService userDetailsService;
	
    public BasicConfiguration() {
        super();
    }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests().antMatchers("/paybay/model/insert");
		http.authorizeRequests().antMatchers("/paybay/forget/password");
		http.authorizeRequests().antMatchers("/paybay/update/password");
		
		http.authorizeRequests().antMatchers("/paybay*").hasAuthority("ROLE_USER")
		.antMatchers("/paybay/*").hasAuthority("ROLE_USER")
		.antMatchers("/").hasAuthority("ROLE_USER").and().formLogin().loginPage("/loginApp").loginProcessingUrl("/login")
        .defaultSuccessUrl("/home", true);
		http.logout().logoutSuccessUrl("/loginApp?logout");
		
		http.csrf().disable();
		http.cors();
	}
	
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }
	
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
