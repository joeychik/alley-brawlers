/*
 * Floor.java
 * Physical objects can be interacted with by other objects
 * 5/29/2018
 * @author Eric Ke
 */

import java.awt.Rectangle;

class Floor extends Physical {
  
  Floor(int x, int y, int h, int w) {
    super(x, y, h, w);
  }
  
  Floor() {
    super(300, 100, 0, 100000);
  }
 
}