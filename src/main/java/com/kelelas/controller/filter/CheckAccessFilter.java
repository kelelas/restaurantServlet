package com.kelelas.controller.filter;

import com.kelelas.model.entity.RoleType;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class CheckAccessFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String path = request.getServletPath();
        RoleType roleType = (RoleType) session.getAttribute("role");
        if (path.contains("/logout") || request.getRequestURI().contains("/img/")){
            filterChain.doFilter(request, response);
            return;
        }
        if (path.contains("/admin") && roleType!=null && roleType.equals(RoleType.ADMIN)){
            filterChain.doFilter(request, response);
            return;
        }
        if (path.contains("/user") && roleType!=null && roleType.equals(RoleType.USER)){
            filterChain.doFilter(request, response);
            return;
        }
        if (!path.contains("/admin") && !path.contains("/user") && roleType==null){
            filterChain.doFilter(request, response);
            return;
        }
        request.getRequestDispatcher("/error").forward(request, response);
        filterChain.doFilter(request, response);

    }

    @Override
    public void destroy() {

    }
}
