package com.viljandi.service;

import com.viljandi.entity.Client;
import com.viljandi.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public String getCurrentClientId() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Client client = clientRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        return client.getClientId();
    }

    public Client getCurrentClient() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return clientRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Client not found"));
    }
}
