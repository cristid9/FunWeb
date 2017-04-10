package main.java.daos;

import main.java.user.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.HashMap;
import java.util.List;

public class UserDAO {

    //private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
   // private JdbcTemplate jdbcTemplate;
    //private SimpleJdbcInsert insert;

    // add crud methods here


    public User getUser(Long id){
        //String sql = "select name from User where id=" + id +";";

        //HashMap<String, Long> parameters  = new HashMap<String, Long>();
        //parameters.put("id" , id);

        //List<User> query = namedParameterJdbcTemplate.query(sql , parameters , userRowMapper);

        return null;
    }
}
