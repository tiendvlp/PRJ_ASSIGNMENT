/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.dao;

import data.common.DbHelper;
import data.dto.OrderDetailDto;
import data.dto.OrderDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author dangminhtien
 */
public class OrderDao {

    public OrderDto add(OrderDto order) throws ClassNotFoundException, SQLException {
        int effectedRow = 0;
        try (Connection con = DbHelper.connect()) {
            PreparedStatement insertStatement = con.prepareStatement(
                    "INSERT INTO ORDERS ( ID, USEREMAIL, ADDRESS, PHONE, TOTAL_PRICE, ORDER_DATE, PAYMENT_STATUS ) VALUES ( ?, ?, ?, ?, ?, ?, ? )"
            );
            insertStatement.setString(1, order.getId());
            insertStatement.setString(2, order.getUserEmail());
            insertStatement.setString(3, order.getAddress());
            insertStatement.setString(4, order.getPhoneNumber());
            insertStatement.setDouble(5, order.getTotalPrice());
            insertStatement.setDate(6, order.getOrderDate());
            // always yes
            insertStatement.setInt(7, 1);
            effectedRow = insertStatement.executeUpdate();
        }
        if (effectedRow > 0) {
            if (addOrderDetails(order.getOrders()) != null) {
                return order;
            }
        }
        return null;
    }

    public List<OrderDetailDto> addOrderDetails(List<OrderDetailDto> orders) throws ClassNotFoundException, SQLException {
        int effectedRow = 0;
        try (Connection con = DbHelper.connect()) {
            PreparedStatement insertStatement = con.prepareStatement(
                    "INSERT INTO ORDERDETAIL (ID, ORDERID, PRODUCTID, QUANTITY, TOTAL_PRICE , STATUS) VALUES (?,?,?,?,?,?)"
            );
            OrderDetailDto cached;
            for (int i = 0; i < orders.size(); i++) {
                cached = orders.get(i);
                insertStatement.setString(1, cached.getId());
                insertStatement.setString(2, cached.getOrderId());
                insertStatement.setString(3, cached.getProductId());
                insertStatement.setInt(4, cached.getQuantity());
                insertStatement.setDouble(5, cached.getTotalPrice());
                insertStatement.setString(6, "1");
                if (insertStatement.executeUpdate() > 0) {
                    effectedRow++;
                }
            }
        }
        if (effectedRow == orders.size()) {
            return orders;
        }
        return null;
    }

}
