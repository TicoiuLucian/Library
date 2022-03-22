package library.rest;

import library.generators.RandomTokenGenerator;
import library.entity.MyUser;
import library.service.UserService;
import library.service.impl.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/register")
public class RegisterController {

    @Autowired
    private EmailSender emailSender;

    @Autowired
    private RandomTokenGenerator randomTokenGenerator;

    @Autowired
    UserService userService;

    @PostMapping
    public String registerUser(@RequestBody MyUser user) {
        userService.saveUser(user);
        String token = randomTokenGenerator.randomToken();
        emailSender.sendEmail("taf.florina@gmail.com", "Test Email", token);
        return "register";

    }

}
