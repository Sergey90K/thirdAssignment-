package com.shpp.p2p.cs.skurochka.assignment3;

import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;
import java.awt.*;

public class Assignment3Part6 extends WindowProgram {
    // Head diameter in the X and Y coordinate axes.
    public static final double DIAMETER_OF_HEAD_X = 250;
    public static final double DIAMETER_OF_HEAD_Y = 200;
    // Ear diameter in the X and Y coordinate axes.
    public static final double DIAMETER_OF_EAR_X = 50;
    public static final double DIAMETER_OF_EAR_Y = 50;
    // The diameter of the inner part of the ear in the X and Y coordinate axes.
    public static final double DIAMETER_OF_EAR_INNER_X = 25;
    public static final double DIAMETER_OF_EAR_INNER_Y = 25;
    // Diameter of the eye in the X and Y coordinate axes.
    public static final double DIAMETER_OF_EYE_X = 20;
    public static final double DIAMETER_OF_EYE_Y = 20;
    // Diameter of the surface part of the eye in the X and Y axis.
    public static final double DIAMETER_OF_EYE_OVAL_X = 40;
    public static final double DIAMETER_OF_EYE_OVAL_Y = 35;
    // Diameter of the white part of the face in the X and Y axis.
    public static final double DIAMETER_OF_WHITE_PLACE_X = 95;
    public static final double DIAMETER_OF_WHITE_PLACE_Y = 55;
    // Nose diameter in the X and Y axis.
    public static final double DIAMETER_OF_NOSE_X = 20;
    public static final double DIAMETER_OF_NOSE_Y = 15;
    // Diameter of the mouth in the X and Y axis.
    public static final double DIAMETER_OF_MOUTH_X = 50;
    public static final double DIAMETER_OF_MOUTH_Y = 5;
    // The width and height of the brick.
    private static final double BRICK_WIDTH = 50;
    private static final double BRICK_HEIGHT = 25;
    // The initial velocity of the brick in coordinates X and Y.
    private static final double BRICK_START_SPEED_X = 10;
    private static final double BRICK_START_SPEED_Y = 0;
    // The initial position of the brick in coordinates X and Y.
    private static final double BRICK_START_POSITION_X = 0;
    private static final double BRICK_START_POSITION_Y = 0;
    // Font settings for the caption.
    private static final Font FONT_FOR_LABEL = new Font("Serif", Font.BOLD, 25);
    // The force of gravity.
    private static final double GRAVITY = 0.425;
    // The power of returning an eye that has left its orbit.
    private static final double RETURN_OF_THE_EYE_ROLL = 0.005;
    // The amount of time to pause between frames (48fps).
    private static final double PAUSE_TIME = 1000.0 / 48;
    // The elasticity parameter with which the brick bounces off the floor and walls
    private static final double ELASTICITY = 0.75;
    // The size of the mustache.
    private static final double MUSTACHE_SIZE = DIAMETER_OF_WHITE_PLACE_X * 2.0;
    // Displacement of the mustache.
    private static final double MUSTACHE_DISPLACEMENT = 10;
    // The value of five seconds in milliseconds.
    private static final long FIVE_SECONDS = 5000;

    // Enables the test mode to check the animation execution time.
    private static final boolean TEST_MODE = true;

    // The method of launching the program.
    @Override
    public void run() {
        // Get half the height and width for optimization.
        double halfWidth = getWidth() / 2.0;
        double halfHeight = getHeight() / 2.0;
        // A method of drawing all fixed objects.
        drawFixedParts(halfWidth, halfHeight);
        // A method of animating objects passed to it.
        animateObjects(getMobileRightEye(halfWidth, halfHeight), getMobileLeftEye(halfWidth, halfHeight),
                getMovableBrick());
    }

    /*
     * A method of animating objects.
     * In a sequence, the object adds all the passed objects to the composition.
     * Gets the initial values of the brick's position change.
     * Gets the starting positions of the eyes.
     * The animation completion time is calculated.
     * The animation loop method is invoked, passing all the necessary parameters.
     * */
    private void animateObjects(GOval rightEie, GOval leftEie, GRect brick) {
        addObjectsToScreen(rightEie, leftEie, brick);
        double startPositionEieX = leftEie.getX();
        double startPositionEieY = leftEie.getY();
        long endAnimateTime = System.currentTimeMillis() + FIVE_SECONDS;
        animationCycle(endAnimateTime, brick, BRICK_START_SPEED_X, BRICK_START_SPEED_Y,
                leftEie, rightEie, startPositionEieX, startPositionEieY);
    }

    /*
     * The animation looping method.
     * In the middle, a loop is started and runs until the computer reaches five seconds.
     * Initial variables are created to change the position of the eyes.
     * Then a shift is performed for the brick.
     * Then the change in the brick's velocity depending on gravity is calculated.
     * Then the possibility of changing the position of the brick when it hits an obstacle is calculated.
     * Next, the required change in eye position in the X and Y direction is calculated.
     * The eyes are then moved depending on the calculated parameters.
     * The last step calls the pause method with a specified time.
     * After the cycle is completed, if necessary, the test method is called to display the time of the animation.
     * */
    private void animationCycle(long endAnimateTime, GRect brick, double dxBrick, double dyBrick, GOval leftEie,
                                GOval rightEie, double startPositionEieX, double startPositionEieY) {
        while (endAnimateTime > System.currentTimeMillis()) {
            double dxEie = 0;
            double dyEie = 0;
            brick.move(dxBrick, dyBrick);
            dyBrick += GRAVITY;
            dyBrick = pushingOffTheFloor(brick, dyBrick);
            dxBrick = pushingOffTheWallRight(brick, dxBrick);
            dxBrick = pushingOffTheWallLeft(brick, dxBrick);
            dxEie = calculateThePositionOfTheEyesX(leftEie, startPositionEieX, dxEie, brick);
            dyEie = calculateThePositionOfTheEyesY(leftEie, startPositionEieY, dyEie, brick);
            leftEie.move(0 + dxEie, 0 + dyEie);
            rightEie.move(0 + dxEie, 0 + dyEie);
            pause(PAUSE_TIME);
        }
        testMode(endAnimateTime);
    }

    /*
     * A method for easy testing.
     * The method checks if the display is allowed, and if so,
     * it displays information about the animation execution time.
     * */
    private void testMode(long endAnimateTime) {
        if (TEST_MODE) {
            System.out.println("Time = " + (System.currentTimeMillis() - (endAnimateTime - FIVE_SECONDS)));
        }
    }

    /*
     * A method for calculating the possibility of eye displacement along the Y-axis.
     * The method checks the conditions for the eye to leave the border of the surface of the eye,
     * if it does not go beyond them, then the displacement is allowed.
     * But if the eye goes beyond the boundaries, it is returned by special constants.
     * */
    private double calculateThePositionOfTheEyesY(GOval leftEie, double startPositionEieY, double dyEie, GRect brick) {
        if (leftEie.getY() > startPositionEieY - DIAMETER_OF_EAR_INNER_Y / 2.0 + DIAMETER_OF_EYE_Y / 4.0 &&
                leftEie.getY() < startPositionEieY + DIAMETER_OF_EAR_INNER_Y / 2.0 - DIAMETER_OF_EYE_Y / 3.0) {
            return dyEie + getPositionY(brick);
        } else {
            if (leftEie.getY() < startPositionEieY - DIAMETER_OF_EAR_INNER_Y / 2.0 + DIAMETER_OF_EYE_Y / 4.0) {
                return RETURN_OF_THE_EYE_ROLL;
            } else if (leftEie.getY() > startPositionEieY + DIAMETER_OF_EAR_INNER_Y / 2.0 - DIAMETER_OF_EYE_Y / 3.0) {
                return -RETURN_OF_THE_EYE_ROLL;
            }
        }
        return dyEie;
    }

    /*
     * A method for calculating the possibility of eye displacement along the Х-axis.
     * The method checks the conditions for the eye to leave the border of the surface of the eye,
     * if it does not go beyond them, then the displacement is allowed.
     * But if the eye goes beyond the boundaries, it is returned by special constants.
     * */
    private double calculateThePositionOfTheEyesX(GOval leftEie, double startPositionEieX, double dxEie, GRect brick) {
        if (leftEie.getX() > startPositionEieX - DIAMETER_OF_EAR_INNER_X / 2.0 + DIAMETER_OF_EYE_X / 4.0 &&
                leftEie.getX() < startPositionEieX + DIAMETER_OF_EAR_INNER_X / 2.0 - DIAMETER_OF_EYE_X / 2.0) {
            return dxEie + getPositionX(brick);
        } else {
            if (leftEie.getX() < startPositionEieX - DIAMETER_OF_EAR_INNER_X / 2.0 + DIAMETER_OF_EYE_X / 4.0) {
                return RETURN_OF_THE_EYE_ROLL;
            } else if (leftEie.getX() > startPositionEieX + DIAMETER_OF_EAR_INNER_X / 2.0 - DIAMETER_OF_EYE_X / 2.0) {
                return -RETURN_OF_THE_EYE_ROLL;
            }
        }
        return dxEie;
    }

    /*
     * A method of pushing bricks away from the floor.
     * The conditions are checked to see if the brick has hit the floor,
     * and if the conditions are met, the direction of the brick is changed using a constant.
     * */
    private double pushingOffTheFloor(GRect brick, double dyBrick) {
        if (brickBelowFloor(brick) && dyBrick > 0) {
            return dyBrick * -ELASTICITY;
        }
        return dyBrick;
    }

    /*
     * The method of pushing a brick away from the wall from the left side.
     * The conditions are checked to see if the brick has hit the wall, and if the conditions are met,
     * the direction of the brick is changed using a constant.
     * */
    private double pushingOffTheWallLeft(GRect brick, double dxBrick) {
        if (brickBehindTheWallLeft(brick) && dxBrick < 0) {
            return dxBrick * -ELASTICITY;
        }
        return dxBrick;
    }

    /*
     * The method of pushing a brick away from the wall from the right side.
     * The conditions are checked to see if the brick has hit the wall,
     * and if the conditions are met, the direction of the brick is changed using a constant.
     * */
    private double pushingOffTheWallRight(GRect brick, double dxBrick) {
        if (brickBehindTheWallRight(brick) && dxBrick > 0) {
            return dxBrick * -ELASTICITY;
        }
        return dxBrick;
    }

    /*
     * A method of adding objects to the screen.
     * In the middle, each object is added to the screen composition one by one.
     * */
    private void addObjectsToScreen(GOval rightEie, GOval leftEie, GRect brick) {
        add(rightEie);
        add(leftEie);
        add(brick);
    }

    /*
     * The method of obtaining the object is brick.
     * The method simply returns a newly created GRect object with the specified parameters.
     * */
    private GRect getMovableBrick() {
        return getOneBrick(BRICK_START_POSITION_X, BRICK_START_POSITION_Y, BRICK_WIDTH, BRICK_HEIGHT);
    }

    /*
     * Method for getting the left eye object.
     * The method simply returns a newly created GOval object with the specified parameters.
     * */
    private GOval getMobileLeftEye(double halfWidth, double halfHeight) {
        return getOval(halfWidth - DIAMETER_OF_EYE_X / 2 + DIAMETER_OF_HEAD_X / 4.0,
                halfHeight - DIAMETER_OF_EYE_Y / 2.0,
                DIAMETER_OF_EYE_X,
                DIAMETER_OF_EYE_Y,
                Color.BLACK);
    }

    /*
     * Method for getting the right eye object.
     * The method simply returns a newly created GOval object with the specified parameters.
     * */
    private GOval getMobileRightEye(double halfWidth, double halfHeight) {
        return getOval(halfWidth - DIAMETER_OF_EYE_X / 2 - DIAMETER_OF_HEAD_X / 4.0,
                halfHeight - DIAMETER_OF_EYE_Y / 2.0,
                DIAMETER_OF_EYE_X,
                DIAMETER_OF_EYE_Y,
                Color.BLACK);
    }

    /*
     * A method of drawing fixed parts of a figure.
     * The method uses methods to draw first the ears,
     * then the head, then the ovals of the eyes, then the mustache,
     * then the rest of the face and finally adds the inscription.
     * */
    public void drawFixedParts(double halfWidth, double halfHeight) {
        drawEars(halfWidth, halfHeight);
        drawHead(halfWidth, halfHeight);
        drawOvalOfEyes(halfWidth, halfHeight);
        drawMustache(halfWidth, halfHeight);
        drawFace(halfWidth, halfHeight);
        drawLabel(halfWidth);
    }

    /*
     * The method of drawing an inscription.
     * First, an object of type GLabel is created with a label string.
     * The next step is to set the font.
     * Then set the color of the inscription.
     * After that, the position of the label is set.
     * At the end, the label is added to the screen composition.
     * */
    private void drawLabel(double halfWidth) {
        GLabel gLabel = new GLabel("-( Building )-");
        gLabel.setFont(FONT_FOR_LABEL);
        gLabel.setColor(Color.RED);
        gLabel.setLocation(halfWidth - gLabel.getWidth() / 2.0, 0 + gLabel.getHeight());
        add(gLabel);
    }

    /*
     * The method of drawing a face.
     * In the middle, three objects of type GOval are created,
     * for the white part of the face, for the nose and mouth,
     * according to the required parameters.
     * Then all the objects are added to the screen composition one by one.
     * */
    private void drawFace(double halfWidth, double halfHeight) {
        GOval whitePlaceOnFace = getOval(halfWidth - DIAMETER_OF_WHITE_PLACE_X / 2,
                halfHeight - DIAMETER_OF_WHITE_PLACE_Y / 2.0 + DIAMETER_OF_HEAD_Y / 4.0,
                DIAMETER_OF_WHITE_PLACE_X,
                DIAMETER_OF_WHITE_PLACE_Y,
                Color.WHITE);
        GOval nose = getOval(halfWidth - DIAMETER_OF_NOSE_X / 2.0,
                halfHeight - DIAMETER_OF_NOSE_Y / 2.0 + DIAMETER_OF_HEAD_Y / 4.0 -
                        DIAMETER_OF_WHITE_PLACE_Y / 4.0,
                DIAMETER_OF_NOSE_X,
                DIAMETER_OF_NOSE_Y,
                Color.DARK_GRAY);
        GOval mouth = getOval(halfWidth - DIAMETER_OF_MOUTH_X / 2.0,
                halfHeight - DIAMETER_OF_MOUTH_Y / 2.0 + DIAMETER_OF_HEAD_Y / 4.0 +
                        DIAMETER_OF_WHITE_PLACE_Y / 4.0,
                DIAMETER_OF_MOUTH_X,
                DIAMETER_OF_MOUTH_Y,
                Color.RED);
        add(whitePlaceOnFace);
        add(nose);
        add(mouth);
    }

    /*
     * The method of drawing a mustache.
     * In the middle, three objects of type GLine are created for each
     * part of the mustache with the necessary parameters.
     * Then all the objects are added to the screen composition one by one.
     * */
    private void drawMustache(double halfWidth, double halfHeight) {
        GLine centralMustache = getLine(halfWidth - MUSTACHE_SIZE / 2.0,
                halfHeight + DIAMETER_OF_HEAD_Y / 4.0,
                halfWidth + MUSTACHE_SIZE / 2.0,
                halfHeight + DIAMETER_OF_HEAD_Y / 4.0);
        GLine firstMustache = getLine(halfWidth - MUSTACHE_SIZE / 2.0,
                halfHeight + DIAMETER_OF_HEAD_Y / 4.0 + MUSTACHE_DISPLACEMENT,
                halfWidth + MUSTACHE_SIZE / 2.0,
                halfHeight + DIAMETER_OF_HEAD_Y / 4.0 - MUSTACHE_DISPLACEMENT);
        GLine secondMustache = getLine(halfWidth - MUSTACHE_SIZE / 2.0,
                halfHeight + DIAMETER_OF_HEAD_Y / 4.0 - MUSTACHE_DISPLACEMENT,
                halfWidth + MUSTACHE_SIZE / 2.0,
                halfHeight + DIAMETER_OF_HEAD_Y / 4.0 + MUSTACHE_DISPLACEMENT);
        add(centralMustache);
        add(firstMustache);
        add(secondMustache);
    }

    /*
     * The method of drawing ovals for the eyes.
     * In the middle, two objects of type GOval are created with the appropriate parameters,
     * which serve as the right and left oval for the eyes.
     * Then both objects are added to the screen composition one by one.
     * */
    private void drawOvalOfEyes(double halfWidth, double halfHeight) {
        GOval rightOvalEie = getOval(halfWidth - DIAMETER_OF_EYE_OVAL_X / 2 - DIAMETER_OF_HEAD_X / 4.0,
                halfHeight - DIAMETER_OF_EYE_OVAL_Y / 2.0,
                DIAMETER_OF_EYE_OVAL_X,
                DIAMETER_OF_EYE_OVAL_Y,
                Color.WHITE);
        GOval leftOvalEie = getOval(halfWidth - DIAMETER_OF_EYE_OVAL_X / 2 + DIAMETER_OF_HEAD_X / 4.0,
                halfHeight - DIAMETER_OF_EYE_OVAL_Y / 2.0,
                DIAMETER_OF_EYE_OVAL_X,
                DIAMETER_OF_EYE_OVAL_Y,
                Color.WHITE);
        add(rightOvalEie);
        add(leftOvalEie);
    }

    /*
     * The method of drawing a head.
     * A GOval object is created with the appropriate parameters for the head.
     * Then the object is added to the screen composition.
     * */
    private void drawHead(double halfWidth, double halfHeight) {
        GOval headOval = getOval((halfWidth) - (DIAMETER_OF_HEAD_X / 2.0),
                (halfHeight) - (DIAMETER_OF_HEAD_Y / 2.0),
                DIAMETER_OF_HEAD_X,
                DIAMETER_OF_HEAD_Y,
                Color.YELLOW);
        add(headOval);
    }

    /*
     * The method of drawing ears.
     * First, a parameter is created that is repeated in two cases.
     * Then four objects of type GOval are created with the appropriate parameters,
     * which serve as the right ear and the right part of the inner ear,
     *  and the same parts for the left ear.
     * Then the created objects are added to the screen composition one by one.
     * */
    private void drawEars(double halfWidth, double halfHeight) {
        double positionY = (halfHeight) - (DIAMETER_OF_HEAD_Y / 2.0) + (DIAMETER_OF_EAR_Y / 2.0) -
                (DIAMETER_OF_EAR_INNER_Y / 2.0);
        GOval earRightOval = getOval((halfWidth) + ((DIAMETER_OF_HEAD_X + DIAMETER_OF_EAR_Y) / 4.0),
                (halfHeight) - (DIAMETER_OF_HEAD_Y / 2.0),
                DIAMETER_OF_EAR_X,
                DIAMETER_OF_EAR_Y,
                Color.YELLOW);
        GOval earRightOvalInner = getOval((halfWidth) + ((DIAMETER_OF_HEAD_X + DIAMETER_OF_EAR_Y) / 4.0) +
                        (DIAMETER_OF_EAR_X / 2.0) - (DIAMETER_OF_EAR_INNER_X / 2.0),
                positionY,
                DIAMETER_OF_EAR_INNER_X,
                DIAMETER_OF_EAR_INNER_Y,
                Color.WHITE);
        GOval earLeftOval = getOval((halfWidth) - (DIAMETER_OF_HEAD_X / 2.0),
                (halfHeight) - (DIAMETER_OF_HEAD_Y / 2.0),
                DIAMETER_OF_EAR_X,
                DIAMETER_OF_EAR_Y,
                Color.YELLOW);
        GOval earLeftOvalInner = getOval((halfWidth) - (DIAMETER_OF_HEAD_X / 2.0) + (DIAMETER_OF_EAR_X / 4.0),
                positionY,
                DIAMETER_OF_EAR_INNER_X,
                DIAMETER_OF_EAR_INNER_Y,
                Color.WHITE);
        add(earRightOval);
        add(earRightOvalInner);
        add(earLeftOval);
        add(earLeftOvalInner);
    }

    /*
     * A method of drawing lines with appropriate parameters.
     * In the middle, a GLine object is created with the passed parameters.
     * Then the color is set to the object.
     * Then the created object is returned to the point of the method call.
     * */
    private GLine getLine(double startX, double startY, double finishX, double finishY) {
        GLine gLine = new GLine(startX, startY, finishX, finishY);
        gLine.setColor(Color.GRAY);
        return gLine;
    }

    /*
     * Method for obtaining a mix of positions along the Y-axis.
     * First, the position of the brick along the Y-axis is calculated.
     * Then the value calculated depending on the position of the brick is returned.
     * */
    private double getPositionY(GRect gRect) {
        double position = gRect.getY();
        return receivePositionData(position);
    }

    /*
     * A method of obtaining data on the required axis offset.
     * The method checks the input parameters against constants and calculates the result
     * of the required offset for the eyes depending on the position of the brick.
     * */
    private double receivePositionData(double position) {
        if (position < 100) {
            return -0.17;
        } else if (position < 200) {
            return -0.15;
        } else if (position < 300) {
            return 0;
        } else if (position < 400) {
            return 0.15;
        } else {
            return 0.17;
        }
    }

    /*
     * Method for obtaining a mix of positions along the Х-axis.
     * First, the position of the brick along the Х-axis is calculated.
     * Then the value calculated depending on the position of the brick is returned.
     * */
    private double getPositionX(GRect gRect) {
        double position = gRect.getX();
        return receivePositionData(position);
    }

    /*
     * A method of checking whether a brick is under the floor.
     * A boolean value is returned depending on the result of the comparison.
     **/
    private boolean brickBelowFloor(GRect brick) {
        return brick.getY() + brick.getHeight() >= getHeight();
    }

    /*
     * A method for checking whether a brick is behind the right wall.
     * The boolean result of the comparison of parameters is returned.
     * */
    private boolean brickBehindTheWallRight(GRect brick) {
        return brick.getX() + brick.getWidth() >= getWidth();
    }

    /*
     * A method for checking whether a brick is behind the left wall.
     * The boolean result of the comparison of parameters is returned.
     * */
    private boolean brickBehindTheWallLeft(GRect brick) {
        return brick.getX() + brick.getWidth() <= brick.getWidth();
    }

    /*
     * The method of obtaining ovals.
     * The method creates an object of type GOval with position input parameters.
     * Then the filling of objects with color is turned on.
     * Then the color of the interior of the object is set.
     * At the end, the newly created object is returned to the method call point.
     * */
    private GOval getOval(double positionX, double positionY, double diameterX, double diameterY, Color color) {
        GOval gOval = new GOval(positionX, positionY, diameterX, diameterY);
        gOval.setFilled(true);
        gOval.setFillColor(color);
        return gOval;
    }

    /*
     * The method of obtaining one brick.
     * The method creates an object of type GRect with position input parameters.
     * Then the visibility of the internal part of the object is turned on.
     * After that, the color of the inner part is set.
     * The last step is to return the newly created object to the place of the method call.
     * */
    private GRect getOneBrick(double startPositionX, double startPositionY, double brickWidth, double brickHeight) {
        GRect gRect = new GRect(startPositionX, startPositionY, brickWidth, brickHeight);
        gRect.setFilled(true);
        gRect.setFillColor(Color.YELLOW);
        return gRect;
    }
}