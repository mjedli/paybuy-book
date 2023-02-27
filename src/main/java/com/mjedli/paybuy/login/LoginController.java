/**
 * 
 */
package com.mjedli.paybuy.login;

import java.security.Principal;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mjedli.paybuy.login.model.LoginPojo;
import com.mjedli.paybuy.login.model.HomeObject;
import com.mjedli.paybuy.login.model.LoginObject;


/**
 * @author mjedli
 *
 */
@Controller
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"})
public class LoginController {

	
	private static final String HREF_BASE = "/paybay";

	@Autowired
	ParismonService parismonService;
	
	@Value( "${server.address}" )
	String server;
	
	@Value( "${server.port}" )
	String port;
	
	@Autowired
	private JavaMailSender emailSender;
	
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@GetMapping(value = "/logout")
	public String logoutPage () {
		SecurityContextHolder.getContext().setAuthentication(null);
	    return "redirect:/loginApp?logout";
	}
	
	@GetMapping(value = "/")
	private String start() {
		return "paybuy/index";
	}
	
	@GetMapping(value = "/home")
	private String login(Principal principal, Model modelMap) {
		if(principal == null) {
			return HREF_BASE + "/login";
		}
		modelMap.addAttribute("username", principal.getName());
		
		//String idBarber = parismonService.findParismonByMail(principal.getName()).getId();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM, dd yyyy");
		
		HomeObject homeObject = new HomeObject();
		
		modelMap.addAttribute("homeObject", homeObject);
		
		return "paybuy/index";
	}
	
	@GetMapping(value= HREF_BASE + "/regisration/{id}")
	private String findBarberByActiveToken(@PathVariable String id) {
		LoginPojo parismon = parismonService.findParismonByActiveToken(id);
		parismon.setActive(true);
		parismonService.updateParismon(parismon);
		return HREF_BASE + "/login";
	}
	
	@PostMapping(value = HREF_BASE + "/model/insert")
	private String insertBarberModel(@ModelAttribute LoginObject barber1, Model modelMap) {
		try {
			
		if(!barber1.getPassword().equals(barber1.getRepassword())) {
			modelMap.addAttribute("passworderror", "passworderror");
			return "/login/registration";
		}
		
		LoginPojo barber = new LoginPojo();
		barber.setEmail(barber1.getEmail());
		barber.setPassword(barber1.getPassword());
		
		// TODO Bouchon to remove
		barber.setActive(true);
		
		MimeMessage mimeMessage = emailSender.createMimeMessage();
		MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "utf-8"); 
        
		message.setFrom("noreply@barber.com");
        message.setTo(barber.getEmail()); 
        message.setSubject("Validation email"); 
        message.setReplyTo("noreply@barber.com");
        

        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 50;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int) 
              (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        
        String generatedString = buffer.toString();
        barber.setActiveMailToken(generatedString);
       
        StringBuilder b = new StringBuilder();
        b.append("<a href='http://");
        b.append(server);
        b.append(":");
        b.append(port);
        b.append("/login/regisration/");
        b.append(generatedString);
        b.append("'>");
        b.append("Active my barber a count!");
        b.append("</a>");
        message.setText(b.toString(), true);
        
		LoginPojo barberTemp = parismonService.insertParismon(barber);
		
		if("exist".equals(barberTemp.getEmail())) {
			modelMap.addAttribute("exist", "exist");
			return "/login/registration";
		} else {
			// TODO reactive the mail sender
			//emailSender.send(mimeMessage);
		}
		
		return "active";
		
		} catch(Exception e) {
			return null;
		}
	}
	
	@GetMapping(value= HREF_BASE + "/forgetpassword/{id}")
	private String forgetPassword(@PathVariable String id, ModelMap modelMap) {
		LoginPojo barber = parismonService.findParismonByActiveToken(id);
		modelMap.addAttribute("barber", barber);
		if(barber != null && id.equals(barber.getActiveMailToken())) {
			return HREF_BASE + "/password";
		} else {
			return HREF_BASE + "/forget";
		}
	}
	
	@PostMapping(value = HREF_BASE + "/update/password")
	private String updatePassword(@ModelAttribute LoginPojo barber) {
        LoginPojo barber1 = parismonService.findParismonByMail(barber.getEmail());
        if(barber1 != null && barber1.getActiveMailToken().equals(barber.getActiveMailToken())) {
	        barber1.setPassword(barber.getPassword());
	        barber1.setActiveMailToken("");
			parismonService.updateParismon(barber1);
			return HREF_BASE + "/login";
        } else {
        	return HREF_BASE + "/forget";
        }
	}
	
	@PostMapping(value = HREF_BASE + "/forget/password")
	private String forgetPassword(@ModelAttribute LoginPojo barber) {
		try {

        LoginPojo barber1 = parismonService.findParismonByMail(barber.getEmail());
        
        if(barber1 != null && barber1.getEmail().equals(barber.getEmail())) {
        
			MimeMessage mimeMessage = emailSender.createMimeMessage();
			MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "utf-8");
		
            int leftLimit = 97; // letter 'a'
            int rightLimit = 122; // letter 'z'
            int targetStringLength = 50;
            Random random = new Random();
            StringBuilder buffer = new StringBuilder(targetStringLength);
            for (int i = 0; i < targetStringLength; i++) {
                int randomLimitedInt = leftLimit + (int) 
                  (random.nextFloat() * (rightLimit - leftLimit + 1));
                buffer.append((char) randomLimitedInt);
            }
            
            String generatedString = buffer.toString();
            barber.setActiveMailToken(generatedString);
           
            StringBuilder b = new StringBuilder();
            b.append("<a href='http://");
            b.append(server);
            b.append(":");
            b.append(port);
            b.append(HREF_BASE + "/forgetpassword/");
            b.append(generatedString);
            b.append("'>");
            b.append("Active my barber a count!");
            b.append("</a>");
            message.setText(b.toString(), true);
            
            message.setFrom("noreply@barber.com");
            message.setTo(barber.getEmail()); 
            message.setSubject("Validation email"); 
            message.setReplyTo("noreply@barber.com");
            
            emailSender.send(mimeMessage);
        	
	        barber1.setActiveMailToken(generatedString);
	        
			parismonService.updateParismon(barber1);
			
			return "active";
			
        } else 
        	return HREF_BASE + "/forget";
		} catch(Exception e) {
			return null;
		}
	}
}
