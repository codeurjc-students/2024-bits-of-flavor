package es.codeurjc.bof.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.bof.service.UserService;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
public class LoginRestController {

    @Autowired
    private UserService userService;
    
   /* @PostMapping("/login")
    public ResponseEntity<AuthResponse> login (
        @CookieValue(name = "accessToken", required = false) String acessToken,
        @CookieValue(name = "refreshToken", required = false) String refreshToken,
        @RequestBody LoginRequest loginRequest) {
    }
    */
}
