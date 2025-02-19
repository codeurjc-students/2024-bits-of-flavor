package es.codeurjc.bof.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.bof.model.Product;
import es.codeurjc.bof.model.Ticket;
import es.codeurjc.bof.model.User;
import es.codeurjc.bof.repository.TicketRespository;

@Service
public class TicketService {

    @Autowired
    private TicketRespository ticketRespository;
    
    public Ticket newTicket(User user, Product product, LocalDate date){
        Ticket ticket = new Ticket(user, product, date);
        ticketRespository.save(ticket);
        return ticket;
    }
}
