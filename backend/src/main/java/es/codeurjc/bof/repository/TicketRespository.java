package es.codeurjc.bof.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.codeurjc.bof.model.Ticket;
import es.codeurjc.bof.model.User;

public interface TicketRespository extends JpaRepository<Ticket, Long>{
    
    @Query("SELECT t FROM Ticket t WHERE t.user = user ORDER BY t.date DESC")
    List<Ticket> findByUserOrderByDate(User user);
}
