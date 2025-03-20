package es.codeurjc.bof.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.bof.model.Ticket;
import es.codeurjc.bof.model.User;

public interface TicketRespository extends JpaRepository<Ticket, Long>{
    
    List<Ticket> findByUser(User user);
}
