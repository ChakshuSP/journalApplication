package com.example.JournalApp.repository;

import com.example.JournalApp.entity.JournalEntry;
import com.example.JournalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository <User, ObjectId>{
    User findByuserName (String userName);
    void deleteByUserName(String userName);
}
