/*
 * Clock.java
 * Gives elapsed time for speed and stuff
 * @author Eric Ke
 */

class Clock {
  long elapsedTime;
  long lastTimeCheck;

  /**
   * initializes time checking
   */
  public Clock() { 
    lastTimeCheck=System.nanoTime();
    elapsedTime=0;
  }
  
  /**
   * Checks amount of time passed
   */
  public void update() {
  long currentTime = System.nanoTime();  //if the computer is fast you need more precision
  elapsedTime=currentTime - lastTimeCheck;
  lastTimeCheck=currentTime;
  }
  
  /**
   * gives amount of time passed
   * @return time passed in milliseconds
   */
  public double getElapsedTime() {
    return elapsedTime/1.0E9;
  }
}