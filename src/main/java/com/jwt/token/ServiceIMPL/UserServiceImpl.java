package com.jwt.token.ServiceIMPL;

import com.jwt.token.Model.User;
import com.jwt.token.Repository.UserRepository;
import com.jwt.token.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User newUser= userRepository.save(user);
        return newUser;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
