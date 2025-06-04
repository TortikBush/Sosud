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
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

import static HelpClasses.CashedResource.CashedResource;
import static HelpClasses.CashedResource.cachedManuForest;
import static HelpClasses.CenterLocation.CenterLocationObject;

/**
 * Main application class that serves as the entry point for the application.
 * Sets up the main frame with buttons for game start, settings, achievements, etc.
 */
public class Main extends JFrame {
    /**
     * Main method that initializes the application
     *
     * @param args Command line arguments (not used)
     * @throws IOException            If there is an error loading resources
     * @throws SQLException           If there is an error connecting to the database
     * @throws ClassNotFoundException If the JDBC driver class is not found
     */
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        DesktopWidget.Desktop();
        EnableSetting();
        MusicEnable("src/resource/SoundForSosud/music.piano_bg.wav");
        CashedResource();
        new Main();
    }

    /**
     * Cached MP3 data for sound effects
     */
    public static byte[] mp3Data;

    /**
     * Constructor that initializes the main frame with all UI components
     *
     * @throws IOException            If there is an error loading resources
     * @throws SQLException           If there is an error connecting to the database
     * @throws ClassNotFoundException If the JDBC driver class is not found
     */
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
        JLabel label = new JLabel(cachedManuForest);

        //Кнопка Играть
        JButton btnStartGame = transformButton(850, 150, "src/resource/ButtonStartGame.png", null);

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
                try {
                    new NovelFrame("src/story.json", "start");
                    frameMain.dispose();
                } catch (IOException | FontFormatException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        //Кнопка Сохранение
        JButton btnSave = transformButton(500, 50, "src/resource/ButtonSave.png", new Insets(10, 250, 0, 0));

        //обработчик кнопки сохранение
        btnSave.addActionListener(e -> {

            if (Users.GetUserName() == null) {
                //
                try {
                    new Registration();
                    frameMain.dispose();
                } catch (IOException | ClassNotFoundException | SQLException | FontFormatException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                try {
                    new Saves();
                } catch (IOException | FontFormatException ex) {
                    throw new RuntimeException(ex);
                }
                frameMain.dispose();
            }

        });

        //Кнопка Настройки
        JButton btnSettings = transformButton(90, 70, "src/resource/ButtonSetting.png", null);

        //обработчик кнопки Настройки
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
        JButton btnExit = transformButton(300, 100, "src/resource/ButtonExit.png", new Insets(10, 350, 0, 0));
        btnExit.addActionListener(e -> {
            System.exit(0);
        });
        //Кнопка Достижения
        JButton btnAchievement = transformButton(90, 70, "src/resource/Achievement.png", null);

        //обработчик кнопки Ачивки
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

        //Работа с панелью (Работа с гвоздями и картиной)
        panelMainButton.setSize(900, 450);
        panelMainButton.setOpaque(false);
        panelMainButton.setLayout(new GridLayout(0, 1, 2, 4));
        panelMainButton.add(btnStartGame);
        panelMainButton.add(btnSave);
        panelMainButton.add(btnExit);
        panelMainButton.setLocation(800, 600);

        //Работа с фреймом (Работа со стеной)
        frameMain.add(panelMainButton);
        int[] topLocation = CenterLocationObject(frameMain);
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

    /**
     * Applies common properties to a button to give it a consistent appearance
     *
     * @param button The button to modify
     * @return The modified button
     */
    public static JButton parButton(JButton button) {
        button.setBackground(Color.black);
        button.setOpaque(false);
        button.setFocusable(false);
        button.setBorderPainted(false);
        return button;
    }

    /**
     * Transforms a button by setting its size, loading an image, scaling it, and applying common properties
     *
     * @param width     Button width
     * @param height    Button height
     * @param imagePath Path to the button image
     * @param margin    Optional margin to set (can be null)
     * @return Configured JButton
     * @throws IOException If image loading fails
     */
    public static JButton transformButton(int width, int height, String imagePath, Insets margin) throws IOException {
        JButton button = new JButton();
        button.setSize(width, height);

        if (margin != null) {
            button.setMargin(margin);
        }

        File imgFile = new File(imagePath).getAbsoluteFile();
        Image imgRead = ImageIO.read(imgFile);
        Image scaledImg = imgRead.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(scaledImg));

        return parButton(button);
    }

    /**
     * Enables or disables music based on user settings
     *
     * @param url The URL of the music file to play
     */
    public static void MusicEnable(String url) {
        if (Setting.GetCheckboxMusicEnable()) {
            MusicOnMenu.playMusic(url);
        } else {
            MusicOnMenu.stopMusic();
        }
    }

    /**
     * Loads user settings from the database and applies them
     *
     * @throws SQLException           If there is an error connecting to the database
     * @throws ClassNotFoundException If the JDBC driver class is not found
     */
    public static void EnableSetting() throws SQLException, ClassNotFoundException {
        if (Users.GetIdUser() == 0) {
            Setting.setCheckboxSoundEnable(true);
            Setting.setCheckboxMusicEnable(true);
            return;
        }

        Connection connection = JDBCPostgreSQLConnection.OpenConnection();
        String sql1 = "Select user_setting.isactive, settings.name from user_setting " +
                        "join settings on settings.id = user_setting.idsetting where user_setting.iduser = ?;";
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
