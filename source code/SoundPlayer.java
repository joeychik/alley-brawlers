/**
 * SoundPlayer.java
 * plays sound
 * @author Eric Ke
 * 6/13/2018
 */

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.sound.sampled.*;
import java.io.File;
import javax.sound.sampled.*;

class SoundPlayer {
  
  SoundPlayer() {
    
  }
  
 public void playSound(String filename) {
     try {
      File audioFile = new File("resources/sound/" + filename);
      AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
      DataLine.Info infoThing = new DataLine.Info(Clip.class, audioStream.getFormat());
      Clip soundClip = (Clip) AudioSystem.getLine(infoThing);
      soundClip.addLineListener(new SoundListener());
      soundClip.open(audioStream);
      soundClip.start();
      
 
    }catch (Exception e) {
      e.printStackTrace();
       }
  } 
 class SoundListener implements LineListener {
    public void update(LineEvent event) {
      if (event.getType() == LineEvent.Type.STOP) {
        event.getLine().close(); 
        
      }
    
 
}
 }
}