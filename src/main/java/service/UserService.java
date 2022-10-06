package service;

import entity.ListPairs;
import entity.Pair;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Scanner;

@Data
@NoArgsConstructor
public class UserService {

    private Pair choicePair;
    private ListPairs listPairs;

    public UserService(ListPairs listPairs) {
        this.listPairs = listPairs;
    }

    public void doChoicePair() {
        System.out.println("Выберите пару на какую поставите: ");
        Scanner console = new Scanner(System.in);
        int choiceNumber = console.nextInt();
        if (choiceNumber <1 || choiceNumber > 3){
            System.out.println("Всего существует 3 пары ");
            doChoicePair();
        }
        List<Pair> pairs = listPairs.getPairs();

        choicePair = pairs.get(choiceNumber-1);
    }
}
