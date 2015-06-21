package org.praguehacks.signalizatorJ.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by kucera.ja@gmail.com on 13.6.2015.
 */
@Service
public class UserManager {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    UserRepo userRepo;


    public void save(User u) {
        userRepo.save(u);
    }

    public void saveAll(List<User> list) {
        userRepo.save(list);
    }

    public List<User> getAll() {
        return userRepo.findAll();
    }

    public List<User> findAllByEmail(String email) {
        return userRepo.findAllByEmail(email);
    }
   
}
