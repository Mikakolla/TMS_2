import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.RaceService;
import service.UserService;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

        UserService choicePair = (UserService) context.getBean("choicePair");

        choicePair.doChoicePair();

        RaceService raceService = (RaceService) context.getBean("raceService");

        raceService.startRace();

    }
}
