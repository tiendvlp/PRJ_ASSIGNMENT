/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.dto;

import java.sql.Date;

/**
 *
 * @author dangminhtien
 */
public class ProductDto {

    private String id;
    private String name;
    private String description;
    private double price;
    private String categoryTitle;
    private String categoryId;
    private int quantity;
    private Date lastUpdate;

    public ProductDto(String id, String name, String description, double price, String categoryTitle, String categoryId, int quantity, Date lastUpdate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.categoryTitle = categoryTitle;
        this.categoryId = categoryId;
        this.quantity = quantity;
        this.lastUpdate = lastUpdate;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public int getQuantity() {
        return quantity;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

}
