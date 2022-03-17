package library.rest;

import library.entity.MyUser;
import library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/register")
public class RegisterController {

    @Autowired
    UserService userService;

    @PostMapping
    public String registerUser(@RequestBody MyUser user) {
        userService.saveUser(user);
        return "register";

    }

}
