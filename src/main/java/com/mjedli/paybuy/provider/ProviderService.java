/**
 * 
 */
package com.mjedli.paybuy.provider;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mjedli.paybuy.provider.model.Provider;
import com.mongodb.client.result.DeleteResult;

/**
 * @author mjedli
 *
 */
@Service
public class ProviderService {


	@Autowired
	private ProviderRepository providerRepository;
	
	public Provider addProvider(Provider provider) {
		return providerRepository.addProvider(provider);
	}

	public List<Provider> getSearchProvider(String value) {
		return providerRepository.getSearchProvider(value);
	}

	public Provider getProviderById(String id) {
		return providerRepository.getProviderById(id);
	}

	public Provider updateProvider(Provider provider) {
		return providerRepository.updateProvider(provider);
	}

	public DeleteResult removeProvider(Provider provider) {
		return providerRepository.removeProvider(provider);
	}

}
