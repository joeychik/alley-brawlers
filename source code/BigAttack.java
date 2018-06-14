/*
 * BigAttack.java
 * Used by characters to deal damage to other characters
 * Big attack that stuns the enemy
 * @author Eric Ke
 * 6/5/2018
 */

import java.awt.Rectangle;

class BigAttack extends Attack {
  
  private Rectangle attackBox;
  private Character target;
  private double cooldown;
  
  /**
   * Constructs the attack, giving it damage, duration, range, etc.
   * @param multiplier the user's damage multiplier
   */
  BigAttack(double multiplier){
    super(50, 100,100, 175, multiplier);
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
   * gets the cooldown
   * @return cooldown remaining on the skill
   */
  public double getCooldown() {
   return this.cooldown; 
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
      this.cooldown = 1000; //attack has a cooldown, can't be used during it
      if (attackBox.intersects(target.getBoundingBox())) { //deals damage, stuns target if it hits them
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