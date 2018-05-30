/*
 * Physical.java
 * Physical objects can be interacted with by other objects
 * 5/29/2018
 * @author Eric Ke
 */

import java.awt.Rectangle;

abstract class Physical {
 private double xPos , yPos;
 private int height, width;
 private Rectangle boundingBox;
 
 Physical(int x, int y, int h, int w) {
  this.xPos = x;
  this.yPos = y;
  this.height = h;
  this.width = w;
  boundingBox = new Rectangle((int)xPos, (int)yPos, w, h);
 }
  
  
}