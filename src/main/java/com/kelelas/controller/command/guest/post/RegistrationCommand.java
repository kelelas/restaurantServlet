package com.kelelas.controller.command.guest.post;

import com.kelelas.controller.command.Command;
import com.kelelas.controller.config.BCryptPasswordEncoder;
import com.kelelas.controller.config.PageConfig;
import com.kelelas.controller.config.Regex;
import com.kelelas.model.dto.NewUser;
import com.kelelas.model.entity.RoleType;
import com.kelelas.model.entity.User;
import com.kelelas.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand implements Command {
    private final UserService userService;

    public RegistrationCommand() {
        userService = new UserService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        String nameUkr = request.getParameter("nameUkr");
        String nameEng = request.getParameter("nameEng");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        return save(new NewUser.Builder()
                        .nameUkr(nameUkr)
                        .nameEng(nameEng)
                        .email(email)
                        .password(password)
                        .build());

    }
    public String save(NewUser newUser){

        if (verify(newUser)) {
            try {
                userService.save(new User.Builder()
                        .nameUkr(newUser.getNameUkr())
                        .nameEng(newUser.getNameEng())
                        .email(newUser.getEmail())
                        .password(new BCryptPasswordEncoder().encode(newUser.getPassword()))
                        .role(RoleType.USER)
                        .isActive(true)
                        .balance(5000)
                        .build());
                return "redirect:/login";
            } catch (Exception e) {
                return "redirect:/registration?error=userAlreadyExist";
            }
        }else
            return "redirect:/registration?regex=error";
    }
    public boolean verify(NewUser newUser){
        return newUser.getEmail().matches(Regex.EMAIL_REGEX)
                && newUser.getNameEng().matches(Regex.NAME_REGEX)
                && newUser.getNameUkr().matches(Regex.NAME_UKR_REGEX)
                && newUser.getPassword().matches(Regex.PASSWORD_REGEX);

    }
}
