package service;

import entity.ListPairs;
import entity.Pair;
import lombok.Data;

import java.util.*;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

@Data
public class RaceService {

    private double circleDistance = 1000.0;
    private Map<Pair, Double> resultCircle = new HashMap<>();
    private ListPairs listPairs;
    private UserService userService;
    private EventService eventService;

    public RaceService(ListPairs listPairs, UserService userService, EventService eventService) {
        this.listPairs = listPairs;
        this.userService = userService;
        this.eventService = eventService;
    }

    public void startRace() throws InterruptedException {

        printAllPair();

        for (int i = 1; i <= 5; i++) {
            System.out.println("-------- " + i + " Круг -------- ");
            startNewCircle();
            Thread.sleep(2000);
            resultCircle = resultCircle.entrySet().stream().sorted(comparingByValue()) .collect( toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));
            if (i != 5)
                printResult();

        }
        System.out.println("\n\n\nРезультаты скачек:");
        printResult();
        resultBet();
    }

    public void startNewCircle() {

        List<Pair> pairs = listPairs.getPairs();

        for (Pair pair : pairs) {
            Double timeCircle = circleDistance/pair.getTotalAmount();
            Double additionalTime = randomEvent(pair);
            if (!resultCircle.isEmpty() && resultCircle.get(pair) != null){
                resultCircle.computeIfPresent(pair, (k, v) -> v + timeCircle + additionalTime);
            } else {
                resultCircle.put(pair, timeCircle + additionalTime);
            }
        }
    }

    public void printResult() {

        int place = 1;

        resultCircle = resultCircle.entrySet().stream().sorted(comparingByValue()) .collect( toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));

        for (Map.Entry<Pair, Double> pair : resultCircle.entrySet()) {
            System.out.println(place + " Место \nПара: Лошадь " + pair.getKey().getHorse().getName() + " и Наездник " + pair.getKey().getRider().getName() + " завершили заед с временем: " + pair.getValue());
            place++;
        }
    }

    public void resultBet() {
        System.out.println("\n\n\n");

        Pair winnerPair = resultCircle.entrySet().iterator().next().getKey();

        if (winnerPair == userService.getChoicePair()) {
            System.out.println("Поздравляем, вы выйграли!");
        } else {
            System.out.println("К сожалению, вы проиграли :с");
        }
    }

    public void printAllPair() {
        List<Pair> pairs = listPairs.getPairs();

        int number = 1;

        for (Pair pair : pairs) {
            System.out.println("Пара № " + number + " Лошадь - " + pair.getHorse().getName() + " и Наездник - " + pair.getRider().getName());
            number++;
        }
    }

    private Double randomEvent(Pair pair) {
        int event = 1 + (int)(Math.random() * ((20 - 1) + 1));
        Double timeCircle = 0.0;

        if (event > 17) {

            System.out.println("\nЧто-то произошло на этом круге!");

            switch (event) {
                case 18:
                    timeCircle = eventService.catRan(pair);
                    break;
                case 19:
                    timeCircle = eventService.loudNoise(pair);
                    break;
                case 20:
                    timeCircle = eventService.lostHorsewhip(pair);
            }
            System.out.println();
        }
        return timeCircle;
    }
}
