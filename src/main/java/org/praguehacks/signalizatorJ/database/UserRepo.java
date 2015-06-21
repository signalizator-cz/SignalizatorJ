package org.praguehacks.signalizatorJ.database;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by kucerajn on 13.6.2015.
 */
public interface UserRepo extends JpaRepository<User, Integer> {

    List<User> findAllByEmail(String email);

}
