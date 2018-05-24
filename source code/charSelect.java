/** 
 * charSelect.java 
**/


//Imports
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingUtilities;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.border.EmptyBorder;

  
class StartingFrameTwo extends JFrame { 

  JFrame thisFrame;
  String picName = "";
  
  //Constructor - this runs first
  StartingFrameTwo() { 
    super("Start Screen");
    this.thisFrame = this; //lol  
    
    //configure the window
    this.setSize(1920,1080);    
    this.setLocationRelativeTo(null); //start the frame in the center of the screen
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    this.setResizable (false);
    
    //Display the frame without border and invisible
    this.setUndecorated(true);
    this.setVisible(true);
    setBackground(new Color(0,0,0,0));
         
    //Create a Panel for stuff
    JPanel decPanel = new DecoratedPanel();
    decPanel.setBorder(new EmptyBorder(768-240*2, 68, 68, 68));

    
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());
    mainPanel.setBackground(new Color(0, 0, 0, 0));
    mainPanel.setPreferredSize(new Dimension(1920,1080));
    
    
    //Create a JButton for the centerPanel
    ImageIcon sb =new ImageIcon("startbutton.png");
    ImageIcon arrowPic = new ImageIcon("arrow.png");


    JButton startButton = new JButton(sb);
    startButton.setBackground(new Color(0, 0, 0, 0));
    startButton.setRolloverIcon(new ImageIcon("startbuttonpressed.png"));
    startButton.setBorder(BorderFactory.createEmptyBorder());
    startButton.setFocusPainted(false);
    startButton.addActionListener(new StartButtonListener());
    
    JButton arrowButton = new JButton(arrowPic);
    arrowButton.setBackground(new Color(0, 0, 0, 0));
    arrowButton.setRolloverIcon(new ImageIcon("startbuttonpressed.png"));
    arrowButton.setBorder(BorderFactory.createEmptyBorder());
    arrowButton.setFocusPainted(false);
    arrowButton.addActionListener(new ArrowListener());
    
    JPanel bottomPanel = new JPanel();
    bottomPanel.setBackground(new Color(0, 0, 0, 0));
    bottomPanel.add(arrowButton);
    bottomPanel.add(startButton);
    
    this.picName = "char1.jpg";
    ImageIcon char1Pic = new ImageIcon(picName);
    
    JLabel character = new JLabel(char1Pic);
    
    JPanel charPanel = new JPanel();
    charPanel.setBackground(new Color(0,0,0,0));
    charPanel.add(character);
   
 
     //Create a JButton for the centerPanel
    JLabel startLabel = new JLabel("<HTML><big><font color='black'>Welome to some game or something</big></HTML>");
    
    //Add all panels to the mainPanel according to border layout
    mainPanel.add(bottomPanel,BorderLayout.EAST);
    mainPanel.add(startLabel,BorderLayout.NORTH);
    mainPanel.add(charPanel,BorderLayout.CENTER);
    
    decPanel.add(mainPanel);
    //add the main panel to the frame
    this.add(decPanel);
    
    //Start the app
    this.setVisible(true);
  }
  
  public void setCharPic(String name) {
   this.picName = name;
  }
  
  //INNER CLASS - Overide Paint Component for JPANEL
  private class DecoratedPanel extends JPanel {
    
    DecoratedPanel() {
      this.setBackground(new Color(0,0,0,0));
    }
    
    public void paintComponent(Graphics g) { 
        super.paintComponent(g);     
        Image pic = new ImageIcon("charSelectBG.png").getImage();
        g.drawImage(pic,0,0,1920,1080,null); 
   }
  
   
  }
  //This is an inner class that is used to detect a button press
 class StartButtonListener implements ActionListener {  //this is the required class definition
    public void actionPerformed(ActionEvent event)  {  
      System.out.println("Starting new Game");
      thisFrame.dispose();
      new GameFrameTwo(); //create a new FunkyFrame (another file that extends JFrame)

    }

  }
 
 class ArrowListener implements ActionListener {
   public void actionPerformed(ActionEvent event)  {
      System.out.println("a");//create a new FunkyFrame (another file that extends JFrame)
      setCharPic("char2.jpg");
      thisFrame.repaint();

    }
 }
  
  //Main method starts this application
  public static void main(String[] args) { 
    new StartingFrameTwo();

  }
  
}