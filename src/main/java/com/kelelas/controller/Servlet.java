package com.kelelas.controller;

import com.kelelas.controller.command.Command;
import com.kelelas.controller.command.LogoutCommand;
import com.kelelas.controller.command.admin.OrderListCommand;
import com.kelelas.controller.command.admin.StatisticsCommand;
import com.kelelas.controller.command.admin.UpdateIngredientsCommand;
import com.kelelas.controller.command.admin.post.ConfirmOrderCommand;
import com.kelelas.controller.command.admin.post.UpdateCommand;
import com.kelelas.controller.command.guest.*;
import com.kelelas.controller.command.guest.post.LoginCommand;
import com.kelelas.controller.command.guest.post.RegistrationCommand;
import com.kelelas.controller.command.user.*;
import com.kelelas.controller.command.user.post.AddToOrderCommand;
import com.kelelas.controller.command.user.post.ConfirmCommand;
import com.kelelas.controller.command.user.post.DeleteFromOrderCommand;
import com.kelelas.controller.command.user.post.PayForOrderCommand;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@WebServlet("/")
public class Servlet extends HttpServlet {
    private Map<String, Command> commands = new HashMap<>();
    private static final Logger logger = Logger.getLogger(Servlet.class);


    public void init(ServletConfig servletConfig){
        servletConfig.getServletContext()
                .setAttribute("loggedUsers", new HashSet<String>());

        commands.put("logout", new LogoutCommand());
        commands.put("login", new LoginPageCommand());
        commands.put("log", new LoginCommand());
        commands.put("reg", new RegistrationCommand());
        commands.put("registration", new RegistrationPageCommand());
        commands.put("", new MainCommand());

        commands.put("admin/update_ingredients", new UpdateIngredientsCommand());
        commands.put("admin/orders_list", new OrderListCommand());
        commands.put("admin/statistics", new StatisticsCommand());

        commands.put("user/refill", new RefillCommand());
        commands.put("user/menu", new MenuCommand());
        commands.put("user/history", new HistoryCommand());
        commands.put("user/cart", new CartCommand());
        commands.put("user/bill", new BillCommand());

        commands.put("user/addToOrder", new AddToOrderCommand());
        commands.put("user/payForOrder", new PayForOrderCommand());
        commands.put("user/confirm", new ConfirmCommand());
        commands.put("user/deleteFromOrder", new DeleteFromOrderCommand());

        commands.put("admin/confirmOrder", new ConfirmOrderCommand());
        commands.put("admin/update", new UpdateCommand());
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        path = path.replaceAll(".*/web/" , "");
        logger.info(path);
        Command command = commands.getOrDefault(path ,
                (req)->"/error.jsp");
        String page = command.execute(request);
        if (page.contains("redirect"))
            response.sendRedirect(request.getContextPath() + page.replace("redirect:", ""));
        else
            request.getRequestDispatcher(page).forward(request,response);
    }

}
