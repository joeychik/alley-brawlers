/*
 * VictoryScreen.java
 * A screen to display the winner of a battle
 * @author Eric Ke
 * 6/13/2018
 */

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class VictoryScreen extends JFrame {
    private static int screenHeight;
    private static int screenWidth;
    private static double scaleRatio;
    private JFrame thisFrame;
    private byte playerNum;
    
    /**
     * Creates a victory screen
     * @param playerWin the number of the player that won
     */
    VictoryScreen(byte playerWin) {
        super("Victory");
        this.playerNum = playerWin;
        
        this.thisFrame = this;
        
        screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        scaleRatio = (double) screenHeight / 1080;
        
        this.setSize(screenWidth , screenHeight);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        DecoratedPanel mainPanel = new DecoratedPanel();
        this.add(mainPanel);
        this.addKeyListener(new ExitListener());
        this.setVisible(true);
    }
    
    private class DecoratedPanel extends JPanel {
        DecoratedPanel() {
          super();
        }
        
        
        /**
         * draws the graphics on the screen
         * @param g the graphics
         */
        public void paintComponent(Graphics g) { 
          Image image = new ImageIcon("resources/victory_screen.png").getImage();
            
          Font font = new Font("Arial", Font.PLAIN, (int)(scaleRatio * 72));
          super.paintComponent(g);
          setDoubleBuffered(true);
          g.setFont(font);
          g.setColor(Color.RED);
          g.drawImage(image , 0 , 0 , (int) (scaleRatio * 1920) , (int) (scaleRatio * 1080) , null);
          g.drawString("PLAYER " + playerNum + " HAS WON!", (int)(scaleRatio * 600), (int)(scaleRatio * 500));
          g.drawString("Press Enter to play again", (int)(scaleRatio * 600), (int)(scaleRatio * 700));
          repaint();
        }
        
    }
    
    private class ExitListener implements KeyListener {
      /**
       * does nothing
       */
      public void keyTyped(KeyEvent e) {
      }
      
      /**
       * does nothing
       */
      public void keyReleased(KeyEvent e) {
      }
      
      /**
       * goes back to menu when escape or backspace are pressed
       * @param e the key being pressed
       */
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          thisFrame.dispose();
          thisFrame = null;
          new Menu();
        }
      }
    }
}