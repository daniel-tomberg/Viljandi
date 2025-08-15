package com.viljandi.repository;

import com.viljandi.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByClientId(String clientId);
    Order findByPoNumber(String poNumber);
}
