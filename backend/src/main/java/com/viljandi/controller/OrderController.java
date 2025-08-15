package com.viljandi.controller;

import com.viljandi.entity.Order;
import com.viljandi.entity.OrderLine;
import com.viljandi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> getOrders() {
        List<Order> orders = orderService.getOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{poNumber}/lines")
    public ResponseEntity<List<OrderLine>> getOrderLines(@PathVariable String poNumber) {
        List<OrderLine> orderLines = orderService.getOrderLines(poNumber);
        return ResponseEntity.ok(orderLines);
    }
}
