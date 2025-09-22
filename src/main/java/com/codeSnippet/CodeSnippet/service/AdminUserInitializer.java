package com.codeSnippet.CodeSnippet.service;

import com.codeSnippet.CodeSnippet.entity.Users;
import com.codeSnippet.CodeSnippet.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AdminUserInitializer {

    @Bean
    public CommandLineRunner createAdminUser(UserRepository userRepository, PasswordEncoder passwordEncoder){
        return args ->{
            Optional<Users> user = userRepository.findByUsername("admin");
            if(user.isEmpty()){
                Users admin = new Users();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin1234"));
                admin.setRole("ROLE_ADMIN");

                userRepository.save(admin);
                System.out.println("Default admin user saved in DB");
            }else{
//               Users u = user.get();
//               u.setRole("ROLE_ADMIN");
//               userRepository.save(u);
            }
        };
    }
}
