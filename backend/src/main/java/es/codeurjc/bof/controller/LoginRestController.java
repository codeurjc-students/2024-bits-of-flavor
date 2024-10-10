package es.codeurjc.bof.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.bof.model.User;
import es.codeurjc.bof.security.jwt.AuthResponse;
import es.codeurjc.bof.security.jwt.LoginRequest;
import es.codeurjc.bof.security.jwt.UserLoginService;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
public class LoginRestController {

    @Autowired
    private UserLoginService userLoginService;
    
    @PostMapping(value="/login",consumes = MediaType.APPLICATION_JSON_VALUE)   
    public ResponseEntity<AuthResponse> login(
                @CookieValue(name = "accessToken", required = false) String accessToken,
                @CookieValue(name = "refreshToken", required = false) String refreshToken,
                @RequestBody LoginRequest loginRequest) {
            
            return userLoginService.login(loginRequest, accessToken, refreshToken);
    }
}
