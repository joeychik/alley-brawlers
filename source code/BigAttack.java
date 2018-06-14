/*
 * BigAttack.java
 * Used by characters to deal damage to other characters
 * @author Eric Ke
 * 6/5/2018
 */

import java.awt.Rectangle;

class BigAttack extends Attack {
  
  private Rectangle attackBox;
  private Character target;
  private double cooldown;
  
  
  BigAttack(double multiplier){
    super(50, 100,100, 100, multiplier);
    cooldown = 0;
  }
  
  /**
   * updates the cooldown
   * @param elapsedTime amount of time passed
   */
  public void update(double elapsedTime){
   this.cooldown -= elapsedTime*100; 
  }
  
  /**
   * uses the attack
   * @param target the character to be targeted
   * @param x the x coordinate of the attack
   * @param y the y coordinate of the attack
   */
  public void useAttack(Character target, int x, int y) {
    if (this.cooldown <= 0) {
      setDuration(100); 
      attackBox = new Rectangle(x,y,getWidth(),getHeight());
      playSound("shatter.wav");
      this.cooldown = 1000;
      if (attackBox.intersects(target.getBoundingBox())) {
        target.changeHealth(-(this.getDamage())); 
        setDamageDealt(true);
        playSound("bighit.wav");
        target.stun(400);
      }
    } else {
     setDuration(0); 
    }
  }
}