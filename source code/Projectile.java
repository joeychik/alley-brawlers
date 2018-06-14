/*
 * Projectile.java
 * damages things when it touches something 
 * 5/29/2018
 * @author Eric Ke
 */

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;

class Projectile extends Physical implements Moveable {
  private double xSpeed, ySpeed;
  private Image sprite;
  Character target;
  SoundPlayer audioPlayer = new SoundPlayer();
  
  /**
   * Constructs a projectile and makes it fly
   * @param x the initial x position
   * @param y the initial y position
   * @param scaleRatio the screen ratio
   * @param target the target of the projectile
   * @param facing the direction of travel
   */
  Projectile(int x, int y, double scaleRatio, Character target, char facing) {
    super(x,y,(int)(75*scaleRatio), (int)(75*scaleRatio), scaleRatio); 
    this.target = target;
    sprite = new ImageIcon("resources/projectile.png").getImage();
    if (facing == 'l') {
     moveLeft();
    } else if (facing == 'r') {
     moveRight();
    }
 }
  
  /**
   * updates the projectile
   * @param elapsedTime amount of time passed
   */
  public void update(double elapsedTime) {
   setXPos(getXPos()+xSpeed*elapsedTime*100);
   setYPos(getYPos()+ySpeed*elapsedTime*100);//d = d0 + vt
   setBoxPosition(getXPos(), getYPos());
  }
  
  /**
   * sets the projectile to move right
   * 
   */
  public void moveRight() {
      this.xSpeed = 25; 
  }
  
  /**
   * sets the projectile to move left
   */
  public void moveLeft() {
      this.xSpeed = -25; 
  }
  
  /**
   * draws the projectile on the screen
   * @param g the graphics
   */
  public void draw(Graphics g) {
    g.drawImage(sprite, (int)getXPos(), (int)getYPos(), getWidth(), getHeight(), null); 
  }
  
  /**
   * deals damage to the target
   * @param damage amount of damage dealt
   */
  public void attack(double damage) {
    audioPlayer.playSound("shock.wav");
   this.target.changeHealth(-damage);  
  }
  
  /**
   * detects whether the projectile intersects with the target
   * @return if it collides or not
   */
  public boolean collision() {
    if (this.getBoundingBox().intersects(target.getBoundingBox())) {
      return true;
    } else {
      return false;
    }
  }
  
}