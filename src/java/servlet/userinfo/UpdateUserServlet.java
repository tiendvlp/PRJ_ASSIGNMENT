/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.userinfo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import data.dao.UserDao;
import java.sql.SQLException;
import static common.Config.*;
import common.ResouceDynamicMapping;
import data.dto.UserDto;
import java.util.HashMap;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import servlet.common.cookie.BuiltinAuthCookie;
import servlet.common.sessionmodel.SelectedUserSessionModel;
import servlet.common.sessionmodel.UserSessionModel;

/**
 *
 * @author dangminhtien
 */
public class UpdateUserServlet extends HttpServlet {

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String url = "";
        int errorCode = -1;
        String userRole = null;
        HttpSession session = req.getSession();
        ResouceDynamicMapping resourceMapping = (ResouceDynamicMapping) req.getServletContext().getAttribute(ResouceDynamicMapping.KEY);
        UserSessionModel userSessionModel = null;
        SelectedUserSessionModel selectedUser = (SelectedUserSessionModel) session.getAttribute(SelectedUserSessionModel.SESSION_KEY);
        SelectedUserSessionModel.EditedValue editedValue = selectedUser.getEditedValue();
        try {
            if (userRole == null || userRole.trim().isEmpty()) {
                userRole = "USER";
            }

            if (editedValue.getRole().equalsIgnoreCase("Admin")) {
                userRole = "ADMIN";
            }
            log("Chat toi day 2");

            userSessionModel = (UserSessionModel) session.getAttribute(UserSessionModel.SESSION_KEY);
            String currentUserEmail = userSessionModel.getEmail();
            UserDao dao = new UserDao();

            UserDto userDto = new UserDto(editedValue.getEmail(), true, editedValue.getSignInMethod(), editedValue.getFullName(), editedValue.getPassword(), editedValue.getPhoneNumber(), editedValue.getAddress(), userRole);
            UserDto updatedUser = dao.updateUser(editedValue.getEmail(), userDto);
            log("Chat toi day 3");
            // if update current account
            if (editedValue.getEmail().equals(currentUserEmail)) {
                // update current session
                log("Update current user");
                userSessionModel.setAddress(updatedUser.getAddress());
                userSessionModel.setEmail(updatedUser.getEmail());
                userSessionModel.setPassword(updatedUser.getPassword());
                userSessionModel.setFullName(updatedUser.getFullName());
                userSessionModel.setPhoneNumber(updatedUser.getPhoneNumber());
                String previousRole = userSessionModel.getRole();
                userSessionModel.setRole(updatedUser.getRole());
                session.setAttribute(UserSessionModel.SESSION_KEY, userSessionModel);
                // remove old cookie
                clearCookie(resp, editedValue.getEmail());
                // write new cookie
                addNewAuthCookie(resp, editedValue.getEmail(), editedValue.getPassword());
                // if role got changed => signout
                if (!updatedUser.getRole().equalsIgnoreCase(previousRole)) {
                    url = SIGNOUT_CONTROLLER;
                }
            }

        } catch (SQLException ex) {
            log("SQLException at UpdateUserServlet: " + ex.getMessage());
            errorCode = 500;
        } catch (ClassNotFoundException ex) {
            log("ClassNotFoundException at UpdateUserServlet: " + ex.getMessage());
            errorCode = 500;
        } finally {
            if (errorCode > -1) {
                resp.sendError(errorCode);
            } else {
                if (userSessionModel.getRole().equals("ADMIN")) {
                    if (session.getAttribute("LAST_SEARCH_VALUE") != null) {
                        url = SEARCH_PAGE + "?btAction=Search&txtSearch=" + session.getAttribute("LAST_SEARCH_VALUE");
                    } else {
                        url = SEARCH_PAGE;
                    }
                } else {
                    url = resourceMapping.getDefaultUrl();
                }
                resp.sendRedirect(url);
            }
        }
    }

    private void addNewAuthCookie(HttpServletResponse resp, String newEmail, String newPassword) {
        BuiltinAuthCookie authCookie = new BuiltinAuthCookie(newEmail, newPassword);
        Cookie cookie = authCookie.getCookie();
        cookie.setMaxAge(60 * 3);
        resp.addCookie(cookie);
    }

    private void clearCookie(HttpServletResponse resp, String email) {
        Cookie killCookie = new Cookie(email.replace("@", "acong"), "");
        killCookie.setMaxAge(0);
        resp.addCookie(killCookie);
        killCookie.setPath("/");
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
