package ouhk.comps380f.service;

import java.io.IOException;
import java.util.List;
import ouhk.comps380f.model.Poll;


public interface PollService {
    
    public void createPoll(String topic, String optionone, String optiontwo,
            String optionthree, String optionfour) throws IOException;
    
    public List<Poll> getPolls();
    
    public Poll getPoll(long pollid);
    
    public void createVote(long pollid, String username, String choice) throws IOException;
}
