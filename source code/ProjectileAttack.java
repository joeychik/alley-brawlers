/*
 * ProjectileAttack.java
 * Used by characters to deal damage to other characters
 * Eric Ke
 * 6/5/2018
 */

import java.awt.Rectangle;

class ProjectileAttack extends Attack {
  
  private double cooldown;
  private double startX;
  Projectile ball;
  
  ProjectileAttack(double multiplier){
    super(25, 20,100, 175, multiplier);
  }
  
  public void update(double elapsedTime) {
    
    
    if (ball != null) {
      System.out.println("lmao");
      ball.update(elapsedTime);
      if (ball.collision()) {
        ball.attack(getDamage());
        ball = null; 
      }
      
    }
    cooldown -= elapsedTime*100;
  }
  
  public Projectile getBall() {
   return ball; 
  }
  
  /**
   * 
   * CHANGE THIS GODD AMN THING
   */
  public void useAttack(Character target, int x, int y) {
    if (this.cooldown <= 0) {
      setDuration(20); 
      this.cooldown = 200;
      ball = new Projectile(x,y,GameFrame.getScaleRatio(), target);
      this.startX = x;
    } else {
     setDuration(0); 
    }
  }
  
}