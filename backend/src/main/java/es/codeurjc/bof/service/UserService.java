package es.codeurjc.bof.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.bof.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
}
