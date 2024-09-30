package es.codeurjc.bof.controller;

import java.sql.SQLException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.bof.model.Product;
import es.codeurjc.bof.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/product")
public class ProductRestController {
    
    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public Collection<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable long id) {
        Product product = productService.getProduct(id);
        if (product == null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(product);
        }
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<Object> getProductImg(@PathVariable long id) throws SQLException{
        Product product = productService.getProduct(id);
        
        if (product.getImageFile() != null) {
            Resource file = new InputStreamResource(product.getImageFile().getBinaryStream());
            return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                .contentLength(product.getImageFile().length())
                .body(file);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
}
