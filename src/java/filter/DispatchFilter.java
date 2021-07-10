/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import common.ResouceDynamicMapping;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author dangminhtien
 */
public class DispatchFilter implements Filter {

    private FilterConfig filterConfig = null;

    public DispatchFilter() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        log("DispatchFilter run");
        HttpServletRequest req = (HttpServletRequest) request;
        ServletContext context = req.getServletContext();
        String uri = req.getRequestURI();
        String url = null;

        ResouceDynamicMapping resourceMapping = (ResouceDynamicMapping) context.getAttribute(ResouceDynamicMapping.KEY);
        int lastIndex = uri.lastIndexOf("/");
        String key = uri.substring(lastIndex + 1);
        log("Found resource:" + key);
        String resource = resourceMapping.getRoadMap().get(key);
        url = resource;
        if (url == null) {
            url = resourceMapping.getDefaultUrl();
        }
        req.getRequestDispatcher(url).forward(request, response);
    }

    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    public void destroy() {
    }

    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("ResouceMappingFilter()");
        }
        StringBuffer sb = new StringBuffer("ResouceMappingFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
