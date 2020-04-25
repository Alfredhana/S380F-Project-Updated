package ouhk.comps380f.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ouhk.comps380f.model.Ticket;


public interface OtherRepository extends JpaRepository<Ticket, String>{
}
