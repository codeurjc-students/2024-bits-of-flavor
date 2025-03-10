package es.codeurjc.bof.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import es.codeurjc.bof.model.Offer;
import es.codeurjc.bof.model.Product;
import java.util.List;


@Repository
public interface OfferRepository extends JpaRepository<Offer, Long>{

    List<Offer> findByProduct(Product product);
}