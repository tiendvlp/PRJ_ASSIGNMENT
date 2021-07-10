/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.login;

import static common.Config.Action.*;
import static common.Config.*;
import common.ResouceDynamicMapping;
import data.dao.UserDao;
import data.dto.UserDto;
import java.io.IOException;
import static java.lang.System.out;
import java.sql.SQLException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import servlet.common.cookie.BuiltinAuthCookie;
import servlet.common.error.LoginError;
import servlet.common.sessionmodel.UserSessionModel;

public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int errorCode = -1;
        String txtUserId = req.getParameter("txtUserId");
        String txtPassword = req.getParameter("txtPassword");
        UserDao accountDao = new UserDao();
        String url = LOGIN_PAGE;
        ResouceDynamicMapping resourceMapping = (ResouceDynamicMapping) req.getServletContext().getAttribute(ResouceDynamicMapping.KEY);
        HashMap<String, String> roadMap = resourceMapping.getRoadMap();
        boolean hasError = false;
        LoginError error = new LoginError();
        String btAction = req.getParameter("btAction");

        if (btAction.equals(REGISTER_ACTION)) {
            resp.sendRedirect(REGISTER_PAGE);
            return;
        } else if (btAction.equals(SHOPPING_ACTION)) {
            resp.sendRedirect(SHOPPING_PAGE);
            return;
        }

        try {
            UserDto result = accountDao.getUser(txtUserId.trim(), txtPassword.trim());
            if (result != null) {
                hasError = false;
                log("Login Sucess: " + result.getEmail());
                url = resourceMapping.getDefaultUrl();
                HttpSession session = req.getSession(true);
                UserSessionModel user = new UserSessionModel(result.getEmail(), result.getFullName(), result.getPassword(), result.getPhoneNumber(), result.getAddress(), result.getRole(), UserSessionModel.SIGNIN_METHOD.BUILTIN);
                session.setAttribute(UserSessionModel.SESSION_KEY, user);
                BuiltinAuthCookie authCookie = new BuiltinAuthCookie(result.getEmail(), result.getPassword());
                Cookie cookie = authCookie.getCookie();
                cookie.setMaxAge(60 * 60);
                resp.addCookie(cookie);
                if (!result.isIsVerified()) {
                    url = VERIFY_EMAIL_PAGE;
                }
            } else {
                url = roadMap.get(LOGIN_PAGE);
                hasError = true;
                error.setIncorrect();
            }

        } catch (ClassNotFoundException | SQLException ex) {
            errorCode = 500;
            log("LoginServlet Error due to: " + ex.getMessage());
        } catch (Exception ex) {
            errorCode = 500;
            log("LoginServlet Error due to: " + ex.getMessage());
        } finally {
            if (hasError) {
                req.setAttribute("UERROR", error);
                req.getRequestDispatcher(url).forward(req, resp);
            } else {
                if (errorCode == -1) {
                    resp.sendRedirect(url);
                    out.close();
                } else {
                    resp.sendError(errorCode);
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
        processRequest(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

}
