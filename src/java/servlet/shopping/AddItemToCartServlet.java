/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.shopping;

import servlet.common.sessionmodel.Cart;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static common.Config.*;
import common.ResouceDynamicMapping;
import java.util.HashMap;
import servlet.common.error.AddToCartError;

public class AddItemToCartServlet extends HttpServlet {

    private void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String selectedCategories = req.getParameter("cboCategory");
        boolean hasError = false;
        AddToCartError error = null;
        ResouceDynamicMapping resourceMapping = (ResouceDynamicMapping) req.getServletContext().getAttribute(ResouceDynamicMapping.KEY);
        HashMap<String, String> roadMap = resourceMapping.getRoadMap();
        try {
            HttpSession session = req.getSession(true);
            Cart cart = (Cart) session.getAttribute("CART");
            if (cart == null) {
                cart = new Cart();
            }
            String txtProductName = req.getParameter("txtProductName");
            String txtProductId = req.getParameter("txtProductId");
            String txtQuantity = req.getParameter("txtProductQuantity");
            String txtPrice = req.getParameter("txtProductPrice");

            error = new AddToCartError(txtProductId);

            int quantity = Integer.parseInt(txtQuantity);

            double price = Double.parseDouble(txtPrice);
            cart.addItemToCart(txtProductId, txtProductName, quantity, price);
            session.setAttribute("CART", cart);

        } catch (NumberFormatException ex) {
            hasError = true;
            error.setIncorectQuantity();
            log("Add item to cart error due to: " + ex.getMessage());
        } finally {
            if (hasError) {
                req.setAttribute("UERROR", error);
                if (selectedCategories != null) {
                    req.setAttribute("cboCategory", selectedCategories);
                }
                req.getRequestDispatcher(roadMap.get(GET_SHOPPING_PRODUCT_CONTROLLER)).forward(req, res);
            } else {
                if (selectedCategories == null) {
                    res.sendRedirect(SHOPPING_PAGE);
                } else {
                    res.sendRedirect(SHOPPING_PAGE + "?cboCategory=" + selectedCategories);
                }
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
