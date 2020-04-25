package ouhk.comps380f.controller;

import java.io.IOException;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;
import ouhk.comps380f.dao.PollRepository;
import ouhk.comps380f.model.Poll;

@Controller
public class PollController {
    
    @Resource
    PollRepository pollRepo;
    
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String displayPoll(ModelMap model) {
        model.addAttribute("pollDB", pollRepo.findAll());
        return "index";
    }
    
    public static class PollaForm {

        private String topic;
        private String optionone;
        private String optiontwo;
        private String optionthree;
        private String optionfour;

        public String getTopic() {
            return topic;
        }

        public void setTopic(String topic) {
            this.topic = topic;
        }

        public String getOptionone() {
            return optionone;
        }

        public void setOptionone(String optionone) {
            this.optionone = optionone;
        }

        public String getOptiontwo() {
            return optiontwo;
        }

        public void setOptiontwo(String optiontwo) {
            this.optiontwo = optiontwo;
        }

        public String getOptionthree() {
            return optionthree;
        }

        public void setOptionthree(String optionthree) {
            this.optionthree = optionthree;
        }

        public String getOptionfour() {
            return optionfour;
        }

        public void setOptionfour(String optionfour) {
            this.optionfour = optionfour;
        }        
    }
    
    @GetMapping("/createPoll")
    public ModelAndView createPoll() {
        return new ModelAndView("addPoll", "pollForm", new PollaForm());
    }
    
    @PostMapping("/createPoll")
    public View createPoll(PollaForm form) throws IOException {
        Poll aPoll = new Poll(form.getTopic(), form.getOptionone(), form.getOptiontwo(), form.getOptionthree(), form.getOptionfour());
        pollRepo.save(aPoll);
        return new RedirectView("/user/list", true);
    }
    
    
}
