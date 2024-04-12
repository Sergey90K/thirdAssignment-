package com.shpp.p2p.cs.skurochka.assignment3;

import acm.graphics.GLine;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;
import java.awt.*;

public class Assignment3Part4 extends WindowProgram {
    public static final double BRICK_HEIGHT = 20;
    public static final double BRICK_WIDTH = 50;
    public static final int BRICKS_IN_BASE = 10;

    public static final double BRICK_HEIGHT_DEFAULT = 20;
    public static final double BRICK_WIDTH_DEFAULT = 50;
    public static final int BRICKS_IN_BASE_DEFAULT = 15;

    public static final boolean TEST_MODE = false;

    @Override
    public void run() {
        double brickWidth = getBrickWidth(BRICK_WIDTH);
        double brickHeight = getBrickHeight(BRICK_HEIGHT);
        int bricksInBase = getBrickInBase(BRICKS_IN_BASE);
        double allBrickWidth = brickWidth * bricksInBase;
        double startPositionX = (getWidth() / 2.0) - (allBrickWidth / 2.0);
        double startPositionY = getHeight() - brickHeight;
        int towerHeightIterations = bricksInBase;

        drawFullPyramid(towerHeightIterations, startPositionX, startPositionY, brickWidth, brickHeight, bricksInBase);

        drawTestLine(TEST_MODE);
    }

    private void drawFullPyramid(int towerHeight,
                                 double startPositionX,
                                 double startPositionY,
                                 double brickWidth,
                                 double brickHeight,
                                 int bricksInBase) {
        for (int i = 0; i < towerHeight; i++) {
            drawOneRowBrick(startPositionX + (brickWidth / 2.0 * i),
                    startPositionY - (brickHeight * i),
                    bricksInBase - i,
                    brickWidth,
                    brickHeight);
        }
    }

    private void drawOneRowBrick(double startPositionX,
                                 double startPositionY,
                                 double bricksNumber,
                                 double brickWidth,
                                 double brickHeight) {
        for (int i = 0; i < (int) bricksNumber; i++) {
            drawOneBrick(startPositionX + (brickWidth * i),
                    startPositionY,
                    brickWidth,
                    brickHeight);
        }
    }

    private void drawOneBrick(double startPositionX,
                              double startPositionY,
                              double brickWidth,
                              double brickHeight) {
        GRect gRect = new GRect(startPositionX, startPositionY, brickWidth, brickHeight);
        gRect.setFilled(true);
        gRect.setFillColor(Color.YELLOW);
        add(gRect);
    }

    private double getBrickWidth(double inputWidth) {
        if (inputWidth > 0) {
            return inputWidth;
        } else {
            return BRICK_WIDTH_DEFAULT;
        }
    }

    private double getBrickHeight(double inputHeight) {
        if (inputHeight > 0) {
            return inputHeight;
        } else {
            return BRICK_HEIGHT_DEFAULT;
        }
    }

    private int getBrickInBase(int inputSize) {
        if (inputSize > 0) {
            return inputSize;
        } else {
            return BRICKS_IN_BASE_DEFAULT;
        }
    }

    private void drawTestLine(boolean testMode) {
        if (testMode) {
            GLine gLine = new GLine(getWidth() / 2.0, 0, getWidth() / 2.0, getHeight());
            gLine.setColor(Color.RED);
            add(gLine);
        }
    }
}
