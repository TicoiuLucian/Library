package library.service.user.impl;

import library.entity.MyUser;
import library.entity.Role;
import library.repository.RoleRepository;
import library.repository.UserRepository;
import library.service.email.BodyBuilderService;
import library.service.email.EmailSender;
import library.service.token.RandomTokenService;
import library.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    BodyBuilderService bodyBuilderService;

    @Autowired
    EmailSender emailSender;

    @Autowired
    private RandomTokenService randomTokenService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public MyUser findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public MyUser findUserByUserName(String userName) {
        return userRepository.findByUsernameIgnoreCase(userName);
    }

    public boolean findUserByUserNameAndPassword(String userName, String password) {
        final Optional<MyUser> myUser = Optional.ofNullable(userRepository.findByUsernameIgnoreCase(userName));
        return myUser.filter(user -> BCrypt.checkpw(password, user.getPassword())).isPresent();
    }

    public List<MyUser> findAll() {
        return userRepository.findAll();
    }

    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    public MyUser saveUser(MyUser u) {
        MyUser user = new MyUser(u);
        user.setPassword(new BCryptPasswordEncoder().encode(u.getPassword()));
        user.setRandomToken(randomTokenService.randomToken());
        emailSender.sendEmail(user.getEmail(), "Activate your Account", bodyBuilderService.emailBody(u) + "\n" + user.getRandomToken());
        u.getRoles().forEach(role -> {
            final Role roleByName = roleRepository.findByName(role.getName());
            if (roleByName == null)
                user.getRoles().add(roleRepository.save(role));
            else {
                role.setId(roleByName.getId());
            }
        });
        return userRepository.save(user);
    }


}
