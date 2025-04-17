package HelpClasses;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MusickOnMenu {
    private static Clip clip;

    public static void playMusic(String filepath) {
        try {
            if (clip != null && clip.isRunning()) {
                clip.stop();
            }

            File audioFile = new File(filepath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void stopMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }
}