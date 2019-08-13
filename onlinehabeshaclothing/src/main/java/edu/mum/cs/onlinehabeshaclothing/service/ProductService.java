package edu.mum.cs.onlinehabeshaclothing.service;

import edu.mum.cs.onlinehabeshaclothing.model.Product;

import java.util.List;



public interface ProductService {
	List<Product> getProducts();

	void saveProduct(Product product);

	Product getProduct(Long id);

	void deleteProduct(Long id);
	Product updateProduct(Product product);

}
