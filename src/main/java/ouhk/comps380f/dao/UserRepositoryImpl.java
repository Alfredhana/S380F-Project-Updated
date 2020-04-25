/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ouhk.comps380f.dao;

import ouhk.comps380f.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private DataSource dataSource;
    private JdbcOperations jdbcOp;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcOp = new JdbcTemplate(this.dataSource);
    }

    private static final class UserRowMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int i) throws SQLException {
            User user = new User();
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            return user;
        }
    }

    @Override
    public void create(User user) {
        jdbcOp.update("insert into users (username, password) values (?, ?)", user.getUsername(), user.getPassword());
        jdbcOp.update("insert into user_roles (username, role) values (?, 'ROLE_USER')", user.getUsername());
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcOp.queryForList("select username, password from users");

        for (Map<String, Object> row : rows) {
            User user = new User();
            String username = (String) row.get("username");
            user.setUsername(username);
            user.setPassword((String) row.get("password"));
            List<Map<String, Object>> roleRows = jdbcOp.queryForList("select username, role from user_roles where username = ?", username);
            for (Map<String, Object> roleRow : roleRows) {
                user.addRole((String) roleRow.get("role"));
            }
            users.add(user);
        }
        return users;
    }

    public boolean isUserExists(String username) {
        String sql = "SELECT count(*) FROM USERS WHERE username = ?";
        boolean result = false;
        
        int count = jdbcOp.queryForObject(
                sql, new Object[]{username}, Integer.class);
        if (count > 0) {
            result = true;
        }
        return result;
    }

    
    @Override
    public User findByUsername(String username) {
        User user = jdbcOp.queryForObject("select username, password from users where username = ?", new UserRowMapper(), username);
        List<Map<String, Object>> rows = jdbcOp.queryForList("select username, role from user_roles where username = ?", username);
        for (Map<String, Object> row : rows) {
            user.addRole((String) row.get("role"));
        }
        return user;
    }

    @Override
    public void deleteByUsername(String username) {
        jdbcOp.update("delete from user_roles where username = ?", username);
        jdbcOp.update("delete from users where username = ?", username);
    }
    
    @Override
    public void UpdateByUser(User user) {  
        if(!user.hasRole("ROLE_ADMIN")){
            user.addRole("ROLE_ADMIN");
            jdbcOp.update("insert into user_roles (username, role) values (?, ?)" , user.getUsername(), user.getRoles().get(1));
        }
              
    }
    
    
    
    
}
