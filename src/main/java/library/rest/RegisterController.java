package library.rest;

import library.entity.Role;
import library.mapper.MyUserMapper;
import library.rest.model.MyUserDTO;
import library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/register")
    public String registerForm(Model model) {
        MyUserDTO userDTO = new MyUserDTO();
        userDTO.setAccountNonExpired(true);
        userDTO.setAccountNonLocked(true);
        userDTO.setCredentialsNonExpired(true);
        userDTO.setEnabled(true);

        model.addAttribute("user", userDTO);

        return "register";
    }


    @PostMapping(value = "/register")
    public String registerUser(@ModelAttribute("user") @RequestBody MyUserDTO myUserDTO) {
        if (myUserDTO.getPassword().equalsIgnoreCase(myUserDTO.getPasswordConfirm())) {
            myUserDTO.setRoles(Set.of(new Role("ROLE_USER")));
            MyUserMapper.fromEntityToDTO(userService.saveUser(MyUserMapper.fromDtoToEntity(myUserDTO)));
            return "register-success";
        } else {
            return "register";
        }
    }

    @GetMapping(value = "/user/all")
    public String getAllUsers(Model model){
        model.addAttribute("users", userService.findAll().stream().map(MyUserMapper::fromEntityToDTO).collect(Collectors.toList()));
        return "all-users";
    }

}
