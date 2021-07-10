/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.login;

import data.dao.UserDao;
import data.dto.UserDto;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static common.Config.*;
import common.ResouceDynamicMapping;
import java.util.HashMap;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import servlet.common.cookie.GoogleAuthCookie;
import servlet.common.error.LoginError;
import servlet.common.sessionmodel.UserSessionModel;

/**
 *
 * @author dangminhtien
 */
public class LoginGoogleServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResouceDynamicMapping resourceMapping = (ResouceDynamicMapping) req.getServletContext().getAttribute(ResouceDynamicMapping.KEY);
        HashMap<String, String> roadMap = resourceMapping.getRoadMap();
        String url = resourceMapping.getDefaultUrl();
        try {
            String code = req.getParameter("code");
            log("Login with google success register: " + code);
            if (code == null || code.isEmpty()) {
                url = resourceMapping.getDefaultUrl();
            } else {
                HttpSession session = req.getSession();
                String accessToken = GoogleUtils.getToken(code);
                GoogleAuthCookie authCookie = new GoogleAuthCookie(accessToken);
                GooglePojo googlePojo = authCookie.getGooglePojo();
                UserDao dao = new UserDao();
                UserDto userDto = dao.getUser(googlePojo.getEmail());
                if (userDto != null) {
                    // nếu như user ở database tồn tại có nghĩa là người dùng này đã đk rồi thì sẽ trở về processrequestservlet để xử lý
                    url = resourceMapping.getDefaultUrl();
                    UserSessionModel user = new UserSessionModel(userDto.getEmail(), userDto.getFullName(), userDto.getPassword(), userDto.getPhoneNumber(), userDto.getAddress(), userDto.getRole(), UserSessionModel.SIGNIN_METHOD.GOOGLE_SIGNIN);
                    // write new cookie
                    Cookie cookie = authCookie.getCookie();
                    cookie.setMaxAge(60 * 60);
                    resp.addCookie(cookie);
                    session.setAttribute(UserSessionModel.SESSION_KEY, user);
                    log("Login with google success");
                    // user already exist in database => Login
                    resp.sendRedirect(url);
                    return;
                } else {
                    // move to register page to fullfill missing infomation
                    // set to session
                    UserSessionModel user = new UserSessionModel();
                    user.setEmail(googlePojo.getEmail());
                    user.setSignInMethod(UserSessionModel.SIGNIN_METHOD.GOOGLE_SIGNIN);
                    req.setAttribute(UserSessionModel.SESSION_KEY, user);
                    log("Login with google success register");
                    url = roadMap.get(REGISTER_PAGE);
                    req.getRequestDispatcher(url).forward(req, resp);
                    return;
                }
            }
        } catch (ClassNotFoundException ex) {
                log("LoginGoogleServlet error due to: " + ex.getMessage());
        } catch (SQLException ex) {
                log("LoginGoogleServlet error due to: " + ex.getMessage());
        } finally {
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
