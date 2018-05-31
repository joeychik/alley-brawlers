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
  
  Floor(int x, int y, int h, int w) {
    super(x, y, h, w);
  }
  
  Floor() {
    super(0, 700, 200, 10000);
  }
  
  public void draw(Graphics g) { 
    g.setColor(Color.BLACK); //There are many graphics commands that Java can use
    g.fillRect((int)getXPos(), (int)getYPos(), getWidth(), getHeight()); //notice the y is a variable that we control from our animate method          
 }
 
}