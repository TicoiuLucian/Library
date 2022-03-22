package library.generators;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RandomTokenGenerator {

    public String randomToken() {
        String tokenChars = "ABCDEFGHIJKLmnopqrstuvxyz1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 8) { // length of the random string.
            int index = (int) (rnd.nextFloat() * tokenChars.length());
            salt.append(tokenChars.charAt(index));
        }
        return salt.toString();
    }

}
