package Frame;

import HelpClasses.CustomFont;
import DBSourse.JDBCPostgreSQLConnection;
import HelpClasses.Users;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static Frame.Main.cachedSettingFon;

public class Setting {
    private static final JCheckBox checkboxMusic = new JCheckBox();
    private static final JCheckBox checkboxSound = new JCheckBox();

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

        // Создание фрейма
        JFrame frameSetting = new JFrame();
        frameSetting.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameSetting.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel panelBlockSettings = new JPanel();
        panelBlockSettings.setLayout(new BoxLayout(panelBlockSettings, BoxLayout.Y_AXIS));
        panelBlockSettings.setSize(350, 350);
        int[] centerLocation;
        centerLocation = Registration.CenterLocationObject(frameSetting);
        panelBlockSettings.setLocation(centerLocation[0] - 150, centerLocation[1] - 50);
        panelBlockSettings.setBackground(Color.blue);
        panelBlockSettings.setOpaque(false);

        JButton buttonClose = new JButton();
        File imgAchievement = new File("src/resource/CloseImage.png").getAbsoluteFile();
        Image imgReadAchievement = ImageIO.read(imgAchievement);
        buttonClose.setSize(60, 60);
        Image newImg3 = imgReadAchievement.getScaledInstance((int) buttonClose.getSize().getWidth(), (int) buttonClose.getSize().getHeight(), Image.SCALE_SMOOTH);
        buttonClose.setIcon(new ImageIcon(newImg3));
        Main.parButton(buttonClose);
        int[] centerLocation1 = Registration.CenterLocationObject(frameSetting);
        buttonClose.setLocation(centerLocation1[0] + 550, centerLocation1[1] - 180);
        buttonClose.addActionListener(e -> {
            try {
                new Main();
                frameSetting.dispose();
            } catch (IOException | SQLException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        checkboxMusic.setText("Музыка");
        checkboxMusic.setFont(CustomFont.CustomFont2().deriveFont(70f));
        checkboxMusic.setPreferredSize(new Dimension(500, 130));
        checkboxMusic.setSize(500, 180);
        checkboxMusic.setBackground(Color.black);
        checkboxMusic.setIcon(unchecked);
        checkboxMusic.setSelectedIcon(checked);
        checkboxMusic.setForeground(new Color(254, 222, 143));
        checkboxMusic.setFocusable(false);
        checkboxMusic.setBorderPainted(false);
        checkboxMusic.setFocusable(false);
        checkboxMusic.setOpaque(false);
//        checkboxMusic.setSelected(Users.GetMusicActive());

        checkboxSound.setText("Звуки");
        checkboxSound.setFont(CustomFont.CustomFont2().deriveFont(70f));
        checkboxSound.setPreferredSize(new Dimension(500, 130));
        checkboxSound.setSize(500, 130);
        checkboxSound.setIcon(unchecked);
        checkboxSound.setSelectedIcon(checked);
        checkboxSound.setForeground(new Color(254, 222, 143));
        checkboxSound.setFocusable(false);
        checkboxSound.setBorderPainted(false);
        checkboxSound.setFocusable(false);
        checkboxSound.setOpaque(false);
//        checkboxSound.setSelected(Users.GetSoundActive());

        JButton buttonSave = new JButton("Сохранить");
        buttonSave.setFont(CustomFont.CustomFont2().deriveFont(50f));
        buttonSave.setSize(500, 10);
        buttonSave.setForeground(new Color(254, 222, 143));
        buttonSave.setFocusable(false);
        buttonSave.setBorder(BorderFactory.createLineBorder(new Color(254, 222, 143), 3));
        Main.parButton(buttonSave);
        buttonSave.setBorderPainted(true);

        buttonSave.addActionListener(e -> {
            try {
                Connection connection = JDBCPostgreSQLConnection.OpenConnection();

                String sql1 = "UPDATE user_setting SET isactive = ? from settings WHERE user_setting.idsetting = 1 and user_setting.iduser = ?;";
                PreparedStatement stm1 = connection.prepareStatement(sql1);
                stm1.setBoolean(1, checkboxSound.isSelected());
                stm1.setInt(2, Users.GetIdUser());

                String sql2 = "UPDATE user_setting SET isactive = ? from settings WHERE user_setting.idsetting = 2 and user_setting.iduser = ?;";
                PreparedStatement stm2 = connection.prepareStatement(sql2);
                stm2.setBoolean(1, checkboxMusic.isSelected());
                stm2.setInt(2, Users.GetIdUser());

                stm1.executeUpdate();
                stm2.executeUpdate();

                Users.SoundActive = checkboxSound.isSelected();
                Users.MusicActive = checkboxMusic.isSelected();

                new Main();
                frameSetting.dispose();

            } catch (SQLException | ClassNotFoundException | IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        frameSetting.add(buttonClose);
        panelBlockSettings.add(checkboxMusic);
        panelBlockSettings.add(checkboxSound);
        panelBlockSettings.add(buttonSave);

        JLabel label = new JLabel(cachedSettingFon);
        frameSetting.add(panelBlockSettings);
        frameSetting.add(label);
        frameSetting.setUndecorated(true);
        frameSetting.setVisible(true);
    }



    public static boolean GetCheckboxSoundEnable() {
        return checkboxSound.isSelected();
    }

    public static void setCheckboxSoundEnable(boolean enable) {
        checkboxSound.setSelected(enable);
    }

    public static boolean GetCheckboxMusicEnable() {
        return checkboxMusic.isSelected();
    }

    public static void setCheckboxMusicEnable(boolean enable) {
        checkboxMusic.setSelected(enable);
    }
}
