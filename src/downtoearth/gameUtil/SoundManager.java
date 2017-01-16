/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.gameUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author Tomt
 */
public class SoundManager {
    
    private InputStream in;
    private AudioStream audioStream;

    public SoundManager() {
    }
    
    public void playSound(final String path)
    {
        new Thread()
        {
            public void run (){
                try {
                in = new FileInputStream("C:\\Users\\Tomt\\Documents\\J2S1\\Proftaak\\Sfx\\Edit\\"+path);
                audioStream = new AudioStream(in);
                }
                catch (IOException ex)
                {
                    Logger.getLogger(SoundManager.class.getName()).log(Level.SEVERE, null, ex);
                }
                    AudioPlayer.player.start (audioStream);
            }
        }.start();
    }  
}
