package org.praguehacks.signalizatorJ.database;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by kucerajn on 13.6.2015.
 */


@Service
public class AddressManager {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    AddressRepo addressRepo;

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Address dfp) {
        addressRepo.save(dfp);
    }

    public void saveAll(List<Address> list) {
        addressRepo.save(list);
    }

    public List<Address> getAll() {
        return addressRepo.findAll();
    }

    //    public List<Map<String, Object>> findAllInRectangle(double llx, double lly, double upx, double ury) {
//        StringBuilder query = new StringBuilder();
//        query.append("");
//        return jdbcTemplate.queryForList(query.toString());
//    }

    public List<Address> listItems() {
        return entityManager.createQuery("select a from Address a where a.documentId < 7",
                Address.class).getResultList();
    }

    public List<Address> listItemsNamed() {
        return entityManager.createNamedQuery("Address.findAll",
                Address.class).getResultList();
    }

   /* public List<Document> findDocumentsInRectangle(double lowerLeftX, double lowerLeftY, double upperRightX, double upperRightY) {
        return entityManager.createNamedQuery("Address.findDocumentsInRectangle",
                Document.class)
                .setParameter("lowerLeftX", lowerLeftX)
                .setParameter("lowerLeftY", lowerLeftY)
                .setParameter("upperRightX", upperRightX)
                .setParameter("upperRightY", upperRightY)
                .getResultList();
    }*/

}
