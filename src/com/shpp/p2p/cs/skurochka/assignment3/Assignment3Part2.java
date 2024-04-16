package com.shpp.p2p.cs.skurochka.assignment3;

import com.shpp.cs.a.console.TextProgram;

import java.util.InputMismatchException;

public class Assignment3Part2 extends TextProgram {

    // The method of program launch.
    @Override
    public void run() {
        // An attempt to run a program in a block with the ability to catch errors.
        try {
            // Receiving data from the user using a special method.
            int inputUsersData = getDataFromUser();
            // Calculating input data and outputting information.
            calculateDataAndShowIt(inputUsersData);
            // A block that allows you to process the corresponding errors.
        } catch (InputMismatchException e) {
            System.out.println("We're sorry, but you are entering incorrect information.");
        }
    }

    /*
     * A method of receiving data from a user. Returns an int value.
     * A variable with an initial value is created in the middle of the method.
     * Next, a variable is created to control the cycle.
     * Then the loop is started in which the previously created variable is used.
     * The loop accepts a value from the user and assigns it to the variable that will be the result.
     * After that, this value is compared with the boundary conditions, and if they are met,
     * the variable responsible for repeating the cycle is changed, and the cycle terminates.
     * If the condition is not met, a message is displayed and the loop repeats.
     * After the loop completes, the result is returned to the point of the method call.
     * */
    private int getDataFromUser() {
        int result = 1;
        boolean workFlag = true;
        while (workFlag) {
            result = readInt("Enter a number: ");
            if (result >= 1 && result < Integer.MAX_VALUE) {
                workFlag = false;
            } else {
                System.out.println("You have entered an incorrect number. ");
            }
        }
        return result;
    }

    /*
     * A method that calculates the input data and displays it on the screen. The method accepts int values.
     * In the middle of the method,
     * the input value is converted to another type to handle boundary conditions without problems.
     * then a check is performed to see if the input value is greater than zero.
     * If it is less than zero or zero, the program simply displays a message about the invalidity of the data.
     * But if the conditions are met, then a loop is started that checks whether the input value is not equal to one.
     * If it is equal to one, the loop is skipped.
     * If the condition is met,
     * then a loop is started that checks for parity and oddness and performs the appropriate actions.
     * The loop continues until the input data is equal to one.
     * After that, the program proceeds to the last step, which displays a message about the completion of the work.
     * */
    private void calculateDataAndShowIt(int inputData) {
        long inputDataLong = inputData;
        if (inputDataLong == 1) {
            System.out.println(" The number is already equal to one and does not need to be calculated. ");
        } else if (inputDataLong > 1) {
            calculatingValueInLoop(inputDataLong);
        } else {
            System.out.println("Sorry, but you cannot enter zero or negative numbers.");
        }
        System.out.println(" The end ");
    }

    /*
     * Method for calculating a value in a loop.
     * The loop is executed until the input value is equal to one.
     * If the number is even, it is divided by two, if it is not even,
     * it is multiplied by three and one is added.
     * The data in the process of calculation is displayed on the screen.
     * */
    private void calculatingValueInLoop(long inputDataLong) {
        while (inputDataLong != 1) {
            long beforeData = inputDataLong;
            if (inputDataLong % 2 == 0) {
                inputDataLong = inputDataLong / 2;
                System.out.println(" " + beforeData + " is even so I take half: " + inputDataLong);
            } else {
                inputDataLong = inputDataLong * 3 + 1;
                System.out.println(" " + beforeData + " is odd so I make 3n + 1: " + inputDataLong);
            }
        }
    }
}