/** 
 * this template can be used for a start menu
 * for your final project
 **/


//Imports

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.*;
import javax.swing.SwingUtilities;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Dimension;
import javax.swing.ImageIcon;

class Menu extends JFrame { 
    
    private static int screenHeight;
    private static int screenWidth;
    private static int buttonHeight;
    private static int buttonWidth;
    private static double scaleRatio;
    
    Menu() { 
        super("Start Screen");
        
        // get the size of the screen
        screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        scaleRatio = (double) screenHeight / 1080;
        
        // configure the window
        this.setSize(screenWidth , screenHeight);
        this.setResizable(false);
        this.setLocationRelativeTo(null); //start the frame in the center of the screen
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Create a Panel for stuff
        DecoratedPanel mainPanel = new DecoratedPanel("resources/menu.png");
        mainPanel.setLayout(null);
        
        MenuButton[] menuButtons = new MenuButton[3];
        
        menuButtons[0] = new StartButton("resources/start_button.png");
        menuButtons[1] = new ExitButton("resources/exit_button.png");
        menuButtons[2] = new InstructionButton("resources/instruction_button.png");
        
        //add the main panel to the frame
        this.add(mainPanel);
        
        for (int i = 0; i < 3; i++) {
            mainPanel.add(menuButtons[i]);
        }
        
        buttonWidth = menuButtons[0].getWidth();
        buttonHeight = menuButtons[0].getHeight();
        
        for (int i = 0; i < 3; i++) {
            menuButtons[i].setBounds(screenWidth / 2 - buttonWidth / 2 , screenHeight / 2 + buttonHeight * i , buttonWidth , buttonHeight);
        }
        
        //Start the app
        this.setVisible(true);
        
    }
    
    private class DecoratedPanel extends JPanel {
        private String picAddress;
        private Image pic;
        
        DecoratedPanel(String picAddress) {
            super();
            this.picAddress = picAddress;
            this.pic = new ImageIcon(picAddress).getImage();
        }
        
        public void paintComponent(Graphics g) { 
            super.paintComponent(g);
            Image pic = new ImageIcon(picAddress).getImage();
            g.drawImage(pic , 0 , 0 , screenWidth , screenHeight , null); 
        }
        
        public Image getImage() {
            return this.pic;
        }
    }
    
    private class MenuButton extends DecoratedPanel implements MouseListener {  
        private int height;
        private int width;
        
        MenuButton(String picAddress) {
            super(picAddress);
            addMouseListener(this);
            height = (int) (scaleRatio * this.getImage().getHeight(null));
            width = (int) (scaleRatio * this.getImage().getWidth(null));
            System.out.println(height + ", " + width);
        }
        
        public void paintComponent(Graphics g) { 
            super.paintComponent(g);
            g.drawImage(this.getImage() , 0 , 0 , this.width , this.height , null); 
        }
        
        public void mousePressed(MouseEvent e) {
            System.out.println("pressed");
        }
        
        public void mouseReleased(MouseEvent e) {
            System.out.println("released");
        }
        
        public void mouseEntered(MouseEvent e) {
            System.out.println("entered");
        }
        
        public void mouseExited(MouseEvent e) {
            System.out.println("exited");
        }
        
        public void mouseClicked(MouseEvent e) {
            System.out.println("clicked");
        }
        
        public int getHeight() {
            return this.height;
        }
        
        public int getWidth() {
            return this.width;
        }
    }
    
    private class StartButton extends MenuButton {  
        StartButton(String picAddress) {
            super(picAddress);
        }
        
        public void mouseClicked(MouseEvent e) {
            System.out.println("clicked");
            new CharSelect();
        }
    }
    
    private class InstructionButton extends MenuButton {  
        InstructionButton(String picAddress) {
            super(picAddress);
        }
        
        public void mouseClicked(MouseEvent e) {
            System.out.println("clicked");
            new InstructionScreen();
        }
    }
    
    private class ExitButton extends MenuButton {  
        ExitButton(String picAddress) {
            super(picAddress);
        }
        
        public void mouseClicked(MouseEvent e) {
            System.out.println("clicked");
            System.exit(0);
        }
    }
    
    //Main method starts this application
    public static void main(String[] args) { 
        new Menu();
        
    }
    
}