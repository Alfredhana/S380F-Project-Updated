package ouhk.comps380f.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ouhk.comps380f.service.PollService;

@Controller
public class PollController {
    
//    @Autowired
//    private PollService pollService;   
//    
//    @GetMapping
//    public String displayPoll(ModelMap model) {
//        model.addAttribute("pollDB", pollService.getPolls());
//        return "index";
//    }
}
