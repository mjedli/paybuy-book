/**
 * 
 */
package com.mjedli.paybuy.provider;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mjedli.paybuy.SequenceGeneratorService;
import com.mjedli.paybuy.provider.model.Provider;

/**
 * @author mjedli
 *
 */
@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"})
public class ProviderController {
	
	private static final String HREF_BASE = "/paybay";
	
	@Autowired
	private ProviderService providerService;
	
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;
	
	@PostMapping(value = HREF_BASE + "/provider/add")
	@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"})
	private Provider addProvider(@RequestBody Provider provider) {
		
		provider.setId(sequenceGeneratorService.generateSequence(Provider.SEQUENCE_NAME));
		
		return providerService.addProvider(provider);
	
	}
	
	@PostMapping(value = HREF_BASE + "/provider")
	@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"})
	private List<Provider> getSearchProvider(@RequestBody String value) {

		return providerService.getSearchProvider(value);
	
	}
	
	@GetMapping(value = HREF_BASE + "/provider/{id}")
	@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"})
	private Provider getProviderById(@PathVariable String id) {

		return providerService.getProviderById(id);
	
	}
	
	@PostMapping(value = HREF_BASE + "/provider/update")
	@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"})
	private Provider updateCustomer(@RequestBody Provider provider) {

		return providerService.updateProvider(provider);
	
	}
	
	@PostMapping(value = HREF_BASE + "/provider/remove")
	@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"})
	private int removeProvider(@RequestBody Provider provider) {

		if(providerService.removeProvider(provider).getDeletedCount()==1) {
			return 1;
		};
		
		return 0;
	
	}
}
