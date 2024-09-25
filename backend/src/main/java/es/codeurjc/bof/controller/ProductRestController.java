package es.codeurjc.bof.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.bof.model.Product;
import es.codeurjc.bof.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {
    
    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public Collection<Product> getAllProducts() {
        return productService.getAllProducts();
    }
    
}
