package es.codeurjc.bof.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.bof.model.User;
import es.codeurjc.bof.service.UserService;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
public class LoginRestController {

    @Autowired
    private UserService userService;
    
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user){
        User createdUser = userService.getUserByUsernameAndPassword(user.getUsername(), user.getEncodedPassword());
        
        if (createdUser == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok(createdUser);
        }
    }
}
