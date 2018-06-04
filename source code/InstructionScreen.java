import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

class InstructionScreen extends JFrame {
    private static int screenHeight;
    private static int screenWidth;
    private static double scaleRatio;
    
    InstructionScreen() {
        super("Instructions");
        
        screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        scaleRatio = (double) screenHeight / 1080;
        
        this.setSize(screenWidth , screenHeight);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        DecoratedPanel mainPanel = new DecoratedPanel("resources/placeholder.jpg");
        this.add(mainPanel);
        
        this.setVisible(true);
    }
    
    private class DecoratedPanel extends JPanel {
        String picAddress;
        
        DecoratedPanel(String picAddress) {
            super();
            this.picAddress = picAddress;
        }
        
        public void paintComponent(Graphics g) { 
            super.paintComponent(g);
            Image pic = new ImageIcon(picAddress).getImage();
            g.drawImage(pic , 0 , 0 , screenWidth , screenHeight , null); 
        }
    }
}