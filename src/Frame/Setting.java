package Frame;

import DBSourse.CustomFont;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import static Frame.Main.cachedSettingFon;

public class Setting {
    public Setting() throws IOException, SQLException, ClassNotFoundException, FontFormatException {

        ImageIcon uncheckedImg = new ImageIcon(new File("src/resource/CheckBoxRes/unchecked.png").getAbsolutePath());
        Image scaledImage = uncheckedImg.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon unchecked = new ImageIcon(scaledImage);

        ImageIcon checkedImg = new ImageIcon(new File("src/resource/CheckBoxRes/checked.png").getAbsolutePath());
        Image scaledImage1 = checkedImg.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon checked = new ImageIcon(scaledImage1);

        Font customFont1 = Font.createFont(Font.TRUETYPE_FONT, new File("src/resource/fonts/font1.ttf"))
                .deriveFont(Font.PLAIN, 70f); // размер и стиль
        GraphicsEnvironment ge1 = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge1.registerFont(customFont1);

         // Регистрация в системе Java



        // Создание фрейма
        JFrame frameSetting = new JFrame();
        frameSetting.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameSetting.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel panelBlockSettings = new JPanel();
        panelBlockSettings.setLayout(new BoxLayout(panelBlockSettings, BoxLayout.Y_AXIS));
        panelBlockSettings.setSize(350,230);
        int[] centerLocation;
        centerLocation = Registration.CenterLocationObject(frameSetting);
        panelBlockSettings.setLocation(centerLocation[0]-150, centerLocation[1]-10);
        panelBlockSettings.setBackground(Color.blue);
        panelBlockSettings.setOpaque(false);


        JCheckBox checkboxMusic = new JCheckBox("Музыка");
        checkboxMusic.setFont(CustomFont.CustomFont2().deriveFont(70f));
        checkboxMusic.setPreferredSize(new Dimension(500, 130));
        checkboxMusic.setSize(500,130);
        checkboxMusic.setBackground(Color.black);
        checkboxMusic.setIcon(unchecked);
        checkboxMusic.setSelectedIcon(checked);
        checkboxMusic.setForeground(new Color(254, 222, 143));
        checkboxMusic.setFocusable(false);
        checkboxMusic.setBorderPainted(false);
        checkboxMusic.setFocusable(false);
        checkboxMusic.setOpaque(false);


        JCheckBox checkboxSound = new JCheckBox("Звуки");
        checkboxSound.setFont(CustomFont.CustomFont2().deriveFont(70f));
        checkboxSound.setPreferredSize(new Dimension(500, 130));
        checkboxSound.setSize(500,130);
        checkboxSound.setIcon(unchecked);
        checkboxSound.setSelectedIcon(checked);
        checkboxSound.setForeground(new Color(254, 222, 143));
        checkboxSound.setFocusable(false);
        checkboxSound.setBorderPainted(false);
        checkboxSound.setFocusable(false);
        checkboxSound.setOpaque(false);



        panelBlockSettings.add(checkboxMusic);
        panelBlockSettings.add(checkboxSound);

        JLabel label = new JLabel(cachedSettingFon);
        frameSetting.add(panelBlockSettings);
        frameSetting.add(label);
        frameSetting.setUndecorated(true);
        frameSetting.setVisible(true);

    }
}
