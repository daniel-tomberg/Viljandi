package com.viljandi.service;

import com.viljandi.entity.UpcomingDelivery;
import com.viljandi.repository.UpcomingDeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryService {

    @Autowired
    private UpcomingDeliveryRepository deliveryRepository;
    
    @Autowired
    private ClientService clientService;

    public List<UpcomingDelivery> getUpcomingDeliveries() {
        String clientId = clientService.getCurrentClientId();
        return deliveryRepository.findByClientId(clientId);
    }
}
