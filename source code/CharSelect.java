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


<<<<<<< HEAD
class CharSelect extends JFrame{ 
=======
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
>>>>>>> 177e7d706a27f0b2caab6af02bc156d74877e170
    
    JFrame thisFrame;
    private String[] selectedCharacter;
    private int selection;
    private int screenHeight;
    private int screenWidth;
    static double scaleRatio;
    JLabel character;
    
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
        
        this.screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        this.scaleRatio = (double) screenHeight / 1080;
        
        //configure the window
        this.setSize(this.screenWidth, this.screenHeight);    
        this.setLocationRelativeTo(null); //start the frame in the center of the screen
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        this.setResizable (false);
        
        //Display the frame without border and invisible
        this.setUndecorated(true);
        setBackground(new Color(0,0,0,0));
        
        //Create a Panel for stuff
        JPanel decPanel = new DecoratedPanel("resources/charSelect/charSelectBG.png");        
                
        //JPanel charPanel1 = new DecoratedPanel();
        //JPanel charPanel2 = new DecoratedPanel();
        
        //Create a JButton for the centerPanel
        ImageIcon sb =new ImageIcon("resources/charSelect/startbutton.png");
        int sbWidth = (int) (sb.getImage().getWidth(null) * scaleRatio);
        int sbHeight = (int) (sb.getImage().getHeight(null) * scaleRatio);
        sb.setImage(sb.getImage().getScaledInstance((int) (sbWidth * scaleRatio), (int) (sbHeight * scaleRatio),  java.awt.Image.SCALE_SMOOTH));
        
        //Border emptyBorder = BorderFactory.createEmptyBorder();
        
        JButton startButton = new JButton(sb);
        //startButton.setBorder(emptyBorder);
        startButton.setBackground(new Color(0, 0, 0, 0));
        startButton.setFocusPainted(false);
        startButton.addActionListener(new StartButtonListener());
        
        decPanel.setLayout(null);
        decPanel.add(startButton);
        this.add(decPanel);
        
        startButton.setBounds((int) (960 * scaleRatio - sbWidth / 2) , (int) (950 * scaleRatio) , sbWidth , sbHeight);

        //Start the app
        this.setVisible(true);
    }
    
    public void setCharPic(int n) {
        this.selection = n;
    }
    
    //INNER CLASS - Overide Paint Component for JPANEL    
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
    
    //This is an inner class that is used to detect a button press
    class StartButtonListener implements ActionListener {  //this is the required class definition
        public void actionPerformed(ActionEvent event)  {  
            System.out.println("Starting new Game");
            //thisFrame.dispose();
            new GameFrame(); //create a new FunkyFrame (another file that extends JFrame)
            thisFrame.dispose();
            
        }
        
    }
    
<<<<<<< HEAD
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
=======
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
      new GameFrame(); //create a new FunkyFrame (another file that extends JFrame)
      thisFrame.dispose();

>>>>>>> 177e7d706a27f0b2caab6af02bc156d74877e170
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