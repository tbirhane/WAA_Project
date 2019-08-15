package edu.mum.cs.onlinehabeshaclothing.service;


import edu.mum.cs.onlinehabeshaclothing.model.CustomerOrder;
import edu.mum.cs.onlinehabeshaclothing.repository.CustomerOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerOrderService implements ICustomerOrderService {
    @Autowired
    private CustomerOrderRepository customerOrderRepository;
    @Override
    public void save(CustomerOrder customerOrder) {
        customerOrderRepository.save(customerOrder);
    }

    @Override
    public CustomerOrder findById(Long id) {
        return customerOrderRepository.findById(id).get();
    }

    @Override
    public List<CustomerOrder> findAll() {
        return (List<CustomerOrder>) customerOrderRepository.findAll();
    }

    @Override
    public void update(CustomerOrder customerOrder) {
        customerOrderRepository.save(customerOrder);
    }

    @Override
    public void delete(CustomerOrder customerOrder) {
        customerOrderRepository.delete(customerOrder);
    }

    @Override
    public List<CustomerOrder> findAllOrdersByUserId(Long id) {
        return customerOrderRepository.findAllOrdersByUserId(id);
    }

}
