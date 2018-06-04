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
    
    //Constructor - this runs first
    Menu() { 
        super("Start Screen");
        
        screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        
        ClickListener clickListener = new ClickListener();
        
        //configure the window
        this.setSize(screenWidth , screenHeight);
        this.setResizable(false);
        this.setLocationRelativeTo(null); //start the frame in the center of the screen
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Create a Panel for stuff
        BackgroundPanel mainPanel = new BackgroundPanel();
        mainPanel.setLayout(null);
        
        MenuButton button1 = new MenuButton("resources/placeholder.jpg");
        
        // add listeners to mainpanel
        button1.addMouseListener(clickListener);
        
        //add the main panel to the frame
        this.add(mainPanel);
        mainPanel.add(button1);
        
        button1.setBounds(screenWidth / 7 , screenHeight / 2 , 400 , 150);
        
        //Start the app
        this.setVisible(true);
        
        
    }
    
    private class BackgroundPanel extends JPanel {
        public void paintComponent(Graphics g) { 
            super.paintComponent(g);
            Image pic = new ImageIcon("resources/menu.png").getImage();
            g.drawImage(pic , 0 , 0 , screenWidth , screenHeight , null); 
        }
    }
    
    private class ClickListener implements MouseListener {        
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
            new CharSelect();
            
        }
    }
    
    private class MenuButton extends JPanel {
        
        String picAddress;
        
        MenuButton(String picAddress) {
            this.picAddress = picAddress;
        }
        
        public void paintComponent(Graphics g) { 
            super.paintComponent(g);
            Image pic = new ImageIcon(picAddress).getImage();
            g.drawImage(pic , 0 , 0 , 400 , 150 , null); 
        }
    }
    
    //Main method starts this application
    public static void main(String[] args) { 
        new Menu();
        
    }
    
}