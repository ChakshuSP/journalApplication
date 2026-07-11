//package com.example.JournalApp.controllers;
//
//import com.example.JournalApp.entity.JournalEntry;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/_journal")
//public class EntryController {
//    private Map<Long, JournalEntry> journalEntries = new HashMap<>();
//
//@GetMapping("/entries")
//public List<JournalEntry> getAll(){
//
//    return new ArrayList<>(journalEntries.values());
//}
//
//@PostMapping
//    public boolean createEntry(@RequestBody JournalEntry myEntry){
//    journalEntries.put(myEntry.getId(),myEntry);
//    return true;
//}
//
//@GetMapping("/byId/{id}")
//    public JournalEntry getJournalEntryById(@PathVariable Long id){
//    return journalEntries.get(id);
//}
//    @DeleteMapping("/byId/{id}")
//    public JournalEntry deleteJournalEntryById(@PathVariable Long id){
//        return journalEntries.remove(id);
//
//    }
//@PutMapping("/byId/{id}")
//public JournalEntry putJournalEntryById(@PathVariable Long id,@RequestBody JournalEntry jr){
//     journalEntries.put(id,jr);
//    return journalEntries.get(id);
//}
//}
