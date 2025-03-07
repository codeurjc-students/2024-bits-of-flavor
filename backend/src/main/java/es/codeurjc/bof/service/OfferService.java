package es.codeurjc.bof.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.bof.model.Offer;
import es.codeurjc.bof.model.Product;
import es.codeurjc.bof.repository.OfferRepository;

@Service
public class OfferService {
    
    @Autowired
    private OfferRepository offerRepository;

    public Collection<Offer> getAllOffers () {
        return this.offerRepository.findAll();
    }

    public Offer createOffer(Offer offer, Product product){
        Offer createdOffer = new Offer();
        createdOffer.setName(offer.getName());
        createdOffer.setExpDate(offer.getExpDate());
        createdOffer.setDiscount(offer.getDiscount());
        createdOffer.setProduct(product);
        createdOffer.setPrice(product.getPrice() * (100 - offer.getDiscount()) / 100);
        offerRepository.save(createdOffer);
        return createdOffer;
    }

    public Offer deleteOffer(Long id){
        Optional<Offer> offer = offerRepository.findById(id);
        if (offer.isPresent()) {
            offerRepository.deleteById(id);
            return offer.get();
        } else {
            return null;
        }
    }
}
