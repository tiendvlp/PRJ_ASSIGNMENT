/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.shopping;

import static common.Config.Action.*;
import static common.Config.*;
import common.ResouceDynamicMapping;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import servlet.common.sessionmodel.SelectedUserSessionModel;
import servlet.common.sessionmodel.UserSessionModel;

/**
 *
 * @author dangminhtien
 */
public class ShoppingDispatcher extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = request.getServletContext();
        HttpSession session = request.getSession();
        ResouceDynamicMapping resourceMapping = (ResouceDynamicMapping) context.getAttribute(ResouceDynamicMapping.KEY);
        HashMap<String, String> roadMap = resourceMapping.getRoadMap();

        String btAction = request.getParameter("btAction");
        log("SearchPage: btAction=" + btAction);
        log("Co attribute: " + request.getAttribute("userId"));
        String url = resourceMapping.getDefaultUrl();
        if (null == btAction || btAction.isEmpty()) {
            url = roadMap.get(GET_SHOPPING_PRODUCT_CONTROLLER);
            request.getRequestDispatcher(url).forward(request, response);
        } else {
            if (btAction.equals(ADD_ITEM_TO_CART_ACTION)) {
                url = roadMap.get(ADD_ITEM_TO_CART_CONTROLLER);
                request.getRequestDispatcher(url).forward(request, response);
            } else if (btAction.equals(VIEW_CART_ACTION)) {
                response.sendRedirect(VIEW_CART_PAGE);
            } else if (btAction.equals(USER_INFO_ACTION)) {
                UserSessionModel currentUser = (UserSessionModel) session.getAttribute(UserSessionModel.SESSION_KEY);
                SelectedUserSessionModel.OriginalValue original = new SelectedUserSessionModel.OriginalValue(
                        currentUser.getEmail(), currentUser.getFullName(), currentUser.getPassword(), currentUser.getPhoneNumber(), currentUser.getAddress(), currentUser.getRole(), currentUser.getSignInMethod().name());
                SelectedUserSessionModel sessionModel
                        = new SelectedUserSessionModel(original);
                session.setAttribute(SelectedUserSessionModel.SESSION_KEY, sessionModel);
                url = USER_INFO_PAGE;
                response.sendRedirect(url);
            }

        }

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
