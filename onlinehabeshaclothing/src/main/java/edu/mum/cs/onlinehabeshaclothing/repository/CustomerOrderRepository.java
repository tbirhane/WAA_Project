package edu.mum.cs.onlinehabeshaclothing.repository;


import edu.mum.cs.onlinehabeshaclothing.model.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder,Long> {
    @Query("select distinct o from CustomerOrder o where o.user.id =:id")
   List<CustomerOrder> findAllOrdersByUserId(Long id);
}
