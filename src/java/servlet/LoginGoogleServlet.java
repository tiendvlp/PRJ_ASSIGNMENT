/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import data.dao.UserDao;
import data.dto.UserDto;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static common.Config.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import servlet.cookie.GoogleAuthCookie;
import servlet.sessionmodel.UserSessionModel;

/**
 *
 * @author dangminhtien
 */
public class LoginGoogleServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = DISPATCH_CONTROLLER;
        try {
            String code = req.getParameter("code");
            if (code == null || code.isEmpty()) {
                RequestDispatcher dis = req.getRequestDispatcher(DISPATCH_CONTROLLER);
                dis.forward(req, resp);
            } else {
                HttpSession session = req.getSession();
                String accessToken = GoogleUtils.getToken(code);
                GoogleAuthCookie authCookie = new GoogleAuthCookie(accessToken);
                GooglePojo googlePojo = authCookie.getGooglePojo();
                UserDao dao = new UserDao();
                UserDto userDto = dao.getUser(googlePojo.getEmail());
                if (userDto != null) {
                    // nếu như user ở database tồn tại có nghĩa là người dùng này đã đk rồi thì sẽ trở về processrequestservlet để xử lý
                    url = DISPATCH_CONTROLLER;
                    UserSessionModel user = new UserSessionModel(userDto.getEmail(), userDto.getFullName(), userDto.getPassword(), userDto.getPhoneNumber(), userDto.getAddress(), userDto.getRole(), UserSessionModel.SIGNIN_METHOD.GOOGLE_SIGNIN);
                    // write new cookie
                    Cookie cookie = authCookie.getCookie();
                    cookie.setMaxAge(60 * 3);
                    resp.addCookie(cookie);
                    session.setAttribute(UserSessionModel.SESSION_KEY, user);
                    // user already exist in database => Login
                } else {
                    // move to register page to fullfill missing infomation
                    // set to session
                    UserSessionModel user = new UserSessionModel();
                    user.setEmail(googlePojo.getEmail());
                    user.setSignInMethod(UserSessionModel.SIGNIN_METHOD.GOOGLE_SIGNIN);
                    req.setAttribute("USER", user);
                    url = getRegisterUrl();
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginGoogleServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LoginGoogleServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            req.getRequestDispatcher(url).forward(req, resp);
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
