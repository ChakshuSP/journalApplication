package com.example.JournalApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {
    @Autowired
    private EmailService emailService;
    @Test
    public void testMail() {
        emailService.sendEmail(
                ("sis.ghanshyam111@gmail.com"),
                ("Congratulations you are selected"),
                ("you are selected to be my husband and forever partner in crime I love you so much")

        );
    }
}
