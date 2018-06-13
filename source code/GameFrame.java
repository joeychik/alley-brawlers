/**
* GameFrame.java
* Frame that runs the actual game
* Based on the template provided by Mr. Mangat
* @author Eric Ke, Joey Chik
**/

//Graphics &GUI imports
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;  
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

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
   static double scaleRatio;
   static GameAreaPanel gamePanel;
   
   GameFrame currentGameFrame;
   
   Floor ground;
   Character player, player2;
   
  //Constructor - this runs first
  GameFrame() {                      // IMPORTANT     add in extra parameters for selected characters.
    super("My Game");                //               this is how we get the character selection from the players
    // Set the frame to full screen 
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
    this.scaleRatio = (double) Toolkit.getDefaultToolkit().getScreenSize().height / 1080;
    
    this.player = new Character(200,579,200,100, scaleRatio, 'r', "resources/characters/pikachu/");
    this.player2 = new Character(1600,579,200,100, scaleRatio, 'l', "resources/characters/lisa/");

    gamePanel = new GameAreaPanel();
    gamePanel.setBackground(new Color(0, 0, 0, 0));
    
   // this.setLocationRelativeTo(null); //start the frame in the center of the screen
    this.setSize(Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height);
    this.setUndecorated(true);  //Set to true to remove title bar
    this.setBackground(new Color(0, 0, 0, 0));    
    
    //Set up the game panel (where we put our graphics)
    
    this.add(new GameAreaPanel());

    
    MyKeyListener keyListener = new MyKeyListener();
    this.addKeyListener(keyListener);

    MyMouseListener mouseListener = new MyMouseListener();
    this.addMouseListener(mouseListener);

    this.requestFocusInWindow(); //make sure the frame has focus   
    
    this.setVisible(true);
  
    //Start the game loop in a separate thread
   
  } //End of Constructor
  
  public static double getScaleRatio() {
   return scaleRatio; 
  }

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
      //notice the x,y variables that we control from our animate method      
      ground = new Floor(scaleRatio);
      clock = new Clock(); 
    }
    
    public void paintComponent(Graphics g) {
      
      Font font = new Font("Arial", Font.PLAIN, (int)(scaleRatio * 48));
      super.paintComponent(g); //removed to keep transparent panel
      Image pic = new ImageIcon("resources/background.png").getImage();
      g.drawImage(pic,0,0, (int)(scaleRatio * 1920), (int)(scaleRatio * 1080), null);     
      setDoubleBuffered(true);
      clock.update();
      player.update(clock.getElapsedTime());
      player2.update(clock.getElapsedTime());
      
      if (player.getBoundingBox().intersects(ground.getBoundingBox())){
        player.setYSpeed(0);
        player.setJumping(false);
      }
      if (player2.getBoundingBox().intersects(ground.getBoundingBox())){
        player2.setYSpeed(0);
        player2.setJumping(false);
      }
      
      player.draw(g);
      player2.draw(g);
      g.setFont(font);
      g.setColor(Color.BLACK);
      g.fillRect(0,(int)(scaleRatio*30),(int)(scaleRatio*300),(int)(scaleRatio*100));
      g.setColor(Color.BLACK);
      g.fillRect(1620,(int)(scaleRatio*30),(int)(scaleRatio*300),(int)(scaleRatio*100));
      g.setColor(Color.RED);
      g.drawString("HP: " + String.valueOf((int)player.getHealth()), (int)(scaleRatio * 10), (int)(scaleRatio * 100));
      g.setColor(Color.RED);
      g.drawString("HP: " + String.valueOf((int)player2.getHealth()), (int)(scaleRatio * 1700), (int)(scaleRatio * 100));
      ground.draw(g);
      repaint();
      
      
    }
  }
  
  // -----------  Inner class for the keyboard listener - this detects key presses and runs the corresponding code
    private class MyKeyListener implements KeyListener {
      
      public void keyTyped(KeyEvent e) {
        
      }

      public void keyPressed(KeyEvent e) {
        if (!player2.getAttacking()) {
          if (e.getKeyCode() == KeyEvent.VK_RIGHT) {  //If 'D' is pressed
            player2.moveRight();
            player2.setDirection('r');
          } 
          if (e.getKeyCode() == KeyEvent.VK_LEFT) {  
            player2.moveLeft();
            player2.setDirection('l');
          }
          if (e.getKeyCode() == KeyEvent.VK_UP) {  
            player2.jump();
          }
          if (e.getKeyCode() == KeyEvent.VK_NUMPAD1) {  
            player2.attack(0, player);
          }
          if (e.getKeyCode() == KeyEvent.VK_NUMPAD2) {  
            player2.attack(1, player);
          }
          if (e.getKeyCode() == KeyEvent.VK_NUMPAD0) {  
            player2.attack(2, player);
          }
        }
        if (!player.getAttacking()) {
          if (KeyEvent.getKeyText(e.getKeyCode()).equals("D")) {  //If 'D' is pressed
            player.moveRight();
            player.setDirection('r');
          } 
          if (KeyEvent.getKeyText(e.getKeyCode()).equals("A")) {  
            player.moveLeft();
            player.setDirection('l');
          }
          if (KeyEvent.getKeyText(e.getKeyCode()).equals("W")) {  
            player.jump();
          }
          if (KeyEvent.getKeyText(e.getKeyCode()).equals("F")) {  
            player.attack(0, player2);
          }
           if (KeyEvent.getKeyText(e.getKeyCode()).equals("R")) {  
            player.attack(1, player2);
          } 
           if (KeyEvent.getKeyText(e.getKeyCode()).equals("E")) {  
            player.attack(2, player2);
          }
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {  //If ESC is pressed
            System.exit(0);
          }
      }   
      
      public void keyReleased(KeyEvent e) {
        if (KeyEvent.getKeyText(e.getKeyCode()).equals("D") || KeyEvent.getKeyText(e.getKeyCode()).equals("A")) {  //stop player 1
          player.stopMoving();
        } if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_LEFT) {  //stop player 2
          player2.stopMoving();
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