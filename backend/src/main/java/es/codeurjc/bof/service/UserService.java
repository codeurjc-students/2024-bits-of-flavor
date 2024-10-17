package es.codeurjc.bof.service;

import java.io.IOException;
import java.util.Optional;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import es.codeurjc.bof.model.User;
import es.codeurjc.bof.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

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
