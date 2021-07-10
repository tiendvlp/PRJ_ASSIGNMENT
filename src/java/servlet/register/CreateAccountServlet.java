/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.register;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static common.Config.*;
import common.ResouceDynamicMapping;
import data.dao.UserDao;
import data.dto.UserDto;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import servlet.common.cookie.BuiltinAuthCookie;
import servlet.common.error.RegisterError;
import servlet.common.error.errorvalidator.RegistrationReqHandler;
import servlet.common.sessionmodel.UserSessionModel;
import servlet.common.sessionmodel.UserSessionModel.SIGNIN_METHOD;
import static servlet.common.sessionmodel.UserSessionModel.SIGNIN_METHOD.BUILTIN;
import static servlet.common.sessionmodel.UserSessionModel.SIGNIN_METHOD.GOOGLE_SIGNIN;

/**
 *
 * @author dangminhtien
 */
public class CreateAccountServlet extends HttpServlet {

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RegistrationReqHandler reqHandler = new RegistrationReqHandler();
        boolean hasError = reqHandler.init(req);;
        RegisterError error = reqHandler.getError();
        HttpSession session = req.getSession();
        SIGNIN_METHOD method = BUILTIN;
        log("MOVE to registration page");
        ServletContext context = req.getServletContext();
        ResouceDynamicMapping resourceMapping = (ResouceDynamicMapping) context.getAttribute(ResouceDynamicMapping.KEY);
        HashMap<String, String> roadMap = resourceMapping.getRoadMap();
        boolean isVerified = false;
        // nếu như là login bằng google thì không cần verified lại email
        if (reqHandler.getTxtSignInMethod().equals("GOOGLE_SIGNIN")) {
            isVerified = true;
            method = GOOGLE_SIGNIN;
        }
        try {
            UserDao dao = new UserDao();
            if (dao.getUser(reqHandler.getTxtEmail()) != null) {
                hasError = true;
                if (error == null) {
                    error = new RegisterError();
                }
                error.setEmailErrorMessage("Email already exist");
            }
            if (!hasError) {
                UserDto userDto = new UserDto(reqHandler.getTxtEmail(), isVerified, method.name(), reqHandler.getTxtFullName(), reqHandler.getTxtPassword(), reqHandler.getTxtPhoneNumber(), reqHandler.getTxtAddress(), "User");
                dao.createUser(userDto);
                // get an existance session
                UserSessionModel user = new UserSessionModel(userDto.getEmail(), userDto.getFullName(), userDto.getPassword(), userDto.getPhoneNumber(), userDto.getAddress(), userDto.getRole(), method);
                session.setAttribute(UserSessionModel.SESSION_KEY, user);
                BuiltinAuthCookie authCookie = new BuiltinAuthCookie(userDto.getEmail(), userDto.getPassword());
                Cookie cookie = authCookie.getCookie();
                resp.addCookie(cookie);
            }

        } catch (ClassNotFoundException ex) {
            log("CreateAccountServlet error due to: " + ex.getMessage());
        } catch (SQLException ex) {
            log("CreateAccountServlet error due to: " + ex.getMessage());
        } finally {
            if (hasError) {
                /**
                 * Tại sao phải làm như vầy ? vì req Attribute bị mất đi sau khi
                 * forward nhiều lần Mà trang jsp lại cần req attribute nên
                 * chúng ta phải gán lại để đảm bảo nó không bị mất
                 *
                 */
                UserSessionModel userAttr = new UserSessionModel();
                userAttr.setEmail(reqHandler.getTxtEmail());
                userAttr.setSignInMethod(method);
                req.setAttribute(UserSessionModel.SESSION_KEY, userAttr);
                req.setAttribute("UERROR", error);
                req.getRequestDispatcher(roadMap.get(REGISTER_PAGE)).forward(req, resp);
            } else {
                // login
                resp.sendRedirect(resourceMapping.getDefaultUrl());
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
