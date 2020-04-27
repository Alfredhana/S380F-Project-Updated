package ouhk.comps380f.service;

import java.io.IOException;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ouhk.comps380f.dao.PollRepository;
import ouhk.comps380f.dao.VoteRepository;
import ouhk.comps380f.model.Poll;
import ouhk.comps380f.model.Vote;

@Service
public class PollServiceImpl implements PollService{
    
    @Resource
    private PollRepository pollRepo;
    
    @Resource
    private VoteRepository voteRepo;
    
    @Override
    @Transactional
    public List<Poll> getPolls(){
        return pollRepo.findAll();
    }
    
    @Override
    @Transactional
    public Poll getPoll(long pollid) {
        return pollRepo.findById(pollid).orElse(null);
    }
    
    @Override
    @Transactional
    public void createPoll(String topic, String optionone, String optiontwo,
            String optionthree, String optionfour) throws IOException{
        Poll aPoll = new Poll();
        aPoll.setTopic(topic);
        aPoll.setOptionone(optionone);
        aPoll.setOptiontwo(optiontwo);
        aPoll.setOptionthree(optionthree);
        aPoll.setOptionfour(optionfour);
        pollRepo.save(aPoll);
    }
    
    @Override
    @Transactional
    public void createVote(long pollid, String username, String choice) throws IOException{
        Poll aPoll = getPoll(pollid);
        Vote aVote = new Vote();
        aVote.setUsername(username);
        aVote.setChoice(choice);
        aVote.setPoll(aPoll);
        voteRepo.save(aVote);
    }
}
