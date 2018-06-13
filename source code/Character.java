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
  private double speedStat;
  private double health;
  private double attackStrength;
  private double size;
  private char facing;
  private Image sprite;
  private Image[] sprites = new Image[4];
  
  private double scaleRatio;
  
  private boolean jumping;
  private double stunTime = 0;
  private boolean attacking = false;
  private double attackRemainingTime = 0;
  
  private Attack[] attackList = new Attack[3];
  
  
  public Character(int x,int y,int h, int w, double scaleRatio, char facing, String spriteAddress) {
   super(x,y,h,w,scaleRatio);
   Random randNum = new Random();
   this.scaleRatio = scaleRatio;
   this.health = randNum.nextInt(1000)+1000.0;

   this.sprite = new ImageIcon(spriteAddress + "rest.png").getImage();
   this.sprites[0] = new ImageIcon(spriteAddress + "rest.png").getImage();
   this.sprites[1] = new ImageIcon(spriteAddress + "left.png").getImage();
   this.sprites[2] = new ImageIcon(spriteAddress + "punch.png").getImage();
   this.sprites[3] = new ImageIcon(spriteAddress + "leftPunch.png").getImage();

   this.attackStrength = (randNum.nextInt(500)+1500)/1000.0;
   this.speedStat = Math.random() + 0.5;
   this.jumping = true;
   this.xSpeed = 0;
   this.ySpeed = 0;
   this.facing = facing;
   this.attackList[0] = new MeleeAttack(attackStrength);
   this.attackList[1] = new BigAttack(attackStrength);
   this.attackList[2] = new ProjectileAttack(attackStrength);
  }
  
  public boolean isStunned() {
    if (stunTime > 0) {
      return true;
    } else {
      return false;
    }
  }
  
  public void stun(double duration) {
   stunTime = duration; 
  }
  
  public void update(double elapsedTime){
   //update the content
    this.facing = this.facing;
   setXPos(getXPos()+xSpeed*elapsedTime*100);
   setYPos(getYPos()+ySpeed*elapsedTime*100);//d = d0 + vt
   
   ((BigAttack)attackList[1]).update(elapsedTime);
   ((ProjectileAttack)attackList[2]).update(elapsedTime);
   ((ProjectileAttack)attackList[2]).setDirection(this.facing);
   if (stunTime > 0) {
    xSpeed = 0; 
    stunTime -= 100*elapsedTime;
   } 
   
   if(getXPos() < 0) {
    setXPos(0); 
   } else if (getXPos() > scaleRatio * 1920-getWidth()) {
     setXPos(scaleRatio * 1920-getWidth()); 
   } 
   
   if(getYPos() > 880) {
    setXPos(880); 
   }
   
   if(jumping) {
    this.ySpeed += 50*elapsedTime; 
   }
   
      if (attackRemainingTime > 0) {
      this.attacking = true;
      this.xSpeed = 0;
      this.attackRemainingTime -= 100*elapsedTime; 
      for(int i = 0; i > attackList.length; i++) {
       attackList[i].removeRectangle(); 
      }
    } else {
      attacking = false;
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
     this.ySpeed = -20 * speedStat;
     this.setYPos(getYPos() - 1);
    } 
  }
  
  public void attack(int n, Character c) {
    if(this.facing == 'r' && !isStunned()) {
      attackList[n].useAttack(c, (int)getXPos()+this.getWidth(), (int)getYPos());
    } else if (this.facing == 'l' && !isStunned()) {
      attackList[n].useAttack(c, (int)getXPos()-this.attackList[n].getWidth(), (int)getYPos());
    }
   
   this.attackRemainingTime = attackList[n].getDuration();
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
    if(!isStunned()) {
      this.xSpeed = 14 * speedStat; 
    }
  }
  
  public void stopMoving() {
    this.xSpeed = 0; 
  }
  
  public void moveLeft() {
    if(!isStunned()) {
      this.xSpeed = -14 * speedStat; 
    }
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
  
  public void setJumping(boolean jump) { 
    this.jumping = jump;
  }
  
  public boolean getAttacking() {
   return this.attacking; 
  }
  
  public double getHealth() {
   return this.health; 
  }
  
  public void draw(Graphics g) { 
    //System.out.println((int)getXPos()+", "+(int)getYPos()+", "+getWidth()+", "+getHeight());
    try{
    g.drawImage(sprite, (int)(getXPos() - getWidth() * 0.5), (int)getYPos(), getWidth() * 2, getHeight(), null); //notice the y is a variable that we control from our animate method 
    } catch (Exception e) {
    }
    try {
    ((ProjectileAttack)attackList[2]).getBall().draw(g);
    } catch (Exception e) {}
    
  }
}