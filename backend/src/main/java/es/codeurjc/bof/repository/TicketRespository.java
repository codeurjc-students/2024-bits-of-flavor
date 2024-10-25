package es.codeurjc.bof.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.bof.model.Ticket;

public interface TicketRespository extends JpaRepository<Ticket, Long>{
    
}
