/*
 * ProjectileAttack.java
 * Used by characters to deal damage to other characters
 * @author Eric Ke
 * 6/5/2018
 */

import java.awt.Rectangle;

class ProjectileAttack extends Attack {
  
  private double cooldown;
  private double startX;
  private char direction;
  private Projectile ball;
  
  /**
   * Constructor for projectile attack
   * Sets the damage and duration for the attack
   */
  ProjectileAttack(double multiplier){
    super(25, 20,100, 175, multiplier);
    
  }
  
  public void setDirection(char d){
   this.direction = d; 
  }
  
  /**
   * updates the projectile
   * @param elapsedTime amount of time passed
   */
  public void update(double elapsedTime) {
    
    
    if (ball != null) {
      ball.update(elapsedTime);
      if (ball.collision()) {
        ball.attack(getDamage()); //ball deals damage when colliding with stuff
        ball = null; 
      } else if (ball.getXPos()-startX > 1400 || ball.getXPos()-startX < -1400) {
       ball = null; 
      }
      
    }
    cooldown -= elapsedTime*100;
  }
  
  /**
   * gets the projectile of the attack
   * @return the projectile object
   */
  public Projectile getBall() {
   return ball; 
  }
  
  /**
   * uses the attack
   * @param target the character to be targeted
   * @param x the x coordinate of the attack
   * @param y the y coordinate of the attack
   */
  public void useAttack(Character target, int x, int y) {
    if (this.cooldown <= 0) {
      setDuration(20); 
      this.cooldown = 200;
      playSound("egattack.wav");
      ball = new Projectile(x,y,GameFrame.getScaleRatio(), target, this.direction);
      this.startX = x;
    } else {
     setDuration(0); 
    }
  }
  
  
}