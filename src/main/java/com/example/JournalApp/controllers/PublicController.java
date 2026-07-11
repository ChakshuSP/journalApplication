package com.example.JournalApp.controllers;

import com.example.JournalApp.entity.User;
import com.example.JournalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/")
public class PublicController {

    @Autowired
    private  UserService userService;
    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody User user){
        boolean b = userService.saveNewUser(user);
        if(b==true)
        return new ResponseEntity<>(b, HttpStatus.ACCEPTED);
        else
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
    }











    public String healthCheck(){
        return "OK";
    }
}
