package mole;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    public static void main(String [] args) {
        SpringApplication.run(Application.class, args);
    }
/*
    @Bean
    public CommandLineRunner demo(UserRepository repository) {
        return (args) -> {
            // save a couple of customers
            repository.save(new User("Jack", "Bauer"));
            repository.save(new User("Chloe", "O'Brian"));
            repository.save(new User("Kim", "Bauer"));
            repository.save(new User("Jack", "Palmer"));
            repository.save(new User("Michelle", "Dessler"));

            // fetch all customers
            log.info("User found with findAll():");
            log.info("-------------------------------");
            for (User user : repository.findAll()) {
                log.info(user.toString());
            }
            log.info("");

            // fetch an individual customer by ID
            User user = repository.findOne(1L);
            log.info("User found with findOne(1L):");
            log.info("--------------------------------");
            log.info(user.toString());
            log.info("");

            // fetch customers by last name
            log.info("User found with findByLastName('Bauer'):");
            log.info("--------------------------------------------");
            for (User searchResult : repository.findByLastName("Bauer")) {
                log.info(searchResult.toString());
            }
            log.info("");
        };
    }
    */
}
