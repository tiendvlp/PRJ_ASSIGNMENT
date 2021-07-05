/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.dao;

import data.common.DbHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author dangminhtien
 */
public class OrderDao {

    public void add (String productId, int quantity) throws ClassNotFoundException, SQLException {
        try (Connection con = DbHelper.connect()) {
            PreparedStatement insertStatement;
            insertStatement = con.prepareStatement("INSERT INTO ORDERS VALUES (?, ?, ?)");
            insertStatement.setString(1, System.currentTimeMillis() + "");
            insertStatement.setString(2, productId);
            insertStatement.setInt(3, quantity);
            insertStatement.executeUpdate();
        }
    }
}
