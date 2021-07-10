/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import static common.Config.*;
import common.ResouceDynamicMapping;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import servlet.common.error.errorvalidator.UpdateReqHandler;
import servlet.common.sessionmodel.SelectedUserSessionModel;

/**
 *
 * @author dangminhtien
 */
public class UpdateUserReqValidationFilter implements Filter {

    private FilterConfig filterConfig = null;

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        log("UpdateUserReqFilter run");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        ResouceDynamicMapping resourceMapping = (ResouceDynamicMapping) req.getServletContext().getAttribute(ResouceDynamicMapping.KEY);
        HttpSession session = req.getSession();
        HashMap<String, String> roadMap = resourceMapping.getRoadMap();
        UpdateReqHandler reqHandler = new UpdateReqHandler();
        reqHandler.init(req);
        String isConfirmed = req.getParameter("isConfirm");
        String url = "";
        SelectedUserSessionModel sessionModel = (SelectedUserSessionModel) session.getAttribute(SelectedUserSessionModel.SESSION_KEY);
        if (reqHandler.hasError()) {
            if (sessionModel.getOriginalValue() == null) {
                url = roadMap.get(SEARCH_PAGE) + "?btAction=Search&txtSearch=" + session.getAttribute("LAST_SEARCH_VALUE").toString();
                req.setAttribute("UERROR_INDEX", sessionModel.getEditedValue().getEmail());
            } else {
                url = roadMap.get(USER_INFO_PAGE);
            }
            req.setAttribute("UERROR", reqHandler.getError());
            sessionModel.setEditedValue(null);
            req.getRequestDispatcher(url).forward(request, response);
            return;
        }

        if (isConfirmed == null) {
            url = "ConfirmUpdate";
            resp.sendRedirect(url);
            return;
        }
        chain.doFilter(request, response);

    }

    public void destroy() {
    }

    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("UpdateUserReqValidationFilter()");
        }
        StringBuffer sb = new StringBuffer("UpdateUserReqValidationFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
