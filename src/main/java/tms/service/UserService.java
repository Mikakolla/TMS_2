package tms.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tms.entity.Pair;

import java.util.List;
import java.util.Scanner;

@Data
@Service
public class UserService {

    private Pair pair;
    @Autowired
    private List<Pair> pairs;


    public UserService() {
    }

    public void doChoicePair() {
        System.out.println("Выберите пару на какую поставите: ");
        Scanner console = new Scanner(System.in);
        int choiceNumber = console.nextInt();
        if (choiceNumber <1 || choiceNumber > 3){
            System.out.println("Всего существует 3 пары ");
            doChoicePair();
        }

        pair = pairs.get(choiceNumber-1);
    }
}
