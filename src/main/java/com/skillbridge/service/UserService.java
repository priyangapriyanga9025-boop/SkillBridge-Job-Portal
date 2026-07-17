package com.skillbridge.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.skillbridge.entity.User;
import com.skillbridge.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
        }

    public User loginUser(String email, String password) {

    User user = userRepository.findByEmail(email);

    if (user != null && user.getPassword().equals(password)) {
        return user;
    }

    return null;
}
public List<User> getAllUsers() {
    return userRepository.findAll();
}
public long getUserCount() {
    return userRepository.count();
}

  }
