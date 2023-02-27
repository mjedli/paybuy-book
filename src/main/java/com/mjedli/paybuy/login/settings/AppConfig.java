/**
 * 
 */
package com.mjedli.paybuy.login.settings;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author mjedli
 *
 */

@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/loginApp").setViewName("login/login");
        registry.addViewController("/registrationApp").setViewName("login/registration");
        registry.addViewController("/forgetApp").setViewName("login/forget");
        
    }
        
}