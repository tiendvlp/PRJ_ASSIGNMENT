/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.sessionmodel;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author dangminhtien
 */
public class Cart implements Serializable {
    private HashMap<String, CartItem> items = new HashMap();
    
    public void addItemToCart (String id, String productName) {
        if (id == null || id.isEmpty()) {
            return;
        }
        
        CartItem item = items.get(id);
        if (item == null) {
            item = new CartItem(productName, 0);
            this.items.put(id, item);
        }
        item.addQuantity();
        
    }
    
    public Map<String, CartItem> getAll () {
        return this.items;
    }
    
    public void removeItem (String id) {
        if (id == null || id.trim().isEmpty()) {
            return;
        }
        items.remove(id);
    }
}
