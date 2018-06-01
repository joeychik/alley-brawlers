/*
 * Character.java
 * Characters in the game that fight each other and stuff
 * 
 */

import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Color;

class Character extends Physical implements Moveable {
  
  private double xSpeed, ySpeed;
  private int health, maxHealth;
  private double size;
  
  private double jumpStartY;
  private boolean jumping;
  
  public Character(int x, int y, int h, int w) {
   super(x,y,h,w);
   this.xSpeed = 0;
   this.ySpeed = 15;
   this.jumping = false;
  }
  
  public void update(double elapsedTime){
   //update the content
   setXPos(getXPos()+xSpeed*elapsedTime*100);
   setYPos(getYPos()+ySpeed*elapsedTime*100);//d = d0 + vt
   if(getXPos() < 0) {
    setXPos(0); 
   } else if (getXPos() > 1920-getWidth()) {
     setXPos(1920-getWidth()); 
   }
   
   setBoxPosition(getXPos(), getYPos());
   //System.out.println(elapsedTime*10+"\n");
  }
  
  public void jump() {
    if(jumping == false) {
      
     this.jumpStartY = getYPos();
     this.ySpeed = 14; 
    } 
  }
  
  public double getXSpeed() {
   return this.xSpeed; 
  }
  
  public double getYSpeed() {
   return this.ySpeed; 
  }
  
  public void changeHealth(double x) {
   this.health += x; 
  }
  
  public void moveRight() {
   this.xSpeed = 7; 
  }
  
  public void stopMoving() {
    this.xSpeed = 0; 
  }
  
  public void moveLeft() {
   this.xSpeed = -7; 
  }
  
  public void setXSpeed(double s) {
   this.xSpeed = s; 
  }
  
  public void setYSpeed(double s) {
   this.ySpeed = s; 
  }
  
  public void draw(Graphics g) { //replace with dank sprite later
    g.setColor(Color.BLUE); //There are many graphics commands that Java can use
    g.fillRect((int)getXPos(), (int)getYPos(), getWidth(), getHeight()); //notice the y is a variable that we control from our animate method          
 }
  
}