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

    @GetMapping(value = "/activation/{username}")
    public String registerForm(@PathVariable String username, Model model) {
        MyUser myUser = new MyUser();
        model.addAttribute("user", userService.findUserByUserName(username));
        return "activation";
    }

    @PostMapping(value = "/activation/{username}")
    public String registerUser(@ModelAttribute("user") @RequestBody MyUser user) {
        user.setRandomToken(userService.findUserByUserName(user.getUsername()).getRandomToken());
        if (user.getRandomToken().equals(user.getRandomTokenEmail())) {
            userService.findUserByUserName(user.getUsername()).setEnabled(true);
            userRepository.save(userService.findUserByUserName(user.getUsername()));
            return "redirect:/index";
        } else {
            return "redirect:/activation/{username}";
        }
    }

}
