package com.kelelas.controller.command.guest.post;

import com.kelelas.controller.command.Command;
import com.kelelas.controller.command.CommandUtility;
import com.kelelas.controller.config.BCryptPasswordEncoder;
import com.kelelas.controller.config.PageConfig;
import com.kelelas.controller.config.Regex;
import com.kelelas.model.entity.RoleType;
import com.kelelas.model.entity.User;
import com.kelelas.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {
    private final UserService userService;


    public LoginCommand() {
        this.userService = new UserService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user;
        try {
            if (isValid(email, password) ) {
                user = userService.findByEmail(email);
                if (new BCryptPasswordEncoder().matches(password, user.getPassword()) && CommandUtility.checkUserIsNotLogged(request, email)) {
                    CommandUtility.setUserToSession(request, user);
                }else {
                    return PageConfig.getProperty("path.page.redirect.login.userError");
                }

            }else {
                return PageConfig.getProperty("path.page.redirect.login.userValidationError");
            }
        }catch (Exception e){
            return PageConfig.getProperty("path.page.redirect.login.userException");
        }

        return redirect(user.getRole());

    }

    //todo
    private boolean isValid (String email, String password){
        return email.matches(Regex.EMAIL_REGEX) && password.matches(Regex.PASSWORD_REGEX);
    }
    private String redirect(RoleType role){
        return role.equals(RoleType.USER)
                ? PageConfig.getProperty("path.page.redirect.user.menu")
                : PageConfig.getProperty("path.page.redirect.admin.updateIngredient");
    }
}
