package com.mjedli.paybuy.login.settings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.mjedli.paybuy.login.UserService;

@Configuration
@EnableWebSecurity
public class BasicConfiguration {

	@Autowired
	UserService userDetailsService;

    @Bean
    public DaoAuthenticationProvider authenticationProvider() 
    {
      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
      authProvider.setUserDetailsService(userDetailsService);
      authProvider.setPasswordEncoder(passwordEncoder());
   
      return authProvider;
    }    
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception 
    {
        return authConfiguration.getAuthenticationManager();
    }    

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeHttpRequests()
		.requestMatchers("/content/**").permitAll()
		.requestMatchers("/paybay*").hasAuthority("ROLE_USER")
		.requestMatchers("/paybay/*").hasAuthority("ROLE_USER")
		.requestMatchers("/").hasAuthority("ROLE_USER").and()
				.formLogin().loginPage("/loginApp").permitAll().loginProcessingUrl("/login")
		        .defaultSuccessUrl("/home", true).and()
				.logout().logoutSuccessUrl("/loginApp?logout");
		
		http.cors();
		
		http
		.authorizeHttpRequests((requests) -> requests
			.requestMatchers("/paybay/model/insert", "/registrationApp", "/forgetApp", "/paybay/forget/password", "/paybay/forget", "/paybay/update/password").permitAll()
			.anyRequest().authenticated()
		);
		
		return http.build();
	}
	
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }
	
    @Bean
    public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
    }

}
