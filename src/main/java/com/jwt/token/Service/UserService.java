package com.jwt.token.Service;

import com.jwt.token.Model.User;
import java.util.List;

public interface UserService {

    User createUser(User user);
    List<User> getAllUsers();
}
