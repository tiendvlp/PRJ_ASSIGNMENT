/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import common.Config;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import servlet.cookie.GoogleAuthCookie;
import servlet.sessionmodel.UserSessionModel;

public class SignOutServlet extends HttpServlet {

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log("Signout run");
        HttpSession session = req.getSession();
        UserSessionModel userSession = (UserSessionModel) session.getAttribute(UserSessionModel.SESSION_KEY);
        Cookie cookie1 = new Cookie(userSession.getEmail().replace("@", "acong"), "");
        cookie1.setMaxAge(0);
        resp.addCookie(cookie1);
        Cookie cookie2 = new Cookie(GoogleAuthCookie.COOKIE_NAME, "");
        cookie2.setMaxAge(0);
        resp.addCookie(cookie2);
        Cookie cookie3 = new Cookie("JSESSIONID", "");
        cookie3.setMaxAge(0);
        resp.addCookie(cookie3);
        cookie3.setPath("/");
        cookie1.setPath("/");
        cookie2.setPath("/");
        session.removeAttribute(UserSessionModel.SESSION_KEY);
        resp.sendRedirect(Config.DISPATCH_CONTROLLER);
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
