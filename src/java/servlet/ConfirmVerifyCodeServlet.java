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
import javax.servlet.http.HttpSession;
import static common.Config.*;
import data.dao.UserDao;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import servlet.sessionmodel.UserSessionModel;

/**
 *
 * @author dangminhtien
 */
public class ConfirmVerifyCodeServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String url = DISPATCH_CONTROLLER;
        String errorMessage = null;
        try {
            HttpSession session = req.getSession();
            UserSessionModel user = (UserSessionModel) session.getAttribute(UserSessionModel.SESSION_KEY);
            String code = (String) session.getAttribute("VERIFIEDMAILCODE");
            String txtInputCode = req.getParameter("txtInputCode");
            
            // Nếu như mà code bằng null có nghĩa là sự cố gì đã xảy ra rồi => trở về mh chính
            if (code != null) {
                // nếu như code không null mà không khớp thì cho nhập lại
                if (txtInputCode == null || !txtInputCode.equals(code)) {
                    errorMessage = "Verified code is incorrect";
                    url = getVerifiedMailPageUrl();
                }
                UserDao dao = new UserDao();
                dao.updateMailVerifiedState(user.getEmail(), true);
                // nếu code trùng khớp => login (có nghĩa là trở về dispatchercontroller nó sẽ tự đăng nhập
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConfirmVerifyCodeServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConfirmVerifyCodeServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (errorMessage != null) {
                req.setAttribute("UERROR", errorMessage);
                req.getRequestDispatcher(url).forward(req, resp);
            } else {
                resp.sendRedirect(url);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
