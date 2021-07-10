/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.register;

import servlet.register.CreateAccountServlet;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import static common.Config.*;
import sendmail.ConfirmAccountEmail;
import sendmail.Email;
import sendmail.MailSenderApi;
import servlet.common.sessionmodel.UserSessionModel;

/**
 *
 * @author dangminhtien
 */
public class SendVerifyCodeServlet extends HttpServlet {

    private final OkHttpClient httpClient = new OkHttpClient();

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, UnsupportedEncodingException {
        String url = "processrequestservlet";
        try {
            HttpSession session = req.getSession();
            UserSessionModel user = (UserSessionModel) session.getAttribute(UserSessionModel.SESSION_KEY);
            if (user != null) {
                String emailToVerify = user.getEmail();
                String uuid = UUID.randomUUID().toString();
                String code = uuid.substring(uuid.length() - 5, uuid.length() - 1);
                Email confirmEmail = new ConfirmAccountEmail(code, emailToVerify);
                MailSenderApi mailSender = new MailSenderApi();
                Response sendMailResp =  mailSender.send(confirmEmail);
                if (sendMailResp.isSuccessful()) {
                    url = VERIFIED_MAIL_PAGE;
                    log("Send email result: " + sendMailResp.body().string());
                }
                session.setAttribute("VERIFIEDMAILCODE", code);
            }
            // Get response body
        } catch (IOException ex) {
            log("SendVerifyCodeServlet error due to: " + ex.getMessage());
        } finally {
            resp.sendRedirect(url);
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
