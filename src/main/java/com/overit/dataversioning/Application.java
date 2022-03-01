package com.overit.dataversioning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.overit.dataversioning.repository")
public class Application {//implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

//    @Autowired
//    DataSource dataSource;
//
//    @Autowired
//    AUserRepository aUserRepository;
//
//    @Transactional(readOnly = true)
//    @Override
//    public void run(String... args) throws Exception {
//        System.out.println("DATASOURCE = " + dataSource);
//
//        System.out.println("\n1.findAll()...");
//        for (AUser user : aUserRepository.findAll()) {
//            System.out.println(user);
//        }
//
//        System.out.println("Done!");
//        System.exit(0);
//    }
}
