package tms.service;

import org.springframework.stereotype.Service;
import tms.entity.Pair;

@Service
public class EventService {

    public Double catRan (Pair pair) {
        System.out.println("На ипподром выбежала кошка, как раз рядом пробегала лошадь " + pair.getHorse().getName());
        System.out.println("Данная пара потеряла 20 секунд времени");
        System.out.println("Главное что с кошкой все в порядке");
        return 20.0;
    }

    public Double loudNoise (Pair pair) {
        System.out.println("Громкий звук с трибун испугал лошадь " + pair.getHorse().getName());
        System.out.println("На успокоение лошади понадобилось 25 секунд");
        return 25.0;
    }

    public Double lostHorsewhip (Pair pair) {
        if (pair.getRider().getHorsewhip()) {
            System.out.println("Наездник " + pair.getRider().getName() + " потерял свой хлыст");
            System.out.println("Скорость лошади заметно уменьшилась");
            pair.getRider().setHorsewhip(false);
            Integer newSpeed = pair.getHorse().getSpeed() - 5;
            pair.getHorse().setSpeed(newSpeed);
        }

        return 0.0;
    }
}
