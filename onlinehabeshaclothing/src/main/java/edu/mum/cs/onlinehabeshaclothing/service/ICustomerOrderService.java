package edu.mum.cs.onlinehabeshaclothing.service;


import edu.mum.cs.onlinehabeshaclothing.model.CustomerOrder;

import java.util.List;

public interface ICustomerOrderService {
     void save(CustomerOrder customerOrder);
     CustomerOrder findById(Long id);
     List<CustomerOrder> findAll();
     void update(CustomerOrder customerOrder);
     void delete(CustomerOrder customerOrder);
     List<CustomerOrder> findAllOrdersByUserId(Long id);
}
