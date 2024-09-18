package es.codeurjc.bof.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.bof.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
