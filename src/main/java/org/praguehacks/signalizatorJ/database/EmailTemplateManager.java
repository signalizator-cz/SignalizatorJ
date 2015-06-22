package org.praguehacks.signalizatorJ.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kucera.ja@gmail.com on 13.6.2015.
 */
@Service
public class EmailTemplateManager {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    EmailTemplateRepo emailTemplateRepo;


    public void save(EmailTemplate et) {
        emailTemplateRepo.save(et);
    }

    public void saveAll(List<EmailTemplate> list) {
        emailTemplateRepo.save(list);
    }

    public List<EmailTemplate> getAll() {
        return emailTemplateRepo.findAll();
    }

    public List<EmailTemplate> findAllByType (String type) {
        return emailTemplateRepo.findAllByType(type);
    }

}
