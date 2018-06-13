<<<<<<< HEAD
/*
 * BigAttack.java
 * Used by characters to deal damage to other characters
 * Eric Ke
 * 6/5/2018
 */

import java.awt.Rectangle;

class BigAttack extends Attack {
  
  private Rectangle attackBox;
  private double channelDuration;
  private static double channelTime = 35;
  private Character target;
  private int x, y;
  
  
  
  BigAttack(double multiplier){
    super(50, 50,100, 300, multiplier);
  }
  
  public static double getChannelTime() {
    return channelTime;
  }
  
  public void update(double elapsedTime) {
    if (channelDuration > 0) {
     channelDuration -= elapsedTime;
    } else {
     this.endAttack(target, x, y); 
    }
  }
  
  public void endAttack(Character target, int x, int y){
   attackBox = new Rectangle(x,y,getWidth(),getHeight());
    
    if (attackBox.intersects(target.getBoundingBox())) {
     target.changeHealth(-(this.getDamage())); 
     setDamageDealt(true);
    }
  } 
  
  public void useAttack(Character target, int x, int y) {
    this.channelDuration = channelTime;
    this.target = target;
    this.x = x;
    this.y = y;
  }
=======
/*
 * BigAttack.java
 * Used by characters to deal damage to other characters
 * Eric Ke
 * 6/5/2018
 */

import java.awt.Rectangle;

class BigAttack extends Attack {
  
  private Rectangle attackBox;
  private Character target;
  private double cooldown;
  
  
  BigAttack(double multiplier){
    super(50, 75,100, 175, multiplier);
    cooldown = 0;
  }
  
  public void update(double elapsedTime){
   this.cooldown -= elapsedTime*100; 
  }
  
  public void useAttack(Character target, int x, int y) {
    if (this.cooldown <= 0) {
      setDuration(75); 
      attackBox = new Rectangle(x,y,getWidth(),getHeight());
      this.cooldown = 800;
      if (attackBox.intersects(target.getBoundingBox())) {
        target.changeHealth(-(this.getDamage())); 
        setDamageDealt(true);
        target.stun(200);
      }
    } else {
     setDuration(0); 
    }
  }
>>>>>>> 2e87cf8d825458ec8cd28170f1aed9b1119df616
}