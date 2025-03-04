package es.codeurjc.bof.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.bof.model.Offer;
import es.codeurjc.bof.model.Product;
import es.codeurjc.bof.service.OfferService;
import es.codeurjc.bof.service.ProductService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/offers")
public class OfferRestController {
    
    @Autowired
    OfferService offerService;

    @Autowired
    ProductService productService;
    
    @PostMapping("/{id}")
    public ResponseEntity<Offer> addOffer (@RequestBody Offer offer, @PathVariable Long id) {
        Product product = productService.getProduct(id);
        Offer createdOffer = offerService.createOffer(offer, product);
        if (createdOffer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.created(URI.create("/api/offers/" + createdOffer.getId())).body(createdOffer);
        }
    }    
}
