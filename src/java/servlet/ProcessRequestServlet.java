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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import servlet.cookie.BuiltinAuthCookie;
import servlet.cookie.GoogleAuthCookie;
import servlet.sessionmodel.UserSessionModel;

public class ProcessRequestServlet extends HttpServlet {

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = LOGIN_PAGE;
        HttpSession session = req.getSession(true);
        UserSessionModel user = (UserSessionModel) session.getAttribute(UserSessionModel.SESSION_KEY);
        try {
            UserDao userDao = new UserDao();
            BuiltinAuthCookie builtinAuthCookie = new BuiltinAuthCookie();
            GoogleAuthCookie googleAuthCookie = new GoogleAuthCookie();
            boolean buitinParseResult = false;
            boolean ggSignInParseResult = false;
            if (user != null) {
                url = SEARCH_PAGE;
            } else {
                Cookie[] cookies = req.getCookies();
                String userPassword;
                UserDto userDto = null;
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        buitinParseResult = builtinAuthCookie.parseFromCookie(cookie);
                        if (buitinParseResult) {
                            userDto = userDao.getUser(builtinAuthCookie.getEmail(), builtinAuthCookie.getPassword());
                            // if login success then save userName password into session
                            if (userDto != null) {
                                url = SEARCH_PAGE;
                                user = new UserSessionModel(userDto.getEmail(), userDto.getFullName(), userDto.getPassword(), userDto.getPhoneNumber(), userDto.getAddress(), userDto.getRole(), UserSessionModel.SIGNIN_METHOD.GOOGLE_SIGNIN);
                                session.setAttribute(UserSessionModel.SESSION_KEY, user);
                                break;
                            }
                        }
                        ggSignInParseResult = googleAuthCookie.parseFromCookie(cookie);
                        if (ggSignInParseResult) {
                            GooglePojo googlePojo = googleAuthCookie.getGooglePojo();
                            userDto = userDao.getUser(googlePojo.getEmail());
                            if (userDto != null) {
                                user = new UserSessionModel(userDto.getEmail(), userDto.getFullName(), userDto.getPassword(), userDto.getPhoneNumber(), userDto.getAddress(), userDto.getRole(), UserSessionModel.SIGNIN_METHOD.GOOGLE_SIGNIN);
                                url = SEARCH_PAGE;
                                session.setAttribute(UserSessionModel.SESSION_KEY, user);
                                break;
                            }
                        }
                    }
                }
            }
            // check whether user verified or not
            if (user != null) {
                if (!userDao.getVerifiedEmailState(user.getEmail())) {
                    url = getVerifiedMailPageUrl();
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            req.getRequestDispatcher(url).forward(req, resp);
        }
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
