/** 
 * Menu.java
 * class for menu screen
 * @author Eric Ke, Joey Chik
 **/

//imports

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Dimension;
import java.io.File;
import javax.sound.sampled.*;

class Menu extends JFrame { 
    
    private static int screenHeight;
    private static int screenWidth;
    private static int buttonHeight;
    private static int buttonWidth;
    private static double scaleRatio;
    private JFrame thisFrame;
    private Clip music;
    
    /**
     * creates the menu
     */
    Menu() { 
        super("Start Screen"); // name of window
        this.thisFrame = this;
        
        // play music
        playMusic("SuperSmashBrosMelee.wav");
        
        // get the size of the screen
        screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        scaleRatio = (double) screenHeight / 1080;
        
        // configure the window
        this.setSize(screenWidth , screenHeight);
        this.setResizable(false);
        this.setLocationRelativeTo(null); //start the frame in the center of the screen
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // create a panel for stuff
        DecoratedPanel mainPanel = new DecoratedPanel("resources/menu/menu.png");
        mainPanel.setLayout(null);
        
        MenuButton[] menuButtons = new MenuButton[3];
        
        menuButtons[0] = new StartButton("resources/menu/start_button.png");
        menuButtons[1] = new InstructionButton("resources/menu/instruction_button.png");
        menuButtons[2] = new ExitButton("resources/menu/exit_button.png");
        
        //add the main panel to the frame
        this.add(mainPanel);
        
        for (int i = 0; i < 3; i++) {
            mainPanel.add(menuButtons[i]);
        }
        
        buttonWidth = menuButtons[0].getWidth();
        buttonHeight = menuButtons[0].getHeight();
        
        for (int i = 0; i < 3; i++) {
            menuButtons[i].setBounds((int) (960 * scaleRatio - buttonWidth / 2) , (int) (540 * scaleRatio + buttonHeight * i) , buttonWidth , buttonHeight);
        }
        
        //Start the app
        this.setVisible(true);
        
    }
    
      /**
   * plays the music for the battle
   * @param filename the name of the file
   */
  public void playMusic(String filename) {
    music = null;
     try {
      File audioFile = new File("resources/sound/" + filename);
      AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
      DataLine.Info infoThing = new DataLine.Info(Clip.class, audioStream.getFormat());
      music = (Clip) AudioSystem.getLine(infoThing);
      music.addLineListener(new MusicListener());
      music.open(audioStream);
      music.start();
 
    }catch (Exception e) {
      e.printStackTrace();
       }
  }
  

  class MusicListener implements LineListener {
  /**
   * closes the music and restarts it when it finishes
   * @param event the music event
   */
    public void update(LineEvent event) {
      if (event.getType() == LineEvent.Type.STOP) {
        event.getLine().close();
        if (music != null) {
          playMusic("FightForQuiescence.wav");
        }
      }
    }
  } 
    
    private class DecoratedPanel extends JPanel {
        private String picAddress;
        private Image pic;
        
        /**
         * Creates a panel
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
         * gets the image
         * @return the image
         */
        public Image getImage() {
            return this.pic;
        }
    }
    
    private class MenuButton extends DecoratedPanel implements MouseListener {  
        private int height;
        private int width;
        
        /**
         * creates a menu button
         * @param picAddress the name of the picture
         */
        MenuButton(String picAddress) {
            super(picAddress);
            addMouseListener(this);
            height = (int) (scaleRatio * this.getImage().getHeight(null));
            width = (int) (scaleRatio * this.getImage().getWidth(null));
            this.setOpaque(false);
        }
        
        /**
         * draws the graphics on the screen
         * @param g the graphics
         */
        public void paintComponent(Graphics g) { 
            super.paintComponent(g);
            g.drawImage(this.getImage() , 0 , 0 , this.width , this.height , null); 
        }
        
        public void mousePressed(MouseEvent e) {
        }
        
        public void mouseReleased(MouseEvent e) {
        }
        
        public void mouseEntered(MouseEvent e) {
        }
        
        public void mouseExited(MouseEvent e) {
        }
        
        public void mouseClicked(MouseEvent e) {
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
            thisFrame.dispose();
            new CharSelect();
        }
    }
    
    private class InstructionButton extends MenuButton {  
        InstructionButton(String picAddress) {
            super(picAddress);
        }
        
        public void mouseClicked(MouseEvent e) {
            new InstructionScreen();
        }
    }
    
    private class ExitButton extends MenuButton {  
        ExitButton(String picAddress) {
            super(picAddress);
        }
        
        public void mouseClicked(MouseEvent e) {
            System.exit(0);
        }
    }
    
    //Main method starts this application
    public static void main(String[] args) { 
        new Menu();
        
    }
}