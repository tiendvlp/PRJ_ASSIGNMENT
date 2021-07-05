/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static common.Config.*;
import data.dao.UserDao;
import data.dto.UserDto;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
/**
 *
 * @author dangminhtien
 */

@WebFilter(filterName = "AuthFilter", urlPatterns = {"/*"}, dispatcherTypes = {DispatcherType.REQUEST})
public class AuthFilter implements Filter {
    
    
    @Override
    public void init(FilterConfig fc) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc) throws IOException, ServletException {
        fc.doFilter(request, response);

//
//        if (httpRequest.getRequestURI().startsWith("/DevlogsPrj/login.html")) {
//                if (session.getAttribute("UserId") != null) {
//                        fc.doFilter(httpRequest, httpResponse);
//                        return;
//                }
//                               httpResponse.sendRedirect(LOGIN_PAGE);
//
//                   return;
//            }
//        
//        if (session.getAttribute("UserId") == null) {
//            httpResponse.sendRedirect(LOGIN_PAGE);
//            return;
//        } else {
//            fc.doFilter(httpRequest, httpResponse);
//        }
        
//        fc.doFilter(httpRequest, httpResponse);

//        try {
//            String userId;
//            String password;
//            if (session.getAttribute("UserId") != null) {
//                userId = session.getAttribute("UserId").toString();
//                password = session.getAttribute("Password").toString();
//                httpRequest.setAttribute("UserId", userId);
//                httpRequest.setAttribute("Password", password);
//            } else {
//                UserDao userDao = new UserDao();
//                Cookie[] cookies = httpRequest.getCookies();
//                if (cookies != null) {
//                    UserDto userDto = null;
//                for (Cookie cookie : cookies) {
//                    userId = cookie.getName();
//                    password = cookie.getValue();
//                    userDto = userDao.getUser(userId, password);
//                    
//                    // if login success then save userName password into session
//                    if (userDto != null) {
//                        session.setAttribute("UserId", userDto.getId());
//                        session.setAttribute("Password", userDto.getPassword());
//                        break;
//                    } 
//                }
//                   if (userDto == null) {
//                        httpResponse.sendRedirect(LOGIN_PAGE);
//                   }
//            }
//            }
//                fc.doFilter(httpRequest, httpResponse);
//        } catch (Exception ex) {
//            
//        } finally {
//
//        }
    }

    @Override
    public void destroy() {
    }
}
