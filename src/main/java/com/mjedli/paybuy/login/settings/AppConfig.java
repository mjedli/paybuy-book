/**
 * 
 */
package com.mjedli.paybuy.login.settings;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author mjedli
 *
 */

@Configuration
public class AppConfig {
    
    @Bean
    public WebMvcConfigurer forwardToIndex() {
        return new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/loginApp").setViewName("login/login");
                registry.addViewController("/registrationApp").setViewName("login/registration");
                registry.addViewController("/forgetApp").setViewName("login/forget");
                
            }
        };
    }
    
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
        
}