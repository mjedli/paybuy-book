/**
 * 
 */
package com.mjedli.paybuy.stock;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mjedli.paybuy.customer.CutsomerRepository;
import com.mjedli.paybuy.stock.model.Product;
import com.mongodb.client.result.DeleteResult;

/**
 * @author mjedli
 *
 */
@Service
public class StockService {

	@Autowired
	private StockRepository stockRepository;
	
	public Product addProduct(Product product) {
		return stockRepository.addProduct(product);
	}

	public List<Product> getSearchStock(String value) {
		return stockRepository.getSearchStock(value);
	}

	public Product getProductById(String id) {
		return stockRepository.getProductById(id);
	}

	public Product updateProduct(Product product) {
		return stockRepository.updateProduct(product);
	}

	public DeleteResult removeProduct(Product product) {
		return stockRepository.removeProduct(product);
	}

	public List<Product> getSearchEmptyProduct() {
		return stockRepository.getSearchEmptyProduct();
	}

}
