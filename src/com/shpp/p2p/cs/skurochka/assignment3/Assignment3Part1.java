package com.shpp.p2p.cs.skurochka.assignment3;

import com.shpp.cs.a.console.TextProgram;
import java.util.InputMismatchException;

public class Assignment3Part1 extends TextProgram {
    // The number of minutes you need to exercise for heart health.
    private static final int NUMBER_OF_MINUTES_NEEDED_FOR_HEART = 30;
    // The number of minutes you need to train for low blood pressure.
    private static final int NUMBER_OF_MINUTES_NEEDED_FOR_BLOOD_PRESSURE = 40;
    // The number of days you need to train for heart health.
    private static final int NUMBER_OF_DAYS_FOR_HEART = 5;
    // The number of days you need to train for low blood pressure.
    private static final int NUMBER_OF_DAYS_FOR_BLOOD_PRESSURE = 3;

    // The method of launching the program.
    @Override
    public void run() {
        // Attempting to execute a program with error catching.
        try {
            // Get an array of data entered by the user.
            int[] dataOfAllDays = getDataForAllDays();
            // Show information about heart exercises.
            showCardiovascularHealthString(getNumberDaysGreaterNumber(dataOfAllDays, NUMBER_OF_MINUTES_NEEDED_FOR_HEART));
            // Show information about blood pressure exercises.
            showBloodPressureString(getNumberDaysGreaterNumber(dataOfAllDays, NUMBER_OF_MINUTES_NEEDED_FOR_BLOOD_PRESSURE));
            // A block for catching errors of the corresponding type.
        } catch (InputMismatchException e) {
            System.out.println("Sorry, but you are entering incorrect information.");
        }
    }

    /*
     * A method for displaying information about the fulfillment of conditions for blood pressure.
     * The method takes an int value.
     * The method outputs an introductory one.
     * Then the value of the number of days that were entered into the method with the required constant is displayed.
     * If the condition is fulfilled, the congratulation message is displayed, if the condition is not fulfilled,
     * the message is displayed in which the number of days that are missing is calculated.
     * */
    private void showBloodPressureString(int numberOfDays) {
        System.out.println("Blood pressure:");
        if (numberOfDays >= NUMBER_OF_DAYS_FOR_BLOOD_PRESSURE) {
            System.out.println("Great job! You've done enough exercise to keep a low blood pressure.");
        } else {
            System.out.println("You needed to train hard for at least " +
                    (NUMBER_OF_DAYS_FOR_BLOOD_PRESSURE - numberOfDays) + " more day(s) a week!");
        }
    }

    /*
     * Method for displaying information about the fulfillment of conditions for heart health.
     * The method takes an int value.
     * The method outputs an introductory one.
     * Then the value of the number of days that were entered into the method with the required constant is displayed.
     * If the condition is fulfilled, the congratulation message is displayed, if the condition is not fulfilled,
     * the message is displayed in which the number of days that are missing is calculated.
     * */
    private void showCardiovascularHealthString(int numberOfDays) {
        System.out.println("Cardiovascular health:");
        if (numberOfDays >= NUMBER_OF_DAYS_FOR_HEART) {
            System.out.println("Great job! You've done enough exercise for cardiovascular health.");
        } else {
            System.out.println("You needed to train hard for at least " +
                    (NUMBER_OF_DAYS_FOR_HEART - numberOfDays) + " more day(s) a week!");
        }
    }

    /*
     * A method of receiving data from a user.
     * The method returns an array of int values.
     * The method contains internal variables for the number of days in a week and the number of minutes in one day.
     * Next, a data array is created with the size of the number of days. After that,
     * a loop is started that fills the array with data when the specified conditions are met.
     * If the condition is not met, then re-entering the data is requested.
     * At the end of the loop, the array is returned to the point of calling this method.
     * */
    private int[] getDataForAllDays() {
        int numberOfDaysInWeek = 7;
        int maxNumberOfMinutesPerDay = 60 * 24; // 1440 minute
        int[] dataOfAllDay = new int[numberOfDaysInWeek];
        for (int i = 1; i <= numberOfDaysInWeek; i++) {
            boolean workFlag = true;
            while (workFlag) {
                int inputData = readInt("How many minutes did you do on day " + i + " ?");
                if (0 <= inputData && inputData <= maxNumberOfMinutesPerDay) {
                    dataOfAllDay[i - 1] = inputData;
                    workFlag = false;
                } else {
                    System.out.println("You entered incorrect data for the day number " + i + ".");
                }
            }
        }
        return dataOfAllDay;
    }

    /*
     * A method for getting the number of days greater than the entered number.
     * The method returns an int value.
     * In the middle, the method has an initial value of the number of successful days.
     * then there is a loop that starts checking all the elements of the input array.
     * The loop checks the current value of the array with the number entered for comparison and, if successful,
     * the number of days is increased by 1.
     * After the loop is completed, the result is returned to the point of the method call.
     * */
    private int getNumberDaysGreaterNumber(int[] inputDataOfAllDay, int numberForCompare) {
        int doneDay = 0;
        for (int day : inputDataOfAllDay) {
            if (day >= numberForCompare) {
                doneDay++;
            }
        }
        return doneDay;
    }
}