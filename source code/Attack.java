/*
 * Attack.java
 * Used by characters to deal damage to other characters
 * @author Eric Ke
 * 6/5/2018
 */


import java.awt.Rectangle;

abstract class Attack {
  
  private double damage;
  private double duration;
  private int height, width;
  private boolean damageDealt = false;
  private Rectangle attackBox;
  
  private SoundPlayer audioPlayer = new SoundPlayer();
 
  /**
   * Constructs an attack, giving it properties
   * @param damage the damage of the attack
   * @param time how long it takes to cast
   * @param h the height
   * @param w the range/width
   * @param multiplier damage multipler from the character
   */
  Attack(double damage, double time, int h, int w, double multiplier) {
    this.damage = damage*multiplier;
    this.duration = time;
    this.height = (int)(GameFrame.getScaleRatio()*h);
    this.width = (int)(GameFrame.getScaleRatio()*w);
  }
 
  /**
   * gets duration
   * @return duration of attack
   */
  public double getDuration() {
    return this.duration; 
  }
  
  /**
   * gets damage
   * @return damage of attack
   */
  public double getDamage() {
   return this.damage; 
  }
  
  /**
   * sets whether the damage has been dealt already or not
   * @param d if it has been dealt
   */
  public void setDamageDealt(boolean d) {
   this.damageDealt = d; 
  }
  
  /**
   * sets duration of the attack, for attacks with cooldowns and such
   * @param time the time to set it to
   */
  public void setDuration(double time) {
   this.duration = time; 
  }
  
  /**
   * gets height
   * @return the height
   */
  public int getHeight() {
   return this.height; 
  }
  
  /**
   * gets width
   * @return the width
   */
  public int getWidth() {
   return this.width; 
  }
  
  /**
   * removes the hitbox of the attack after using it
   */
  public void removeRectangle() {
   this.attackBox = null; 
   this.setDamageDealt(false);
  }
  
  /**
   * plays the sound of the attack
   * @param filename the name of the file for the sound
   */ 
  public void playSound(String filename) {
   audioPlayer.playSound(filename); 
  }
  
  /**
   * uses the attack
   * @param target the character to be targeted
   * @param x the x coordinate of the attack
   * @param y the y coordinate of the attack
   */
  abstract public void useAttack(Character target, int x, int y);
  
  
}