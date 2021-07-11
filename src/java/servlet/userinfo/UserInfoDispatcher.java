/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.userinfo;

import static common.Config.Action.RESET_ACTION;
import static common.Config.Action.UPDATE_ACTION;
import static common.Config.UPDATE_CONTROLLER;
import static common.Config.USER_INFO_PAGE;
import static common.Config.USER_INFO_UI;
import common.ResouceDynamicMapping;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import servlet.common.sessionmodel.SelectedUserSessionModel;

/**
 *
 * @author dangminhtien
 */
public class UserInfoDispatcher extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        String btAction = request.getParameter("btAction");
        String url = null;

        ResouceDynamicMapping resourceMapping = (ResouceDynamicMapping) request.getServletContext().getAttribute(ResouceDynamicMapping.KEY);
        HashMap<String, String> roadMap = resourceMapping.getRoadMap();
        HttpSession session = request.getSession();


        if (btAction == null || btAction.isEmpty()) {
            url = roadMap.get(USER_INFO_UI);
            request.getRequestDispatcher(url).forward(request, resp);
        } else {
            switch (btAction) {
                case UPDATE_ACTION: {
                    String txtSignInMethod = request.getParameter("txtSignInMethod");
                    String txtEmail = request.getParameter("txtUserEmail");
                    String txtPassword = request.getParameter("txtUserPassword");
                    String txtFullName = request.getParameter("txtUserFullName");
                    String txtAddress = request.getParameter("txtUserAddress");
                    String txtPhoneNumber = request.getParameter("txtUserPhoneNumber");
                    String userRole = request.getParameter("ddlist");
                    String method = "BUILTIN";

                    if (txtSignInMethod.equalsIgnoreCase("GOOGLE_SIGNIN")) {
                        method = "GOOGLE_SIGNIN";
                    }

                    SelectedUserSessionModel sessionModel
                            = (SelectedUserSessionModel) session.getAttribute(SelectedUserSessionModel.SESSION_KEY);
                    if (userRole == null || userRole.trim().isEmpty()) {
                        userRole = sessionModel.getOriginalValue().getRole();
                    }
                    SelectedUserSessionModel.EditedValue editedValue = new SelectedUserSessionModel.EditedValue(
                            txtEmail, txtFullName, txtPassword, txtPhoneNumber, txtAddress, userRole, method
                    );
                    log("update nha:" + sessionModel.getOriginalValue().getEmail());
                    sessionModel.setEditedValue(editedValue);
                    session.setAttribute(SelectedUserSessionModel.SESSION_KEY, sessionModel);
                    url = UPDATE_CONTROLLER;
                    resp.sendRedirect(url);
                    break;
                }
                case RESET_ACTION: {
                    SelectedUserSessionModel sessionModel
                            = (SelectedUserSessionModel) session.getAttribute(SelectedUserSessionModel.SESSION_KEY);
                    sessionModel.setEditedValue(null);
                    // reset page
                    resp.sendRedirect(USER_INFO_PAGE);
                    break;
                }
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
