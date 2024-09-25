package es.codeurjc.bof.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.bof.model.Product;
import es.codeurjc.bof.repository.ProductRepository;
import jakarta.annotation.PostConstruct;

@Service
public class DatabaseInitializer {
    
    @Autowired
    private ProductRepository productRepository;

    @PostConstruct
    public void init() throws IOException {
        
        //Sample Products
        Product product1 = new Product("test", "info", 0, 0, 0, 0, 0, 0);
        productRepository.save(product1);
    }
}
