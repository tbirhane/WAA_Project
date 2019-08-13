package edu.mum.cs.onlinehabeshaclothing.service;

import java.util.List;

import edu.mum.cs.onlinehabeshaclothing.model.Product;
import edu.mum.cs.onlinehabeshaclothing.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ProductServiceImpl implements ProductService {
  @Autowired
  ProductRepository productrepo;
	@Override
	public List<Product> getProducts() {
		
		return (List<Product> )productrepo.findAll();
	}

	@Override
	public void saveProduct(Product product) {
		productrepo.save(product);
	}

	@Override
	public Product getProduct(Long id) {
		
		return productrepo.findById(id).get();
	}

	@Override
	public void deleteProduct(Long id) {
		// TODO Auto-generated method stub
		productrepo.deleteById(id);
	}

	@Override
	public Product updateProduct(Product product) {
		
		return productrepo.save(product);
	}

}
