/*
 * MeleeAttack.java
 * Used by characters to deal damage to other characters
 * Standard melee attack
 * @author Eric Ke
 * 6/5/2018
 */

import java.awt.Rectangle;

class MeleeAttack extends Attack {
  
  private Rectangle attackBox;
  /**
   * constructs the attack, setting
   * @param multiplier damage multiplier from the character
   */ 
  MeleeAttack(double multiplier){
    super(25, 20,100, 175, multiplier);
  }
  
  /**
   * uses the attack
   * @param target the character to be targeted
   * @param x the x coordinate of the attack
   * @param y the y coordinate of the attack
   */
  public void useAttack(Character target, int x, int y) {
    this.attackBox = new Rectangle(x,y,getWidth(),getHeight());
    playSound("attackweak.wav");
    
    if (attackBox.intersects(target.getBoundingBox())) { //deals damage if it hits target
     target.changeHealth(-(this.getDamage())); 
     playSound("hitweak.wav");
     setDamageDealt(true);
    }
  }
  
}