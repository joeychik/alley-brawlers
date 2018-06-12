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
    this.height = h;
    this.width = w;
  }
 
  
  public double getDuration() {
    return duration; 
  }
  
  public double getDamage() {
   return damage; 
  }
  
  public void setDamageDealt(boolean d) {
   damageDealt = d; 
  }
  
  public int getHeight() {
   return height; 
  }
  
  public int getWidth() {
   return width; 
  }
  
  public void removeRectangle() {
   attackBox = null; 
   setDamageDealt(false);
  }
  
  abstract public void useAttack(Character target, int x, int y);
  
  
}