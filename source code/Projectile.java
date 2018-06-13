/*
 * Projectile.java
 * damages things when it touches something 
 * 5/29/2018
 * @author Eric Ke
 */

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;

class Projectile extends Physical {
  Projectile(int x, int y, double scaleRatio, Character target) {
  super(x,y,100*scaleRatio, 100*scaleRatio, scaleRatio) {
  }
 }
}