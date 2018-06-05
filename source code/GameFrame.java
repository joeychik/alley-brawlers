/**
* This template can be used as reference or a starting point
* for your final summative project
* @author Mangat
**/

//Graphics &GUI imports
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;  
import javax.swing.border.EmptyBorder;

import java.awt.Rectangle;

//Keyboard imports
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//Mouse imports
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

class GameFrame extends JFrame { 

  //class variable (non-static)
   static double x, y;
   static GameAreaPanel gamePanel;
   Floor ground;
   Character player;
  
  
  //Constructor - this runs first
  GameFrame() { 
    
    super("Alley Brawlers");  
    // Set the frame to full screen 
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   // this.setLocationRelativeTo(null); //start the frame in the center of the screen
    this.setSize(Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height);
    this.setUndecorated(true);  //Set to true to remove title bar
    this.setBackground(new Color(0, 0, 0, 0));
    
    //Set up the game panel (where we put our graphics)
    gamePanel = new GameAreaPanel();
    gamePanel.setBackground(new Color(0, 0, 0, 0));
    
    this.add(new GameAreaPanel());
    
    MyKeyListener keyListener = new MyKeyListener();
    this.addKeyListener(keyListener);

    MyMouseListener mouseListener = new MyMouseListener();
    this.addMouseListener(mouseListener);

    this.requestFocusInWindow(); //make sure the frame has focus   
    this.player = new Character(0,579,200,100);
    
    this.setVisible(true);
  
    //Start the game loop in a separate thread
   
  } //End of Constructor

  //the main gameloop - this is where the game state is updated
  public void animate() { 
    
    while(true){
      
      this.repaint();
    }    
  }
  
  /** --------- INNER CLASSES ------------- **/
  
  // Inner class for the the game area - This is where all the drawing of the screen occurs
  private class GameAreaPanel extends JPanel {
    Clock clock;
    
    GameAreaPanel() {
      Image pic = new ImageIcon("resources/background.png").getImage();
      //notice the x,y variables that we control from our animate method      
      ground = new Floor();
      clock = new Clock(); 
    }
    
    public void paintComponent(Graphics g) { 
      super.paintComponent(g);
      setDoubleBuffered(true);
      clock.update();
      player.update(clock.getElapsedTime());
      
      if (player.getBoundingBox().intersects(ground.getBoundingBox())){
        player.setYSpeed(0);
        player.setJumping(false);
      }
      
      player.draw(g);
      ground.draw(g);
      repaint();
      
      
    }
  }
  
  // -----------  Inner class for the keyboard listener - this detects key presses and runs the corresponding code
    private class MyKeyListener implements KeyListener {
      
      public void keyTyped(KeyEvent e) {
        if (KeyEvent.getKeyText(e.getKeyCode()).equals("W")) {  //If 'D' is pressed
          if (player.getBoundingBox().intersects(ground.getBoundingBox())){
            player.jump();
          }
        }
      }

      public void keyPressed(KeyEvent e) {      
        if (KeyEvent.getKeyText(e.getKeyCode()).equals("D")) {  //If 'D' is pressed
          player.moveRight();
        } else if (KeyEvent.getKeyText(e.getKeyCode()).equals("A")) {  //If 'D' is pressed
          player.moveLeft();
        }
        else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {  //If ESC is pressed
          System.out.println("YIKES ESCAPE KEY!"); //close frame & quit
          System.exit(0);
        } 
      }   
      
      public void keyReleased(KeyEvent e) {
        if (KeyEvent.getKeyText(e.getKeyCode()).equals("D") || KeyEvent.getKeyText(e.getKeyCode()).equals("A")) {  //If 'D' is pressed
          player.stopMoving();
        }
      }
    } //end of keyboard listener
  
  // -----------  Inner class for the keyboard listener - This detects mouse movement & clicks and runs the corresponding methods 
    //placeholder listeners for later
    private class MyMouseListener implements MouseListener {
   
      public void mouseClicked(MouseEvent e) {
        System.out.println("Mouse Clicked");
        System.out.println("X:"+e.getX() + " y:"+e.getY());
      }

      public void mousePressed(MouseEvent e) {
      }

      public void mouseReleased(MouseEvent e) {
      }

      public void mouseEntered(MouseEvent e) {
      }

      public void mouseExited(MouseEvent e) {
      }
    } //end of mouselistener
    
}