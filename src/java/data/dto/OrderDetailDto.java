/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.dto;

/**
 *
 * @author dangminhtien
 */
public class OrderDetailDto {
    public enum Status {
        PENDING, SUCCESS, FAILED
    }
    private String id;
    private String orderId;
    private int quantity;
    private String productId;
    private double totalPrice;
    private Status status;

    public OrderDetailDto(String id, String orderId, int quantity, String productId, double totalPrice, Status status) {
        this.id = id;
        this.orderId = orderId;
        this.quantity = quantity;
        this.productId = productId;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getOrderId() {
        return orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getProductId() {
        return productId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public Status getStatus() {
        return status;
    }
    
    
    
}
