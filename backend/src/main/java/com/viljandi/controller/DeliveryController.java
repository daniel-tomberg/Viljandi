package com.viljandi.controller;

import com.viljandi.entity.UpcomingDelivery;
import com.viljandi.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deliveries")
@CrossOrigin(origins = "*")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @GetMapping("/upcoming")
    public ResponseEntity<List<UpcomingDelivery>> getUpcomingDeliveries() {
        List<UpcomingDelivery> deliveries = deliveryService.getUpcomingDeliveries();
        return ResponseEntity.ok(deliveries);
    }
}
