package com.example.JournalApp.controllers;

import com.example.JournalApp.cache.AppCache;
import com.example.JournalApp.entity.User;
import com.example.JournalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserService userService;
    @Autowired
    AppCache appCache;

    @PostMapping("/create-admin")
    public ResponseEntity<?> createAdminUser(@RequestBody User user){
        boolean b = userService.saveNewAdminUser(user);
        if(b==true)
            return new ResponseEntity<>(b, HttpStatus.ACCEPTED);
        else
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
    }
    @GetMapping("/getAllUser")
    public ResponseEntity<?> getALL (){
        List<User> all = userService.getAll();
        if(all!=null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/userId/{id}")
    public ResponseEntity<?> getById(@PathVariable ObjectId id){
        Optional<User> byId = userService.getById(id);
        if(byId!=null){
            return new ResponseEntity<>(byId,HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/clear_app_cache")
    public void clearAppCache(){
        appCache.init();
    }
}
