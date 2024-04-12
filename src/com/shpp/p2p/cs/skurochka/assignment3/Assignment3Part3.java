package com.shpp.p2p.cs.skurochka.assignment3;

import com.shpp.cs.a.console.TextProgram;

public class Assignment3Part3 extends TextProgram {
    // A constant for entering data into a method created exclusively for testing purposes.
    private static final double INPUT_NUMBER = 2;
    // The exponent constant created for testing the method.
    private static final int POWER_OF_NUMBER = 3;

    // The method of launching the program.
    @Override
    public void run() {
        // Displaying the data calculated in the method.
        System.out.println("result = " + raiseToPower(INPUT_NUMBER, POWER_OF_NUMBER));
    }

    /*
     * The method of raising to a power.
     * The method accepts a value of type double to be raised to a power,
     * and the next parameter is a power of int type.
     * The method returns a result of type double.
     * In the middle of the spotting method, the resulting value is created,
     * which is initialized by the input parameter.
     * Then the degree is checked for zero, if it is successful, the value one is added to the result.
     * If the condition is not met, then the next check is whether the exponent is greater than zero, if it is,
     * then a loop is started that multiplies the input value by the number of times the exponent is equal to.
     * The results are entered into the result variable.
     * If none of the conditions is met, the last branch of checks is executed.
     * In this case, the parameters are brought to positive,
     * and a loop is started that sequentially performs multiplication.
     * Then the value of the degree is checked for evenness and the corresponding reduction is performed.
     * The last step is to return the results to the method call point.
     * */
    private double raiseToPower(double base, int exponent) {
        double result = base;
        if (exponent == 0) {
            result = 1;
        } else if (exponent > 0) {
            for (int i = 1; i < exponent; i++) {
                result *= base;
            }
        } else {
            result *= -1;
            base *= -1;
            for (int i = -1; i > exponent; i--) {
                result *= base;
            }
            result = exponent % 2 == 0 ? 1 / result : 1 / result * -1;
        }
        return result;
    }
}