package ouhk.comps380f.controller;

import java.io.IOException;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ouhk.comps380f.model.Poll;
import ouhk.comps380f.service.PollService;

@Controller
public class IndexController {
    
    @Autowired
    private PollService pollService;
    
    @GetMapping
    public String displayPoll(ModelMap model) {
        model.addAttribute("pollDB", pollService.getPolls());
        model.addAttribute("displayPoll", new voteForm());
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    public static class voteForm {

        private String choice;
        private long pollid;

        public long getPollid() {
            return pollid;
        }

        public void setPollid(long pollid) {
            this.pollid = pollid;
        }

        public String getChoice() {
            return choice;
        }

        public void setChoice(String choice) {
            this.choice = choice;
        }        
    }
    
    @PostMapping
    public String vote(voteForm form, Principal principal, HttpServletRequest request) throws IOException {
        Poll aPoll = pollService.getPoll(form.pollid);
        if (aPoll == null || form.getChoice() == null) {
            return "redirect:/";
        }
        pollService.createVote(form.pollid, principal.getName(), form.getChoice());
        return "redirect:/";
    }
}