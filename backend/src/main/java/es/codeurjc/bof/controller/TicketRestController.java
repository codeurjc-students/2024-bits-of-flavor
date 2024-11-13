package es.codeurjc.bof.controller;

import java.net.URI;
import java.security.Principal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.bof.model.Product;
import es.codeurjc.bof.model.Ticket;
import es.codeurjc.bof.model.User;
import es.codeurjc.bof.service.ProductService;
import es.codeurjc.bof.service.TicketService;
import es.codeurjc.bof.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/ticket")
public class TicketRestController {
    
    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @PostMapping("/{id}")
    public ResponseEntity<Ticket> processPayment(@PathVariable Long id, @RequestBody LocalDate date, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        User user = userService.getUserByUsername(principal.getName());
        Product product = productService.getProduct(id);
        if ((user == null) || (product==null)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            Ticket createdTicket = ticketService.newTicket(user, product, date);
            return ResponseEntity.created(URI.create("/api/ticket/" + createdTicket.getId())).body(createdTicket);
        }
    }
}
