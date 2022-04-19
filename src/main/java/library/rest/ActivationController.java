package library.rest;

import library.entity.MyUser;
import library.repository.UserRepository;
import library.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ActivationController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/activation/{randomToken}")
    public String registerForm(@PathVariable String randomToken, Model model) {
        MyUser myUser = new MyUser();
        model.addAttribute("user", userService.findUserByRandomToken(randomToken));
        return "activation";
    }

    @PostMapping(value = "/activation/{randomToken}")
    public String registerUser(@ModelAttribute("user") @RequestBody MyUser user) {
            userService.findUserByRandomToken(user.getRandomToken()).setEnabled(true);
            userRepository.save(userService.findUserByRandomToken(user.getRandomToken()));
            return "activation-success";

    }

}
