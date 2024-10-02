package com.mb.DineEase.service.user;

import com.mb.DineEase.model.user.User;
import com.mb.DineEase.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User getUserById(String id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    public User getRestaurantManagerById(String id) {
        User user = getUserById(id);
        if (user == null || !user.getRole().equals("RESTAURANT_MANAGER")) {
            throw new NoSuchElementException("User not found");
        }
        return user;
    }

    public User getUserByEmail(String email) {
        Optional<User> user = userRepository.findUserByEmail(email);
        return user.orElse(null);
    }
}
