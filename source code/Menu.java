/** 
 * this template can be used for a start menu
 * for your final project
 **/


//Imports
import javax.swing.BoxLayout;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
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
    
    //Constructor - this runs first
    Menu() { 
        super("Start Screen");
        
        //configure the window
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setLocationRelativeTo(null); //start the frame in the center of the screen
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Create a Panel for stuff
        DecoratedPanel mainPanel = new DecoratedPanel();
        
        //add the main panel to the frame
        this.add(mainPanel);
        
        //Start the app
        this.setVisible(true);
        
        
    }
    
    private class DecoratedPanel extends JPanel {
        public void paintComponent(Graphics g) { 
            super.paintComponent(g);
            Image pic = new ImageIcon("resources/placeholder.jpg").getImage();
            g.drawImage(pic,0,0,null); 
        }
    }
    
    //Main method starts this application
    public static void main(String[] args) { 
        new Menu();
        
    }
    
}