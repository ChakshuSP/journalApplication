package com.example.JournalApp.controllers;

import com.example.JournalApp.entity.JournalEntry;
import com.example.JournalApp.entity.User;
import com.example.JournalApp.service.JournalEntryService;
import com.example.JournalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<JournalEntry>> getAllJournalEntriesOfUser(){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                        List<JournalEntry> jr = journalEntryService.getAll(authentication.getName());
            if(jr!=null)
            return new ResponseEntity<>(jr, HttpStatus.OK);
            else
                return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry){
        try{Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String name = authentication.getName();
            journalEntryService.saveEntry(name, myEntry);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId id){
        try {Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String name = authentication.getName();
            User user = userService.findByUserName(name);
            List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());

            if(!collect.isEmpty()) {
                Optional<JournalEntry> journalEntry = journalEntryService.getById(id);
                if (journalEntry.isPresent()) {
                    return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
                }
            }
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
        catch (Exception e){
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId id){
        try {Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String name = authentication.getName();
            User user = userService.findByUserName(name);
            List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());

            if(!collect.isEmpty()) {
                Boolean br = journalEntryService.deleteById(name, id);
                if (br)
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<JournalEntry> putJournalEntryById(@PathVariable ObjectId id,@RequestBody JournalEntry jr){
        try{Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String name = authentication.getName();
            User user = userService.findByUserName(name);
            List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());

            if(!collect.isEmpty()){
                JournalEntry entry = journalEntryService.putById(name,id, jr);
                if (entry != null)
                    return new ResponseEntity<JournalEntry>(entry, HttpStatus.ACCEPTED);
            }

                return new ResponseEntity<JournalEntry>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
