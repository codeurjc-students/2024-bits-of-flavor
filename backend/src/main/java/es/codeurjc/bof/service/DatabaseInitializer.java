package es.codeurjc.bof.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import es.codeurjc.bof.model.Offer;
import es.codeurjc.bof.model.Product;
import es.codeurjc.bof.model.Ticket;
import es.codeurjc.bof.model.User;
import es.codeurjc.bof.repository.OfferRepository;
import es.codeurjc.bof.repository.ProductRepository;
import es.codeurjc.bof.repository.TicketRespository;
import es.codeurjc.bof.repository.UserRepository;
import jakarta.annotation.PostConstruct;

@Service
public class DatabaseInitializer {

    private final TicketRespository ticketRespository;
    
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    DatabaseInitializer(TicketRespository ticketRespository) {
        this.ticketRespository = ticketRespository;
    }

    @PostConstruct
    public void init() throws IOException {
        
        //Sample Products
        Product product1 = new Product("Carrillera de cerdo en salsa Pedro Ximénez",
        "Carrillera de cerdo, salsa PX (jugo de carne, vino pedro ximenez, cebolla, sal, pimienta, almidón de maíz), patata, boniato, zanahoria, hierbas provenzales, judía plana, zanahoria, cebolla, pasta de ajo, aceite de oliva, sal, pimienta.",
        450, 8.15f, 558, 41, 30, 27, "Carne");
        setProductImage(product1, "static/images/carrillera-cerdo.jpg");
        productRepository.save(product1);

        Product product2 = new Product("Salmón con salsa al cava",
        "Salmón, salsa de cava (cava, caldo de pescado, nata, cebolla, tomate frito casero, aceite de oliva, almidón de maíz), tomate, boniato, ajo, queso parmesano, pan rallado, ajo, perejil, vinagre, aceite de oliva virgen, sal, pimienta, especias cajún.",
        450, 8.95f, 477, 35, 30, 22, "Pescado");
        setProductImage(product2, "static/images/salmon-cava.jpg");
        productRepository.save(product2);

        Product product3 = new Product("Arroz de magro y setas", 
        "Arroz, magro, caldo de pollo y cerdo, caldo de setas, setas, cebolla, salmorreta, aceite de oliva virgen, sal, colorante natural.", 
        420, 6.95f, 714, 23, 64, 41, "Carne");
        setProductImage(product3, "static/images/arroz-costilla-setas.jpg");
        productRepository.save(product3);

        Product product4 = new Product("Lasaña boloñesa", 
        "Pasta de trigo, salsa boloñesa (tomate frito casero, carne de ternera, zanahoria, cebolla, vino tinto, apio, sal, pimienta), bechamel (leche, harina de trigo, mantequilla, nuez moscada, pimienta negra, sal) queso mozzarella, aceite de oliva virgen, sal, pimienta.", 
        450, 7.25f, 648, 28, 51, 36, "Pasta");
        setProductImage(product4, "static/images/lasanya-bolonyesa.jpg");
        productRepository.save(product4);

        Product product5 = new Product("Pollo tandoori con arroz al azafrán y dátiles", 
        "Pollo, arroz, salsa tandoori (nata , yogurt, caldo de pollo, ajo, especia tandoori), dátil, azafrán, aceite de oliva virgen, sal, pimienta.", 
        450, 7.45f, 760, 50, 82, 26, "Carne");
        setProductImage(product5, "static/images/pollo-tandori.jpg");
        productRepository.save(product5);
        
        Product product6 = new Product("Merluza a la andaluza con pisto y patata", 
        "Merluza, pisto (zanahoria, cebolla, boniato, tomate), patata, harina de trigo, huevo, aceite de girasol, aceite de oliva virgen, sal, pimienta.", 
        420, 7.95f, 491, 24, 49, 22, "Pescado");
        setProductImage(product6, "static/images/merluza-andaluza.jpg");
        productRepository.save(product6);

        Product product7 = new Product("Pollo en salsa sueca", 
        "Pechuga de pollo, salsa sueca (caldo de pollo, nata, cebolla, mostaza, mantequilla, salsa perrins, sal, pimienta, almidón de maíz), patata, judía, seta, cebolla, aceite de oliva virgen, sal, pimienta.", 
        450, 7.50f, 486, 36, 29, 25, "Carne");
        setProductImage(product7, "static/images/pollo-salsa-sueca.jpg");
        productRepository.save(product7);

        Product product8 = new Product("Pavo en salsa bacon", 
        "Pechuga de pavo, salsa de bacon (caldo de pollo, bacon, nata, cebolla, mantequilla, coñac, sal, pimienta, almidón de maíz), patata, boniato, espinacas, pasas, ajo, piñones, hierbas provenzales, sal, pimienta, aceite de oliva virgen.", 
        450, 7.25f, 463, 30, 45, 21, "Carne");
        setProductImage(product8, "static/images/pollo-salsa-bacon.jpg");
        productRepository.save(product8);

        Product product9 = new Product("Tagliatelle en salsa de espinacas y gamba", 
        "Tagliatelle, caldo de pescado, gamba, nata, espinacas, cebolla, vino blanco, mantequilla, aceite de oliva virgen, ajo, pimienta, sal.", 
        420, 7.25f, 529, 23, 55, 23, "Pasta");
        setProductImage(product9, "static/images/tagliatelle-espinaca-gambas.jpg");
        productRepository.save(product9);

        Product product10 = new Product("Lomo saltado de ternera", 
        "Ternera, arroz, pimiento rojo, pimiento verde, cebolla, tomate, salsa de soja, vinagre de vino, pasta de ajo, pasta ají amarillo, sésamo, aceite de oliva virgen, orégano, sal, pimienta.", 
        450, 8.15f, 459, 49, 46, 9, "Carne");
        setProductImage(product10, "static/images/lomo-saltado-ternera.jpg");
        productRepository.save(product10);

        for (int i = 1; i <= 4; i++) {
            String username = "bot" + i;
            String email = "bot" + i + "@gmail.com";
            String password = "botpass" + i;
            String imagePath = "static/images/profile" + i + ".jpg";
            
            User user = new User(username, email, "123456789", passwordEncoder.encode(password), "USER");
            setUserImage(user, imagePath);
            userRepository.save(user);
        }

        User admin = new User("admin", "admin@gmail.com", "123456789", passwordEncoder.encode("pass"), "USER", "ADMIN");
        setUserImage(admin, "static/images/profile5.jpg");
        userRepository.save(admin);

        List<Product> productList = productRepository.findAll();
        List<User> userList = userRepository.findAll();
        generateTickets(userList, productList);
        for (int i = 0; i < 6; i++) {
            Product product = productList.get(i);
            Offer offer1 = new Offer(product, "Invierno 2024", LocalDate.parse("2024-12-15"), 30, 
            (Math.round(product.getPrice() * 0.7 * 100.0) / 100.0));
            Offer offer2 = new Offer(product, "Primavera 2025", LocalDate.parse("2025-03-15"), 30, 
            (Math.round(product.getPrice() * 0.7 * 100.0) / 100.0));
            Offer offer3 = new Offer(product, "Hot Sale", LocalDate.now().plus(15, ChronoUnit.DAYS), 50, 
            (Math.round(product.getPrice() * 0.5 * 100.0) / 100.0));
            offerRepository.save(offer1);
            offerRepository.save(offer2);
            offerRepository.save(offer3);
        }

    }

    public void setProductImage(Product product, String path) throws IOException{
        Resource image = new ClassPathResource(path);
        product.setImageFile(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
    }

    public void setUserImage(User user, String path) throws IOException {
        Resource image = new ClassPathResource(path);
        user.setImageFile(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
    }

    public void generateTickets(List<User> userList, List<Product> productList) {
        Random random = new Random();
        
        LocalDate today = LocalDate.now();
        LocalDate startDate = today.minus(7, ChronoUnit.DAYS);
        LocalDate endDate = today.plus(14, ChronoUnit.DAYS);

        for (User user: userList) {
            for (Product product: productList) {
                if (random.nextBoolean()) {
                    int numPurchases = random.nextInt(3) + 1;
                    for (int i = 0; i < numPurchases; i++) {
                        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
                        long randomDays = random.nextLong(daysBetween + 1);
                        LocalDate randomDate = startDate.plusDays(randomDays);
                
                        Ticket ticket = new Ticket(user, product, randomDate);
                        ticketRespository.save(ticket);
                    }
                }
            }
        }
    }
}
