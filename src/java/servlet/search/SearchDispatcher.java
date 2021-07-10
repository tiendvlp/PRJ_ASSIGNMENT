/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.search;

import common.ResouceDynamicMapping;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static common.Config.*;
import static common.Config.Action.*;
import javax.servlet.http.HttpSession;
import servlet.common.sessionmodel.SelectedUserSessionModel;
import static servlet.common.sessionmodel.SelectedUserSessionModel.*;
import servlet.common.sessionmodel.UserSessionModel;

/**
 *
 * @author dangminhtien
 */
@WebServlet(name = "search", urlPatterns = {"/search"})
public class SearchDispatcher extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log("Search serevlet run");
        ServletContext context = request.getServletContext();
        HttpSession session = request.getSession();
        ResouceDynamicMapping resourceMapping = (ResouceDynamicMapping) context.getAttribute(ResouceDynamicMapping.KEY);
        HashMap<String, String> roadMap = resourceMapping.getRoadMap();

        String url = roadMap.get(SEARCH_JSP);

        String btAction = request.getParameter("btAction");
        log("SearchPage: btAction=" + btAction);
        log("Co attribute: " + request.getAttribute("userId"));

        if (null == btAction || btAction.isEmpty()) {
            url = roadMap.get(SEARCH_JSP);
            request.getRequestDispatcher(url).forward(request, response);
        } else {
            if (btAction.equals(SEARCH_ACTION)) {
                url = roadMap.get(SEARCH_LASTNAME_CONTROLLER);
                String txtSearch = request.getParameter("txtSearch");
                session.setAttribute("LAST_SEARCH_VALUE", txtSearch);
                request.getRequestDispatcher(url).forward(request, response);
            } else if (btAction.equals(DELETE_ACTION)) {
                url = roadMap.get(DELETE_USER_CONTROLLER);
                request.getRequestDispatcher(url).forward(request, response);
            } else if (btAction.equals(UPDATE_ACTION) || btAction.equals(EDIT_ACTION)) {
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
                log("Signin method=" + userRole);
                if (btAction.equals(UPDATE_ACTION)) {
                    EditedValue editedValue = new EditedValue(
                            txtEmail, txtFullName, txtPassword, txtPhoneNumber, txtAddress, userRole, method
                    );
                    SelectedUserSessionModel sessionModel
                            = new SelectedUserSessionModel();
                    sessionModel.setEditedValue(editedValue);
                    session.setAttribute(SelectedUserSessionModel.SESSION_KEY, sessionModel);
                    url = UPDATE_CONTROLLER + "?isConfirm=YES";
                } else if (btAction.equals(EDIT_ACTION)) {
                    OriginalValue original = new OriginalValue(
                            txtEmail, txtFullName, txtPassword, txtPhoneNumber, txtAddress, userRole, method
                    );
                    SelectedUserSessionModel sessionModel
                            = new SelectedUserSessionModel(original);
                    session.setAttribute(SelectedUserSessionModel.SESSION_KEY, sessionModel);
                    url = USER_INFO_PAGE;
                }
                response.sendRedirect(url);
            } else if (btAction.equals(USER_INFO_ACTION)) {
                UserSessionModel currentUser = (UserSessionModel) session.getAttribute(UserSessionModel.SESSION_KEY);
                OriginalValue original = new OriginalValue(
                        currentUser.getEmail(), currentUser.getFullName(), currentUser.getPassword(), currentUser.getPhoneNumber(), currentUser.getAddress(), currentUser.getRole(), currentUser.getSignInMethod().name());
                SelectedUserSessionModel sessionModel
                        = new SelectedUserSessionModel(original);
                session.setAttribute(SelectedUserSessionModel.SESSION_KEY, sessionModel);
                url = USER_INFO_PAGE;
                response.sendRedirect(url);
            }

        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
