package es.codeurjc.bof.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.bof.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> getUserByUsernameAndEncodedPassword(String username, String password);
    
}
