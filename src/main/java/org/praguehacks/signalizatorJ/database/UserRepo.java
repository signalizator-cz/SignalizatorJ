package org.praguehacks.signalizatorJ.database;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by kucerajn on 13.6.2015.
 */
public interface UserRepo extends JpaRepository<User, Integer> {

    List<User> findAllByEmail(String email);

}
