/** 
 * this template can be used for a start menu
 * for your final project
 **/


//Imports

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.SwingUtilities;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.border.EmptyBorder;

class Menu extends JFrame { 
    
    private JFrame thisFrame;
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
        
        StartButton startButton = new StartButton("resources/placeholder.jpg");
        ExitButton exitButton = new ExitButton("resources/placeholder.jpg");
        
        //add the main panel to the frame
        this.add(mainPanel);
        mainPanel.add(startButton);
        mainPanel.add(exitButton);
        
        buttonWidth = (int) (600 * scaleRatio);
        buttonHeight= (int) (100 * scaleRatio);
        
        startButton.setBounds(screenWidth / 2 - buttonWidth / 2 , screenHeight / 2 - buttonHeight / 2 , buttonWidth , buttonHeight);
        exitButton.setBounds(screenWidth / 2 - buttonWidth / 2 , screenHeight / 2 - buttonHeight / 2 + buttonHeight , buttonWidth , buttonHeight);
        
        //Start the app
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
    
    private class MenuButton extends DecoratedPanel implements MouseListener {  
        MenuButton(String picAddress) {
            super(picAddress);
            addMouseListener(this);
        }
        
        public void paintComponent(Graphics g) { 
            super.paintComponent(g);
            Image pic = new ImageIcon(picAddress).getImage();
            g.drawImage(pic , 0 , 0 , buttonWidth , buttonHeight , null); 
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
    
    /*
    private class StartButton extends MenuButton {  
        StartButton(String picAddress) {
            super(picAddress);
        }
        
        public void mouseClicked(MouseEvent e) {
            System.out.println("clicked");
            new CharSelect();
        }
    }
    */
    
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