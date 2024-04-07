package exercise;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import exercise.daytime.Daytime;
import exercise.daytime.Day;
import exercise.daytime.Night;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    private LocalDateTime startDay = LocalDateTime.of(LocalDate.now(), LocalTime.of(6,0));
    private LocalDateTime endDay = LocalDateTime.of(LocalDate.now(), LocalTime.of(22,0));

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Daytime daytime() {
        if ((LocalDateTime.now().isAfter(startDay)) && (LocalDateTime.now().isBefore(endDay))) {
            return new Day();
        } else {
            return new Night();
        }
    }
}
