import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.html.ImageView;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;


public class Main {
    public static void main(String[] args) throws IOException {
        JFrame frameMain = new JFrame();
        JPanel panelMainButton = new JPanel();


        //Главная картика
        ImageIcon originalIcon = new ImageIcon("C:\\Sosud\\forestMenu.jpg");
        Image scaledImage = originalIcon.getImage().getScaledInstance(1920, 1080, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel label = new JLabel(scaledIcon);
        //картинка настройки


        //Кнопка Играть
        JButton btnStartGame = new JButton();
        btnStartGame.setSize(650, 100);
        File imgStartGame = new File("C:\\Sosud\\StartGame1.png");
        Image imgReadStartGame = ImageIO.read(imgStartGame);
        Image newImg = imgReadStartGame.getScaledInstance((int) btnStartGame.getSize().getWidth(), (int) btnStartGame.getSize().getHeight(), java.awt.Image.SCALE_SMOOTH);
        btnStartGame.setIcon(new ImageIcon(newImg));
        parButton(btnStartGame);
        //обработчик кнопки старта
        btnStartGame.addActionListener(e -> {
            frame2.WorkFrame();
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            frameMain.dispose();

        });


        //Кнопка Сохранение
        JButton btnSave = new JButton();
        btnSave.setSize(200, 50);

        File imgSave = new File("C:\\Sosud\\Save.png");
        Image imgReadSave = ImageIO.read(imgSave);
        Image newImg2 = imgReadSave.getScaledInstance((int) btnStartGame.getSize().getWidth(), (int) btnStartGame.getSize().getHeight(), java.awt.Image.SCALE_SMOOTH);
        btnSave.setIcon(new ImageIcon(newImg2));
        parButton(btnSave);
        //обработчик кнопки старта
        btnSave.addActionListener(e -> {
            System.exit(0);
        });

        //Кнопка Настройки
        JButton btnSettings = new JButton("Настройки");
        btnSettings.setSize(100, 50);
        parButton(btnSettings);
        //обработчик кнопки старта
        btnSettings.addActionListener(e -> {
            System.exit(0);
        });

        //Кнопка Выход
        JButton btnExit = new JButton("Выход");
        btnExit.setSize(100, 50);
        parButton(btnExit);
        btnExit.addActionListener(e -> {
            System.exit(0);
        });

        //Работа с панелью(Работа с гвоздями и картиной)
        panelMainButton.setSize(664, 400);
        panelMainButton.setOpaque(false);
        panelMainButton.setLayout(new GridLayout(0, 1, 2, 4));
        panelMainButton.add(btnStartGame);
        panelMainButton.add(btnSave);
        panelMainButton.add(btnSettings);
        panelMainButton.add(btnExit);
        panelMainButton.setLocation(1000, 600);


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

