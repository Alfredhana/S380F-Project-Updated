package ouhk.comps380f.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ouhk.comps380f.model.Vote;


public interface VoteRepository extends JpaRepository<Vote, Long>{
    
}
