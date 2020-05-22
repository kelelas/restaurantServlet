package com.kelelas.controller.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashSet;
@WebListener
public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HashSet<String> loggedUsers = (HashSet<String>) se
                .getSession().getServletContext()
                .getAttribute("loggedUsers");
        String email = (String) se.getSession()
                .getAttribute("email");
        loggedUsers.remove(email);
        se.getSession().setAttribute("loggedUsers", loggedUsers);
    }
}
