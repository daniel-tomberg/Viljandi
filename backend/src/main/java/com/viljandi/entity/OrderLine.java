package com.viljandi.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "order_lines")
public class OrderLine {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "order_num", nullable = false)
    private Long orderNum;
    
    @Column(nullable = false)
    private Integer line;
    
    @Column(name = "product_code", nullable = false)
    private String productCode;
    
    @Column(name = "ean_code", nullable = false)
    private String eanCode;
    
    @Column(name = "product_name", nullable = false)
    private String productName;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal qty;
    
    @Column(name = "delivery_date", nullable = false)
    private LocalDate deliveryDate;
    
    @Column(name = "po_number", nullable = false)
    private String poNumber;


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getOrderNum() { return orderNum; }
    public void setOrderNum(Long orderNum) { this.orderNum = orderNum; }
    
    public Integer getLine() { return line; }
    public void setLine(Integer line) { this.line = line; }
    
    public String getProductCode() { return productCode; }
    public void setProductCode(String productCode) { this.productCode = productCode; }
    
    public String getEanCode() { return eanCode; }
    public void setEanCode(String eanCode) { this.eanCode = eanCode; }
    
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    
    public BigDecimal getQty() { return qty; }
    public void setQty(BigDecimal qty) { this.qty = qty; }
    
    public LocalDate getDeliveryDate() { return deliveryDate; }
    public void setDeliveryDate(LocalDate deliveryDate) { this.deliveryDate = deliveryDate; }
    
    public String getPoNumber() { return poNumber; }
    public void setPoNumber(String poNumber) { this.poNumber = poNumber; }
}
