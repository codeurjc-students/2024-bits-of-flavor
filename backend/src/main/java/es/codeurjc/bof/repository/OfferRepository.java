package es.codeurjc.bof.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import es.codeurjc.bof.model.Offer;
import es.codeurjc.bof.model.Product;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long>{

    Optional<Offer> getByProduct(Product product);
}