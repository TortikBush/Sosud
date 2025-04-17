package Frame;

import DBSourse.JDBCPostgreSQLConnection;
import Frame.History.NovelFrame;
import HelpClasses.ClickArrow;
import HelpClasses.DesktopWidget;
import HelpClasses.MusicOnMenu;
import HelpClasses.Users;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;


public class Main extends JFrame {
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        EnableSetting();
        MusicEnable("src/resource/SoundForSosud/music.piano_bg.wav");
        DesktopWidget.Desktop();
        new Main();
    }

    public static byte[] mp3Data;

    public Main() throws IOException, SQLException, ClassNotFoundException {
        EnableSetting();
        MusicEnable("src/resource/SoundForSosud/music.piano_bg.wav");
        File file = new File("src/resource/SoundForSosud/Clac.mp3");
        FileInputStream fis = new FileInputStream(file);
        mp3Data = fis.readAllBytes(); // Кешируем в массив байтов
        fis.close();

        JFrame frameMain = new JFrame();
        JPanel panelMainButton = new JPanel();

        //Главная картика
        ImageIcon icon = new ImageIcon(new File("src/resource/MenuForest.png").getAbsolutePath());

        //картинка настройки
        Image scaledImage = icon.getImage().getScaledInstance(1920, 1080, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel label = new JLabel(scaledIcon);

        //Кнопка Играть
        JButton btnStartGame = new JButton();
        btnStartGame.setSize(850, 150);
        File imgStartGame = new File("src/resource/ButtonStartGame.png").getAbsoluteFile();
        Image imgReadStartGame = ImageIO.read(imgStartGame);
        Image newImg = imgReadStartGame.getScaledInstance((int) btnStartGame.getSize().getWidth(), (int) btnStartGame.getSize().getHeight(), Image.SCALE_SMOOTH);
        btnStartGame.setIcon(new ImageIcon(newImg));
        parButton(btnStartGame);

        //обработчик кнопки старта
        btnStartGame.addActionListener(e -> {
            if (Users.GetUserName() == null) {
                //
                try {
                    new Registration();
                    frameMain.dispose();
                } catch (IOException | ClassNotFoundException | SQLException | FontFormatException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                frameMain.dispose();
                try {
                    new NovelFrame("src/story.json");
                } catch (IOException | FontFormatException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        //Кнопка Сохранение
        JButton btnSave = new JButton();
        btnSave.setSize(500, 50);
        btnSave.setMargin(new Insets(10, 250, 0, 0));
        File imgSave = new File("src/resource/ButtonSave.png").getAbsoluteFile();
        Image imgReadSave = ImageIO.read(imgSave);
        Image newImg2 = imgReadSave.getScaledInstance((int) btnSave.getSize().getWidth(), (int) btnSave.getSize().getHeight(), Image.SCALE_SMOOTH);
        btnSave.setIcon(new ImageIcon(newImg2));
        parButton(btnSave);

        //обработчик кнопки сохранение
        btnSave.addActionListener(e -> {
            System.exit(0);
        });

        //Кнопка Настройки
        JButton btnSettings = new JButton();
        btnSettings.setSize(90, 70);

        File imgSetting = new File("src/resource/ButtonSetting.png").getAbsoluteFile();
        Image imgReadSetting = ImageIO.read(imgSetting);
        Image newImg4 = imgReadSetting.getScaledInstance((int) btnSettings.getSize().getWidth(), (int) btnSettings.getSize().getHeight(), Image.SCALE_SMOOTH);
        btnSettings.setIcon(new ImageIcon(newImg4));
        parButton(btnSettings);

        //обработчик кнопки старта
        btnSettings.addActionListener(e ->
        {
            try {
                new Setting();
                frameMain.dispose();
            } catch (IOException | FontFormatException | ClassNotFoundException | SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        //Кнопка Выход
        JButton btnExit = new JButton();
        btnExit.setSize(300, 100);
        btnExit.setMargin(new Insets(10, 350, 0, 0));
        File imgExit = new File("src/resource/ButtonExit.png").getAbsoluteFile();
        Image imgReadExit = ImageIO.read(imgExit);
        Image newImg5 = imgReadExit.getScaledInstance((int) btnExit.getSize().getWidth(), (int) btnExit.getSize().getHeight(), Image.SCALE_SMOOTH);
        btnExit.setIcon(new ImageIcon(newImg5));

        parButton(btnExit);
        btnExit.addActionListener(e ->{
            System.exit(0);
        });
        //Кнопка Достижения
        JButton btnAchievement = new JButton();
        btnAchievement.setSize(90, 70);

        File imgAchievement = new File("src/resource/Achievement.png").getAbsoluteFile();
        Image imgReadAchievement = ImageIO.read(imgAchievement);
        Image newImg3 = imgReadAchievement.getScaledInstance((int) btnAchievement.getSize().getWidth(), (int) btnAchievement.getSize().getHeight(), Image.SCALE_SMOOTH);
        btnAchievement.setIcon(new ImageIcon(newImg3));
        parButton(btnAchievement);

        //обработчик кнопки старта
        btnAchievement.addActionListener(e -> {
            ClickArrow.playCachedMP3();
            try {
                new Achievement();
                Thread.sleep(350);
                frameMain.dispose();
            } catch (SQLException | ClassNotFoundException | IOException | FontFormatException |
                     InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });

        //Работа с панелью(Работа с гвоздями и картиной)
        panelMainButton.setSize(900, 450);
        panelMainButton.setOpaque(false);
        panelMainButton.setLayout(new GridLayout(0, 1, 2, 4));
        panelMainButton.add(btnStartGame);
        panelMainButton.add(btnSave);
        panelMainButton.add(btnExit);
        panelMainButton.setLocation(800, 600);

        //Работа с фреймом(Работа со стеной)
        frameMain.add(panelMainButton);
        int[] topLocation = Registration.CenterLocationObject(frameMain);
        if (Users.GetUserName() != null) {
            frameMain.add(btnAchievement).setLocation(topLocation[0] + 750, 150);
            frameMain.add(btnSettings).setLocation(topLocation[0] + 650, 150);
        }

        frameMain.add(label);
        frameMain.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frameMain.setUndecorated(true);
        frameMain.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frameMain.setVisible(true);
        DesktopWidget.Close();
    }

    static ImageIcon cachedMainBackground;

    static {
        try {
            BufferedImage img = ImageIO.read(new File("src/resource/MainBackground.png"));
            Image scaled = img.getScaledInstance(1920, 1080, Image.SCALE_FAST); // SCALE_FAST быстрее
            cachedMainBackground = new ImageIcon(scaled);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static ImageIcon cachedRegistrationFon;

    static {
        try {
            BufferedImage img = ImageIO.read(new File("src/resource/RegistrationFon.png"));
            Image scaled = img.getScaledInstance(1920, 1080, Image.SCALE_FAST); // SCALE_FAST быстрее
            cachedRegistrationFon = new ImageIcon(scaled);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static ImageIcon cachedSettingFon;

    static {
        try {
            BufferedImage img = ImageIO.read(new File("src/resource/SettingFon.png"));
            Image scaled = img.getScaledInstance(1920, 1090, Image.SCALE_FAST);
            cachedSettingFon = new ImageIcon(scaled);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JButton parButton(JButton button) {
        button.setBackground(Color.black);
        button.setOpaque(false);
        button.setFocusable(false);
        button.setBorderPainted(false);
        return button;
    }

    public static void MusicEnable(String url) {
        if (Setting.GetCheckboxMusicEnable()) {
            MusicOnMenu.playMusic(url);
        } else {
            MusicOnMenu.stopMusic();
        }
    }

    public static void EnableSetting() throws SQLException, ClassNotFoundException {
        if (Users.GetIdUser()==0){
            Setting.setCheckboxSoundEnable(true);
            Setting.setCheckboxMusicEnable(true);
            return;
        }

        Connection connection = JDBCPostgreSQLConnection.OpenConnection();
        String sql1 = "Select user_setting.isactive, settings.name from user_setting join settings on settings.id = user_setting.idsetting where user_setting.iduser = ?;";
        PreparedStatement stmt = connection.prepareStatement(sql1);
        stmt.setInt(1, Users.GetIdUser());
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            switch (rs.getString("name")) {
                case "Звук":
                    Setting.setCheckboxSoundEnable(rs.getBoolean("isactive"));
                    break;
                case "Музыка":
                    Setting.setCheckboxMusicEnable(rs.getBoolean("isactive"));
                    break;
            }
        }
    }
}
