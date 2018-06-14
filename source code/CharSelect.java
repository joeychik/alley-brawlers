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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


class CharSelect extends JFrame{ 
    
    JFrame thisFrame;
    private int screenHeight;
    private int screenWidth;
    static double scaleRatio;
    JLabel character;
    private String[] charList;
    CharPanel charPanel1;
    CharPanel charPanel2;
    
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
    
    
    //Constructor - this runs first
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
        
        charPanel1 = new CharPanel(this.charList);
        charPanel1.setBackground(new Color(0, 0, 0, 0));
        
        charPanel2 = new CharPanel(this.charList);
        charPanel2.setBackground(new Color(0, 0, 0, 0));
        
        decPanel.setLayout(null);
        decPanel.add(startButton);
        decPanel.add(charPanel1);
        decPanel.add(charPanel2);
        this.add(decPanel);
        this.addKeyListener(new SelectionListener(charPanel1 , charPanel2));
        
        startButton.setBounds((int) (960 * scaleRatio - sbWidth / 2) , (int) (950 * scaleRatio) , sbWidth , sbHeight);
        charPanel1.setBounds((int) (scaleRatio * 150) , (int) (scaleRatio * 270) , (int) (scaleRatio * 320) , (int) (scaleRatio * 485));
        charPanel2.setBounds((int) (scaleRatio * 1450) , (int) (scaleRatio * 270) , (int) (scaleRatio * 320) , (int) (scaleRatio * 485));
        
        //Start the app
        this.setVisible(true);
    }
    
    //INNER CLASS - Overide Paint Component for JPANEL    
    private class DecoratedPanel extends JPanel {
        }
        
        
    }
    
    private class CharPanel extends JPanel{
        private String[] imageString;
        private Image[] images;
        private int selection;
        private CharPanel charPanel;
        
        CharPanel(String[] imageString) {
            super();
            this.selection = 2;
            this.imageString = imageString;
            this.images = new Image[imageString.length];
            for (int i = 0; i < imageString.length; i++) {
                images[i] = new ImageIcon("resources/charSelect/" + imageString[i] + ".png").getImage();
            }
        }
        
        public void update(int keyCode) {
            if (keyCode == KeyEvent.VK_ESCAPE || keyCode == KeyEvent.VK_BACK_SPACE) {
                thisFrame.dispose();
            } else if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) {
                if (this.selection == this.images.length - 1) {
                    this.selection = 0;
                } else {
                    this.selection++;
                }
            } else if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) {
                if (this.selection == 0) {
                    this.selection = this.images.length - 1;
                } else {
                    this.selection--;
                }
            }
        }
        
        public void paintComponent(Graphics g) { 
            super.paintComponent(g);
            g.drawImage(this.images[selection] , 0 , 0 , (int)(320 * scaleRatio) , (int)(475 * scaleRatio) , null); 
        }
        
        public String getSelection() {
            return imageString[selection];
        }        
    }
    
    //This is an inner class that is used to detect a button press    
    class StartButtonListener implements ActionListener {  //this is the required class definition
        public void actionPerformed(ActionEvent event)  {  
            System.out.println("Starting new Game");
            //thisFrame.dispose();
            new GameFrame(); 
            thisFrame.dispose();
            
        }
        
    }
    
    private class SelectionListener implements KeyListener {
        private CharPanel charPanel1;
        private CharPanel charPanel2;
        
        SelectionListener(CharPanel first , CharPanel second) {
            this.charPanel1 = first;
            this.charPanel2 = second;
        }
        
        public void keyTyped(KeyEvent e) {
        }
        
        public void keyReleased(KeyEvent e) {
        }
        
        public void keyPressed(KeyEvent e) {
            charPanel1.update(e.getKeyCode());
            charPanel2.update(e.getKeyCode());
        }
    }
    
    //Main method starts this application
    public static void main(String[] args) { 
        new CharSelect();
        
    }
    
}