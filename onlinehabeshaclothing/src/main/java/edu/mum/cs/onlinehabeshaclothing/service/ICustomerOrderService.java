package edu.mum.cs.onlinehabeshaclothing.service;


import edu.mum.cs.onlinehabeshaclothing.model.CustomerOrder;

import java.util.List;

public interface ICustomerOrderService {
    public void save(CustomerOrder customerOrder);
    public CustomerOrder findById(Long id);
    public List<CustomerOrder> findAll();
    public void update(CustomerOrder customerOrder);
    public void delete(CustomerOrder customerOrder);
}
