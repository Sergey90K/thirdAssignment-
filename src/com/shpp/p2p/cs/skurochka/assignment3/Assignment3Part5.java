package com.shpp.p2p.cs.skurochka.assignment3;

import com.shpp.cs.a.console.TextProgram;
import java.util.Random;

public class Assignment3Part5 extends TextProgram {
    // The method of launching the program.
    public void run() {
        // A method of launching a game in a casino.
        startCasinoGame();
    }

    /*
     * A method for launching a casino game.
     * In the middle of the method, an object of the Random class is created.
     * Then local variables are created to store casino money, user money, and a counter for the number of games.
     * A loop is started that will repeat until the amount of user money is equal to 20 or more.
     * In the middle of the loop, a check is included to check what the pseudo-random number generator has produced,
     * if the result is zero, the casino money is doubled.
     * If not, the number of games is increased by one.
     * Then the amount of money that the user won in this game is displayed.
     * The money is added to the user's account.
     * The casino money is set to one again to start a new game.
     * A message about the amount of money the user has is displayed.
     * After all iterations of the loop are completed, the number of games played is displayed
     * */
    private void startCasinoGame() {
        Random randomGenerator = new Random();
        int casinoMoney = 1, userMoney = 0, userGameCounter = 0;
        while (userMoney < 20) {
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
