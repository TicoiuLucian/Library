package library.service.token;

import library.entity.MyUser;

public interface
RandomTokenService {
    String randomToken(MyUser user);
}
