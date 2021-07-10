/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.cart;

import static common.Config.*;
import static common.Config.Action.*;
import common.ResouceDynamicMapping;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author dangminhtien
 */
public class CartDispatcher extends HttpServlet {

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = req.getServletContext();
        HttpSession session = req.getSession();
        ResouceDynamicMapping resourceMapping = (ResouceDynamicMapping) context.getAttribute(ResouceDynamicMapping.KEY);
        HashMap<String, String> roadMap = resourceMapping.getRoadMap();

        String url = "/";

        String btAction = req.getParameter("btAction");
        if (btAction == null) {
            url = roadMap.get(CART_VIEW);
        } else {
            switch (btAction) {
                case CHECKOUT_ACTION:
                    url = roadMap.get(CHECKOUT_CONTROLLER);
                    break;
                case REMOVE_CART_ITEM_ACTION:
                    url = roadMap.get(REMOVE_CART_ITEM_CONTROLLER);
                    break;
                default:
                    url = roadMap.get(CART_VIEW);
                    break;
            }
        }
        req.getRequestDispatcher(url).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

}
