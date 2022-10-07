import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import tms.entity.Horse;
import tms.service.RaceService;
import tms.service.UserService;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        AbstractApplicationContext context = new AnnotationConfigApplicationContext("tms");

        UserService choicePair = context.getBean(UserService.class);
        choicePair.doChoicePair();

        RaceService raceService = context.getBean(RaceService.class);
        raceService.startRace();

    }
}
