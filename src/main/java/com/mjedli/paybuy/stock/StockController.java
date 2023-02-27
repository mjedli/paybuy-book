/**
 * 
 */
package com.mjedli.paybuy.stock;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mjedli.paybuy.SequenceGeneratorService;
import com.mjedli.paybuy.customer.model.Customer;
import com.mjedli.paybuy.stock.model.Product;

/**
 * @author mjedli
 *
 */
@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"})
public class StockController {

	private static final String HREF_BASE = "/paybay";
	
	@Autowired
	private StockService stockService;
	
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;
	
	@PostMapping(value = HREF_BASE + "/stock/add")
	@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"})
	private Product addProduct(@RequestBody Product product) {
		
		product.setId(sequenceGeneratorService.generateSequence(Product.SEQUENCE_NAME));
		
		return stockService.addProduct(product);
	
	}
	
	@PostMapping(value = HREF_BASE + "/stock")
	@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"})
	private List<Product> getSearchStock(@RequestBody String value) {

		return stockService.getSearchStock(value);
	
	}
	
	@PostMapping(value = HREF_BASE + "/stock/notempty")
	@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"})
	private List<Product> getSearchStockNotEmpty(@RequestBody String value) {

		List<Product> resultTemp = stockService.getSearchStock(value);
		
		List<Product> result = new ArrayList<Product>();
		
		for(Product product : resultTemp) {
			
			if(Integer.valueOf(product.getAmount()) > 0) {
				result.add(product);
			}
		}
		
		return result;
	}
	
	@GetMapping(value = HREF_BASE + "/stock/empty")
	@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"})
	private List<Product> getSearchEmptyProduct() {

		return stockService.getSearchEmptyProduct();
	
	}
	
	@GetMapping(value = HREF_BASE + "/stock/{id}")
	@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"})
	private Product getProductById(@PathVariable String id) {

		return stockService.getProductById(id);
	
	}
	
	@PostMapping(value = HREF_BASE + "/stock/update")
	@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"})
	private Product updateProduct(@RequestBody Product product) {

		return stockService.updateProduct(product);
	
	}
	
	@PostMapping(value = HREF_BASE + "/stock/remove")
	@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"})
	private int removeProduct(@RequestBody Product product) {

		if(stockService.removeProduct(product).getDeletedCount()==1) {
			return 1;
		};
		
		return 0;
	
	}
	
}
