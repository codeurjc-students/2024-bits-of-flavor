package es.codeurjc.bof.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.codeurjc.bof.model.Product;
import java.util.List;
import es.codeurjc.bof.model.Ticket;


public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> getByName(String name);

    @Query("SELECT p, t.product " +
       "FROM Product p " +
       "JOIN Ticket t ON t.product = p " +
       "WHERE t IN :tickets ")
    List<Product> findByTicketsGroupedAndOrderedByCategory(List<Ticket> tickets);
}
