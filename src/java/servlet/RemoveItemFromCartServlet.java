/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;
    
import servlet.sessionmodel.Cart;
import static common.Config.*;
import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import servlet.sessionmodel.CartItem;

/**
 *
 * @author dangminhtien
 */
public class RemoveItemFromCartServlet extends HttpServlet {
    private void processRequest (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        try {
             HttpSession session = req.getSession(false);
             if (session != null) {
                 Cart cart = (Cart) session.getAttribute("CART");
                 
                 if (cart != null) {
                     Map<String, CartItem> items = cart.getAll();
                     if (!items.isEmpty()) {
                         String [] removedItems = req.getParameterValues("chkItem");
                         if (removedItems != null) {
                             for (String key : removedItems) {
                                 cart.removeItem(key);
                             }
                         }
                     }
                     session.setAttribute("CART", cart);
                 }
             }
        } finally {
           res.sendRedirect(getViewCartUrl());
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