/*
 * Floor.java
 * Physical objects can be interacted with by other objects
 * 5/29/2018
 * @author Eric Ke
 */

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;

class Floor extends Physical {
  
  /**
   * Constructs a floor
   * @param x the x position of floor
   * @param y the y position of the floor
   * @param h the height of the floor
   * @param w the width of the floor
   * @param scaleRatio scale ratio of the screen
   */
  Floor(int x, int y, int h, int w, double scaleRatio) {
    super(x, y, h, w, scaleRatio);
    System.out.println((int)getXPos() + ", " + (int)getYPos() + ", " + getWidth() + ", " + getHeight());
  }
  
  /**
   * Constructor for floor with default size
   * @param scaleRatio scale ratio of the screen
   */
  Floor(double scaleRatio) {
    super(0, 980, 110, 1930, scaleRatio);
  }
  
  
 
}