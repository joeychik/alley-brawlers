
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class VictoryScreen extends JFrame {
    private static int screenHeight;
    private static int screenWidth;
    private static double scaleRatio;
    private JFrame thisFrame;
    private byte playerNum;
    
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
        
        public void paintComponent(Graphics g) { 
          Font font = new Font("Arial", Font.PLAIN, (int)(scaleRatio * 72));
          super.paintComponent(g);
          setDoubleBuffered(true);
          g.setFont(font);
          g.setColor(Color.RED);
          g.drawString("PLAYER " + playerNum + " HAS WON!", (int)(scaleRatio * 600), (int)(scaleRatio * 500));
          g.drawString("Press Enter to play again", (int)(scaleRatio * 600), (int)(scaleRatio * 700));
          repaint();
        }
        
    }
    private class ExitListener implements KeyListener {
        public void keyTyped(KeyEvent e) {
        }
        
        public void keyReleased(KeyEvent e) {
        }
        
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                thisFrame.dispose();
                new Menu();
            }
        }
    }
}