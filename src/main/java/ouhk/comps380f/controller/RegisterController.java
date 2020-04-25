package ouhk.comps380f.controller;

import ouhk.comps380f.dao.UserRepository;
import ouhk.comps380f.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import sun.util.logging.resources.logging;

@Controller
@Repository
@RequestMapping("register")
public class RegisterController {
    @Autowired
    public DataSource dataSource;
    public Statement stmt;
    
    @Autowired
    UserRepository userRepo;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView showRegisterPage() {
        return new ModelAndView("register", "registerForm", new RegisterForm());
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ModelAndView showRegisterResult(RegisterForm form) {
        ModelAndView success = new ModelAndView("lecture");
        String username, password, password2;
        username = form.getUsername();
        password = form.getPassword();
        password2 = form.getPassword2();

        if(userRepo.isUserExists(username)) {
            ModelAndView mav = new ModelAndView("register");
            mav.addObject("existed", "Username existed.");
            return mav;
        } else if (!password.equals(password2)) {
            ModelAndView mav = new ModelAndView("register");
            mav.addObject("pwNotMatched", "Password is not match.");
            return mav;
        } else {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            userRepo.create(user);
        }
        return success;
    }
    
    public static class RegisterForm {

        private String username;
        private String password;
        private String password2;

        // Getters and Setters of customerName, subject, body, attachments
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
}
