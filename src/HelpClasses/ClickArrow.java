package HelpClasses;

import Frame.Main;
import javazoom.jl.player.Player;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;

public class ClickArrow {
    public static void playCachedMP3() {
        new Thread(() -> {
            try {
                ByteArrayInputStream bais = new ByteArrayInputStream(Main.mp3Data);
                BufferedInputStream bis = new BufferedInputStream(bais);
                Player player = new Player(bis);
                player.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}