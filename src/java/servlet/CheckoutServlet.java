/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import static common.Config.*;
import data.dao.OrderDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import servlet.sessionmodel.Cart;
import servlet.sessionmodel.CartItem;

/**
 *
 * @author dangminhtien
 */
public class CheckoutServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
             OrderDao orderDao = new OrderDao();
             HttpSession session = req.getSession(false);
             if (session != null) {
                 Cart cart = (Cart) session.getAttribute("CART");
                 
                 if (cart != null) {
                     Map<String, CartItem> items = cart.getAll();
                     if (!items.isEmpty()) {
                       for (String key : items.keySet()) {
                           orderDao.add(key,items.get(key).getQuantity());
                       }   
                     }
                     session.removeAttribute("CART");
                 }
             }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CheckoutServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CheckoutServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
           res.sendRedirect(SHOPPING_CONTROLLER);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
    
}
