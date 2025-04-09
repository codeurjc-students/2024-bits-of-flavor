package es.codeurjc.bof.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.codeurjc.bof.model.Offer;
import es.codeurjc.bof.model.Product;
import es.codeurjc.bof.model.Ticket;
import es.codeurjc.bof.model.User;
import es.codeurjc.bof.repository.TicketRespository;

@Service
public class TicketService {

    @Autowired
    private TicketRespository ticketRespository;

    public List<Ticket> getTicketByUser(User user) {
        return ticketRespository.findByUserOrderByDate(user);
    }

    public Ticket getTicket(Long id) {
        Optional<Ticket> ticket = ticketRespository.findById(id);

        if (ticket.isPresent()) {
            return ticket.get();
        } else {
            return null;
        }
    }

    public Page<Ticket> getPageableTicketByUser(User user, Pageable pageable) {
        return ticketRespository.findPageByUserOrderByDate(user, pageable);
    }

    public Page<Ticket> getPageableTickets(Pageable pageable) {
        return ticketRespository.findAllByOrderByDateDesc(pageable);
    }

    public Ticket newTicket(User user, Product product, LocalDate date) {
        Ticket ticket = new Ticket(user, product, date);
        ticketRespository.save(ticket);
        return ticket;
    }

    public Ticket setOffer(Ticket ticket, Offer offer) {
        ticket.setOffer(offer);
        ticketRespository.save(ticket);
        return ticket;
    }

    public Ticket updateTicket(Long id, Ticket ticket) {
        ticket.setId(id);
        ticketRespository.save(ticket);
        return ticket;
    }
}
