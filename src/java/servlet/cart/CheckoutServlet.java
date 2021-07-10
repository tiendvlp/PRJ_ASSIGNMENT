/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.cart;

import static common.Config.*;
import common.ResouceDynamicMapping;
import data.dao.OrderDao;
import data.dto.OrderDetailDto;
import data.dto.OrderDto;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import servlet.common.error.errorvalidator.CheckoutReqHandler;
import servlet.common.sessionmodel.Cart;
import servlet.common.sessionmodel.CartItem;
import java.util.UUID;
import sendmail.CheckoutEmail;
import sendmail.CheckoutEmail.OrderConfirm;
import sendmail.MailSenderApi;

/**
 *
 * @author dangminhtien
 */
public class CheckoutServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        CheckoutReqHandler reqHandler = new CheckoutReqHandler();
        reqHandler.init(req);
        String htmlSuccessRes = "<h1>Thank you for your order</h1></br>";
        ResouceDynamicMapping resourceMapping = (ResouceDynamicMapping) req.getServletContext().getAttribute(ResouceDynamicMapping.KEY);
        HashMap<String, String> roadMap = resourceMapping.getRoadMap();
        try {
            if (!reqHandler.hasError()) {
                OrderDao orderDao = new OrderDao();
                HttpSession session = req.getSession(false);
                if (session != null) {
                    Cart cart = (Cart) session.getAttribute("CART");
                    // create order
                    double totalOrderPrice = 0d;
                    String orderId = UUID.randomUUID().toString();
                    ArrayList<OrderDetailDto> orderDetails = new ArrayList();

                    List<OrderConfirm> ordersMail = new ArrayList();

                    if (cart != null) {
                        Map<String, CartItem> items = cart.getAll();
                        if (!items.isEmpty()) {
                            for (CartItem item : items.values()) {
                                totalOrderPrice += item.getPrice() * item.getQuantity();
                                String orderDetailId = UUID.randomUUID().toString();
                                orderDetails.add(new OrderDetailDto(orderDetailId, orderId, item.getQuantity(), item.getId(), item.getPrice() * item.getQuantity(), OrderDetailDto.Status.PENDING));

                                ordersMail.add(new OrderConfirm(item.getProductName(), item.getPrice(), item.getQuantity()));
                            }
                        }
                        session.removeAttribute("CART");

                        CheckoutEmail mail = new CheckoutEmail(ordersMail, totalOrderPrice, reqHandler.getTxtEmail());
                        MailSenderApi sendMail = new MailSenderApi();
                        htmlSuccessRes = "<h1>Thank you for your order please check your email to see more detail</h1></br>";
                        htmlSuccessRes += mail.getHtml();
                        sendMail.send(mail);
                    }

                    OrderDto orderDto = new OrderDto(orderId, reqHandler.getTxtEmail(), reqHandler.getTxtAddress(), reqHandler.getTxtPhoneNumber(), totalOrderPrice, new Date(System.currentTimeMillis()), true, orderDetails);
                    orderDao.add(orderDto);

                }
            }
        } catch (ClassNotFoundException ex) {
            log("CheckoutServlet error due to: " + ex.getMessage());
        } catch (SQLException ex) {
            log("CheckoutServlet error due to: " + ex.getMessage());
        } finally {
            if (reqHandler.hasError()) {
                req.setAttribute("UERROR", reqHandler.getError());
                req.getRequestDispatcher(roadMap.get(CART_VIEW)).forward(req, res);
            } else {
                // forward to success page
                PrintWriter printer = res.getWriter();
                htmlSuccessRes += "</br>" + "<a href=\"dispatchercontroller\">back</a>";
                printer.print(htmlSuccessRes);
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
