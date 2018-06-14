/** 
 * InstructionScreen.java
 * Window that displays the instructions
 * @author Eric Ke, Joey Chik
 **/

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class InstructionScreen extends JFrame {
  
    private static int screenHeight;
    private static int screenWidth;
    private static double scaleRatio;
    private InstructionScreen thisFrame;
    
    /**
     * constructs an instructions screen
     */
    InstructionScreen() {
        super("Instructions");
        
        this.thisFrame = this;
        
        screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        scaleRatio = (double) screenHeight / 1080;
        
        this.setSize(screenWidth , screenHeight);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        DecoratedPanel mainPanel = new DecoratedPanel("resources/menu/instruction_screen.png");
        this.add(mainPanel);
        this.addKeyListener(new ExitListener());
        
        this.setVisible(true);
    }
    
    private class DecoratedPanel extends JPanel {
        String picAddress;
        
        DecoratedPanel(String picAddress) {
            super();
            this.picAddress = picAddress;
        }
        
        /**
         * paintComponent
         * draws the graphics on the screen
         * @param g the graphics
         */
        public void paintComponent(Graphics g) { 
            super.paintComponent(g);
            Image pic = new ImageIcon(picAddress).getImage();
            g.drawImage(pic , 0 , 0 , screenWidth , screenHeight , null); 
        }
    }
    
    private class ExitListener implements KeyListener {
      
      /**
       * keyTyped
       * does nothing
       */
      public void keyTyped(KeyEvent e) {
      }
      
      /**
       * keyReleased
       * does nothing
       */
      public void keyReleased(KeyEvent e) {
      }
      
      /**
       * keyPressed
       * goes back to menu when escape or backspace are pressed
       * @param e the key being pressed
       */
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE || e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                thisFrame.dispose();
                new Menu();
            }
        }
    }
}