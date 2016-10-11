/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineEvent.Type;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import sun.applet.Main;

/**
 *
 * @author Sanko
 */
public final class soundLogic {
    public static List<String> paths = new ArrayList<>();
    
    public soundLogic()
    {
        //paths = new ArrayList<>();
    }

    public static void playClip(File clipFile) throws IOException,
        UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
        class AudioListener implements LineListener {

            private boolean done = false;

            @Override
            public synchronized void update(LineEvent event) {
                Type eventType = event.getType();
                if (eventType == Type.STOP || eventType == Type.CLOSE) {
                    done = true;
                    notifyAll();
                }
            }

            public synchronized void waitUntilDone() throws InterruptedException {
                while (!done) {
                    wait();
                }
            }
        }
        AudioListener listener = new AudioListener();
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(clipFile);
        try {
            Clip clip = AudioSystem.getClip();
            clip.addLineListener(listener);
            clip.open(audioInputStream);
            try {
                clip.start();
                listener.waitUntilDone();
            } finally {
                clip.close();
            }
        } finally {
            audioInputStream.close();
        }
    }
    
    public static void readFile() throws FileNotFoundException, IOException
    {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Tomt\\Documents\\GitHub\\DownToEarth\\src\\downtoearth\\Sounds\\SoundPaths.txt"))) {
            String line;
            while ((line = br.readLine()) !=null)
            {
                paths.add(line);              
            }
            br.close();
        }
    }
}
