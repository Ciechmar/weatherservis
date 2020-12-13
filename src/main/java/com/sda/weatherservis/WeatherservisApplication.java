package com.sda.weatherservis;

import com.sda.weatherservis.security.User;
import com.sda.weatherservis.security.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
@EnableSwagger2 //po co to???
@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
@EnableScheduling //by zadziałało @Scheduled - odpalanie co 2000ms
@EnableJpaAuditing //By zadziałało @CreatedData w klacie Localization
public class WeatherservisApplication implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(WeatherservisApplication.class, args);
    }

//    @Scheduled (fixedRate = 2000 ) //ms
    @Scheduled (cron = "* * 1 * * *" ) //do generowania konkretnego dnia/minut ->:https://crontab.guru/
    public void generateReport(){
// ile użytkowników jest dodanych do bazy danych
        log.info("At this moment we have " + userRepository.count(), " users");

    }

    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();

        User user1 = new User();
        user1.setUsername("ciechmar");
        user1.setPassword(passwordEncoder.encode("root123!"));
        user1.setAuthorities(Collections.singletonList(() -> "ROLE_USER"));
        userRepository.save(user1);

        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin1"));
        admin.setAuthorities(Collections.singletonList(() -> "ROLE_ADMIN"));
        userRepository.save(admin);

    }
}
