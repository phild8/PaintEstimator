package edu.orangecoastcollege.cs273.pdavis11.paintestimator;

/**
 * The Model. Creates an object: InteriorRoom. Has fields mLength, mWidth, mHeight, mDoors,
 * mWindows. Preforms calculations on how much paint will be required for a room that is a
 * certain square footage.
 *
 * @author Phillip Davis
 * @version  1.0
 *
 * Created by pdavis11 on 9/19/2017.
 */
public class InteriorRoom {

    public static final float DOOR_AREA = 21f;
    public static final float WINDOW_AREA = 16f;
    public static final float SQ_FEET_PER_GALLON = 275f;


    private float mLength;
    private float mWidth;
    private float mHeight;

    private int mDoors;
    private int mWindows;

    /**
     * Gets mLength
     * @return a float of mLength
     */
    public float getLength() {return mLength;}

    /**
     * set a new mLength float
     * @param length the new mLength value
     */
    public void setLength(float length) {
        mLength = length;
    }

    /**
     * Gets the mWidth float
     * @return mWidth
     */
    public float getWidth() {
        return mWidth;
    }

    /**
     * Sets the new mWidth float
     * @param width the mWidth value
     */
    public void setWidth(float width) {
        mWidth = width;
    }

    /**
     * Gets the mHeight float
     * @return the mHeight value.
     */
    public float getHeight() {
        return mHeight;
    }

    /**
     * Sets the new mHeight float
     * @param height the new mHeight value
     */
    public void setHeight(float height) {
        mHeight = height;
    }

    /**
     * Gets mDoors float
     * @return the mDoors value
     */
    public int getDoors() {
        return mDoors;
    }

    /**
     * Sets the mDoors float.
     * @param doors the new mDoors value.
     */
    public void setDoors(int doors) {
        mDoors = doors;
    }

    /**
     * Gets the mWindows float.
     * @return the mWindows value.
     */
    public int getWindows() {
        return mWindows;
    }

    /**
     * Sets the mWindows float.
     * @param windows the new mWindows float value.
     */
    public void setWindows(int windows) {
        mWindows = windows;
    }

    /**
     * Calculates the area of the mDoors and mWindows.
     * @return the float value of the area of mWindows and mDoors.
     */
    public float doorAndWindowArea()
    {
        return mDoors * DOOR_AREA + mWindows * WINDOW_AREA;
    }

    /**
     * Calculates the wall surface area of mLength, mHeight, and mWidth.
     * @return the float value of the surface area of, mWidth, mHeight, and mLength.
     */
    public float wallSurfaceArea()
    {
        return 2 * mLength * mHeight + 2 * mWidth * mHeight + mLength * mWidth;
    }

    /**
     * Calculated surface of the walls, excluding mDoors and mWindows.
     * @return a float value of the surface area excluding mDoors and mWindows.
     */
    public float totalSurfaceArea()
    {
        return wallSurfaceArea() - doorAndWindowArea();
    }

    /**
     * The total float value of gallons paint required to paint the walls.
     * @return a float float value of the paint required to paint the walls.
     */
    public float gallonsOfPaintRequired()
    {
        return totalSurfaceArea() / SQ_FEET_PER_GALLON;
    }
}
