package com.Rest.GolfMax.API.Services;

import com.Rest.GolfMax.API.Repositories.UserRepository;
import com.Rest.GolfMax.API.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public List<User> listAllUsers() {
        return userRepository.findAll();
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User getUserById (Long id) {
        return userRepository.findById(id).get();
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User getStoredUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User getStoredPassword(String password) {
        return userRepository.findByPassword(password);
    }

    public User getStoredEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User getStoredUserData(String username, String password) {
        return userRepository.findUserData(username, password);
    }
    
}
