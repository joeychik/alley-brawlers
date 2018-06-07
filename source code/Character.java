/*
 * Character.java
 * Characters in the game that fight each other and stuff
 * @author Eric Ke, Joey Chik
 */

// imports

import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.util.Random;

class Character extends Physical implements Moveable {
  
  private double xSpeed, ySpeed;
  private double health;
  private double attackStrength;
  private double size;
  private char facing;
  
  private static Image sprite;
  
  private double jumpStartY;
  private boolean jumping;
  private boolean attacking = false;
  private double attackRemainingTime = 0;
  
  private Attack[] attackList = new Attack[3];
  
  
  public Character(int x,int y,int h, int w, char facing, String spriteAddress) {
   super(x,y,h,w);
   Random randNum = new Random();
   this.health = randNum.nextInt(1000)+1000.0;
   this.attackStrength = (randNum.nextInt(500)+1500)/1000.0;
   this.jumping = false;
   this.xSpeed = 0;
   this.ySpeed = 2;
   this.facing = facing;
   this.attackList[0] = new MeleeAttack(attackStrength);
   
   this.sprite = new ImageIcon(spriteAddress).getImage();
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
   
   if(jumping) {
    this.ySpeed += 40*elapsedTime; 
   }
   
   if(attacking) {
    this.xSpeed = 0;
    if (attackRemainingTime > 0) {
      this.attacking = true;
      this.attackRemainingTime -= 100*elapsedTime; 
      for(int i = 0; i > attackList.length; i++) {
       attackList[i].removeRectangle(); 
      }
    } else {
      attacking = false;
    }
   }
   
   setBoxPosition(getXPos(), getYPos());
   //System.out.println(elapsedTime*10+"\n");
  }
  
  public void jump() {
    if(!jumping) {
     this.jumping = true;
     this.jumpStartY = getYPos();
     this.ySpeed = -14;
     this.setYPos(getYPos() - 1);
    } 
  }
  
  public void attack(int n, Character c) {
    if(facing == 'r') {
      attackList[n].useAttack(c, (int)getXPos(), (int)getYPos());
    } else {
      attackList[n].useAttack(c, (int)getXPos()-attackList[n].getWidth(), (int)getYPos()-attackList[n].getHeight());
    }
   attackRemainingTime = attackList[n].getDuration();
   attacking = true;
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
  
  public void setDirection(char direction) {
   this.facing = direction; 
  }
  
  public void setJumping(boolean jump) { // change this so we dont fail
    this.jumping = jump;
  }
  
  public boolean getAttacking() {
   return this.attacking; 
  }
  
  public double getHealth() {
   return this.health; 
  }
  
  public void draw(Graphics g) { //replace with dank sprite later
    g.setColor(Color.BLUE); //There are many graphics commands that Java can use
    //g.fillRect((int)getXPos(), (int)getYPos(), getWidth(), getHeight()); //notice the y is a variable that we control from our animate method     
    g.drawImage(sprite , (int)getXPos() , (int)getYPos() , getWidth() , getHeight() , null);
  }
  
}