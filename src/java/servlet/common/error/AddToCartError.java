/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.common.error;

/**
 *
 * @author dangminhtien
 */
public class AddToCartError implements PresentableError {
    private String overQuantity;
    private String incorectQuantity;
    private String productId;
    
    public AddToCartError (String productId) {
        this.productId = productId;
    }
    
    public AddToCartError(String productId,String overQuantity, String incorectQuantity) {
        this.overQuantity = overQuantity;
        this.productId = productId;
        this.incorectQuantity = incorectQuantity;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
    
    public String getOverQuantity() {
        return overQuantity;
    }

    public void setOverQuantity() {
        this.overQuantity = "Insufficient inventory to supply";
    }

    public String getIncorectQuantity() {
        return incorectQuantity;
    }

    public void setIncorectQuantity() {
        this.incorectQuantity = "Invalid format";
    }
    
}
