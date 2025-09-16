package com.example.rest.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.rest.demo.Model.User;
import com.example.rest.demo.Repository.UserRepository;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(UserRepository userRepository) {
        return args -> {
            User user = new User();
            user.setName("Tanishq");
            user.setEmail("tS@gmail.com");
            userRepository.save(user);
        };
    }

}
