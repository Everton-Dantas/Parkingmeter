package br.com.parquimetro.repository;

import br.com.parquimetro.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByPlaca(String placa);

}
