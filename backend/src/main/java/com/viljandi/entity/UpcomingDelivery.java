package com.viljandi.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "upcoming_deliveries")
public class UpcomingDelivery {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "category_name", nullable = false)
    private String categoryName;
    
    @Column(name = "additional_description")
    private String additionalDescription;
    
    @Column(name = "delivery_week", nullable = false)
    private Integer deliveryWeek;
    
    @Column(name = "client_id", nullable = false)
    private String clientId;


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
    
    public String getAdditionalDescription() { return additionalDescription; }
    public void setAdditionalDescription(String additionalDescription) { this.additionalDescription = additionalDescription; }
    
    public Integer getDeliveryWeek() { return deliveryWeek; }
    public void setDeliveryWeek(Integer deliveryWeek) { this.deliveryWeek = deliveryWeek; }
    
    public String getClientId() { return clientId; }
    public void setClientId(String clientId) { this.clientId = clientId; }
}
