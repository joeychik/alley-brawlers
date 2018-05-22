import javax.swing.*;
import javax.tools.Tool;
import java.awt.image.*; 
import javax.imageio.*; 

class Menu extends JPanel{
    private static Image logo;
    private static Image background;
    private static Image playButton;
    private static Image instructionButton;
    private static Image exitButton;
    
    Menu() {
        super();
        logo = Toolkit.getDefaultToolkit().getImage("resources/placeholder.jpg");
        background = Toolkit.getDefaultToolkit().getImage("resources/placeholder.jpg");
        playButton = Toolkit.getDefaultToolkit().getImage("resources/placeholder.jpg");
        instructionButton = Toolkit.getDefaultToolkit().getImage("resources/placeholder.jpg");
        exitButton = Toolkit.getDefaultToolkit().getImage("resources/placeholder.jpg");
    }
}