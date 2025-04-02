package es.codeurjc.bof.controller;

import java.io.IOException;
import java.net.URI;
import java.security.Principal;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import es.codeurjc.bof.model.Product;
import es.codeurjc.bof.model.Ticket;
import es.codeurjc.bof.model.User;
import es.codeurjc.bof.service.ProductService;
import es.codeurjc.bof.service.TicketService;
import es.codeurjc.bof.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/product")
public class ProductRestController {
    
    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserService userService;

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

    @PostMapping("/")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);

        if (createdProduct == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.created(URI.create("/api/product/" + createdProduct.getId())).body(createdProduct);
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

    @PutMapping("/{id}/image")
    public ResponseEntity<Product> updateImage(@PathVariable Long id, @RequestBody MultipartFile imageFile) throws IOException {
        Product dbProduct = productService.getProduct(id);

        if (dbProduct == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            dbProduct.setImageFile(BlobProxy.generateProxy(imageFile.getInputStream(), imageFile.getSize()));
            productService.updateProduct(id, dbProduct);
            return ResponseEntity.ok(dbProduct);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct (@PathVariable Long id){
        Product deletedProduct = productService.deleteProduct(id);

        if (deletedProduct == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok(deletedProduct);
        }
    }

     @GetMapping("/recommend")
    public Collection<Product> getMethodName(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        if (principal != null) {
            User user = userService.getUserByUsername(principal.getName());
            List<Ticket> ticket_list = ticketService.getTicketByUser(user);
            List<Product> product_list = productService.getProductByTicket(ticket_list);
            Map<String, List<Product>> groupedByCategory = product_list.stream()
                .collect(Collectors.groupingBy(Product::getCategory));

            List<Product> sortedProducts = groupedByCategory.entrySet().stream()
                .sorted((entry1, entry2) -> Integer.compare(entry2.getValue().size(), entry1.getValue().size())) // Orden descendente por tamaño
                .flatMap(entry -> entry.getValue().stream())
                .distinct()
                .limit(4)
                .collect(Collectors.toList());
            
            if (sortedProducts.isEmpty()){
                return product_list.subList(0, 4);
            }
            return sortedProducts;
        }
        List<Product> product_list = productService.getAllProducts();
        return product_list.subList(0, 4);
    }   
    
}
