package servlet.search;


import data.dao.UserDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static common.Config.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dangminhtien
 */
public class DeleteUserServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String userEmail = req.getParameter("txtUserEmail");
            String searchValue = req.getParameter ("txtLastSearchValue");
            this.getServletContext().log("Search value: " + searchValue);
        try {
            UserDao userDao = new UserDao();
            userDao.deleteUser(userEmail);
        } catch (ClassNotFoundException ex) {
            log("DeleteUser Error due to: " + ex.getMessage());
        } catch (SQLException ex) {
            log("DeleteUser Error due to: " + ex.getMessage());
        } finally {
            resp.sendRedirect(SEARCH_PAGE + "?txtSearch=" + searchValue + "&btAction=Search" );
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
