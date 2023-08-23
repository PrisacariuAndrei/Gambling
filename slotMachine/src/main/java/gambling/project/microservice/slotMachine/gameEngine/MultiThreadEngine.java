package gambling.project.microservice.slotMachine.gameEngine;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
public class MultiThreadEngine implements Runnable {

    private static final String[] SYMBOLS = {"Cherry", "Orange", "Lemon", "Seven", "Strawberry", "Grapes"};

    private  int threadNumber;
    private Random random = new Random();
    private List<String> finalResult = new ArrayList<>();
    public MultiThreadEngine (int threadNumber) {
        this.threadNumber = threadNumber;
    }

    @Override
    public void run()  {
        for (int i = 0; i < threadNumber; i++) {

            finalResult.add(SYMBOLS[random.nextInt(SYMBOLS.length)]);

            System.out.println(finalResult);
            System.out.println("/////////////////////////");
        }
    }
}
