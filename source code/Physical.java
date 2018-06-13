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
 static double scaleRatio;
 
 Physical(int x, int y, int h, int w, double scaleRatio) {
  this.xPos = scaleRatio * x;
  this.yPos = scaleRatio * y;
  this.height = (int) (scaleRatio * h);
  this.width = (int) (scaleRatio * w);
  this.scaleRatio = scaleRatio;
  boundingBox = new Rectangle((int)xPos, (int)yPos, w, h);
 }
 
 /**
  * getXPos
  * gets the x position of the object
  * @return x position
  */
 public double getXPos() {
  return xPos; 
 }
 
 /**
  * getYPos
  * gets the y position of the object
  * @return y position
  */
 public double getYPos() {
  return yPos; 
 }
 
 /**
  * getHeight
  * gets the height of the object
  * @return the height
  */
 public int getHeight() {
  return height; 
 }
 
 /**
  * getWidth
  * gets the width of the object
  * @return the width
  */
 public int getWidth() {
  return width; 
 }
 
 /**
  * getBoundingBox
  * gets the hitbox of the object
  * @return the bounding box
  */
 public Rectangle getBoundingBox() {
  return boundingBox; 
 }
 
 /**
  * setYPos
  * sets the x position of the object
  * @param x the x coordinate
  */
 public void setXPos(double x) {
   this.xPos = x;
 }
 
 /**
  * setYPos
  * sets the y position of the object
  * @param y the y coordinate
  */
 public void setYPos(double y) {
   this.yPos = y;
 }
 
 /**
  * setBoxPosition
  * sets the position of the bounding box
  * @param x the x coordinate
  * @param y the y coordinate
  */
 public void setBoxPosition(double x, double y) {
  boundingBox.x=(int)x;
  boundingBox.y=(int)y;
 }
  
}