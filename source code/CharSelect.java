/** 
 * CharSelect.java 
 * Character selection screen for the Alley Brawlers game
 * @author Eric Ke, Joey Chik
**/


//Imports
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingUtilities;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.border.EmptyBorder;
import java.util.Scanner;
import java.io.File;

  
class CharSelect extends JFrame{ 

  JFrame thisFrame;
  private String[] selectedCharacter;
  private int selection;
  static double scaleRatio;
  JLabel character;
  
  /**
   * readCharList
   * reads the list of characters from a list
   */
  private void readCharList(String filename) throws Exception{
    int i = 0;
    File characterList = new File(filename);
    
    Scanner numReader = new Scanner(characterList);
    Scanner fileReader = new Scanner(characterList);
    while(numReader.hasNext()) {
     numReader.next();
     i++;
    }
    
    selectedCharacter = new String[i];
    i=0;
    
    while(fileReader.hasNext()) {
     selectedCharacter[i] = fileReader.next();
     i++;
    }
  }

  
  //Constructor - this runs first
  CharSelect() { 
    super("Character Selection");
    try{
    readCharList("resources/charSelect/characters.txt");
    } catch(Exception e) {
     System.out.println("Error reading character list"); 
    }
    this.thisFrame = this; //lol  
    this.scaleRatio = (double) Toolkit.getDefaultToolkit().getScreenSize().height / 1080;
    
    //configure the window
    this.setSize((int)(1920 * scaleRatio), (int)(1080 * scaleRatio));    
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
    mainPanel.setPreferredSize(new Dimension((int)(scaleRatio * 1920), (int)(scaleRatio * 1080)));
    
    JPanel charPanel = new JPanel();
        
    //Create a JButton for the centerPanel
    ImageIcon sb =new ImageIcon("resources/charSelect/startbutton.png");
    ImageIcon arrowPic = new ImageIcon("resources/charSelect/arrow.png");
    ImageIcon arrowPicLeft = new ImageIcon("resources/charSelect/arrow2.png");


    JButton startButton = new JButton(sb);
    startButton.setBackground(new Color(0, 0, 0, 0));
    startButton.setRolloverIcon(new ImageIcon("resources/charSelect/startbuttonpressed.png"));
    startButton.setBorder(BorderFactory.createEmptyBorder());
    startButton.setFocusPainted(false);
    startButton.addActionListener(new StartButtonListener());
    
    JButton arrowButton = new JButton(arrowPic);
    arrowButton.setBackground(new Color(0, 0, 0, 0));
    arrowButton.setBorder(BorderFactory.createEmptyBorder());
    arrowButton.setFocusPainted(false);
    arrowButton.addActionListener(new ArrowListener());
    
    JButton arrowButton2 = new JButton(arrowPicLeft);
    arrowButton2.setBackground(new Color(0, 0, 0, 0));
    arrowButton2.setBorder(BorderFactory.createEmptyBorder());
    arrowButton2.setFocusPainted(false);
    arrowButton2.addActionListener(new LeftArrowListener());
    
    JPanel bottomPanel = new JPanel();
    bottomPanel.setBackground(new Color(0, 0, 0, 0));
    bottomPanel.add(arrowButton);
    bottomPanel.add(startButton);
    
    JPanel leftPanel = new JPanel();
    leftPanel.setBackground(new Color(0, 0, 0, 0));
    leftPanel.add(arrowButton2);
    
    ImageIcon char1Pic = new ImageIcon("resources/" + selectedCharacter[selection] + ".png");
    
    character = new JLabel(char1Pic);
    
    charPanel.add(character);
    
     //Create a JButton for the centerPanel
    JLabel startLabel = new JLabel("<HTML><big><font color='black'>Welome to some game or something</big></HTML>");
    
    //Add all panels to the mainPanel according to border layout
    mainPanel.add(bottomPanel,BorderLayout.EAST);
    mainPanel.add(startLabel,BorderLayout.NORTH);
    mainPanel.add(charPanel,BorderLayout.CENTER);
    mainPanel.add(leftPanel,BorderLayout.WEST);
    decPanel.add(mainPanel);
    //add the main panel to the frame
    this.add(decPanel);
    
    //Start the app
    this.setVisible(true);
  }
  
  public void setCharPic(int n) {
   this.selection = n;
  }
  
  //INNER CLASS - Overide Paint Component for JPANEL
  private class DecoratedPanel extends JPanel {
    
    DecoratedPanel() {
      this.setBackground(new Color(0,0,0,0));
    }
    
    /**
     * draws the graphics on the screen
     * @param g the graphics
     */
    public void paintComponent(Graphics g) { 
        super.paintComponent(g);     
        Image pic = new ImageIcon("resources/charSelectBG.png").getImage();
        g.drawImage(pic,0,0, (int)(1920 * scaleRatio), (int)(1080 * scaleRatio),null); 
   }
  
   
  }
  //This is an inner class that is used to detect a button press
 class StartButtonListener implements ActionListener {  //this is the required class definition
    public void actionPerformed(ActionEvent event)  {  
      System.out.println("Starting new Game");
      thisFrame.dispose();
      new GameFrame(); //create a new FunkyFrame (another file that extends JFrame)
      thisFrame.dispose();

    }

  }
 
 class ArrowListener implements ActionListener {
   
   public void actionPerformed(ActionEvent event)  {
     if (selection >= selectedCharacter.length-1) {
      selection = 0; 
     } else {
      selection++; 
     }
      character.setIcon(new ImageIcon("resources/" + selectedCharacter[selection] + ".png"));
      thisFrame.repaint();
      

    }
 }
 
 class LeftArrowListener implements ActionListener {
   
   public void actionPerformed(ActionEvent event)  {
     if (selection <= 0) {
      selection = selectedCharacter.length - 1; 
     } else {
      selection--; 
     }
      character.setIcon(new ImageIcon("resources/" + selectedCharacter[selection] + ".png"));
      thisFrame.repaint();
    }
 }
  
  //Main method starts this application
  public static void main(String[] args) { 
    new CharSelect();

  }
  
}