/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static common.Config.*;
import data.dao.UserDao;
import data.dto.UserDto;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import servlet.cookie.BuiltinAuthCookie;
import servlet.error.RegisterError;
import servlet.error.errorvalidator.RegistrationReqHandler;
import servlet.sessionmodel.UserSessionModel;
import servlet.sessionmodel.UserSessionModel.SIGNIN_METHOD;
import static servlet.sessionmodel.UserSessionModel.SIGNIN_METHOD.BUILTIN;
import static servlet.sessionmodel.UserSessionModel.SIGNIN_METHOD.GOOGLE_SIGNIN;

/**
 *
 * @author dangminhtien
 */
public class CreateAccountServlet extends HttpServlet {

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RegistrationReqHandler reqHandler = new RegistrationReqHandler();
        boolean hasError = reqHandler.init(req);;
        RegisterError error = reqHandler.getError();
        SIGNIN_METHOD method = BUILTIN;
        
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
                HttpSession session = req.getSession(true);
                // get an existance session
                UserSessionModel user = new UserSessionModel(userDto.getEmail(), userDto.getFullName(), userDto.getPassword(), userDto.getPhoneNumber(), userDto.getAddress(), userDto.getRole(), method);
                session.setAttribute(UserSessionModel.SESSION_KEY, user);
                BuiltinAuthCookie authCookie = new BuiltinAuthCookie(userDto.getEmail(), userDto.getPassword());
                Cookie cookie = authCookie.getCookie();
                cookie.setMaxAge(60 * 3);
                resp.addCookie(cookie);
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CreateAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CreateAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
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
                req.setAttribute("USER", userAttr);
                req.setAttribute("UERROR", error);
                req.getRequestDispatcher(REGISTRATION_PAGE).forward(req, resp);
            } else {
                // login
                resp.sendRedirect(DISPATCH_CONTROLLER);
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
