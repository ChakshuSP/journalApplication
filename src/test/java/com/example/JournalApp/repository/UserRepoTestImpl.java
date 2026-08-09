package com.example.JournalApp.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepoTestImpl {
    @Autowired
   private UserRepositoryImpl userRepositoryImpl;
    @Test
    public void getUserUsingCriteria(){
        Assertions.assertNotNull(userRepositoryImpl.getUserForS());
    }
}
