package es.codeurjc.bof.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.bof.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> getByName(String name);
    
}
