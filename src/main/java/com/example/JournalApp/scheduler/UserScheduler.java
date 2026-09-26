package com.example.JournalApp.scheduler;

import com.example.JournalApp.cache.AppCache;
import com.example.JournalApp.entity.JournalEntry;
import com.example.JournalApp.entity.User;
import com.example.JournalApp.enums.Sentiment;
import com.example.JournalApp.repository.UserRepositoryImpl;
import com.example.JournalApp.service.EmailService;
import com.example.JournalApp.service.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class UserScheduler {
    
    @Autowired
    private EmailService emailService;
    
    @Autowired
    private UserRepositoryImpl userRepositoryImpl;

    @Autowired
    private AppCache appCache;

    
    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;

    @Scheduled(cron = "0 0 13 * * *", zone = "Asia/Kolkata")
    public void fetchUsersAndSendSaMail() {
        List<User> users = userRepositoryImpl.getUserForS();
        System.out.println("=== USERS FOUND: " + users.size() + " ===");

        for (User user : users) {
            System.out.println("Processing user: " + user.getUserName() + " | Email: " + user.getEmail());

            List<JournalEntry> journalEntries = user.getJournalEntries();
            if (journalEntries == null || journalEntries.isEmpty()) {
                System.out.println("--> Journal entries list is null or empty for " + user.getUserName());
                continue;
            }

            List<Sentiment> sentiments = journalEntries.stream()
                    .filter(x -> x.getDate() != null && x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS)))
                    .map(JournalEntry::getSentiment)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            System.out.println("--> Sentiments in last 7 days: " + sentiments.size());

            Map<Sentiment, Integer> sentimentCount = new HashMap<>();
            for (Sentiment sentiment : sentiments) {
                sentimentCount.put(sentiment, sentimentCount.getOrDefault(sentiment, 0) + 1);
            }

            Sentiment mostFrequentSentiment = null;
            int maxcount = 0;
            for (Map.Entry<Sentiment, Integer> entry : sentimentCount.entrySet()) {
                if (entry.getValue() > maxcount) {
                    maxcount = entry.getValue();
                    mostFrequentSentiment = entry.getKey();
                }
            }

            if (mostFrequentSentiment != null) {
                System.out.println("--> Attempting to send email to " + user.getEmail() + " with sentiment " + mostFrequentSentiment);
                emailService.sendEmail(user.getEmail(), "Weekly Sentiment Analysis", mostFrequentSentiment.toString());
                System.out.println("--> Email sent call finished.");
            } else {
                System.out.println("--> No predominant sentiment found (mostFrequentSentiment is null).");
            }
        }
    }
    public void clearCache(){
        appCache.init();
    }
}
