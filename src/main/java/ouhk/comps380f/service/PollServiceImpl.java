package ouhk.comps380f.service;

import java.io.IOException;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ouhk.comps380f.dao.PollRepository;
import ouhk.comps380f.model.Poll;

@Service
public class PollServiceImpl implements PollService{
    
    @Resource
    private PollRepository pollRepo;
    
    @Override
    @Transactional
    public List<Poll> getPolls(){
        return pollRepo.findAll();
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
}
