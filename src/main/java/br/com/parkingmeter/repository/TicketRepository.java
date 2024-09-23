package br.com.parkingmeter.repository;

import br.com.parkingmeter.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByPlaca(String placa);

}
