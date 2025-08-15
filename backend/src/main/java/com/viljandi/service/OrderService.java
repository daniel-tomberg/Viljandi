package com.viljandi.service;

import com.viljandi.entity.Order;
import com.viljandi.entity.OrderLine;
import com.viljandi.repository.OrderRepository;
import com.viljandi.repository.OrderLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private OrderLineRepository orderLineRepository;
    
    @Autowired
    private ClientService clientService;

    public List<Order> getOrders() {
        String clientId = clientService.getCurrentClientId();
        return orderRepository.findByClientId(clientId);
    }

    public List<OrderLine> getOrderLines(String poNumber) {

        Order order = orderRepository.findByPoNumber(poNumber);
        if (order == null || !order.getClientId().equals(clientService.getCurrentClientId())) {
            throw new RuntimeException("Order not found or access denied");
        }
        
        return orderLineRepository.findByPoNumber(poNumber);
    }
}
