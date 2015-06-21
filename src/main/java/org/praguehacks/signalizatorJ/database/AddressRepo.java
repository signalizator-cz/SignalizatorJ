package org.praguehacks.signalizatorJ.database;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by kucerajn on 13.6.2015.
 */
    public interface AddressRepo extends  JpaRepository<Address, Integer> {
    
      
    }


