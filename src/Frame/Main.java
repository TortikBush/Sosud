package Frame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;


public class Main {
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        MainFrain();
    }

    public static void MainFrain() throws IOException, SQLException, ClassNotFoundException {
        JFrame frameMain = new JFrame();
        JPanel panelMainButton = new JPanel();

        //Главная картика
        ImageIcon icon = new ImageIcon(new File("src/resource/Начало-01.png").getAbsolutePath());

        //картинка настройки
        Image scaledImage = icon.getImage().getScaledInstance(1920, 1080, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel label = new JLabel(scaledIcon);

        //Кнопка Играть
        JButton btnStartGame = new JButton();
        btnStartGame.setSize(650, 100);
        File imgStartGame = new File("src/resource/StartGame1.png").getAbsoluteFile();
        Image imgReadStartGame = ImageIO.read(imgStartGame);
        Image newImg = imgReadStartGame.getScaledInstance((int) btnStartGame.getSize().getWidth(), (int) btnStartGame.getSize().getHeight(), Image.SCALE_SMOOTH);
        btnStartGame.setIcon(new ImageIcon(newImg));
        parButton(btnStartGame);

        //обработчик кнопки старта
        btnStartGame.addActionListener(e -> {
            Users user = new Users();
            if (user.getUserName() == null) {
                //
                try {
                    Registration.RegistrationFraim();
                } catch (IOException | ClassNotFoundException | SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    Thread.sleep(600);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }

                frameMain.dispose();
            } else {
                //Начало игры
            }


        });

        //Кнопка Сохранение
        JButton btnSave = new JButton();
        btnSave.setSize(200, 50);

        File imgSave = new File("src/resource/Save.png").getAbsoluteFile();
        Image imgReadSave = ImageIO.read(imgSave);
        Image newImg2 = imgReadSave.getScaledInstance((int) btnStartGame.getSize().getWidth(), (int) btnStartGame.getSize().getHeight(), Image.SCALE_SMOOTH);
        btnSave.setIcon(new ImageIcon(newImg2));
        parButton(btnSave);

        //обработчик кнопки старта
        btnSave.addActionListener(e -> {
            try {
                Achievement.Achievement();
            } catch (SQLException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            try {
                Thread.sleep(600);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            frameMain.dispose();

    });

    //Кнопка Настройки
    JButton btnSettings = new JButton("Настройки");
        btnSettings.setSize(100,50);

    parButton(btnSettings);

    //обработчик кнопки старта
        btnSettings.addActionListener(e ->

    {
        System.exit(0);
    });

    //Кнопка Выход
    JButton btnExit = new JButton("Выход");
        btnExit.setSize(100,50);

    parButton(btnExit);
        btnExit.addActionListener(e ->

    {
        System.exit(0);
    });

    //Работа с панелью(Работа с гвоздями и картиной)
        panelMainButton.setSize(664,400);
        panelMainButton.setOpaque(false);
        panelMainButton.setLayout(new

    GridLayout(0,1,2,4));
        panelMainButton.add(btnStartGame);
        panelMainButton.add(btnSave);
        panelMainButton.add(btnSettings);
        panelMainButton.add(btnExit);
        panelMainButton.setLocation(1000,600);

    //Работа с фреймом(Работа со стеной)
        frameMain.add(panelMainButton);
        frameMain.add(label);
        frameMain.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frameMain.setExtendedState(JFrame.MAXIMIZED_BOTH); // Максимизируем окно
        frameMain.setUndecorated(true);// Убираем границы и заголовок окна

        frameMain.show();
}

public static JButton parButton(JButton button) {
    button.setBackground(Color.black);
    button.setOpaque(false);
    button.setFocusable(false);
    button.setBorderPainted(false);
    return button;
}
}

