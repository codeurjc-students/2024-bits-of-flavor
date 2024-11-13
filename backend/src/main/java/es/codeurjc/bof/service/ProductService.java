package es.codeurjc.bof.service;

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

    public Product getProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()){
            return product.get();
        } else {
            return null;
        }
    }

    public Product createProduct(Product newProduct) {
        Optional<Product> product = productRepository.getByName(newProduct.getName());

        if (product.isPresent()){
            return null;
        } else {
            Product createdProduct = new Product();
            createdProduct.setName(newProduct.getName());
            createdProduct.setInfo(newProduct.getInfo());
            createdProduct.setWeight(newProduct.getWeight());
            createdProduct.setPrice(newProduct.getPrice());
            createdProduct.setCal(newProduct.getCal());
            createdProduct.setProtein(newProduct.getProtein());
            createdProduct.setFat(newProduct.getFat());
            createdProduct.setCarbo(newProduct.getCarbo());
            createdProduct.setCategory(newProduct.getCategory());
            productRepository.save(createdProduct);
            return createdProduct;
        }
    }

    public Product updateProduct(Long id, Product updateProduct) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()){
            updateProduct.setId(id);
            productRepository.save(updateProduct);
            return updateProduct;
        } else {
            return null;
        }
    }

    public Product deleteProduct(Long id){
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()){
            productRepository.deleteById(id);
            return product.get();
        } else {
            return null;
        }
    }
}
