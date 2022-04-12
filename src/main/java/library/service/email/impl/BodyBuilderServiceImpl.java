package library.service.email.impl;

import library.entity.MyUser;
import library.service.email.BodyBuilderService;
import library.service.token.RandomTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BodyBuilderServiceImpl implements BodyBuilderService {

    @Autowired
    RandomTokenService randomTokenService;


    @Override
    public String emailBody(MyUser myUser) {
        return "Hello," +
                "In order to activate your account please access the following link:\n" +
                "http://localhost:8080/activation/" + myUser.getRandomToken();
    }
}
