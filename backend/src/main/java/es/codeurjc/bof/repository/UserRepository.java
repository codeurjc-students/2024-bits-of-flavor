package es.codeurjc.bof.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.bof.model.User;
import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> getByUsername(String username);
    Optional<User> getUserByUsernameAndEncodedPassword(String username, String password);
    
}
