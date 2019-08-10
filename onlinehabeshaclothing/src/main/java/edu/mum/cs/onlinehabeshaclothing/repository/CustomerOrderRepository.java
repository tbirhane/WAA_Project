package edu.mum.cs.onlinehabeshaclothing.repository;


import edu.mum.cs.onlinehabeshaclothing.model.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder,Long> {
}
