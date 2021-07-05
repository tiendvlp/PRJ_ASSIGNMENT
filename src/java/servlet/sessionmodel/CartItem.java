/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.sessionmodel;

/**
 *
 * @author dangminhtien
 */
public class CartItem {
    private String productName;
    private int quantity;

    public CartItem(String productName, int quantity) {
        this.productName = productName;
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }
    
    public void addQuantity () {
        quantity += 1;
    }
}
