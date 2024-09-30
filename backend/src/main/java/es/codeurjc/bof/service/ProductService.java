package es.codeurjc.bof.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.bof.model.Product;
import es.codeurjc.bof.repository.ProductRepository;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(long id) {
        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()){
            return product.get();
        } else {
            return null;
        }
    }
}
