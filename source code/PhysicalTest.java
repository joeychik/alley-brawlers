/*
 * PhysicalTest.java
 * Test File 0
 * 
 */

import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Color;

class PhysicalTest extends Physical implements Moveable {
  
  private double xSpeed, ySpeed;
  
  public PhysicalTest(int x, int y, int h, int w) {
   super(x,y,h,w);
   xSpeed = 2;
   ySpeed = 2;
  }
  
  public void update(double elapsedTime){
   //update the content
   setXPos(getXPos()+xSpeed*elapsedTime*100);
   setYPos(getYPos()+ySpeed*elapsedTime*100);//d = d0 + vt
   setBoxPosition(getXPos(), getYPos());
   //System.out.println(elapsedTime*10+"\n");
  }
  
  public void setXSpeed(double s) {
   this.xSpeed = s; 
  }
  
  public void setYSpeed(double s) {
   this.ySpeed = s; 
  }
  
  public void draw(Graphics g) { 
    g.setColor(Color.BLUE); //There are many graphics commands that Java can use
    g.fillRect((int)getXPos(), (int)getYPos(), getWidth(), getHeight()); //notice the y is a variable that we control from our animate method          
 }
  
}