package es.codeurjc.bof.controller;

import java.io.IOException;
import java.net.URI;
import java.security.Principal;
import java.sql.SQLException;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import es.codeurjc.bof.model.User;
import es.codeurjc.bof.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api/user")
public class UserRestController {
    
    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public ResponseEntity<User> me(HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        User user = userService.getUserByUsername(principal.getName());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(user);
    }

    @PostMapping("/")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);

        if (createdUser == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.created(URI.create("/api/user/" + createdUser.getId())).body(createdUser);
        }
    }
    

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User newUser) throws SQLException {
        User dbUser = this.userService.getUser(id);

        if (dbUser == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        newUser.setImageFile(BlobProxy.generateProxy(dbUser.getImageFile().getBinaryStream(), dbUser.getImageFile().length()));
            
        User updatedUser = this.userService.updateUser(id, newUser);

        if (updatedUser == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok(updatedUser);
        }
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<Object> getProductImg(@PathVariable long id) throws SQLException{
        User user = userService.getUser(id);
        
        if (user.getImageFile() != null) {
            Resource file = new InputStreamResource(user.getImageFile().getBinaryStream());
            return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                .contentLength(user.getImageFile().length())
                .body(file);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/image")
    public ResponseEntity<User> updateImage(@PathVariable Long id, @RequestBody MultipartFile imageFile) throws IOException {
        User dbUser = userService.getUser(id);

        if (dbUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            dbUser.setImageFile(BlobProxy.generateProxy(imageFile.getInputStream(), imageFile.getSize()));
            userService.updateUser(id, dbUser);
            return ResponseEntity.ok(dbUser);
        }
    }
    
}
