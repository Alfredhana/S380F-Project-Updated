package ouhk.comps380f.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
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

        public String getChoice() {
            return choice;
        }

        public void setChoice(String choice) {
            this.choice = choice;
        }
        
        
    }
}