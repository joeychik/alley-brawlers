/** 
 * CharSelect.java 
 * Character selection screen for the Alley Brawlers game
 * @author Eric Ke, Joey Chik
 **/


//Imports
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


class CharSelect extends JFrame{ 
    
    JFrame thisFrame;
    private int screenHeight;
    private int screenWidth;
    static double scaleRatio;
    private String[] charList;
    CharPanel charPanel1;
    CharPanel charPanel2;
    
    
    /**
     * Reads the list of characters from a text file
     * @param filename The name of the file of characters
     * @return an array filled with the characters
     */
    private String[] readCharList(String filename) throws Exception{
        String[] characters;
        int i = 0;
        File characterList = new File(filename);
        
        Scanner numReader = new Scanner(characterList);
        Scanner fileReader = new Scanner(characterList);
        while(numReader.hasNext()) {
            numReader.next();
            i++;
        }
        
        characters = new String[i];
        i=0;
        
        while(fileReader.hasNext()) {
            characters[i] = fileReader.next();
            i++;
        }
        
        return characters;
    }
    
    
    /**
     * Creates a character select
     */
    CharSelect() { 
        super("Character Selection");
        try{
            this.charList = readCharList("resources/charSelect/characters.txt");
        } catch(Exception e) {
            System.out.println("Error reading character list"); 
        }
        this.thisFrame = this; //lol
        
        this.screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        this.scaleRatio = (double)screenHeight / 1080;
        
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
        
        charPanel1 = new CharPanel(this.charList , KeyEvent.VK_A , KeyEvent.VK_D);
        charPanel1.setBackground(new Color(0, 0, 0, 0));
        
        charPanel2 = new CharPanel(this.charList , KeyEvent.VK_LEFT , KeyEvent.VK_RIGHT);
        charPanel2.setBackground(new Color(0, 0, 0, 0));
        
        decPanel.setLayout(null);
        decPanel.add(startButton);
        decPanel.add(charPanel1);
        decPanel.add(charPanel2);
        this.add(decPanel);
        this.addKeyListener(new SelectionListener());
        setFocusable(true);
        
        startButton.setBounds((int) (960 * scaleRatio - sbWidth / 2) , (int) (950 * scaleRatio) , sbWidth , sbHeight);
        charPanel1.setBounds((int) (scaleRatio * 150) , (int) (scaleRatio * 270) , (int) (scaleRatio * 320) , (int) (scaleRatio * 485));
        charPanel2.setBounds((int) (scaleRatio * 1450) , (int) (scaleRatio * 270) , (int) (scaleRatio * 320) , (int) (scaleRatio * 485));
        
        //Start the app
        this.setVisible(true);
    }
    
    
    private class DecoratedPanel extends JPanel {
      private String picAddress;
      private Image pic;
      
      /**
       * Creates a panel for character select
       * @param picAddress the picture's address 
       */
      DecoratedPanel(String picAddress) {
        super();
        this.picAddress = picAddress;
        this.pic = new ImageIcon(picAddress).getImage();
      }
      
      /**
       * draws the graphics on the screen
       * @param g the graphics
       */
      public void paintComponent(Graphics g) { 
        super.paintComponent(g);
        Image pic = new ImageIcon(picAddress).getImage();
        g.drawImage(pic , 0 , 0 , screenWidth , screenHeight , null); 
      }
      
      /**
     * Gets the image of the panel
     * @return the image
     */
      public Image getImage() {
        return this.pic;
        }
    }
    
    private class CharPanel extends JPanel{
      
        private String[] imageString;
        private Image[] images;
        private int selection;
        private CharPanel charPanel;
        private int keyCode1 , keyCode2;
        
        /**
         * panel for the character
         * @param imageString array of image names
         * @param key1 a key to switch character
         * @param key2 a key to switch character
         */
        CharPanel(String[] imageString , int key1 , int key2) {
            super();
            this.selection = 0;
            this.imageString = imageString;
            this.images = new Image[imageString.length];
            this.keyCode1 = key1;
            this.keyCode2 = key2;
            for (int i = 0; i < imageString.length; i++) {
                images[i] = new ImageIcon("resources/charSelect/" + imageString[i] + ".png").getImage();
            }
        }
        
        /**
         * Updates the character image on screen
         * @param keyCode the key inputted
         */
        public void update(int keyCode) {
            if (keyCode == KeyEvent.VK_ESCAPE || keyCode == KeyEvent.VK_BACK_SPACE) {
                thisFrame.dispose();
            } else if (keyCode == this.keyCode1) {
                if (this.selection == this.images.length - 1) {
                    this.selection = 0;
                } else {
                    this.selection++;
                }
            } else if (keyCode == this.keyCode2) {
                if (this.selection == 0) {
                    this.selection = this.images.length - 1;
                } else {
                    this.selection--;
                }
            }
        }
        
        /**
       * draws the graphics on the screen
       * @param g the graphics
       */
        public void paintComponent(Graphics g) { 
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            g.fillRect(0 , 0 , (int)(320 * scaleRatio) , (int)(475 * scaleRatio));
            g.drawImage(this.images[selection] , 0 , 0 , (int)(320 * scaleRatio) , (int)(475 * scaleRatio) , null); 
            repaint();
        }
        
        
        /**
         * gets the current selection
         * @return the current character selection
         */
        public String getSelection() {
            return imageString[selection];
        }        
    }
    
    //This is an inner class that is used to detect a button press    
    class StartButtonListener implements ActionListener {  //this is the required class definition
        public void actionPerformed(ActionEvent event)  {  
            System.out.println("Starting new Game");
            new GameFrame(charPanel1.getSelection() , charPanel2.getSelection()); 
            thisFrame.dispose();
            
        }
        
    }
    
    private class SelectionListener implements KeyListener {        
        public void keyTyped(KeyEvent e) {
        }
        
        public void keyReleased(KeyEvent e) {
        }
        
        /**
         * makes the players do actions when specific keys are pressed
         * @param e the key pressed
         */
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                new GameFrame(charPanel1.getSelection() , charPanel2.getSelection()); 
                thisFrame.dispose();
            }
            charPanel1.update(e.getKeyCode());
            charPanel2.update(e.getKeyCode());
        }
    }
    
    //Main method starts this application
    public static void main(String[] args) { 
        new CharSelect();
        
    }
    
}