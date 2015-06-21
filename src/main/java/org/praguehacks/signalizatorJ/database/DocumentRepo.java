package org.praguehacks.signalizatorJ.database;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by kucerajn on 13.6.2015.
 */
public interface DocumentRepo extends JpaRepository<Document, Integer> {

    List<Document> findAllByLoc(String loc);
//    List<Document> findAllByloadedTime(String loadedTime);
    List<Document> findAllByLocNotNull();
    List<Document> findAllByTextNotNull();
}
