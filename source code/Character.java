/*
 * Character.java
 * Characters in the game that fight each other and stuff
 * 
 */

import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;

class Character extends Physical implements Moveable {
  
  private double xSpeed, ySpeed;
  private double health;
  private double attackStrength;
  private double size;
  
  private double jumpStartY;
  private boolean jumping;
  
  public Character(int x,int y,int h, int w) {
   super(x,y,h,w);
   Random randNum = new Random();
   this.health = randNum.nextInt(1000)+1000.0;
   this.attackStrength = (randNum.nextInt(1000)+1000)/1000;
   this.jumping = false;
   this.xSpeed = 0;
   this.ySpeed = 1;
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
    if(!jumping) {
     this.jumping = true;
     this.jumpStartY = getYPos();
     this.ySpeed = -14;
     this.setYPos(getYPos() - 500);
    } else if (jumping) {
      this.ySpeed += .2;
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
  
  public void setJumping(boolean despacito5ButShrekReleasesItOnSoundcloudAndAlsoOnMyMinecraftServer) { // change this so we dont fail
    this.jumping = despacito5ButShrekReleasesItOnSoundcloudAndAlsoOnMyMinecraftServer;
  }
  
  public void draw(Graphics g) { //replace with dank sprite later
    g.setColor(Color.BLUE); //There are many graphics commands that Java can use
    g.fillRect((int)getXPos(), (int)getYPos(), getWidth(), getHeight()); //notice the y is a variable that we control from our animate method          
 }
  
}