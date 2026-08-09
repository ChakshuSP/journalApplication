package com.example.JournalApp.repository;

import com.example.JournalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public class UserRepositoryImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> getUserForS(){
        Query query=new Query();
        query.addCriteria(Criteria.where("userName").is("Admin"));
        query.addCriteria(Criteria.where("email").exists(true));
        List<User> users = mongoTemplate.find(query, User.class);
        if(users!=null)
        return users ;
        else return null;
    }
}
