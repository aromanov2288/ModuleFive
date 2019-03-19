package ru.romanov.modulefive.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.romanov.modulefive.form.LoginForm;

@Controller
public class SecurityPagesController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginPage(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET, params = "error")
    public String showErrorLoginPage(Model model) {
        model.addAttribute("mes", "Логин и/или пароль не верны!");
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }
}
