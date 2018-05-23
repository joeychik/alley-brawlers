/** 
 * this template can be used for a start menu
 * for your final project
 **/


//Imports
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingUtilities;

class Menu extends JFrame { 
    
    JFrame thisFrame;
    
    //Constructor - this runs first
    Menu() { 
        super("Start Screen");
        
        //configure the window
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setLocationRelativeTo(null); //start the frame in the center of the screen
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        this.setResizable (false);
        
        //Create a Panel for stuff
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel , BoxLayout.Y_AXIS));
        
        //Create a JButton for the centerPanel
        JButton startButton = new JButton("START");
        startButton.addActionListener(new StartButtonListener());
        
        //Create a JButton for the centerPanel
        JLabel startLabel = new JLabel("Welome to some game or something");
        
        //Add all panels to the mainPanel according to border layout
        mainPanel.add(startButton);
        mainPanel.add(startLabel);
        
        //add the main panel to the frame
        this.add(mainPanel);
        
        //Start the app
        this.setVisible(true);
    }
    
    //This is an inner class that is used to detect a button press
    class StartButtonListener implements ActionListener {  //this is the required class definition
        public void actionPerformed(ActionEvent event)  {  
            System.out.println("Starting new Game");
            //thisFrame.dispose();
            new GameFrame(); //create a new FunkyFrame (another file that extends JFrame)
            
        }
        
    }
    
    class MenuPanel extends JPanel {
        public void paintComponent(Graphics g) {
            setDoubleBuffered(true);
            g.setColor(Color.BLACK);
            
            g.drawImage();
        }
    }
    
    //Main method starts this application
    public static void main(String[] args) { 
        new Menu();
        
    }
    
}