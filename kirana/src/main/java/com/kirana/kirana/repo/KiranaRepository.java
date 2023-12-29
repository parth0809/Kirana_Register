package com.kirana.kirana.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.kirana.kirana.models.Kirana;

public interface KiranaRepository extends MongoRepository<Kirana, String> {
    @Query("{cust_id:?0}")
    List<Kirana> findByCust_id(String cust_id);

    @Query("{cust_id:?0,date:?1}")
    List<Kirana> findByCust_idandDate(String cust_id, String date);

    @Query(value = "{cust_id:?0}", fields = "{method:1,Currency:1,price:1,quantity:1}")
    List<Kirana> findByCust_idT(String cust_id);

    @Query(value = "{cust_id:?0,date:?1}", fields = "{method:1,Currency:1,price:1,quantity:1}")
    List<Kirana> findByCust_idandDateT(String cust_id, String date);

}
