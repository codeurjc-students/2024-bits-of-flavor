package es.codeurjc.bof.controller;

import java.net.URI;
import java.security.Principal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.bof.model.Offer;
import es.codeurjc.bof.model.Product;
import es.codeurjc.bof.model.Ticket;
import es.codeurjc.bof.model.User;
import es.codeurjc.bof.service.OfferService;
import es.codeurjc.bof.service.ProductService;
import es.codeurjc.bof.service.TicketService;
import es.codeurjc.bof.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/ticket")
public class TicketRestController {
    
    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OfferService officeService;

    @PostMapping("/{id}")
    public ResponseEntity<Ticket> processPayment(@PathVariable Long id, @RequestBody LocalDate date, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        User user = userService.getUserByUsername(principal.getName());
        Product product = productService.getProduct(id);
        if ((user == null) || (product==null)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            Ticket createdTicket = ticketService.newTicket(user, product, date);
            if (product.isActive()) {
                Offer offer = officeService.getOfferByProduct(product);
                createdTicket = ticketService.setOffer(createdTicket, offer);
            }
            return ResponseEntity.created(URI.create("/api/ticket/" + createdTicket.getId())).body(createdTicket);
        }
    }

    @GetMapping("/")
    public Page<Ticket> getMyTickets (@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size,
                                        HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        if (principal != null) {
            User user = userService.getUserByUsername(principal.getName());
            if (user != null) {
                return ticketService.getPageableTicketByUser(user, PageRequest.of(page, size));
            }
        }
        return null;
    }
    
   @GetMapping("/all")
   public Page<Ticket> getAllTickets (@RequestParam(defaultValue = "0") int page,
   @RequestParam(defaultValue = "10") int size){
        return ticketService.getPageableTickets(PageRequest.of(page, size));
   }

   @PutMapping("/{id}")
   public ResponseEntity<Ticket> updateTicket(@PathVariable Long id, @RequestBody Ticket newTicket) {
        Ticket dbTicket = ticketService.getTicket(id);
        if (dbTicket == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Ticket updatedTicket = ticketService.updateTicket(id, newTicket);
        return ResponseEntity.ok(updatedTicket);
   }
    
}
