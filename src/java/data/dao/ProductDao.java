/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.dao;

import data.common.DbHelper;
import data.dto.CategoryDto;
import data.dto.ProductDto;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import data.dto.*;
import java.sql.PreparedStatement;
/**
 *
 * @author dangminhtien
 */
public class ProductDao {

    public List<CategoryDto> getAllCategories() throws ClassNotFoundException, SQLException {
        ArrayList<CategoryDto> result;
        try (Connection con = DbHelper.connect()) {
            Statement queryStatement = con.createStatement();
            ResultSet dbResults = queryStatement.executeQuery("select ID, TITLE from CATEGORY");
            result = new ArrayList();
            CategoryDto cached;
            while (dbResults.next()) {
                String id = dbResults.getString("ID");
                String title = dbResults.getString("TITLE");
                cached = new CategoryDto(id, title);
                result.add(cached);
            }
        }
        return result;
    }

    public List<ProductDto> getAllProductByCategory(String productCategoryId) throws ClassNotFoundException, SQLException {
        ArrayList<ProductDto> result;
        try (Connection con = DbHelper.connect()) {
            PreparedStatement queryStatement = con.prepareStatement("SELECT PRODUCT.ID AS ID, TITLE AS CATEGORYTITLE, NAME, DESCRIPTION, CATEGORY AS CATEGORYID, PRICE, QUANTITY, LAST_UPDATE FROM PRODUCT INNER JOIN CATEGORY ON PRODUCT.CATEGORY = CATEGORY.ID WHERE PRODUCT.CATEGORY=?");
            queryStatement.setString(1, productCategoryId);
            ResultSet dbResults = queryStatement.executeQuery();
            result = new ArrayList();
            ProductDto cached;
            while (dbResults.next()) {
                String categoryId = dbResults.getString("CATEGORYID");
                String categoryTitle = dbResults.getString("CATEGORYTITLE");
                int quantity = dbResults.getInt("QUANTITY");
                String name = dbResults.getString("NAME");
                String id = dbResults.getString("ID");
                Date lastUpdate = dbResults.getDate("LAST_UPDATE");
                String description = dbResults.getString("DESCRIPTION");
                double price = dbResults.getDouble("PRICE");
                cached = new ProductDto(id, name, description, price, categoryTitle, categoryId, quantity, lastUpdate);
                result.add(cached);
            }
        }
        return result;
    }

    public List<ProductDto> getAll() throws ClassNotFoundException, SQLException {
        ArrayList<ProductDto> result;
        try (Connection con = DbHelper.connect()) {
            Statement searchStatement = con.createStatement();
            ResultSet dbResults = searchStatement.executeQuery("SELECT PRODUCT.ID AS ID, TITLE AS CATEGORYTITLE, NAME, DESCRIPTION, CATEGORY AS CATEGORYID, PRICE, QUANTITY, LAST_UPDATE FROM PRODUCT INNER JOIN CATEGORY ON PRODUCT.CATEGORY = CATEGORY.ID");
            result = new ArrayList();
            ProductDto cached;
            while (dbResults.next()) {
                String categoryId = dbResults.getString("CATEGORYID");
                String categoryTitle = dbResults.getString("CATEGORYTITLE");
                int quantity = dbResults.getInt("QUANTITY");
                String name = dbResults.getString("NAME");
                String id = dbResults.getString("ID");
                Date lastUpdate = dbResults.getDate("LAST_UPDATE");
                String description = dbResults.getString("DESCRIPTION");
                double price = dbResults.getDouble("PRICE");
                cached = new ProductDto(id, name, description, price, categoryTitle, categoryId, quantity, lastUpdate);
                result.add(cached);
            }
        }
        return result;
    }
}
