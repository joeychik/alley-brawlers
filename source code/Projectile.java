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
  
  public void update(double elapsedTime) {
   setXPos(getXPos()+xSpeed*elapsedTime*100);
   setYPos(getYPos()+ySpeed*elapsedTime*100);//d = d0 + vt
   setBoxPosition(getXPos(), getYPos());
  }
  
  public void moveRight() {
      this.xSpeed = 25; 
  }
  
  public void moveLeft() {
      this.xSpeed = -25; 
  }
  
  public void draw(Graphics g) {
    g.drawImage(sprite, (int)getXPos(), (int)getYPos(), getWidth(), getHeight(), null); 
  }
  
  public void attack(double damage) {
   this.target.changeHealth(-damage);  
  }
  
  /**
   * detects whether the projectile has collided with something
   */
  public boolean collision() {
    if (this.getBoundingBox().intersects(target.getBoundingBox())) {
      return true;
    } else {
      return false;
    }
  }
  
}