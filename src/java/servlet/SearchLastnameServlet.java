/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import common.Config;
import data.dao.UserDao;
import data.dto.UserDto;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servlet.bean.UserBean;
/**
 *
 * @author dangminhtien
 */
public class SearchLastnameServlet extends HttpServlet {
    private void processRequest (HttpServletRequest req ,HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        String txtSearch = req.getParameter("txtSearch");
        String url = Config.SEARCH_PAGE;
        PrintWriter writer = res.getWriter();
        try {
            if (txtSearch != null && !txtSearch.trim().isEmpty()) {
                String refactoredSearchValue = txtSearch.trim();
                UserDao userDao = new UserDao ();
                List<UserDto> result = userDao.searchUser(refactoredSearchValue);
                req.setAttribute("SEARCH_RESULT", result);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SearchLastnameServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SearchLastnameServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            RequestDispatcher dispatcher = req.getRequestDispatcher(url);
            dispatcher.forward(req, res);
            writer.close();
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
