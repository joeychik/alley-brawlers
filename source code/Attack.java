/*
 * Attack.java
 * Used by characters to deal damage to other characters
 * Eric Ke
 * 6/5/2018
 */


import java.awt.Rectangle;

abstract class Attack {
  
  private double damage;
  private double duration;
  private int height, width;
  private boolean damageDealt = false;
  private Rectangle attackBox;
  
 
  Attack(double damage, double time, int h, int w, double multiplier) {
    this.damage = damage*multiplier;
    this.duration = time;
    this.height = (int)(GameFrame.getScaleRatio()*h);
    this.width = (int)(GameFrame.getScaleRatio()*w);
  }
 
  
  public double getDuration() {
    return this.duration; 
  }
  
  public double getDamage() {
   return this.damage; 
  }
  
  public void setDamageDealt(boolean d) {
   this.damageDealt = d; 
  }
  
  public void setDuration(double time) {
   this.duration = time; 
  }
  
  public int getHeight() {
   return this.height; 
  }
  
  public int getWidth() {
   return this.width; 
  }
  
  public void removeRectangle() {
   this.attackBox = null; 
   this.setDamageDealt(false);
  }
  
  abstract public void useAttack(Character target, int x, int y);
  
  
}