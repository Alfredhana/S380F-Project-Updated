package ouhk.comps380f.controller;

import java.io.IOException;
import java.security.Principal;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;
import ouhk.comps380f.dao.PollRepository;
import ouhk.comps380f.dao.TicketUserRepository;
import ouhk.comps380f.model.Poll;
import ouhk.comps380f.model.TicketUser;
import ouhk.comps380f.service.PollService;
import ouhk.comps380f.service.TicketUserService;

@Controller
@RequestMapping("/user")
public class TicketUserController {

    @Resource
    TicketUserRepository ticketUserRepo;
    PollRepository pollRepo;
    
    @Autowired
    private TicketUserService ticketUserService;
    
    @Autowired
    private PollService pollService;

    @GetMapping({"", "/list"})
    public String list(ModelMap model) {
        model.addAttribute("ticketUsers", ticketUserRepo.findAll());
        return "listUser";
    }

    public static class Form {

        private String username;
        private String password;
        private String[] roles;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String[] getRoles() {
            return roles;
        }

        public void setRoles(String[] roles) {
            this.roles = roles;
        }
    }

    @GetMapping("/create")
    public ModelAndView create() {
        return new ModelAndView("addUser", "ticketUser", new Form());
    }

    @PostMapping("/create")
    public View create(Form form) throws IOException {
        TicketUser user = new TicketUser(form.getUsername(),
                form.getPassword(), form.getRoles()
        );
        ticketUserRepo.save(user);
        return new RedirectView("/user/list", true);
    }

    @GetMapping("/delete/{username}")
    public View deleteTicket(@PathVariable("username") String username) {
        ticketUserRepo.delete(ticketUserRepo.findById(username).orElse(null));
        return new RedirectView("/user/list", true);
    }
    
    @GetMapping("/edit/{username}")
    public ModelAndView showUserEdit(@PathVariable("username") String username,
            HttpServletRequest request) {
        TicketUser ticketUser = ticketUserRepo.findById(username).orElse(null);
        if (ticketUser == null) {
            throw new UsernameNotFoundException("User '" + username + "' not found.");
        }
        ModelAndView modelAndView = new ModelAndView("editUser");
        modelAndView.addObject("ticketUser", ticketUser);
        Form ticketUserForm = new Form();
        ticketUserForm.setUsername(ticketUser.getUsername());
        ticketUserForm.setPassword(ticketUser.getPassword());
        modelAndView.addObject("ticketUserForm", ticketUserForm);
        return modelAndView;
    }
    
    @PostMapping("/edit/{username}")
    public String updateUser(@PathVariable("username")String username, String password,
            HttpServletRequest request)
            throws IOException, UsernameNotFoundException {
        TicketUser updatedUser = ticketUserRepo.findById(username).orElse(null);
        if (updatedUser == null) {
            throw new UsernameNotFoundException("User '" + username + "' not found.");
        }
        updatedUser.setUsername(username);
        updatedUser.setPassword(password);
        ticketUserRepo.save(updatedUser);
        return "redirect:/user/list/";
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
        pollService.createPoll(form.topic, form.optionone, form.optiontwo, form.optionthree, form.optionfour);
        return new RedirectView("/user/list", true);
    }
}
