//package com.example.JournalApp.service;
//
//import com.example.JournalApp.repository.UserRepository;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.CsvSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//@SpringBootTest
//public class UserServiceTest {
//    @Autowired
//    UserRepository userRepository;
//    @Disabled
//    @Test
//    public void testFindByUserName() {
//        assertNotNull(userRepository.findByuserName("Shivay"));
//    }
//        @ParameterizedTest
//                @CsvSource({
//                        "1,2,3",
//                        "1,4,5",
//                        "3,3,3"
//                })
//        public void test(int a,int b, int expected){
//            assertEquals(expected ,a+b);
//        }
//    }
//
