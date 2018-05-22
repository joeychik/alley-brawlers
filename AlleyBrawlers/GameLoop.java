

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*; 
import java.util.Scanner;
import java.util.Random;
import java.awt.image.*; 
import javax.imageio.*; 


//This class represents the game window
class GameWindow extends JFrame { 
  
  //Window constructor
  public GameWindow() { 
   setTitle("Alley Brawlers");
   //setSize(1280,1024);  // set the size of my window to 400 by 400 pixels
   setResizable(true);  // set my window to allow the user to resize it
   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // set the window up to end the program when closed
   
   getContentPane().add(new GamePanel());
   pack(); //makes the frame fit the contents
   setVisible(true);
  }
  


// An inner class representing the panel on which the game takes place
static class GamePanel extends JPanel {
  
  Background background;
  FrameRate frameRate;
  Clock clock;
  
  //constructor
  public GamePanel() { 
    setPreferredSize(new Dimension(1024,768));
    frameRate = new FrameRate();
    background = new Background();
    clock=new Clock();
  }
  
  
  public void paintComponent(Graphics g) { 
    super.paintComponent(g); //required to ensure the panel si correctly redrawn
    
 
    
    //update the content
    clock.update();
    frameRate.update();
    
    //draw the screen
    background.draw(g);
    
    frameRate.draw(g,10,10);

    //request a repaint
    repaint();
  }
  
  
  }

}

//A class to track time

class Clock {
  long elapsedTime;
  long lastTimeCheck;

  public Clock() { 
    lastTimeCheck=System.nanoTime();
    elapsedTime=0;
  }
  
  public void update() {
  long currentTime = System.nanoTime();  //if the computer is fast you need more precision
  elapsedTime=currentTime - lastTimeCheck;
  lastTimeCheck=currentTime;
  }
  
  //return elapsed time in milliseconds
  public double getElapsedTime() {
    return elapsedTime/1.0E9;
  }
}


//A class to represent the object moving around on the screen
class Background {
 Image background = Toolkit.getDefaultToolkit().getImage("background.png");

 public Background() {
 }


 public void draw(Graphics g) { 
   g.drawImage(background,0,0,null);
 }
}

//Better to abstract the FrameRate stuff
class FrameRate { 

  String frameRate; //to display the frame rate to the screen
  long lastTimeCheck; //store the time of the last time the time was recorded
  long deltaTime; //to keep the elapsed time between current time and last time
  int frameCount; //used to cound how many frame occurred in the elasped time (fps)

  public FrameRate() { 
    lastTimeCheck = System.currentTimeMillis();
    frameCount=0;
    frameRate="0 fps";
  }
  
  public void update() { 
  long currentTime = System.currentTimeMillis();  //get the current time
    deltaTime += currentTime - lastTimeCheck; //add to the elapsed time
    lastTimeCheck = currentTime; //update the last time var
    frameCount++; // everytime this method is called it is a new frame
    if (deltaTime>=1000) { //when a second has passed, update the string message
      frameRate = frameCount + " fps" ;
      frameCount=0; //reset the number of frames since last update
      deltaTime=0;  //reset the elapsed time     
    }
  }
  
   public void draw(Graphics g, int x, int y) {
      g.drawString(frameRate,x,y); //display the frameRate
   }
   

}
