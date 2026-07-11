//package com.example.JournalApp.service;
//
//import com.example.JournalApp.entity.User;
//import com.example.JournalApp.repository.UserRepository;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.ArrayList;
//
//import static org.mockito.Mockito.when;
//
//public class UserDetaailsServiceImplTest {
//    @InjectMocks
//    private UserDetailsServiceImpl userDetailsService;
//    @Mock
//    private UserRepository userRepository;
//
//    @BeforeEach
//    void setup(){
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    void loadUserByUsernameTest(){
//        when(userRepository.findByuserName(ArgumentMatchers.anyString())).thenReturn((User.builder().userName("ram").password("adfsfsdge").roles(new ArrayList<>())).build() );
//        UserDetails user = userDetailsService.loadUserByUsername("ram");
//        Assertions.assertNotNull(user);
//    }
//}
