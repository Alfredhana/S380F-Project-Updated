package ouhk.comps380f.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ouhk.comps380f.dao.TicketUserRepository;
import ouhk.comps380f.dao.UserRoleRepository;
import ouhk.comps380f.model.TicketUser;
import ouhk.comps380f.model.UserRole;

@Controller
@RequestMapping("/register")
public class RegisterController {
    
    @Autowired
    private TicketUserRepository ticketUserRepo;
    
    @Autowired
    private UserRoleRepository userRoleRepo;
    
    @GetMapping
    public ModelAndView register() {
        return new ModelAndView("register", "registerForm", new registerForm());
    }
    
    public static class registerForm {
        
        private String username;
        private String password;
        private String password2;

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

        public String getPassword2() {
            return password2;
        }

        public void setPassword2(String password2) {
            this.password2 = password2;
        }        
    }
    
    @PostMapping
    public ModelAndView register(registerForm form) throws IOException {
        
        ModelAndView success = new ModelAndView("login");
        String username, password, password2;
        username = form.getUsername();
        password = form.getPassword();
        password2 = form.getPassword2();
        
        if (!password.equals(password2)) {
            ModelAndView mav = new ModelAndView("register");
            mav.addObject("pwNotMatched", "Password is not match.");
            return mav;
        } else if (ticketUserRepo.existsById(username)) {
            ModelAndView mav = new ModelAndView("register");
            mav.addObject("existed", "Username already existed.");
            return mav;
        } else {
            TicketUser nUser = new TicketUser();
//            UserRole nUserRole = new UserRole();
            nUser.setUsername(username);
            nUser.setPassword("{noop}" + password);
//            nUserRole.setId(0);
//            nUserRole.setUsername(username);
//            nUserRole.setRole("ROLE_USER");
            ticketUserRepo.save(nUser);
//            userRoleRepo.save(nUserRole);
        }
        return success;
    }
}
