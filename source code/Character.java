/*
 * Character.java
 * Characters in the game that fight each other and stuff
 * @author Eric Ke, Joey Chik
 */

import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;

class Character extends Physical implements Moveable {
  
  private double xSpeed, ySpeed;
  private double health;
  private double attackStrength;
  private double size;
  private char facing;
  private Image sprite;
  private Image[] sprites = new Image[4];
  
  
  static double scaleRatio;
  
  private double jumpStartY;
  private boolean jumping;
  private boolean attacking = false;
  private double attackRemainingTime = 0;
  
  private Attack[] attackList = new Attack[3];
  
  
  public Character(int x,int y,int h, int w, double scaleRatio, char facing, String spriteAddress) {
   super(x,y,h,w,scaleRatio);
   System.out.println("character");
   Random randNum = new Random();
   this.health = randNum.nextInt(1000)+1000.0;
   this.sprite = new ImageIcon(spriteAddress + ".png").getImage();
   this.sprites[0] = new ImageIcon(spriteAddress + ".png").getImage();
   this.sprites[1] = new ImageIcon(spriteAddress + "Left.png").getImage();
   this.sprites[2] = new ImageIcon(spriteAddress + "Punch.png").getImage();
   this.sprites[3] = new ImageIcon(spriteAddress + "PunchLeft.png").getImage();
   this.attackStrength = (randNum.nextInt(500)+1500)/1000.0;
   this.jumping = true;
   this.xSpeed = 0;
   this.ySpeed = 0;
   this.facing = facing;
   this.attackList[0] = new MeleeAttack(attackStrength);
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
   
   if(getYPos() > 880) {
    setXPos(880); 
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
   
   if (attacking == false) {
     if (facing == 'r') {
       this.sprite = this.sprites[0]; 
     } else {
       this.sprite = this.sprites[1]; 
     }
   } else {
     if (facing == 'r') {
       this.sprite = this.sprites[2]; 
     } else {
       this.sprite = this.sprites[3]; 
     }
   }
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
      attackList[n].useAttack(c, (int)getXPos()-attackList[n].getWidth(), (int)getYPos());
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
    //System.out.println((int)getXPos()+", "+(int)getYPos()+", "+getWidth()+", "+getHeight());
    g.drawImage(sprite, (int)getXPos(), (int)getYPos(), getWidth(), getHeight(), null); //notice the y is a variable that we control from our animate method          
 }
  
}