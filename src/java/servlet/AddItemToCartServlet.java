/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import servlet.sessionmodel.Cart;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static common.Config.*;

public class AddItemToCartServlet extends HttpServlet {

    private void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String selectedCategories = req.getParameter("cboCategory");
        try {
            HttpSession session = req.getSession(true);
            Cart cart = (Cart) session.getAttribute("CART");
            if (cart == null) {
                cart = new Cart();
            }
            String value = req.getParameter("cboBook");
            log(value);
            cart.addItemToCart(value.split("/")[0], value.split("/")[1]);
            session.setAttribute("CART", cart);
        } finally {
            if (selectedCategories == null) {
                log("Selected null");
                res.sendRedirect(getShoppingOnlineUrl());
            } else {
                res.sendRedirect(getShoppingOnlineUrl() + "&cboCategory=" + selectedCategories);
            }
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
