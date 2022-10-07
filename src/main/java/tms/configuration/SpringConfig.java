package tms.configuration;

import tms.entity.Horse;
import tms.entity.Pair;
import tms.entity.Rider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public Horse horse1() {
        return new Horse("Спирит");
    }

    @Bean
    public Rider rider1() {
        return new Rider("Саша");
    }

    @Bean
    public Pair pair1() {
        return new Pair(horse1(), rider1());
    }

    @Bean
    public Horse horse2() {
        return new Horse("Пегас");
    }

    @Bean
    public Rider rider2() {
        return new Rider("Дима");
    }

    @Bean
    public Pair pair2() {
        return new Pair(horse2(), rider2());
    }

    @Bean
    public Horse horse3() {
        return new Horse("Ветерок");
    }

    @Bean
    public Rider rider3() {
        return new Rider("Алина");
    }

    @Bean
    public Pair pair3() {
        return new Pair(horse3(), rider3());
    }
}
