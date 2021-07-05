/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static common.Config.*;
import static common.Config.Action.*;

public class DispatcherController extends HttpServlet {

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        String button = req.getParameter("btAction");
        String url = LOGIN_PAGE;
        try {
            if (null == button || button.isEmpty()) {
                url = PROCESS_REQUEST_CONTROLLER;
            } else {
                switch (button) {
                    case LOGIN_ACTION:
                        url = LOGIN_CONTROLLER;
                        break;
                    case SEARCH_ACTION:
                        url = SEARCH_LASTNAME_CONTROLLER;
                        break;
                    case DELETE_ACTION:
                        url = DELETE_USER_CONTROLLER;
                        break;
                    case VERIFY_MAIL_ACTION:
                        url = VERIFIED_MAIL_PAGE;
                        break;
                    case SHOPPING_ACTION:
                        url = SHOPPING_CONTROLLER;
                        break;
                    case CONFIRM_VERIFY_CODE_ACTION:
                        url = CONFIRM_VERIFY_CODE_CONTROLLER;
                        break;
                    case SEND_VERIFY_CODE_ACTION:
                        url = SEND_VERIFY_CODE_CONTROLLER;
                        break;
                    case REGISTER_ACTION:
                        url = REGISTRATION_PAGE;
                        break;
                    case VIEW_USER_INFO_ACTION:
                        url = USER_INFO_PAGE;
                        break;
                    case CREATE_ACCOUNT_ACTION:
                        url = CREATE_ACCOUNT_CONTROLLER;
                        break;
                    case SIGNOUT_ACTION:
                        url = SIGNOUT_CONTROLLER;
                        break;
                    case ADD_ITEM_TO_CART_ACTION:
                        url = ADD_ITEM_TO_CART_CONTROLLER;
                        break;
                    case UPDATE_ACTION:
                        url = UPDATE_CONTROLLER;
                        break;
                    case VIEW_CART_ACTION:
                        url = VIEW_CART_PAGE;
                        break;
                    case REMOVE_CART_ITEM_ACTION:
                        url = REMOVE_CART_ITEM_CONTROLLER;
                        break;
                    case CHECKOUT_ACTION:
                        url = CHECKOUT_CONTROLLER;
                        break;
                    default:
                        break;
                }
            }
        } finally {
            RequestDispatcher dispatcher = req.getRequestDispatcher(url);
            dispatcher.forward(req, resp);
            out.close();
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
