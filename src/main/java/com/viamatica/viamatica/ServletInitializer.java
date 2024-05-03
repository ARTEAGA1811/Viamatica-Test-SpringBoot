package com.viamatica.viamatica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ViamaticaApplication.class);
    }

    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner init() {
        return args -> {
            System.out.println("encoder passsss");
            System.out.println(passwordEncoder.encode("12345678"));
        };
    }

    @GetMapping("/test")
    public String test() {
        return "Hello World";
    }
}
