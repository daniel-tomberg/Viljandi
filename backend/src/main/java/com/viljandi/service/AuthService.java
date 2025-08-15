package com.viljandi.service;

import com.viljandi.dto.LoginRequest;
import com.viljandi.dto.LoginResponse;
import com.viljandi.entity.Client;
import com.viljandi.repository.ClientRepository;
import com.viljandi.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    
    @Autowired
    private ClientRepository clientRepository;
    
    public LoginResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
            )
        );
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        Client client = clientRepository.findByUsername(loginRequest.getUsername())
            .orElseThrow(() -> new RuntimeException("Client not found"));
        
        String token = jwtTokenProvider.generateToken(authentication);
        
        LoginResponse response = new LoginResponse();
        response.setToken(token);
        response.setStoreName(client.getStoreName());
        response.setClientId(client.getClientId());
        
        return response;
    }
}
