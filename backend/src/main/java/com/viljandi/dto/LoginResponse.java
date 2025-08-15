package com.viljandi.dto;

public class LoginResponse {
    private String token;
    private String storeName;
    private String clientId;


    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    
    public String getStoreName() { return storeName; }
    public void setStoreName(String storeName) { this.storeName = storeName; }
    
    public String getClientId() { return clientId; }
    public void setClientId(String clientId) { this.clientId = clientId; }
}
