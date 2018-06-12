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
}