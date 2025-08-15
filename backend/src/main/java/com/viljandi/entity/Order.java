package com.viljandi.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "orders")
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "po_number", nullable = false, unique = true)
    private String poNumber;
    
    @Column(name = "order_date", nullable = false)
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate orderDate;
    
    @Column(name = "expected_delivery", nullable = false)
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate expectedDelivery;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;
    
    @Column(name = "client_id", nullable = false)
    private String clientId;
    
    public enum OrderStatus {
        PROCESSING, DELIVERED, CANCELLED
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getPoNumber() { return poNumber; }
    public void setPoNumber(String poNumber) { this.poNumber = poNumber; }
    
    public LocalDate getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDate orderDate) { this.orderDate = orderDate; }
    
    public LocalDate getExpectedDelivery() { return expectedDelivery; }
    public void setExpectedDelivery(LocalDate expectedDelivery) { this.expectedDelivery = expectedDelivery; }
    
    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }
    
    public String getClientId() { return clientId; }
    public void setClientId(String clientId) { this.clientId = clientId; }
}
