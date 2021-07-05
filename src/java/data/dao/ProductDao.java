/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.dao;

import data.common.DbHelper;
import data.dto.ProductDto;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dangminhtien
 */
public class ProductDao {
   
    public List<ProductDto> getAll () throws ClassNotFoundException, SQLException {
        ArrayList<ProductDto> result;
        try (Connection con = DbHelper.connect()) {
            Statement searchStatement = con.createStatement();
            ResultSet dbResults = searchStatement.executeQuery("select * from PRODUCT");
            result = new ArrayList();
            ProductDto cached;
            while (dbResults.next()) {
                String name = dbResults.getString("NAME");
                String id = dbResults.getString("ID");
                String description = dbResults.getString("DESCRIPTION");
                double price = dbResults.getDouble("PRICE");
                cached = new ProductDto(id,name, description,price);
                result.add(cached);
            }
        }
       return result;
    }
}
