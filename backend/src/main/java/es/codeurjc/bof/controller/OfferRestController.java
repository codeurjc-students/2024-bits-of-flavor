package es.codeurjc.bof.controller;

import java.net.URI;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.bof.model.Offer;
import es.codeurjc.bof.model.Product;
import es.codeurjc.bof.service.OfferService;
import es.codeurjc.bof.service.ProductService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/offers")
public class OfferRestController {
    
    @Autowired
    OfferService offerService;

    @Autowired
    ProductService productService;

    @GetMapping("/")
    public Collection<Offer> getAllOffers() {
        return offerService.getAllOffers();
    }

    @GetMapping("/page")
    public Page<Offer> getAllOffers(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size,
                                        @RequestParam(defaultValue = "false") boolean active){
        if(active){
            return offerService.getActivePageableOffers(PageRequest.of(page, size)); 
        } else {
            return offerService.getPageableOffers(PageRequest.of(page, size));
        }
        
    }    

    @GetMapping("/{productId}")
    public ResponseEntity<Offer> getOfferByProduct(@PathVariable Long productId) {
        Product product = productService.getProduct(productId);
        if (product == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
        } else {
            Offer offer = offerService.getOfferByProduct(product);
            return ResponseEntity.ok(offer);
        }
    }
    
    
    
    @PostMapping("/{id}")
    public ResponseEntity<Offer> addOffer (@RequestBody Offer offer, @PathVariable Long id) {
        Product product = productService.getProduct(id);
        if (product == null){return new ResponseEntity<>(HttpStatus.NOT_FOUND);}

        Offer createdOffer = offerService.createOffer(offer, product);
        if (createdOffer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.created(URI.create("/api/offers/" + createdOffer.getId())).body(createdOffer);
        }
    } 
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Offer> deleteOffer(@PathVariable Long id) {
        Offer deletedOffer = this.offerService.deleteOffer(id);
        if (deletedOffer == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok(deletedOffer);
        }
    }
    
}
