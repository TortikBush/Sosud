package HelpClasses;

import Frame.Main;
import Frame.Setting;
import javazoom.jl.player.Player;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;

public class ClickArrow {
    public static void playCachedMP3() {
        new Thread(() -> {
            try {
                if (Setting.GetCheckboxSoundEnable()){
                    ByteArrayInputStream bais = new ByteArrayInputStream(Main.mp3Data);
                    BufferedInputStream bis = new BufferedInputStream(bais);
                    Player player = new Player(bis);
                    player.play();
                }else {
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}