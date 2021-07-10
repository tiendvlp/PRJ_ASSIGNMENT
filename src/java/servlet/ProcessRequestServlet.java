package servlet;

import servlet.login.GooglePojo;
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
import servlet.common.cookie.BuiltinAuthCookie;
import servlet.common.cookie.GoogleAuthCookie;
import servlet.common.sessionmodel.UserSessionModel;

public class ProcessRequestServlet extends HttpServlet {

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = LOGIN_PAGE;
        HttpSession session = req.getSession(true);
        UserSessionModel sessionUser = (UserSessionModel) session.getAttribute(UserSessionModel.SESSION_KEY);
        try {
            UserDao userDao = new UserDao();
            BuiltinAuthCookie builtinAuthCookie = new BuiltinAuthCookie();
            GoogleAuthCookie googleAuthCookie = new GoogleAuthCookie();
            boolean buitinParseResult = false;
            boolean ggSignInParseResult = false;
            UserDto userDto = null;

            // if not found user in session let's check cookies
            if (sessionUser == null) {
                Cookie[] cookies = req.getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        log("Check cookie");
                        buitinParseResult = builtinAuthCookie.parseFromCookie(cookie);
                        if (buitinParseResult) {
                            userDto = userDao.getUser(builtinAuthCookie.getEmail(), builtinAuthCookie.getPassword());
                            // if login success then save userName password into session
                            if (userDto != null) {
                                break;
                            }
                        }
                        ggSignInParseResult = googleAuthCookie.parseFromCookie(cookie);
                        if (ggSignInParseResult) {
                            GooglePojo googlePojo = googleAuthCookie.getGooglePojo();
                            userDto = userDao.getUser(googlePojo.getEmail());
                            if (userDto != null) {
                                break;
                            }
                        }
                    }
                }
            }

            if (userDto != null) {
                // save user into session
                sessionUser = new UserSessionModel(userDto.getEmail(), userDto.getFullName(), userDto.getPassword(), userDto.getPhoneNumber(), userDto.getAddress(), userDto.getRole(), UserSessionModel.SIGNIN_METHOD.GOOGLE_SIGNIN);
                session.setAttribute(UserSessionModel.SESSION_KEY, sessionUser);
            }

            if (sessionUser != null) {
                // check user role
                if (sessionUser.getRole().equalsIgnoreCase("user")) {
                    log("Shopping nha");
                    url = SHOPPING_PAGE;
                } else if (sessionUser.getRole().equalsIgnoreCase("admin")) {
                    log("Search nha");
                    url = SEARCH_PAGE;
                }
                // check whether email is verified or not
                if (!userDao.getVerifiedEmailState(sessionUser.getEmail())) {
                    log("Verified nha");
                    url = "VerifiedEmail";
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            log("URL la: " + url);
            resp.sendRedirect(url);
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
