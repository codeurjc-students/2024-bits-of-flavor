package es.codeurjc.bof.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.bof.model.User;
import es.codeurjc.bof.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    public User getUserByUsernameAndPassword(String username, String password){
        Optional<User> user = userRepository.getUserByUsernameAndEncodedPassword(username, password);
        if (user.isPresent()){
            return user.get();
        } else {
            return null;
        }
    }
}
