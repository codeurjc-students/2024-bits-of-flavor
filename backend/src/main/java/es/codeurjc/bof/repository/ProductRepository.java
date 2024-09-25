package es.codeurjc.bof.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.bof.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
