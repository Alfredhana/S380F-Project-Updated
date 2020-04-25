package ouhk.comps380f.service;

import java.io.IOException;
import javax.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import ouhk.comps380f.dao.TicketUserRepository;
import ouhk.comps380f.exception.TicketUserNotFound;
import ouhk.comps380f.model.TicketUser;

public class TicketUserServiceImpl implements UserDetailsService {
    
    @Resource
    private TicketUserRepository ticketUserRepo;
    
    @Transactional(rollbackFor = TicketUserNotFound.class)
    public void updateUser(String username, String newUsername, String newPassword) throws IOException, UsernameNotFoundException, TicketUserNotFound {
        TicketUser updatedUser = ticketUserRepo.findById(username).orElse(null);
        if (updatedUser == null) {
            throw new TicketUserNotFound();
        }
        updatedUser.setUsername(newUsername);
        updatedUser.setPassword(newPassword);
        ticketUserRepo.save(updatedUser);
    }

    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
