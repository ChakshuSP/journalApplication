package com.example.JournalApp.service;

import com.example.JournalApp.entity.JournalEntry;
import com.example.JournalApp.entity.User;
import com.example.JournalApp.repository.JournalEntryRepository;
import com.example.JournalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userService;


    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void  saveEntry(String userName ,JournalEntry journalEntry){
        journalEntry.setDate(LocalDateTime.now());
        User user = userService.findByUserName(userName);
        JournalEntry saved = journalEntryRepository.save(journalEntry);
        user.getJournalEntries().add(saved);
        userService.saveEntry(user);
    }
    public List<JournalEntry> getAll(String userName){
        User user = userService.findByUserName(userName);
        return user.getJournalEntries();
    }

    public Optional<JournalEntry> getById(ObjectId id) {
        return journalEntryRepository.findById(id); // Return the Optional directly
    }

    public Boolean deleteById(String userName, ObjectId id){
        User user=userService.findByUserName(userName);
        user.getJournalEntries().removeIf(x-> x.getId().equals(id));
        userService.saveEntry(user);
        journalEntryRepository.deleteById( id);


        return true;
    }

    public JournalEntry putById(String userName , ObjectId id ,JournalEntry entry){
        User user = userRepository.findByuserName(userName);
        JournalEntry old= journalEntryRepository.findById(id).orElse(null);

        if(old!=null){
             old.setTitle(entry.getTitle()!= null && !entry.getTitle().equals("")? entry.getTitle() :  old.getTitle());
             old.setContent(entry.getContent()!=null && !entry.getContent().equals("")? entry.getContent() : old.getContent());
         }
         journalEntryRepository.save(old);
         userService.saveEntry(user);
        return old;
    }
//    public List<JournalEntry> findByUserName(String userName)
//    {
//        journalEntryRepository.fi
//    }
}
