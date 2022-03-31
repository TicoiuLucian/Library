package library.rest;

import library.entity.MyUser;
import library.service.email.BodyBuilderService;
import library.service.email.EmailSender;
import library.service.token.RandomTokenService;
import library.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {

    @Autowired
    UserService userService;




    @GetMapping(value = "/register")
    public String registerForm(Model model) {
        MyUser user = new MyUser();
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(false);

        model.addAttribute("user", user);

        return "register";
    }

    @PostMapping(value = "/register")
    public String registerUser(@ModelAttribute("user") @RequestBody MyUser user) {
        if (user.getPassword().equalsIgnoreCase(user.getPasswordConfirm())) {
            userService.saveUser(user);
            return "register-success";
        } else {
            return "register";
        }
    }

}
