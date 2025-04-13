package Frame;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import static Frame.Main.cachedMainBackground;

public class Setting {
    public static void SettingFrame() throws IOException, SQLException, ClassNotFoundException {
        // Создание фрейма
        JFrame frameAchievement = new JFrame();
        frameAchievement.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameAchievement.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Создание и установка метки с изображением
        JLabel label = new JLabel(cachedMainBackground);
        label.setBounds(0, 0, 1920, 1080);
        frameAchievement.getContentPane().setLayout(null);
        frameAchievement.getContentPane().add(label);
        frameAchievement.setUndecorated(true);
        frameAchievement.setVisible(true); // Используй setVisible вместо show()
    }
}
