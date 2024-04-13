package com.shpp.p2p.cs.skurochka.assignment3;

import com.shpp.cs.a.console.TextProgram;
import java.util.Random;

public class Assignment3Part5 extends TextProgram {
    public void run() {
        startGame();
    }

    private void startGame() {
        Random randomGenerator = new Random();
        int casinoMoney = 1, userMoney = 0, userGameCounter = 0;
        while (userMoney <= 20) {
            if ((randomGenerator.nextInt(2)) == 0) {
                casinoMoney += casinoMoney;
            } else {
                userGameCounter++;
                System.out.println("This game, you earned $" + casinoMoney);
                userMoney += casinoMoney;
                casinoMoney = 1;
                System.out.println("Your total is $" + userMoney);
            }
        }
        System.out.println("It took " + userGameCounter + " games to earn $20");
    }
}
