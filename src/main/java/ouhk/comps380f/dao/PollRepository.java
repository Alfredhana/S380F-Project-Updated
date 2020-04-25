package ouhk.comps380f.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ouhk.comps380f.model.Poll;


public interface PollRepository extends JpaRepository<Poll, String>{
}
