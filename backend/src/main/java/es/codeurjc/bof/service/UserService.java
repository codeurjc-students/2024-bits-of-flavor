package es.codeurjc.bof.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import es.codeurjc.bof.model.User;
import es.codeurjc.bof.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User getUser(Long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()){
            return user.get();
        } else {
            return null;
        }
    }
    
    public User getUserByUsernameAndPassword(String username, String password){
        Optional<User> user = userRepository.getUserByUsernameAndEncodedPassword(username, password);
        if (user.isPresent()){
            return user.get();
        } else {
            return null;
        }
    }

    public User getUserByUsername(String username){
        Optional<User> user = userRepository.getUserByUsername(username);
        if (user.isPresent()){
            return user.get();
        } else {
            return null;
        }
    }

    public User createUser(User newUser) {
        Optional<User> user = userRepository.getByUsername(newUser.getUsername());

        if (user.isPresent()){
            return null;
        } else {
            User createdUser = new User();
            createdUser.setUsername(newUser.getUsername());
            createdUser.setEmail(newUser.getEmail());
            createdUser.setEncodedPassword(passwordEncoder.encode(newUser.getEncodedPassword()));
            createdUser.setPhoneNumber(newUser.getPhoneNumber());
            createdUser.setRoles(List.of("USER"));
            userRepository.save(createdUser);
            return createdUser;
        }
    }

    public User updateUser(Long id, User updatedUser) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            updatedUser.setId(id);
            userRepository.save(updatedUser);
            return updatedUser;
        } else {
            return null;
        }
    }
}
