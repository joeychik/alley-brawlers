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
  
 
  Attack(double damage, double time, int h, int w) {
    this.damage = damage;
    this.duration = time;
    this.height = h;
    this.width = w;
  }
  
  public double getDuration() {
    return duration; 
  }
  
  
  
  
}