/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.dto;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author dangminhtien
 */
public class OrderDto {
    private String id;
    private String userEmail;
    private String address;
    private String phoneNumber;
    private double totalPrice;
    private Date orderDate;
    private boolean paymentStatus;
    private String productId;
    private int quantity;
    private List<OrderDetailDto> orders;
    
    public OrderDto(String id, String userEmail, String address, String phoneNumber, double totalPrice, Date orderDate, boolean paymentStatus, String productId, int quantity, List<OrderDetailDto> orders) {
        this.id = id;
        this.userEmail = userEmail;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.paymentStatus = paymentStatus;
        this.productId = productId;
        this.quantity = quantity;
    }

    public List<OrderDetailDto> getOrders() {
        return orders;
    }
    
    public String getId() {
        return id;
    }

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public boolean isPaymentStatus() {
        return paymentStatus;
    }
    
    
}
