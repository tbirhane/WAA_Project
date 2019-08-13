package edu.mum.cs.onlinehabeshaclothing.service;


import edu.mum.cs.onlinehabeshaclothing.model.Product;

import java.util.List;

public interface IProductService {
    public void save(Product product);
    public Product findById(Long id);
    public List<Product> findAll();
    public void update(Product product);
    public void deleteById(Long id);
    //public byte[] getPhotoById(Long id);
}
