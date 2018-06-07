/*
 * MeleeAttack.java
 * Used by characters to deal damage to other characters
 * Eric Ke
 * 6/5/2018
 */

import java.awt.Rectangle;

class MeleeAttack extends Attack {
  
  private Rectangle attackBox;
  
  MeleeAttack(double multiplier){
    super(25, 20,100, 300, multiplier);
  }
  
  public void useAttack(Character target, int x, int y) {
    attackBox = new Rectangle(x,y,getWidth(),getHeight());
    
    if (attackBox.intersects(target.getBoundingBox())) {
     target.changeHealth(-(this.getDamage())); 
     setDamageDealt(true);
    }
  }
  
}