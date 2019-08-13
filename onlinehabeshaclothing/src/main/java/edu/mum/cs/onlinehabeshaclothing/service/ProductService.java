package edu.mum.cs.onlinehabeshaclothing.service;


import edu.mum.cs.onlinehabeshaclothing.model.Product;
import edu.mum.cs.onlinehabeshaclothing.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public void save(Product product){
        productRepository.save(product);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public void update(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);

    }

//    @Override
//    public byte[] getPhotoById(Long id) {
//        //return null;
//        return productRepository.getPhotoById(id);
//    }


}
