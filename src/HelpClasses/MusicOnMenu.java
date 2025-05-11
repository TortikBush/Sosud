package HelpClasses;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * Utility class for playing and stopping background music.
 * Uses the Java Sound API to play audio files.
 */
public class MusicOnMenu {
    /** The audio clip that is currently playing */
    private static Clip clip;

    /**
     * Plays music from the specified file path
     * If music is already playing, it stops the current music before playing the new one
     * The music will loop continuously until stopped
     * 
     * @param filepath Path to the audio file to play
     */
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

    /**
     * Stops the currently playing music
     * If no music is playing, this method does nothing
     */
    public static void stopMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }
}
