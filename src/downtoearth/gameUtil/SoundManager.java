/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.gameUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import sun.audio.AudioStream;

/**
 *
 * @author Tomt
 */
public class SoundManager {
    
    private InputStream in;
    private AudioStream audioStream;
    private boolean loop=true;
    private Clip clip;

    /**
     * 
     * @param path get string path
     */
    /*public void playSound(final String path){
        new Thread()
        {
            public void run (){
                try {
                in = new FileInputStream("C:\\Users\\Tomt\\Documents\\J2S1\\Proftaak\\Sfx\\Edit\\"+path);
                audioStream = new AudioStream(in);
                AudioPlayer.player.start (audioStream);
                }
                catch (IOException ex)
                {
                    Logger.getLogger(SoundManager.class.getName()).log(Level.SEVERE, null, ex);
                }
                finally
                {                
                    if(loop)
                    {
                        if(!AudioPlayer.player.isAlive())
                        {
                            playSound("swordMissA.wav");
                        }            
                    }
                    return;
                }
            }
        }.start();
    }*/
    
    public void playSound(final String path){
        new Thread()
        {
            public void run()
            {
                try {
                File file = new File("C:\\Users\\Tomt\\Documents\\J2S1\\Proftaak\\Sfx\\Edit\\"+path);
                if (file.exists()) {
                    AudioInputStream sound = AudioSystem.getAudioInputStream(file);
                    clip = AudioSystem.getClip();
                    clip.open(sound);
                    clip.start();
                }
        }
        catch (UnsupportedAudioFileException ex) {
             Logger.getLogger(SoundManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex) {
             Logger.getLogger(SoundManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (LineUnavailableException ex) {
             Logger.getLogger(SoundManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*try {
            File file = new File("C:\\Users\\Tomt\\Documents\\J2S1\\Proftaak\\Sfx\\Edit\\"+path);
            if (file.exists()) {
                AudioInputStream sound = AudioSystem.getAudioInputStream(file);
                clip = AudioSystem.getClip();
                clip.open(sound);
                clip.start();
            }
            else {
                throw new RuntimeException("Sound: file not found: " + path);
            }
        }
        catch (UnsupportedAudioFileException ex) {
             Logger.getLogger(SoundManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex) {
             Logger.getLogger(SoundManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (LineUnavailableException ex) {
             Logger.getLogger(SoundManager.class.getName()).log(Level.SEVERE, null, ex);
        }*/}
        }.start();
    }
}
