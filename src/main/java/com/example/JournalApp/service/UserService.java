package com.example.JournalApp.service;

import com.example.JournalApp.entity.User;
import com.example.JournalApp.repository.JournalEntryRepository;
import com.example.JournalApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
@Slf4j
@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;

 //   private static final Logger logger= LoggerFactory.getLogger(UserService.class);

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void  saveEntry(User user){
        userRepository.save(user);
    }

    public boolean  saveNewUser(User user){
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            log.error(" Error username already exist for {}",user.getUserName(),e);
            log.info(" Error username already exist for {}",user.getUserName(),e);
            log.debug(" Error username already exist for {}",user.getUserName(),e);
            log.warn(" Error username already exist for {}",user.getUserName(),e);
            return false;
        }
    }
    public boolean  saveNewAdminUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userRepository.save(user);
        return true;
    }
    public List<User> getAll(){
        return userRepository.findAll();
    }

    public Optional<User> getById(ObjectId id) {
        return userRepository.findById(id); // Return the Optional directly
    }

    public Boolean deleteById(ObjectId id){
        userRepository.deleteById( id);
        return true;
    }

    public User putById(ObjectId id , User entry){
         User old= userRepository.findById(id).orElse(null);
         if(old!=null){
             old.setUserName(entry.getUserName()!= null && !entry.getUserName().equals("")? entry.getUserName() :  old.getUserName());
             old.setPassword(entry.getPassword()!=null && !entry.getPassword().equals("")? entry.getPassword() : old.getPassword());
         }
         userRepository.save(entry);
        return old;
    }

    public User findByUserName (String userName){
       return userRepository.findByuserName(userName);
    }

}
