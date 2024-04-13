package com.shpp.p2p.cs.skurochka.assignment3;

import acm.graphics.GLine;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;
import java.awt.*;

public class Assignment3Part4 extends WindowProgram {
    // The height constant of the bricks. Available change for testing.
    public static final double BRICK_HEIGHT = 20;
    // Constant width of the bricks. Available change for testing.
    public static final double BRICK_WIDTH = 50;
    // Constant number of bricks in the base. Available change for testing.
    public static final int BRICKS_IN_BASE = 15;

    // Brick height constant, standard value. No change is available for testing.
    public static final double BRICK_HEIGHT_DEFAULT = 20;
    // Brick width constant, standard value. No change is available for testing.
    public static final double BRICK_WIDTH_DEFAULT = 50;
    // Constant for the number of bricks in the base, standard value. No change is available for testing.
    public static final int BRICKS_IN_BASE_DEFAULT = 15;

    // A variable to enable a test mode that adds a centering line. The variable is available for testing.
    public static final boolean TEST_MODE = false;

    // The method of launching the program.
    @Override
    public void run() {
        // The width of the bricks that has been validated.
        double brickWidth = getBrickWidth(BRICK_WIDTH);
        // Brick heights that have been validated.
        double brickHeight = getBrickHeight(BRICK_HEIGHT);
        // The number of bricks that have been validated.
        int bricksInBase = getBrickInBase(BRICKS_IN_BASE);
        // The width of all the bricks.
        double allBrickWidth = brickWidth * bricksInBase;
        // Starting position X of the first brick, at the base.
        double startPositionX = (getWidth() / 2.0) - (allBrickWidth / 2.0);
        // Start position Y of the first brick, at the base.
        double startPositionY = getHeight() - brickHeight;
        // The number of rows of bricks in height.
        int towerHeightIterations = bricksInBase;

        // The method of drawing a pyramid of bricks.
        drawFullPyramid(towerHeightIterations, startPositionX, startPositionY, brickWidth, brickHeight, bricksInBase);
        // Method of enabling the test mode.
        drawTestLine(TEST_MODE);
    }

    /*
     * The method of drawing a pyramid of bricks.
     * The method accepts the parameters of the pyramid height,
     * the starting positions X and Y for the first brick of the base,
     * the width of the brick, the height of the brick, and the number of bricks in the base. In the middle,
     * a loop is enabled that depends on the height of the pyramid, and in the loop draws one line for the pyramid.
     * */
    private void drawFullPyramid(int towerHeight, double startPositionX, double startPositionY,
                                 double brickWidth, double brickHeight, int bricksInBase) {
        for (int i = 0; i < towerHeight; i++) {
            drawOneRowBrick(startPositionX + (brickWidth / 2.0 * i),
                    startPositionY - (brickHeight * i),
                    bricksInBase - i,
                    brickWidth,
                    brickHeight);
        }
    }

    /*
     * A method for drawing a single row of bricks.
     * The method takes the starting positions X and Y for the first brick,
     * the width of the brick, the height of the brick, and the number of bricks in the row.
     * In the middle, a loop is enabled that depends on the number of bricks in the row,
     * and in the loop draws one brick per row of bricks.
     * */
    private void drawOneRowBrick(double startPositionX, double startPositionY, double bricksNumber,
                                 double brickWidth, double brickHeight) {
        for (int i = 0; i < (int) bricksNumber; i++) {
            drawOneBrick(startPositionX + (brickWidth * i), startPositionY, brickWidth, brickHeight);
        }
    }

    /*
     * The method of drawing a single brick.
     * Accepts starting position in X and Y, as well as width and height.
     * In the middle, a GRect object is created with the input parameters.
     * The next step is to set the visibility of the figure's interior.
     * Then we set the long color of the interior.
     * The last step is to add the shape to the screen composition.
     * */
    private void drawOneBrick(double startPositionX, double startPositionY,
                              double brickWidth, double brickHeight) {
        GRect gRect = new GRect(startPositionX, startPositionY, brickWidth, brickHeight);
        gRect.setFilled(true);
        gRect.setFillColor(Color.YELLOW);
        add(gRect);
    }

    /*
     * A method for validating the width of a brick.
     * The parameter of the brick width is accepted.
     * The value is compared to zero in the middle.
     * If the value is greater, it is returned.
     * If not, the default value is returned.
     * */
    private double getBrickWidth(double inputWidth) {
        return inputWidth > 0 ? inputWidth : BRICK_WIDTH_DEFAULT;
    }

    /*
     * Method for validating the height of a brick.
     * The parameter of the brick height is accepted.
     * The value is compared to zero in the middle.
     * If the value is greater, it is returned.
     * If not, the default value is returned.
     * */
    private double getBrickHeight(double inputHeight) {
        return inputHeight > 0 ? inputHeight : BRICK_HEIGHT_DEFAULT;
    }

    /*
     * Method for validating the number of bricks.
     * The parameter of the number of bricks is accepted.
     * The value is compared to zero in the middle.
     * If the value is greater, it is returned.
     * If not, the default value is returned.
     * */
    private int getBrickInBase(int inputSize) {
        return inputSize > 0 ? inputSize : BRICKS_IN_BASE_DEFAULT;
    }

    /*
     * The method of drawing test lines.
     * Takes a Boolean value. If the boolean value is true,
     * a GLine object is created according to the parameters of the middle of the screen.
     * Then the red color for the line is set.
     * The last step is to add the object to the screen composition.
     * */
    private void drawTestLine(boolean testMode) {
        if (testMode) {
            GLine gLine = new GLine(getWidth() / 2.0, 0, getWidth() / 2.0, getHeight());
            gLine.setColor(Color.RED);
            add(gLine);
        }
    }
}