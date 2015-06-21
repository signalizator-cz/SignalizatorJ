package org.praguehacks.signalizatorJ.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by kucera.ja@gmail.com on 13.6.2015.
 */
@Service
public class DocumentManager {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DocumentRepo documentRepo;

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Document dfp) {
        documentRepo.save(dfp);
    }

    public void saveAll(List<Document> list) {
        documentRepo.save(list);
    }

    public List<Document> getAll() {
        return documentRepo.findAll();
    }

    List<Document> findAllByLoc(String loc) {
        return documentRepo.findAllByLoc(loc);
    }
    
//    List<Document> findAllByloadedTime(String loadedTime) {
//        return documentRepo.findAllByloadedTime(loadedTime);
//    }
    
    List<Document> findAllByLocNotNull() {
        return documentRepo.findAllByLocNotNull();
    }
    
    List<Document> findAllByTextNotNull() {
        return documentRepo.findAllByTextNotNull();
    }

    public List<Document> findDocumentsInRectangle(Double lowerLeftX, Double lowerLeftY, Double upperRightX, Double upperRightY) {
        return entityManager.createNamedQuery("Document.findDocumentsInRectangle",
                Document.class)
                .setParameter("lowerLeftX", lowerLeftX)
                .setParameter("lowerLeftY", lowerLeftY)
                .setParameter("upperRightX", upperRightX)
                .setParameter("upperRightY", upperRightY)
                .getResultList();
    }
}
